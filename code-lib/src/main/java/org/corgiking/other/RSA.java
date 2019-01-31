package org.corgiking.other;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {

	private static final String RSA = "RSA";

	/**
	 * 获取公钥
	 * @param keyBytes
	 * @return
	 */
	public static PublicKey getPublicKey(byte[] keyBytes) {
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance(RSA);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("RSA keyFactory获取失败。", e);
		}

		try {
			return keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("获取公钥异常。", e);
		}
	}
	
	/**
	 * 获取私钥
	 * @param keyBytes
	 * @return
	 */
	public static PrivateKey getPrivateKey(byte[] keyBytes) {
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance(RSA);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("RSA keyFactory获取失败。", e);
		}

		try {
			return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("获取私钥异常。", e);
		}
	}

	/**
	 * 加密数据
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, Key key) {
		return operation(data, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 解密数据
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, Key key) {
		return operation(data, key, Cipher.DECRYPT_MODE);
	}

	private static byte[] operation(byte[] data, Key key, int opMode) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(RSA);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException("获取RSA加密实例异常。", e);
		}

		try {
			cipher.init(opMode, key);
		} catch (InvalidKeyException e) {
			throw new RuntimeException("cipher初始化异常。", e);
		}

		try {
			return cipher.doFinal(data);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException("加解密异常。", e);
		}
	}
	
	public static void main(String[] args) throws Exception {

		//获取RSA秘钥对
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
		kpg.initialize(512);
		KeyPair keyPair = kpg.genKeyPair();

		//通过私钥进行加密
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
		byte[] cipherBytes = cipher.doFinal("content".getBytes());

		//通过公钥进行解密
		cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
		byte[] sourceBytes = cipher.doFinal(cipherBytes);
		
		System.out.println(new String(sourceBytes));
	}
	
}
