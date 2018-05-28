package com.itplayer.core.base.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author create by caijun.yang on 2016/5/24.
 */
public class AesUtil {
    // 默认编码集
    private static final String UTF8 = "UTF-8";
    // 定义 加密算法,可用 AES,DES,DESede,Blowfish
    private static final String ALGORITHM_DESEDE = "DESede";
    private static final String ALGORITHM_AES = "AES";
    private static final String VIPARA = "1586323842684526";
    private static final String AESTYPE = "AES/CBC/PKCS5Padding";

    /**
     * 3DES加密
     *
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeEncoder(String src, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] b = cipher.doFinal(src.getBytes(UTF8));
        return parseByte2HexStr(b);
    }

    /**
     * 3DES解密
     *
     * @param dest
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeDecoder(String dest, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] b = cipher.doFinal(str2ByteArray(dest));
        return new String(b, UTF8);

    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf @return @throws
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

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr @return @throws
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 字符串转字节数组
     *
     * @param s
     * @return
     */
    private static byte[] str2ByteArray(String s) {
        int byteArrayLength = s.length() / 2;
        byte[] b = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16).intValue();
            b[i] = b0;
        }
        return b;
    }

    /**
     * 构造3DES加解密方法key
     *
     * @param keyStr
     * @return
     * @throws Exception
     */
    private static byte[] build3DesKey(String keyStr) throws Exception {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes(UTF8);
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }

        return key;
    }

    /**
     * AES 加密
     *
     * @param content  明文
     * @param password 生成秘钥的关键字
     * @return
     */

    public static String aesEncrypt(String content, String password) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM_AES);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes(UTF8));
            // return Base64.encode(encryptedData);
            return parseByte2HexStr(encryptedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param content  密文
     * @param password 生成秘钥的关键字
     * @return
     */

    public static String aesDecrypt(String content, String password) {
        try {
            byte[] byteMi = parseHexStr2Byte(content);
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM_AES);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, UTF8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * md5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getMD5String(String str) {
        try {
            byte[] res = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5".toUpperCase());
            byte[] result = md.digest(res);
            for (int i = 0; i < result.length; i++) {
                md.update(result[i]);
            }
            byte[] hash = md.digest();
            StringBuffer d = new StringBuffer("");
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16)
                    d.append("0");
                d.append(Integer.toString(v, 16).toUpperCase());
            }
            return d.toString();
        } catch (Exception localException) {
        }
        return "";
    }
    
    public static String Md5Encoder(String str, String salt) {
        return new Md5Hash(str, salt, 1).toString();
    }

    public static void main(String[] args) {
        String str = aesEncrypt("成都卓影", "2222222222222222");
        System.out.println(str);
        String str2 = aesDecrypt(str, "2222222222222222");
        System.out.println(str2);
    }
}