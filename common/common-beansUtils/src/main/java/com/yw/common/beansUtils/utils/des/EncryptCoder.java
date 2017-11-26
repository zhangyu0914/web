package com.yw.common.beansUtils.utils.des;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptCoder {

	private final static String DES = "DES";

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 加密
	 * @param password
	 *            密码
	 * @param key
	 *            加密字符串
	 * @return
	 */
	public final static String encrypt(String password, String key) {
		try {
			return byte2String(encrypt(password.getBytes(), key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	public static String byte2String(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) {
//		String encryptString = encrypt("2222222222", "3333333333");
		String encryptString = encrypt("123456", DescryptCoder.DES_KEY);
		
		System.out.println(encryptString);
	}
}