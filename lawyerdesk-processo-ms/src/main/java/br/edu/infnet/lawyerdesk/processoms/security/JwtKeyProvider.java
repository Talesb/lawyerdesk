package br.edu.infnet.lawyerdesk.processoms.security;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class JwtKeyProvider {

	private PrivateKey privateKey;

	private PublicKey publicKey;

	@PostConstruct
	public void init() {
		try {
			privateKey = readPrivateKey("/privatekey.pem");
			publicKey = readPublicKey("/publickey.pem");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
		try (InputStream contentIS = JwtKeyProvider.class.getResourceAsStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	public static PublicKey readPublicKey(final String pemResName) throws Exception {
		try (InputStream contentIS = JwtKeyProvider.class.getResourceAsStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePublicKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(keySpec);
	}

	public static PublicKey decodePublicKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(keySpec);
	}

	public static byte[] toEncodedBytes(final String pemEncoded) {
		final String normalizedPem = removeBeginEnd(pemEncoded);
		return Base64.getDecoder().decode(normalizedPem);
	}

	public static String removeBeginEnd(String pem) {
		pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
