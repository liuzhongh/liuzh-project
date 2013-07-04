package org.jasig.cas.authentication.handler.support;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.jasig.cas.authentication.handler.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder {
	static Logger					logger	= Logger.getLogger(SimplePasswordEncoder.class);
	private static MessageDigest	digest	= null;

	public SimplePasswordEncoder()
	{
	}

	public String encode(String data)
	{
		if (digest == null)
			try
			{
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae)
			{
				logger.error(
						"Failed to load the MD5 MessageDigest. Jive will be unable to function normally.",
						nsae);
			}
		try
		{
			digest.update(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e)
		{
			logger.error(e);
		}
		return encodeHex(digest.digest());
	}

	private String encodeHex(byte bytes[])
	{
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}

		return buf.toString();
	}

}
