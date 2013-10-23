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
import java.util.ArrayList;
import java.util.List;

import com.core.sgip.body.SGIPCommand;
import com.core.sgip.constant.ClientConstant;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.util.SGIPUtils;

public class Submit extends SGIPCommand {
	
	private static int COMMAND_FIX_LENGTH = 21 +21 +1 +5 +10 +1 +6 +6 + 3*1 +16 + 16 + 5*1 + 4 + 8;

	private String spNumber = ClientConstant.SP_NUMBER;//SP的接入号码
	
	private String chargeNumber="000000000000000000000";//由SP支付
	
	private int userCount;//接收短消息的手机数量，取值范围1至100
	
	private List<String> listUserNumber;//接收该短消息的手机号，该字段重复UserCount指定的次数，手机号码前加“86”国别标志
	
	private String corpId = ClientConstant.COMPANY_CODE;
	
	private String serviceType="";//业务代码，由SP定义
	
	private int feeType;//计费类型
	
	private String feeValue="";//取值范围0-99999，该条短消息的收费值，单位为分
	
	private String givenValue=""; //取值范围0-99999，赠送用户的话费，单位为分，由SP定义，特指由SP向用户发送广告时的赠送话费
	
	private int agentFlag; //代收费标志，0：应收；1：实收
	
	private int morelatetoMTFlag = 0; //引起MT消息的原因 
	
	private int priority = 0;//优先级0-9从低到高，默认为0
	
	private String expireTime = "";
	
	private String scheduleTime = "";//短消息定时发送的时间，如果为空，表示立刻发送该短消息
	
	private int reportFlag = 0; //状态报告标记 0-该条消息只有最后出错时要返回状态报告
	
	private int tpPid;//GSM协议类型
	
	private int tpUdhi; //GSM协议类型。详细解释请参考GSM03.40中的9.2.3.23,仅使用1位，右对齐
	
	private int messageCoding = SGIPConstant.MESSAGE_CODING; //GBK 短消息的编码格式
	
	private int messageType = 0; //信息类型：0-短消息信息
	
	private Long messageLength; // 短消息的长度 根据消息内容计算
	
	private String messageContent=""; //短消息的内容
	
	private byte[] reserve = new byte[8];//保留，扩展用

	private byte[] spNumberByte = SGIPUtils.convertString2Bytes(this.spNumber, 21);
	
	private byte[] chargeNumberByte = SGIPUtils.convertString2Bytes(this.chargeNumber, 21);
	
	private byte[] userCountByte = new byte[1];
	
	private List<byte[]> listUserNumberByte = new ArrayList<byte[]>();//21*size
	
	private byte[] corpIdByte = SGIPUtils.convertString2Bytes(ClientConstant.COMPANY_CODE, 5);
	
	private byte[] serviceTypeByte = new byte[10];
	
	private byte[] feeTypeByte = new byte[1];
	
	private byte[] feeValueByte = new byte[6];
	
	private byte[] givenValueByte = new byte[6];
	
	private byte[] agentFlagByte = new byte[1];
	
	private byte[] morelatetoMTFlagByte = new byte[1];
	
	private byte[] priorityByte = new byte[1];
	
	private byte[] expireTimeByte = new byte[16];
	
	private byte[] schedualTimeByte = new byte[16];
	
	private byte[] reportFlagByte = new byte[1];
	
	private byte[] tpPidByte = new byte[1];
	
	private byte[] tpUdhiByte = new byte[1];
	
	private byte[] messageCodingByte = new byte[]{SGIPConstant.MESSAGE_CODING};
	
	private byte[] messageTypeByte = new byte[1];
	
	private byte[] messageLengthByte = new byte[4];
	
	private byte[] messageContentByte;
	
	
	public String getSpNumber()
	{
		return spNumber;
	}

	public void setSpNumber(String spNumber)
	{
		this.spNumber = spNumber;
	}

	public String getChargeNumber()
	{
		return chargeNumber;
	}

	public void setChargeNumber(String chargeNumber)
	{
		this.chargeNumber = chargeNumber;
	}

	public int getUserCount()
	{
		return userCount;
	}

	public void setUserCount(int userCount)
	{
		this.userCount = userCount;
	}

	public List<String> getListUserNumber()
	{
		return listUserNumber;
	}

	public void setListUserNumber(List<String> listUserNumber)
	{
		this.listUserNumber = listUserNumber;
	}

