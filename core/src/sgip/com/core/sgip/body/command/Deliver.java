/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-18
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.body.command;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.core.sgip.body.SGIPCommand;
import com.core.sgip.util.SGIPUtils;

public class Deliver extends SGIPCommand {

	private static Logger	logger	= Logger.getLogger(Deliver.class);
	
	private static final int COMMAND_FIX_LENGTH = 21*2 + 3*1 + 4 + 8;
	
	private String userNumber;
	
	private String spNumber;
	
	private int tpPid;
	
	private int tpUdhi;
	
	private int messageCoding;
	
	private long messageLength;
	
	private String messageContent;
	
	private byte[] reserve = new byte[8];
	
	private byte[] userNumberByte = new byte[21];
	
	private byte[] spNumberByte = new byte[21];
	
	private byte[] tpPidByte = new byte[1];
	
	private byte[] tpUdhiByte = new byte[1];
	
	private byte[] messageCodingByte = new byte[]{15};
	
	private byte[] messageLengthByte = new byte[4];
	
	private byte[] messageContentByte;

	public String getUserNumber()
	{
		return userNumber;
	}

	public void setUserNumber(String userNumber)
	{
		this.userNumber = userNumber;
	}

	public String getSpNumber()
	{
		return spNumber;
	}

	public void setSpNumber(String spNumber)
	{
		this.spNumber = spNumber;
	}

	public int getTpPid()
	{
		return tpPid;
	}

	public void setTpPid(int tpPid)
	{
		this.tpPid = tpPid;
	}

	public int getTpUdhi()
	{
		return tpUdhi;
	}

	public void setTpUdhi(int tpUdhi)
	{
		this.tpUdhi = tpUdhi;
	}

	public int getMessageCoding()
	{
		return messageCoding;
	}

	public void setMessageCoding(int messageCoding)
	{
		this.messageCoding = messageCoding;
	}

	public long getMessageLength()
	{
		return messageLength;
	}

	public void setMessageLength(long messageLength)
	{
		this.messageLength = messageLength;
	}

	public String getMessageContent()
	{
		return messageContent;
	}

	public void setMessageContent(String messageContent)
	{
		this.messageContent = messageContent;
	}

	public Deliver()
	{
		super();
	}
	
	private void calcute()
	{
		
	}
	
	@Override
	public byte[] getByteData()
	{
		calcute();
		//不需要发送Delivery
		return super.getByteData();
	}
	
	
	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		if(source != null && source.length >= COMMAND_FIX_LENGTH)
		{
			int length = source.length;
			int contentLength = length - COMMAND_FIX_LENGTH;
			SGIPUtils.copyBytes(source, this.userNumberByte, 1, 21, 1);
			SGIPUtils.copyBytes(source, this.spNumberByte, 22, 21, 1);
			SGIPUtils.copyBytes(source, this.tpPidByte, 43, 1, 1);
			SGIPUtils.copyBytes(source, this.tpUdhiByte, 44, 1, 1);
			SGIPUtils.copyBytes(source, this.messageCodingByte, 45, 1, 1);
			SGIPUtils.copyBytes(source, this.messageLengthByte, 46, 4, 1);
			this.messageContentByte = new byte[contentLength];
			SGIPUtils.copyBytes(source, this.messageContentByte, 50, contentLength, 1);
			
			this.userNumber = new String(this.userNumberByte);
			this.spNumber = new String(this.spNumberByte);
			this.tpPid = SGIPUtils.convertUnsignByte2Int(this.tpPidByte[0]);
			this.tpUdhi = SGIPUtils.convertUnsignByte2Int(this.tpUdhiByte[0]);
			this.messageCoding = SGIPUtils.convertUnsignByte2Int(this.messageCodingByte[0]);
			this.messageLength = SGIPUtils.byte4ToLong(this.messageLengthByte);
			if(this.messageCoding == 8)
			{
				try
				{
					this.messageContent = new String(messageContentByte,"UTF-16");
				} catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
			}else
			{
				this.messageContent = new String(messageContentByte);
			}
		}else
		{
			logger.error("init Deliver properties from bytes error: the bytes not enough");
		}
	}

	@Override
	public String toString()
	{
		return "Deliver [userNumber=" + userNumber + ", spNumber=" + spNumber
				+ ", tpPid=" + tpPid + ", tpUdhi=" + tpUdhi
				+ ", messageCoding=" + messageCoding + ", messageLength="
				+ messageLength + ", messageContent=" + messageContent + "]";
	}
	
}
