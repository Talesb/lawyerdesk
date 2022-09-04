package br.edu.infnet.lawyerdesk.processoms.security;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	private final JwtKeyProvider jwtKeyProvider;

	public TokenUtils(JwtKeyProvider jwtKeyProvider) {
		this.jwtKeyProvider = jwtKeyProvider;
	}

	public String generateToken(String username) {
		int minuteDuration = 60;

		return Jwts.builder().setExpiration(Date.from(ZonedDateTime.now().plusMinutes(minuteDuration).toInstant()))
				.signWith(SignatureAlgorithm.RS256, jwtKeyProvider.getPrivateKey()).claim("username", username)
				.compact();
	}
	
	public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtKeyProvider.getPublicKey()).parseClaimsJws(jwt);
            return true;
        } catch(JwtException e) {
//            log.warn("Invalid JWT!", e);
        	System.out.println("Invalid JWT");
        }
        return false;
    }
 
    public String getUsernameFrom(String jwt) {
        return (String) getClaims(jwt).get("username");
    }
    
    
    public List<String> getGroupsFrom(String jwt) {
        return (List<String>) getClaims(jwt).get("groups");
    }
    
 
    private Claims getClaims(String jwt) {
        return Jwts.parser()
            .setSigningKey(jwtKeyProvider.getPublicKey())
            .parseClaimsJws(jwt)
            .getBody();
    }

}
