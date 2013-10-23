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

public class UnBindResp extends SGIPCommand {

	private static final int COMMAND_LENGTH = 0;
	
	public UnBindResp()
	{
		super();
		this.commandLength = COMMAND_LENGTH;
	}
	
	@Override
	public byte[] getByteData()
	{
		return new byte[this.commandLength];
	}
	
	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		
	}
}
