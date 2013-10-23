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

import org.apache.log4j.Logger;

import com.core.sgip.body.SGIPCommand;
import com.core.sgip.util.SGIPUtils;

public class Report extends SGIPCommand {

	private static Logger	logger	= Logger.getLogger(Report.class);
	
	private static final int COMMAND_LENGTH = 44;
	
	private byte[] submitSequenceNumber = new byte[12];//12
	
	private String submitSequenceNumberStr="";
	
	public String getSubmitSequenceNumberStr()
	{
		byte[] a = new byte[4];
		byte[] b = new byte[4];
		byte[] c = new byte[4];
		
		SGIPUtils.copyBytes(this.submitSequenceNumber, a, 1, 4, 1);
		SGIPUtils.copyBytes(this.submitSequenceNumber, b, 5, 4, 1);
		SGIPUtils.copyBytes(this.submitSequenceNumber, c, 9, 4, 1);
		
		return String.valueOf(SGIPUtils.byte4ToLong(a))
				+ String.valueOf(SGIPUtils.byte4ToLong(b))
				+ String.valueOf(SGIPUtils.byte4ToLong(c));
	}

	private int reportType;
	
	private String userNumber;
	
	private int state;//0：发送成功 1：等待发送 2：发送失败
	
	private int errorCode;
	
	private byte[] reserve = new byte[8];
	
	private byte[] reportTypeByte = new byte[1];
	
	private byte[] stateByte = new byte[1];
	
	private byte[] errorCodeByte = new byte[1];
	
	private byte[] userNumberByte = new byte[21];
	

	private byte[] getSubmitSequenceNumber()
	{
		return submitSequenceNumber;
	}

	private void setSubmitSequenceNumber(byte[] submitSequenceNumber)
	{
		this.submitSequenceNumber = submitSequenceNumber;
	}

	public int getReportType()
	{
		return reportType;
	}

	public void setReportType(int reportType)
	{
		this.reportType = reportType;
	}

	public String getUserNumber()
	{
		return userNumber;
	}

	public void setUserNumber(String userNumber)
	{
		this.userNumber = userNumber;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public Report()
	{
		super();
		this.commandLength = COMMAND_LENGTH;
	}
	
	private void calcute()
	{
		
	}
	
	@Override
	public byte[] getByteData()
	{
		calcute();
		//不需要发送Report
		return super.getByteData();
	}
	
	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		if(source != null && source.length >= COMMAND_LENGTH)
		{
			SGIPUtils.copyBytes(source, this.submitSequenceNumber, 1, 12, 1);
			SGIPUtils.copyBytes(source, this.reportTypeByte, 13, 1, 1);
			SGIPUtils.copyBytes(source, this.userNumberByte, 14, 21, 1);
			SGIPUtils.copyBytes(source, this.stateByte, 35, 1, 1);
			SGIPUtils.copyBytes(source, this.errorCodeByte, 36, 1, 1);
			
			this.reportType = SGIPUtils.convertUnsignByte2Int(this.reportTypeByte[0]);
			this.userNumber = new String(this.userNumberByte);
			this.state = SGIPUtils.convertUnsignByte2Int(this.stateByte[0]);
			this.errorCode = SGIPUtils.convertUnsignByte2Int(this.errorCodeByte[0]);
		}else
		{
			logger.error("init Report properties from bytes error: the bytes not enough");
		}
	}

	@Override
	public String toString()
	{
		return "Report [submitSequenceNumberStr=" + getSubmitSequenceNumberStr()
				+ ", reportType=" + reportType + ", userNumber=" + userNumber
				+ ", state=" + state + ", errorCode=" + errorCode + "]";
	}
	
}
