package com.ty.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

/**
 * 信息摘要，可以获得CRC32、MD5、SHA-1值
 * 
 * @version 1.0
 * @author dongliyang
 */
public class MsgDigest
{

	/**
	 * 计算文件的32位循环冗余校验和CRC32
	 * 
	 * @param filepath
	 *            文件名
	 * @return String
	 * @throws IOException
	 *             IOException异常
	 */
	public static String crc32Digest(String filepath)
	{

		try
		{

			InputStream in = new BufferedInputStream(new FileInputStream(filepath));
			CRC32 crc = new CRC32();

			int c;
			while ((c = in.read()) != -1)
			{
				crc.update(c);
			}
			in.close();
			return Long.toHexString(crc.getValue());
		} catch (FileNotFoundException e)
		{
			System.out.println("文件未找到异常!");
			throw new RuntimeException(e.getMessage());
		} catch (IOException e)
		{
			System.out.println("IO异常!");
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 计算文件的32位循环冗余校验和CRC32
	 * 
	 * @param file
	 *            文件
	 * @return String
	 * @throws IOException
	 *             IOException异常
	 */
	public static String crc32Digest(File file)
	{

		try
		{

			InputStream in = new BufferedInputStream(new FileInputStream(file));
			CRC32 crc = new CRC32();

			int c;
			while ((c = in.read()) != -1)
			{
				crc.update(c);
			}
			in.close();
			return Long.toHexString(crc.getValue());
		} catch (FileNotFoundException e)
		{
			System.out.println("文件未找到异常!");
			throw new RuntimeException(e.getMessage());
		} catch (IOException e)
		{
			System.out.println("IO异常!");
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 计算字符串的MD5值
	 * 
	 * @param input
	 *            字符串
	 * @return String
	 */
	public static String md5Digest(String input)
	{

		byte[] data = input.getBytes();
		try
		{
			MessageDigest messageDigest = getMD5();
			messageDigest.update(data);
			return toHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e)
		{
			System.out.println("MD5算法初始化失败!");
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 计算文件的MD5值
	 * 
	 * @param filename
	 *            文件名
	 * @return String
	 */
	public static String md5FileDigest(String filepath)
	{
		String md5 = "";
		File file = new File(filepath);
		if (file.exists())
		{
			try
			{
				MessageDigest messageDigest = getMD5();
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				byte[] cache = new byte[CACHE_SIZE];
				int len;
				while ((len = in.read(cache)) != -1)
				{
					messageDigest.update(cache, 0, len);
				}
				in.close();
				byte[] data = messageDigest.digest();
				md5 = toHexString(data);
			} catch (NoSuchAlgorithmException e)
			{
				System.out.println("MD5算法初始化失败!");
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e)
			{
				System.out.println("文件未找到异常!");
				throw new RuntimeException(e.getMessage());
			} catch (IOException e)
			{
				System.out.println("IO异常!");
				throw new RuntimeException(e.getMessage());
			}
		}
		return md5;
	}

	/**
	 * 计算文件的MD5值
	 * 
	 * @param file
	 *            文件
	 * @return String
	 */
	public static String md5FileDigest(File file)
	{

		String md5 = "";
		if (file.exists())
		{
			try
			{
				MessageDigest messageDigest = getMD5();
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				byte[] cache = new byte[CACHE_SIZE];
				int len;
				while ((len = in.read(cache)) != -1)
				{
					messageDigest.update(cache, 0, len);
				}
				in.close();
				byte[] data = messageDigest.digest();
				md5 = toHexString(data);
			} catch (NoSuchAlgorithmException e)
			{
				System.out.println("MD5算法初始化失败!");
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e)
			{
				System.out.println("文件未找到异常!");
				throw new RuntimeException(e.getMessage());
			} catch (IOException e)
			{
				System.out.println("IO异常!");
				throw new RuntimeException(e.getMessage());
			}
		}
		return md5;
	}

	/**
	 * 计算字符串的SHA-1值
	 * 
	 * @param input
	 *            字符串
	 * @return String
	 */
	public static String sha1Digest(String input)
	{

		byte[] data = input.getBytes();
		try
		{
			MessageDigest messageDigest = getSHA1();
			messageDigest.update(data);
			return toHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e)
		{
			System.out.println("MD5算法初始化失败!");
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 计算文件的SHA-1值
	 * 
	 * @param filepath
	 *            文件名
	 * @return String
	 */
	public static String sha1FileDigest(String filepath)
	{

		String sha1 = "";
		File file = new File(filepath);
		if (file.exists())
		{
			try
			{
				MessageDigest messageDigest = getSHA1();
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				byte[] cache = new byte[CACHE_SIZE];
				int len;
				while ((len = in.read(cache)) != -1)
				{
					messageDigest.update(cache, 0, len);
				}
				in.close();
				byte[] data = messageDigest.digest();
				sha1 = toHexString(data);
			} catch (NoSuchAlgorithmException e)
			{
				System.out.println("MD5算法初始化失败!");
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e)
			{
				System.out.println("文件未找到异常!");
				throw new RuntimeException(e.getMessage());
			} catch (IOException e)
			{
				System.out.println("IO异常!");
				throw new RuntimeException(e.getMessage());
			}
		}
		return sha1;
	}

	/**
	 * 计算文件的SHA-1值
	 * 
	 * @param file
	 *            文件
	 * @return String
	 */
	public static String sha1FileDigest(File file)
	{

		String sha1 = "";
		if (file.exists())
		{
			try
			{
				MessageDigest messageDigest = getSHA1();
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				byte[] cache = new byte[CACHE_SIZE];
				int len;
				while ((len = in.read(cache)) != -1)
				{
					messageDigest.update(cache, 0, len);
				}
				in.close();
				byte[] data = messageDigest.digest();
				sha1 = toHexString(data);
			} catch (NoSuchAlgorithmException e)
			{
				System.out.println("MD5算法初始化失败!");
				throw new RuntimeException(e.getMessage());
			} catch (FileNotFoundException e)
			{
				System.out.println("文件未找到异常!");
				throw new RuntimeException(e.getMessage());
			} catch (IOException e)
			{
				System.out.println("IO异常!");
				throw new RuntimeException(e.getMessage());
			}
		}
		return sha1;

	}

	/**
	 * 字节数组转换为16进制字符串
	 * 
	 * @param data
	 *            字节数组
	 * @return String
	 */
	private static String toHexString(byte[] data)
	{
		StringBuilder digestStr = new StringBuilder();
		String stmp = "";
		for (int i = 0; i < data.length; i++)
		{
			stmp = Integer.toHexString(data[i] & 0XFF);
			if (stmp.length() == 1)
			{
				digestStr.append("0" + stmp);
			} else
			{
				digestStr.append(stmp);
			}
		}
		return digestStr.toString();
	}

	/**
	 * 获取MD5实例
	 * 
	 * @return MessageDigest
	 * @throws NoSuchAlgorithmException
	 *             异常
	 */
	private static MessageDigest getMD5() throws NoSuchAlgorithmException
	{
		return MessageDigest.getInstance(ALGORIGTHM_MD5);
	}

	private static MessageDigest getSHA1() throws NoSuchAlgorithmException
	{
		return MessageDigest.getInstance(ALGORIGTHM_SHA1);
	}

	/** MD5算法名称 */
	private static final String ALGORIGTHM_MD5 = "MD5";
	/** SHA-1算法名称 */
	private static final String ALGORIGTHM_SHA1 = "SHA-1";
	/** 字节数组缓存大小 */
	private static final int CACHE_SIZE = 2048;
}