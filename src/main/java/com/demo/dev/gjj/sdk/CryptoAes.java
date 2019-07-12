package com.demo.dev.gjj.sdk;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class CryptoAes {

	private static final byte[] keybytes = { 0x1c, 0x1b, 0x33, 0x1b, 0x01, 0x3c, 0x76, 0x46, 0x2a, 0x56, 0x6d, 0x4e, 0x49, 0x5b, 0x31, 0x38 };
	
	public static String encrypt(String value) {
		if(value==null||value.trim().length()==0){
			return null;
		}
		value = value + Long.toString(Math.round(Math.random() * 100 + 100));
		String s = null;
		int mode = Cipher.ENCRYPT_MODE;
		try {
			Cipher cipher = initCipher(mode,null);
			byte[] outBytes = cipher.doFinal(value.getBytes());
			s = String.valueOf(Hex.encodeHex(outBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static String decrypt(String value) {
		if(value==null||value.trim().length()==0){
			return null;
		}
		String s = null;
		int mode = Cipher.DECRYPT_MODE;
		try {
			Cipher cipher = initCipher(mode,null);
			byte[] outBytes = cipher.doFinal(Hex.decodeHex(value.toCharArray()));
			s = new String(outBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(s==null){
			return s;
		}
		return s.substring(0, s.length() - 3);
	}
	
	private static Cipher initCipher(int mode,String key) throws Exception{
		Cipher cipher = null;
		if(key==null){
			cipher = Cipher.getInstance("AES");
			Key skey = new SecretKeySpec(keybytes, "AES");
			SecureRandom sr = new SecureRandom();
			cipher.init(mode, skey, sr);
		}else{
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] salt = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };
			PBEKeySpec ps=new PBEKeySpec(key.toCharArray(),salt,10000,128);
			SecretKey skey=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(ps);
			byte[] skAsByteArray = skey.getEncoded();
			SecretKeySpec skforAES = new SecretKeySpec(skAsByteArray, "AES");
			byte[] iv = { 0xA, 1, 0xB, 5, 4, 0xF, 7, 9, 0x17, 3, 1, 6, 8, 0xC, 0xD, 91 };
			IvParameterSpec ips=new IvParameterSpec(iv);
			cipher.init(mode, skforAES, ips);
		}
		return cipher;
	}
	
	public static String encrypt(String value, String key) {
		if(value==null||value.trim().length()==0){
			return null;
		}
		if(key==null){
			return encrypt(value);
		}
		String s = null;
		int mode = Cipher.ENCRYPT_MODE;
		try {
			Cipher cipher = initCipher(mode,key);
			byte[] outBytes = cipher.doFinal(value.getBytes("utf-8"));
			s = Base64Encoder.encode(outBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String decrypt(String value, String key) {
		if(value==null||value.trim().length()==0){
			return null;
		}
		if(key==null){
			return decrypt(value);
		}
		String s = null;
		int mode = Cipher.DECRYPT_MODE;
		try {
			Cipher cipher = initCipher(mode,key);
			byte[] sv = Base64Decoder.decodeToBytes(value);
			byte[] outBytes = cipher.doFinal(sv);
			s = new String(outBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
}
