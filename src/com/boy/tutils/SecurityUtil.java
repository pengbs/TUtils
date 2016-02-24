package com.boy.tutils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil {
	private static String MD5_APPEND = "";
	/**
	 * 密钥算法
	 * */
	public static final String TRIPLEDES_ALGORITHM = "DESede";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * */
	public static final String TRIPLEDES_CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";

	/**

	 */
	private static String TRIPLEDES__IV = "";

	/**

	 */
	private static String TRIPLEDES__KEY = "";

	/**

	 */
	private static final String CHARSETNAME = "UTF-8";

	/**
	 * MD5加密
	 *
	 * @param parameter

	 * @return
	 */


	static{
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();

		a.append("a");
		b.append("o");
		c.append("4");
		b.append("$");
		b.append("q");
		c.append("0");
		c.append("3");
		b.append("i");
		b.append("t");
		a.append("c");
		a.append("i");
		b.append("h");
		a.append("t");
		a.append(")");
		b.append("1");
		b.append("2");
		a.append("_");
		b.append("3");
		a.append("(");
		b.append("%");
		a.append("&");
		c.append("7");
		b.append("4");
		a.append("%");
		b.append("9");
		b.append("0");
		c.append("2");
		b.append("~");
		a.append("*");
		b.append("@");
		b.append("#");
		a.append("r");
		a.append("t");
		b.append("5");
		b.append("6");
		a.append("y");
		b.append("7");
		b.append("8");
		c.append("5");
		a.append("^");
		c.append("6");
		b.append("e");
		b.append("c");
		a.append("s");
		b.append("h");
		a.append("m");
		c.append("1");
		b.append("a");

		MD5_APPEND = a.toString();
		TRIPLEDES__KEY = b.toString();
		TRIPLEDES__IV = c.toString();
	}


	public static String md5(String parameter) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest((parameter + MD5_APPEND).getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}

	public static String md5(String parameter,String strAppend) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest((parameter + strAppend).getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}


	/**
	 * 3DES加密
	 *
	 * @param clearText
	 *            明文
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(byte[] clearText) {
		try {
			SecretKey deskey = new SecretKeySpec(TRIPLEDES__KEY.getBytes(CHARSETNAME), TRIPLEDES_ALGORITHM);
			Cipher c1 = Cipher.getInstance(TRIPLEDES_CIPHER_ALGORITHM);
			IvParameterSpec ips = new IvParameterSpec(TRIPLEDES__IV.getBytes(CHARSETNAME));
			c1.init(Cipher.ENCRYPT_MODE, deskey, ips);
			return Base64.encodeToString(c1.doFinal(clearText), Base64.NO_WRAP);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 *  3DES加密
	 * @param clearText
	 * @param TRIPLEDES__KEY
	 * @return
	 */
	public static String encrypt(byte[] clearText,String TRIPLEDES__KEY) {
		try {
			SecretKey deskey = new SecretKeySpec(TRIPLEDES__KEY.getBytes(CHARSETNAME), TRIPLEDES_ALGORITHM);
			Cipher c1 = Cipher.getInstance(TRIPLEDES_CIPHER_ALGORITHM);
			IvParameterSpec ips = new IvParameterSpec(TRIPLEDES__IV.getBytes(CHARSETNAME));
			c1.init(Cipher.ENCRYPT_MODE, deskey, ips);
			return Base64.encodeToString(c1.doFinal(clearText), Base64.NO_WRAP);
		} catch (Exception e) {
		}
		return null;
	}


	/** 
	 * 3DES加密
	 * @param clearText
	 * @param TRIPLEDES__KEY
	 * @param TRIPLEDES__IV
	 * @return
	 */
	public static String encrypt(byte[] clearText,String TRIPLEDES__KEY,String TRIPLEDES__IV) {
		try {
			SecretKey deskey = new SecretKeySpec(TRIPLEDES__KEY.getBytes(CHARSETNAME), TRIPLEDES_ALGORITHM);
			Cipher c1 = Cipher.getInstance(TRIPLEDES_CIPHER_ALGORITHM);
			IvParameterSpec ips = new IvParameterSpec(TRIPLEDES__IV.getBytes(CHARSETNAME));
			c1.init(Cipher.ENCRYPT_MODE, deskey, ips);
			return Base64.encodeToString(c1.doFinal(clearText), Base64.NO_WRAP);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return null;
	}


	/**
     * 3DES解密
     *
     * @param cipherText
     *            密文
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] cipherText) {
        try {
            SecretKey deskey = new SecretKeySpec(TRIPLEDES__KEY.getBytes(CHARSETNAME), TRIPLEDES_ALGORITHM);
            Cipher c1 = Cipher.getInstance(TRIPLEDES_CIPHER_ALGORITHM);
            IvParameterSpec ips = new IvParameterSpec(TRIPLEDES__IV.getBytes(CHARSETNAME));
            c1.init(Cipher.DECRYPT_MODE, deskey, ips);
            return c1.doFinal(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param cipherText
     *            密文
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] cipherText,String TRIPLEDES__KEY,String TRIPLEDES__IV) {
        try {
            SecretKey deskey = new SecretKeySpec(TRIPLEDES__KEY.getBytes(CHARSETNAME), TRIPLEDES_ALGORITHM);
            Cipher c1 = Cipher.getInstance(TRIPLEDES_CIPHER_ALGORITHM);
            IvParameterSpec ips = new IvParameterSpec(TRIPLEDES__IV.getBytes(CHARSETNAME));
            c1.init(Cipher.DECRYPT_MODE, deskey, ips);
            return c1.doFinal(Base64.decode(cipherText, Base64.NO_WRAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}