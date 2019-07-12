package com.demo.dev.gjj.sdk;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSASignature {
	
	public static final String RSA_PRIVATE = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAzVGENKWOHFos/ypcI9lDz+o8QPjyEfPgKMGAJdQErt3rcHQvqUhm6T4dPLJUMbiF7b9trLVG8TEKwaVTlqujYwIDAQABAkEAyBYnq5KnMjQi46vpTTo/HpCblYnFhf9PNQiVlMfQpIN6zt22QZX4EKtYQhbo5Q78jpbolXi/fDQP8Ozb7t1VwQIhAO/dGpyyhXIBu9bTIXl0AgXd0lHtelInuNLLi9PY/auDAiEA2yF6q50ro7yywpWtRQKRhmKSdI7bP94Bjzb2lDh5QqECICSS+UDVc8WbgBHUpbEIQFq2pSA67sDiL6tswAhweNWTAiArMxZz7rBDv1eedNOL303BKH2m7OLcXHACQ9uorNl7AQIhAJV4PErHWZtqdDafYMrnp7ixNLBN2QUvksMNzoAQiQn/";

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * 
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		try {
			String charset = "utf-8";
			if(privateKey==null||privateKey.length()==0){
				privateKey=RSA_PRIVATE;
			}
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();
			System.out.println(signed.length);
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
