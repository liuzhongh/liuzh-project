/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-17
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.body.command;

import com.core.sgip.body.SGIPCommand;
import com.core.sgip.constant.ClientConstant;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.util.SGIPUtils;

public class Bind extends SGIPCommand {
	
	private static final int COMMAND_LENGTH = 41;
	
	private int loginType;
	
	private String loginName = ClientConstant.LOGIN_NAME;
	
	private String loginPassWord = ClientConstant.LOGIN_PWD;
	
	private byte[] loginTypeByte = new byte[1];
	
	private byte[] loginNameByte = new byte[16];
	
	private byte[] loginPwdByte = new byte[16];
	
	
	private byte[] reserve = new byte[8];

	public int getLoginType()
	{
		return loginType;
	}

	public void setLoginType(int loginType)
	{
		this.loginType = loginType;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getLoginPassWord()
	{
		return loginPassWord;
	}

	public void setLoginPassWord(String loginPassWord)
	{
		this.loginPassWord = loginPassWord;
	}

	public Bind()
	{
		this(SGIPConstant.LOGIN_TYPE_SP_SMG);
	}

	public Bind(int loginType)
	{
		super();
		this.loginType = loginType;
		this.commandLength = COMMAND_LENGTH;
	}
	
	@Override
	public byte[] getByteData()
	{
		convertProperties2bytes();
		return SGIPUtils.mergeBytes(this.loginTypeByte,this.loginNameByte,this.loginPwdByte,this.reserve);
	}
	
	private void convertProperties2bytes()
	{
		byte[] source = this.loginName.getBytes();
		int length = source.length;
		SGIPUtils.copyBytes(source, this.loginNameByte, 1, length, 1);
		byte[] pwdSource = this.loginPassWord.getBytes();
		int pwdLength = pwdSource.length;
		SGIPUtils.copyBytes(pwdSource, this.loginPwdByte, 1, pwdLength, 1);
		
		this.loginTypeByte[0] = SGIPUtils.convertInt2Byte(this.loginType); 
	}
	
	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		//接收联通发过来的bind暂时不需要实现 只需根据 head的commandId做判断
		super.initPropertiesByBytes(source);
	}
	
}
