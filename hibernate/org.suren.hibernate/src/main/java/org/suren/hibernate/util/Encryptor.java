/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author suren
 * @date 2015年11月24日 下午5:04:05
 */
public class Encryptor
{
	private KeyGenerator	keyGen;
	private SecretKey	key;
	private Cipher	cipher;
	private byte[]	cipherByte;
	
	public static final String ALG_DES = "DES";
	private static Encryptor encryptor;
	
	private Encryptor(){}
	
	private Encryptor(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException
	{
		keyGen = KeyGenerator.getInstance(algorithm);
		key = keyGen.generateKey();
		
		cipher = Cipher.getInstance(algorithm);
	}
	
	public static Encryptor getInstance(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException
	{
		if(encryptor == null)
		{
			encryptor = new Encryptor(algorithm);
		}
		
		return encryptor;
	}
	
	public byte[] encrypt(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		cipherByte = cipher.doFinal(str.getBytes());
		return cipherByte;
	}
	
	public String encryptStr(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		byte[] encryptArray = encrypt(str);
		
		StringBuffer strBuf = new StringBuffer();
		for(byte e : encryptArray)
		{
			strBuf.append(intToChar((e >> 4) & 0x0f));
			strBuf.append(intToChar(e & 0x0f));
		}
		
		return strBuf.toString();
	}
	
	public byte[] decrypt(byte[] buf) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		cipher.init(Cipher.DECRYPT_MODE, key);
		cipherByte = cipher.doFinal(buf);
		return cipherByte;
	}
	
	public String decryptStr(byte[] buf) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		return new String(decrypt(buf));
	}
	
	/**
	 * decrypt string
	 * @see #encryptStr(String)
	 * @param encryptStr
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NullPointerException if encryptStr is null
	 */
	public String decryptStr(String encryptStr) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		if(encryptStr == null)
		{
			throw new NullPointerException();
		}
		
		encryptStr = encryptStr.toUpperCase();
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		int len = encryptStr.length();
		for(int i = 0; i < len; i += 2)
		{
			int b = ((charToByte(encryptStr.charAt(i)) << 4) & 0xff) | charToByte(encryptStr.charAt(i + 1));
			byteOut.write(b);
		}
		
		return decryptStr(byteOut.toByteArray());
	}
	
	private byte charToByte(char c)
	{
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	private char intToChar(int b)
	{
		return "0123456789ABCDEF".charAt(b);
	}
	
	public void clean()
	{
		cipherByte = null;
	}
}
