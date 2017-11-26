package com.yw.common.beansUtils.utils.des;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.yw.common.beansUtils.utils.Constants;

/**
 * <pre>
 * 功       能:   编码工具类
 * 1.将byte[]转为各种进制的字符串
 * 2.base 64 encode
 * 3.base 64 decode
 * 4.获取byte[]的md5值
 * 5.获取字符串md5值
 * 6.结合base64实现md5加密
 * 7.AES加密
 * 8.AES加密为base 64 code
 * 9.AES解密
 * 10.将base 64 code AES解密
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-2-4下午3:45:24
 * Q    Q: 308053847
 * </pre>
 */
public class QEncodeUtil {

	private static int AES_BIT = 128;
	
	public static final String AES_KEY = "3hwqxR1W4dF&1*37";//AES密钥
	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder()
				.decodeBuffer(base64Code);
	}

	/**
	 * 获取byte[]的md5值
	 * 
	 * @param bytes
	 *            byte[]
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(byte[] bytes) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes);

		return md.digest();
	}

	/**
	 * 获取字符串md5值
	 * 
	 * @param msg
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(String msg) throws Exception {
		return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
	}

	/**
	 * 结合base64实现md5加密
	 * 
	 * @param msg
	 *            待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	public static String md5Encrypt(String msg) throws Exception {
		return StringUtils.isEmpty(msg) ? null : base64Encode(md5(msg));
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey)
			throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(AES_BIT, new SecureRandom(encryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//AES
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));

		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * AES加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey)
			throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}
	
	public static byte[] aesEncrypt(byte[] source, byte rawKeyData[])
            throws GeneralSecurityException {
        // 处理密钥
        SecretKeySpec key = new SecretKeySpec(rawKeyData, "AES");
        // 加密
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);
    }
	
	/**
	 *<pre>
	 * 说       明: 进行封装
	 * @param content
	 * @param key
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-4下午6:43:22
	 *</pre>
	 */
	public static byte[] aesEncryptNew(String content, String key) throws GeneralSecurityException, UnsupportedEncodingException {
        return aesEncrypt((base64Encode(content.getBytes("utf-8"))).getBytes("utf-8"), key.getBytes("utf-8"));
    }
    public static byte[] aesDecrypt(byte[] data, byte rawKeyData[])
            throws GeneralSecurityException {
        // 处理密钥
        SecretKeySpec key = new SecretKeySpec(rawKeyData, "AES");
        // 解密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    
    public static String aesDecryptNew(byte[] encrypt, String key)throws Exception {
        return new String(base64Decode(new String(aesDecrypt(encrypt, key.getBytes()))),"utf-8");
    }

    public static byte[] aesDecryptNewReturnByte(byte[] encrypt, String key)throws Exception {
        return base64Decode(new String(aesDecrypt(encrypt, key.getBytes())));
    }

	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes,
			String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(AES_BIT, new SecureRandom(decryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);

		return new String(decryptBytes);
	}

	/**
	 * 将base 64 code AES解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey)
			throws Exception {
		return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(
				base64Decode(encryptStr), decryptKey);
	}
	
	public static byte[] initKey() throws Exception{  
        //实例化  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        //设置密钥长度  
        kgen.init(128);  
        //生成密钥  
        SecretKey skey = kgen.generateKey();  
        //返回密钥的二进制编码  
        return skey.getEncoded();  
    } 
	
	/**
	 * 加密后返回16六进制字符串
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String encript(String content,String aesKey) throws Exception{
		if(StringUtils.isEmpty(aesKey)){
			aesKey=Constants.AES_KEY;
		}
		return parseByte2HexStr(aesEncryptNew(content, aesKey));
	}
	
	/**
	 * 加密后返回16六进制字符串
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String dencript(String content,String aesKey) throws Exception{
		if(StringUtils.isEmpty(aesKey)){
			aesKey=Constants.AES_KEY;
		}
		return aesDecryptNew(parseHexStr2Byte(content),  aesKey);
	}
	
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String content=".Kig26102";
		String key = "rKYsl6gko1WHQPAd";
		String str = encript("{'fkCommonProjectTid':'5af41c25-b3c9-4338-be7d-d7afc9b63ae7'}", key);
		System.out.println("加密数据：" + str);
		System.out.println("加密数据：" + dencript(str, key));
	}

}
