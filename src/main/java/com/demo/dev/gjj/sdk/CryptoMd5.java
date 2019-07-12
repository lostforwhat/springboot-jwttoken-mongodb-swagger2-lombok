package com.demo.dev.gjj.sdk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class CryptoMd5 {

	public static String encrypt(String value) {
		if(value==null||value.trim().length()==0){
			return null;
		}
		MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] byteArray;
		try {
			byteArray = value.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			return String.valueOf(Hex.encodeHex(md5Bytes));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String content = "certificateNum=53262519590104291X&password=123456";
		String md5Text = encrypt(content);
		String encodeSignature = RSASignature.sign(md5Text, null);
		byte[] decodeSignature = Base64.decode(encodeSignature);
		System.out.println(md5Text);
		System.out.println(encodeSignature);
		System.out.println(decodeSignature);
	}
}
