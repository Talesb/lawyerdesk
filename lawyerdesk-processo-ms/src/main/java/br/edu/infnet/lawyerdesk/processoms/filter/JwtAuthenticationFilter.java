package br.edu.infnet.lawyerdesk.processoms.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.infnet.lawyerdesk.processoms.security.JWTUser;
import br.edu.infnet.lawyerdesk.processoms.security.Role;
import br.edu.infnet.lawyerdesk.processoms.security.TokenUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenUtils jwtService;

	public JwtAuthenticationFilter(TokenUtils jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			String jwt = getJwtFromRequest(request);
			if (jwtService.validateToken(jwt)) {
				
				List<Role> roles = jwtService.getGroupsFrom(jwt)
						.stream()
						.map(grp -> new Role(grp))
						.collect(Collectors.toList());
					
 	                JWTUser userDetails = new JWTUser(jwtService.getUsernameFrom(jwt),true,roles);
 	                
 	                System.out.print(userDetails.getAuthorities());
 	                

	                UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, roles);
	 
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			System.out.println("Could not set user authentication in security context");
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
