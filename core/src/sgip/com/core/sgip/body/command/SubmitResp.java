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

import com.core.sgip.body.SGIPCommand;
import com.core.sgip.util.SGIPUtils;

public class SubmitResp extends SGIPCommand {

	private static final int COMMAND_LENGTH = 9;
	
	private int result;
	
	private byte[] resultByte = new byte[1];
	
	
	private byte[] reserve = new byte[8];

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public SubmitResp(int result)
	{
		super();
		this.result = result;
		this.commandLength = COMMAND_LENGTH;
	}
	
	public SubmitResp()
	{
		this(0);
	}
	
	@Override
	public byte[] getByteData()
	{
		this.resultByte = SGIPUtils.convertInt2Bytes(this.result);
		return SGIPUtils.mergeBytes(this.resultByte,this.reserve);
	}
	
	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		SGIPUtils.copyBytes(source, this.resultByte, 1, 1, 1);
		this.result =  SGIPUtils.convertUnsignByte2Int(this.resultByte[0]);
	}

	@Override
	public String toString()
	{
		return "SubmitResp [result=" + result + "]";
	}
}