	public String getCorpId()
	{
		return corpId;
	}

	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
	}

	public String getServiceType()
	{
		return serviceType;
	}

	public void setServiceType(String serviceType)
	{
		this.serviceType = serviceType;
	}

	public int getFeeType()
	{
		return feeType;
	}

	public void setFeeType(int feeType)
	{
		this.feeType = feeType;
	}

	public String getFreeValue()
	{
		return feeValue;
	}

	public void setFreeValue(String freeValue)
	{
		this.feeValue = freeValue;
	}

	public String getGivenValue()
	{
		return givenValue;
	}

	public void setGivenValue(String givenValue)
	{
		this.givenValue = givenValue;
	}

	public int getAgentFlag()
	{
		return agentFlag;
	}

	public void setAgentFlag(int agentFlag)
	{
		this.agentFlag = agentFlag;
	}

	public int getMorelatetoMTFlag()
	{
		return morelatetoMTFlag;
	}

	public void setMorelatetoMTFlag(int morelatetoMTFlag)
	{
		this.morelatetoMTFlag = morelatetoMTFlag;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	public String getExpireTime()
	{
		return expireTime;
	}

	public void setExpireTime(String expireTime)
	{
		this.expireTime = expireTime;
	}

	public String getScheduleTime()
	{
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime)
	{
		this.scheduleTime = scheduleTime;
	}

	public int getReportFlag()
	{
		return reportFlag;
	}

	public void setReportFlag(int reportFlag)
	{
		this.reportFlag = reportFlag;
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

	public int getMessageType()
	{
		return messageType;
	}

	public void setMessageType(int messageType)
	{
		this.messageType = messageType;
	}

	public Long getMessageLength()
	{
		return messageLength;
	}

	public void setMessageLength(Long messageLength)
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

	public byte[] getReserve()
	{
		return reserve;
	}

	public void setReserve(byte[] reserve)
	{
		this.reserve = reserve;
	}
	
	public Submit()
	{
		this(new ArrayList<String>(),"","");
	}
	
	public Submit(List<String> listUserNumber,String messageContent,String serviceType)
	{
		super();
		this.listUserNumber = listUserNumber;
		this.messageContent = messageContent;
		if(null != serviceType && !"".equals(serviceType) && serviceType.length() > 10)
		{
			serviceType = serviceType.substring(0,10);
		}
		this.serviceType = serviceType;
	}
	
	private void calcute()
	{
			this.expireTimeByte = SGIPUtils.convertString2Bytes(this.expireTime, 16);
			this.schedualTimeByte = SGIPUtils.convertString2Bytes(this.scheduleTime, 16);
			this.feeValueByte = SGIPUtils.convertString2Bytes(this.feeValue, 6);
			this.givenValueByte = SGIPUtils.convertString2Bytes(this.givenValue, 6);
			
			if(null == this.serviceType || this.serviceType == "")
			{
				this.serviceType = ClientConstant.DEFAULT_SERVICE_TYPE;
			}
			
			this.serviceTypeByte = SGIPUtils.convertString2Bytes(this.serviceType, 10);
			
			//短信内容与内容长度
			byte[] mcBytes = null;
			try
			{
				mcBytes = this.messageContent.getBytes("UTF-16");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			this.messageContentByte = mcBytes;
			int length = mcBytes.length;
			this.messageLength = Long.valueOf(length);
			this.messageLengthByte = SGIPUtils.convertLong2FourBytes(this.messageLength);
			//电话号码
			int size = 0;
			if(null != this.listUserNumber)
			{
				size = this.listUserNumber.size();
				this.userCount = size;
				this.userCountByte  = SGIPUtils.convertInt2Bytes(size);
				
				if(size > 0)
				{
					for (String u : listUserNumber)
					{
						byte[] userNumberByte = SGIPUtils.convertString2Bytes(u, 21);
						this.listUserNumberByte.add(userNumberByte);
					}
				}
			}
			this.commandLength = COMMAND_FIX_LENGTH + size * 21 + length;
	}
	
	@Override
	public byte[] getByteData()
	{
		calcute();
		byte[] listUserNumberBytes = SGIPUtils.mergeBytes(this.listUserNumberByte);
		
		return SGIPUtils.mergeBytes(this.spNumberByte,this.chargeNumberByte,this.userCountByte,
				listUserNumberBytes,this.corpIdByte,this.serviceTypeByte,
				this.feeTypeByte,this.feeValueByte,this.givenValueByte,
				this.agentFlagByte,this.morelatetoMTFlagByte,this.priorityByte,
				this.expireTimeByte,this.schedualTimeByte,this.reportFlagByte,
				this.tpPidByte,this.tpUdhiByte,this.messageCodingByte,this.messageTypeByte,
				this.messageLengthByte,this.messageContentByte,this.reserve);
	}
	
	@Override
	public String toString()
	{
		return "Submit [spNumber=" + spNumber + ", chargeNumber="
				+ chargeNumber + ", userCount=" + userCount
				+ ", listUserNumber=" + listUserNumber + ", corpId=" + corpId
				+ ", serviceType=" + serviceType + ", feeType=" + feeType
				+ ", feeValue=" + feeValue + ", givenValue=" + givenValue
				+ ", agentFlag=" + agentFlag + ", morelatetoMTFlag="
				+ morelatetoMTFlag + ", priority=" + priority + ", expireTime="
				+ expireTime + ", scheduleTime=" + scheduleTime
				+ ", reportFlag=" + reportFlag + ", tpPid=" + tpPid
				+ ", tpUdhi=" + tpUdhi + ", messageCoding=" + messageCoding
				+ ", messageType=" + messageType + ", messageLength="
				+ messageLength + ", messageContent=" + messageContent + "]";
	}
	
}
