/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate;

import java.util.Arrays;

import org.suren.hibernate.util.Encryptor;

/**
 * @author suren
 * @date 2015年11月24日 下午5:11:51
 */
public class EncryptorTest
{

	/**
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		final String str = "surenpi.com";
		
		Encryptor encryptor = Encryptor.getInstance(Encryptor.ALG_DES);
		
		byte[] encrypt = encryptor.encrypt(str);

		System.out.println(encrypt.length);
		System.out.println(Arrays.toString(encrypt));
		System.out.println(encryptor.encryptStr(str));
		System.out.println(encryptor.encryptStr(str).length());
		
		System.out.println(encryptor.decryptStr(encrypt));
		
		encryptor = Encryptor.getInstance(Encryptor.ALG_DES);
		System.out.println(encryptor.encryptStr(str));
		System.out.println(encryptor.decryptStr(encryptor.encryptStr(str)));
	}

}
