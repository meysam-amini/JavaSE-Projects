package SecureChat;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;




public class RSA {
	
	
	private KeyPair pair;
	public RSA() throws Exception{

	}
	
	
	
	public static KeyPair generateKeyPair() throws Exception {
	
	    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(512, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();

	    return pair;
	}
	
	
	public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
		Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

	    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

	   // System.out.println("cipherText encrypting: "+new String(cipherText).length());

	    return Base64.getEncoder().encodeToString(cipherText);
	    
	    
        
	}
	
	
	public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);
	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    System.out.println("cipherText decrypting: "+new String(bytes).length());
	    
	    return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
	}
	
	
	public static PublicKey getPublicKeyFromString(String key){
	    try{
	        byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
	        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
	        KeyFactory kf = KeyFactory.getInstance("RSA");

	        return kf.generatePublic(X509publicKey);
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }

	    return null;
	}
	
	
	
	public static PrivateKey getPrivateKeyFromString(String key){
	    try{
	        byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
	        X509EncodedKeySpec X509privateKey = new X509EncodedKeySpec(byteKey);
	        KeyFactory kf = KeyFactory.getInstance("RSA");

	        return kf.generatePrivate(X509privateKey);
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }

	    return null;
	}

	
	public static String sign(String plainText, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA256withRSA");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

	    byte[] signature = privateSignature.sign();

	    return Base64.getEncoder().encodeToString(signature);
	}
	
	
	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

	    byte[] signatureBytes = Base64.getDecoder().decode(signature);

	    return publicSignature.verify(signatureBytes);
	}
	
	public static void main(String[] args) throws Exception {


		//Our secret message
		String message = "the answer to life the universe and everything";

		//Encrypt the message
	//	String cipherText = encrypt(message, (PublicKey)generateKeyPair().getPrivate());

		//Now decrypt it
	//	String decipheredMessage = decrypt(cipherText, (generateKeyPair().getPublic());

		PublicKey p=generateKeyPair().getPublic();
		PrivateKey privatee=generateKeyPair().getPrivate();

	//	System.out.println(p);
		byte [] b=p.getEncoded();
		byte[] b2=privatee.getEncoded();

		String s=Base64.getEncoder().encodeToString(b);

		String pr=Base64.getEncoder().encodeToString(b2);
	//	System.out.println(s);
		
	
		System.out.println("Public key: "+getPublicKeyFromString(s));
		System.out.println("Private key: "+getPrivateKeyFromString(pr));

	}
	
}


