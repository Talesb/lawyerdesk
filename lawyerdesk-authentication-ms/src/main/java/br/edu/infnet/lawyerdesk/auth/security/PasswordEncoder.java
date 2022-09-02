package br.edu.infnet.lawyerdesk.auth.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class PasswordEncoder {

	@ConfigProperty(name = "br.edu.infnet.lawyerdesk.password.secret")  private String secret;
	@ConfigProperty(name = "br.edu.infnet.lawyerdesk.password.iteration")  private Integer iteration;
	@ConfigProperty(name = "br.edu.infnet.lawyerdesk.password.keylength")  private Integer keylength;
	
	/**
	 * More info (https://www.owasp.org/index.php/Hashing_Java) 404 :(
	 * @param cs password
	 * @return encoded password
	 */
	public String encode(CharSequence cs) {
		try {
			byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
											.generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
											.getEncoded();
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
