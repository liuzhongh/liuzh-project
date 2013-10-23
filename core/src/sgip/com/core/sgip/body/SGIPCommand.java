/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-16
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.body;

import com.core.sgip.interf.SGIPByteData;

public class SGIPCommand implements SGIPByteData {
	
	protected int commandLength;
	
	public int getCommandLength()
	{
		return commandLength;
	}
	
	public void setCommandLength(int commandLength)
	{
		this.commandLength = commandLength;
	}

	@Override
	public byte[] getByteData()
	{
		return new byte[]{};
	}

	@Override
	public void initPropertiesByBytes(byte[] source)
	{
		
	}

}
