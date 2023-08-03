package SecureChat;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Formatter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class crypto {

	private static String algo="AES";
	private static byte[] keyValue;

	public static String encrypt(String s,String mykey)throws Exception {
		while(mykey.length()<16)
			mykey+="1";
		keyValue=mykey.getBytes();
		Key key=generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal=c.doFinal(s.getBytes());
		String encrypted=Base64.getEncoder().encodeToString(encVal);
		return encrypted;
				
	}
	
	
	public static String decrypt(String encrypted,String mykey)throws Exception{
	
		while(mykey.length()<16)
			mykey+="1";
		keyValue=mykey.getBytes();
		Key key=generateKey();
		Cipher c = Cipher.getInstance(algo);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decoderValue=Base64.getMimeDecoder().decode(encrypted);
		byte[] decValue=c.doFinal(decoderValue);
		String decrypted=new String(decValue);
		return decrypted;
		

		
	}

	private static Key generateKey() {
		Key key=new SecretKeySpec(keyValue, algo);
		return key;
	}
	
	
	private static final String HMAC_SHA512 = "HmacMD5";

	private static String toHexString(byte[] bytes) {
	    Formatter formatter = new Formatter();
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}

	public static String calculateHMAC(String data, String key)
	    throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
	    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
	    Mac mac = Mac.getInstance(HMAC_SHA512);
	    mac.init(secretKeySpec);
	    return toHexString(mac.doFinal(data.getBytes()));
	}

	public static void main(String[] args) throws Exception {
	    String hmac = calculateHMAC("hhhhhhhhhhhhhhhhhhh", "key");
	    String hmac2 = calculateHMAC("hhhhhhhhhhhhhhhhhhh", "key");

	    System.out.println("length: "+hmac.length()+" //"+hmac);
	    System.out.println("length: "+hmac2.length()+" //"+hmac2);

	}
}
