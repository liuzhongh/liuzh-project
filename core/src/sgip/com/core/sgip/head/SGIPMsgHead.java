/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-14
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.head;

import com.core.sgip.constant.ClientConstant;
import com.core.sgip.interf.SGIPByteData;
import com.core.sgip.util.SGIPSeq;
import com.core.sgip.util.SGIPUtils;
/**
 * 消息头部
 * @author zzqiang
 *
 */
public class SGIPMsgHead implements SGIPByteData{

	public static final int HEAD_LENGTH = 20;
	
	public SGIPMsgHead()
	{
		super();
	}

	public SGIPMsgHead(Long commandId)
	{
		super();
		this.commandId = commandId;
	}
	
	/**
	 * 消息的总长度(字节)
	 */
	private Long messageLength;
	/**
	 * 命令ID
	 */
	private Long commandId;
	
	private byte[] sequenceNumber = new byte[12]; //序列号由 源节点编号+命令产生日期+命令循环序号组成
	
	/**
	 * 源节点编号
	 */
	private Long sourceNodeNumber = Long.valueOf("3"+ClientConstant.AREA_CODE+ClientConstant.COMPANY_CODE);//4字节 为 3AAAAQQQQQ  
	
	/**
	 * 命令产生日期-月日时分秒
	 */
	private Long commandGenerateDate; //4字节
	/**
	 * 命令循环序号
	 */
	private Long commandNumber;//4字节
	
	private String sequenceNumberStr;
	
	
	public String getSequenceNumberStr()
	{
		return String.valueOf(sourceNodeNumber) + String.valueOf(commandGenerateDate) + String.valueOf(commandNumber);
	}

	private Long getSourceNodeNumber()
	{
		return sourceNodeNumber;
	}
	private void setSourceNodeNumber(Long sourceNodeNumber)
	{
		this.sourceNodeNumber = sourceNodeNumber;
	}
	private Long getCommandGenerateDate()
	{
		return commandGenerateDate;
	}
	private void setCommandGenerateDate(Long commandGenerateDate)
	{
		this.commandGenerateDate = commandGenerateDate;
	}
	private Long getCommandNumber()
	{
		return commandNumber;
	}
	private void setCommandNumber(Long commandNumber)
	{
		this.commandNumber = commandNumber;
	}
	public Long getMessageLength()
	{
		return messageLength;
	}
	public void setMessageLength(Long messageLength)
	{
		this.messageLength = messageLength;
	}
	public Long getCommandId()
	{
		return commandId;
	}
	public void setCommandId(Long commandId)
	{
		this.commandId = commandId;
	}
	
	private byte[] getSequenceNumber()
	{
		return sequenceNumber;
	}
	private void setSequenceNumber(byte[] sequenceNumber)
	{
		this.sequenceNumber = sequenceNumber;
	}
	
	/**
	 * 产生命令序列号
	 */
	private void generateSequenceNumber()
	{
		generateDate();
		generateSeq();
		byte[] sourceNodeNumber = SGIPUtils.convertLong2FourBytes(this.sourceNodeNumber);
		byte[] commandDate = SGIPUtils.convertLong2FourBytes(this.commandGenerateDate);
		byte[] commandNum = SGIPUtils.convertLong2FourBytes(this.commandNumber);
		this.sequenceNumber = SGIPUtils.mergeBytes(sourceNodeNumber,commandDate,commandNum);
	}
	
	private void generateDate()
	{
		this.commandGenerateDate = SGIPUtils.getCurrentDate();
	}
	private void generateSeq()
	{
		this.commandNumber = SGIPSeq.getSeq();
	}
	
	@Override
	public byte[] getByteData()
	{
		generateSequenceNumber();
		byte[] commandIdByte = SGIPUtils.convertLong2FourBytes(this.commandId);
		byte[] msgLengthByte = SGIPUtils.convertLong2FourBytes(this.messageLength);
		return SGIPUtils.mergeBytes(msgLengthByte,commandIdByte,this.sequenceNumber);
	}

	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		byte[] messageLengthByte = new byte[4];
		byte[] commandIdByte = new byte[4];
		SGIPUtils.copyBytes(source, messageLengthByte, 1, 4, 1);
		SGIPUtils.copyBytes(source, commandIdByte, 5, 4, 1);
		this.messageLength = SGIPUtils.byte4ToLong(messageLengthByte);
		this.commandId = SGIPUtils.byte4ToLong(commandIdByte);
		SGIPUtils.copyBytes(source, this.sequenceNumber, 9, 12, 1);
		byte[] 	sourceNodeNumberByte  = new byte[4];
		byte[] 	generateDateByte  = new byte[4];
		byte[] 	numberByte  = new byte[4];
		SGIPUtils.copyBytes(this.sequenceNumber, sourceNodeNumberByte, 1, 4, 1);
		SGIPUtils.copyBytes(this.sequenceNumber, generateDateByte, 5, 4, 1);
		SGIPUtils.copyBytes(this.sequenceNumber, numberByte, 9, 4, 1);
		this.sourceNodeNumber = SGIPUtils.byte4ToLong(sourceNodeNumberByte);
		this.commandGenerateDate = SGIPUtils.byte4ToLong(generateDateByte);
		this.commandNumber = SGIPUtils.byte4ToLong(numberByte);
	}

	@Override
	public String toString()
	{
		return "SGIPMsgHead [messageLength=" + messageLength + ", commandId="
				+ commandId + ", sourceNodeNumber=" + sourceNodeNumber
				+ ", commandGenerateDate=" + commandGenerateDate
				+ ", commandNumber=" + commandNumber + "]";
	}
	
}
