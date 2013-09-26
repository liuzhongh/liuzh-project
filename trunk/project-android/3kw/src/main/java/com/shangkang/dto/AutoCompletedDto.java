/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-3-27
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.dto;

import java.io.Serializable;

public class AutoCompletedDto implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public static final int AUTO_COMPLETED_ALERT_COUNT = 10;

	private String	infoId;

	private String	infoKey;

	private String	infoContent;

	public String getInfoId()
	{
		return infoId;
	}

	public void setInfoId(String infoId)
	{
		this.infoId = infoId;
	}

	public String getInfoKey()
	{
		return infoKey;
	}

	public void setInfoKey(String infoKey)
	{
		this.infoKey = infoKey;
	}

	public String getInfoContent()
	{
		return infoContent;
	}

	public void setInfoContent(String infoContent)
	{
		this.infoContent = infoContent;
	}

	public AutoCompletedDto()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "AutoCompletedDto [infoId=" + infoId + ", infoKey=" + infoKey
				+ ", infoContent=" + infoContent + "]";
	}

}
