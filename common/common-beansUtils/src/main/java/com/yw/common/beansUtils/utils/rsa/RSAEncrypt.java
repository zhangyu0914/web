package com.yw.common.beansUtils.utils.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;

/**
 *<pre>
 * 功       能: RSA加密解密
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-3-31下午4:45:03
 * Q    Q: 308053847
 *</pre>
 */
public class RSAEncrypt {
	
	/**
	 * 公钥
	 */
	private static final String DEFAULT_PUBLIC_KEY= 
		"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP7H4MVuXhqbc/MGJWCvHaMZ+X" + "\r" +
		"/jdRsuTuaQS1A/IKzwx8HakarsCeAespUR+Tj7+rrUMy8B9JRBYz7+/P+dskSzD6" + "\r" +
		"oZ5RtauUZRgJ8cojBDNw05fNXD4G2GxhjkDCfwLIr0+cDDFF5O7palxv7YFz7awo" + "\r" +
		"/PQUGN28sWGp7XrDRQIDAQAB" + "\r";
	
	/**
	 * 私钥
	 */
	private static final String DEFAULT_PRIVATE_KEY=
		"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM/sfgxW5eGptz8w" + "\r" +
		"YlYK8doxn5f+N1Gy5O5pBLUD8grPDHwdqRquwJ4B6ylRH5OPv6utQzLwH0lEFjPv" + "\r" +
		"78/52yRLMPqhnlG1q5RlGAnxyiMEM3DTl81cPgbYbGGOQMJ/AsivT5wMMUXk7ulq" + "\r" +
		"XG/tgXPtrCj89BQY3byxYantesNFAgMBAAECgYBOOireV59bvK+t/XUx7CI8NmlW" + "\r" +
		"FRYZDdZmAGr988MP4ABRfhNOb6sWh6BDRjs8GFxDqgdbVc5fkVdNKRuvwtB4dJpm" + "\r" +
		"IpwltIeP4zKN+tlhL4VGtcu0lHfmzkaQ30tHorQKr8yMkhwanvZ9lw3uMsEtf6b5" + "\r" +
		"BwRCC4N32hTyOIomzQJBAOe0Iv5+z3Gg7rtrgg6V9mviN4FbtSFxbu5xJxWDlFSz" + "\r" +
		"eM+KOxRLtFAvrqXtnsuTCM2LteRy2fe2BI5wm0TaRBsCQQDlugIPTiS+eqC7zrfm" + "\r" +
		"8rkPZi8FZnt2m4NC7y7oNA9hqXgY7IbzbOAiSiwPY4SooRkF2rF5PC7jP+q+xhzA" + "\r" +
		"xcwfAkBUYJslxpRQsuR9SAdSvHXCj9zatMTfaumU14v+H66SsdDa82HUYEVHtjWJ" + "\r" +
		"o2Dijab1A1231cNem+BI5uUES9/lAkBIPLVpGqlzPBxO8jHh7XFD5l3i1Nb99OjR" + "\r" +
		"o2Scn8I2c5PQQpAs+49/ONOiAykz3XSA4sPr24Di9LZcRu/Drm8BAkBtyibksaqh" + "\r" +
		"Flvx4sqp3m9Sf80C1Oci3XQhAHdNxxhe82PTj2+8H8NqMrsrYhiQh5u7Msr2L08h" + "\r" +
		"cfT+azI69Ktv" + "\r";

	/**
	 * 私钥
	 */
	private RSAPrivateKey privateKey;

	/**
	 * 公钥
	 */
	private RSAPublicKey publicKey;
	
	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	

	/**
	 * 获取私钥
	 * @return 当前的私钥对象
	 */
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 获取公钥
	 * @return 当前的公钥对象
	 */
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 *<pre>
	 * 说       明: 随机生成密钥对
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:04:51
	 *</pre>
	 */
	public void genKeyPair() throws Exception{
		KeyPairGenerator keyPairGen= null;
		keyPairGen= KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair= keyPairGen.generateKeyPair();
		this.privateKey= (RSAPrivateKey) keyPair.getPrivate();
		this.publicKey= (RSAPublicKey) keyPair.getPublic();
	}

	/**
	 *<pre>
	 * 说       明: 从文件中输入流中加载公钥
	 * @param in 公钥输入流
	 * @throws Exception 加载公钥时产生的异常
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:05:04
	 *</pre>
	 */
	public void loadPublicKey(InputStream in) throws Exception{
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(in));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while((readLine= br.readLine())!=null){
				if(readLine.charAt(0)=='-'){
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 *<pre>
	 * 说       明: 从字符串中加载公钥
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:05:21
	 *</pre>
	 */
	public void loadPublicKey(String publicKeyStr) throws Exception{
		try {
			BASE64Decoder base64Decoder= new BASE64Decoder();
			byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
			this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 *<pre>
	 * 说       明: 从文件中加载私钥
	 * @param in 私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:05:38
	 *</pre>
	 */
	public void loadPrivateKey(InputStream in) throws Exception{
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(in));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while((readLine= br.readLine())!=null){
				if(readLine.charAt(0)=='-'){
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 *<pre>
	 * 说       明: 从字符串中加载私钥
	 * @param privateKeyStr
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:06:10
	 *</pre>
	 */
	public void loadPrivateKey(String privateKeyStr) throws Exception{
		try {
			BASE64Decoder base64Decoder= new BASE64Decoder();
			byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			this.privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 *<pre>
	 * 说       明: 加密过程
	 * @param publicKey 公钥
	 * @param plainTextData 明文数据
	 * @return
	 * @throws Exception 加密过程中的异常信息
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:06:29
	 *</pre>
	 */
	public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{
		if(publicKey== null){
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher= null;
		try {
			cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output= cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		}catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 解密过程
	 * @param privateKey 私钥
	 * @param cipherData 密文数据
	 * @return 明文
	 * @throws Exception 解密过程中的异常信息
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:07:11
	 *</pre>
	 */
	public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception{
		if (privateKey== null){
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher= null;
		try {
			cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output= cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		}catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}		
	}

	/**
	 *<pre>
	 * 说       明: 字节数据转十六进制字符串
	 * @param data 输入数据
	 * @return 十六进制内容
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-31下午5:06:53
	 *</pre>
	 */
	public static String byteArrayToString(byte[] data){
		StringBuilder stringBuilder= new StringBuilder();
		for (int i=0; i<data.length; i++){
			//取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);
			//取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i<data.length-1){
				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}


	public static void main(String[] args){
		RSAEncrypt rsaEncrypt= new RSAEncrypt();
//		rsaEncrypt.genKeyPair(); //用代码生成公私钥

		//加载公钥
		try {
			rsaEncrypt.loadPublicKey(RSAEncrypt.DEFAULT_PUBLIC_KEY);
			System.out.println("加载公钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}

		//加载私钥
		try {
			rsaEncrypt.loadPrivateKey(RSAEncrypt.DEFAULT_PRIVATE_KEY);
			System.out.println("加载私钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载私钥失败");
		}

		//测试字符串
		String encryptStr= "Test String chaijunkun";

		try {
			//加密
			byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), encryptStr.getBytes());
			//解密
			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(), cipher);
			System.out.println("密文长度:"+ cipher.length);
			System.out.println(RSAEncrypt.byteArrayToString(cipher));
			System.out.println("明文长度:"+ plainText.length);
			System.out.println(RSAEncrypt.byteArrayToString(plainText));
			System.out.println(new String(plainText));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}