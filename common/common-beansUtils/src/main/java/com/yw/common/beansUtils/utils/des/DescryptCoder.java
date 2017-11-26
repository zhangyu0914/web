package com.yw.common.beansUtils.utils.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class DescryptCoder {
         
    private final static String DES = "DES";
    public final static String DES_KEY = "234923238jdhd73hdkd";//默认密钥
         
    /**
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
             
        // 正式执行解密操作
        return cipher.doFinal(src);
    }
    
    /**
     *<pre>
     * 说       明: 解密
     * @param data
     * @param key
     * @return
     * 涉及版本: V1.0.0 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-7-8下午4:11:10
     *</pre>
     */
    public final static String decrypt(String data, String key) {
        try {
        	System.out.println("解密成功");
            return new String(decrypt(String2byte(data.getBytes()), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
         
    public static byte[] String2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
         
    public static void main(String[] args){
        String desencryptString = decrypt("E913CC345278005E2A85D84C98628311",DES_KEY);
        System.out.println(desencryptString);//输出：is张三丰
    }
}