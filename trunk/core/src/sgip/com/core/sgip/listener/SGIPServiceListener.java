/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-22
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.listener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.core.sgip.server.SGIPServer;

public class SGIPServiceListener extends HttpServlet {

	private static final String VALUE_YES = "Y";
	
	private static final long	serialVersionUID	= 1L;

	private static Logger	logger	= Logger.getLogger(SGIPServiceListener.class);
	
	@Override
	public void init() throws ServletException
	{
		try
		{
			String start = getInitParameter("start");
			String nio = getInitParameter("nio");
			logger.debug("************* sgip service start param = " + start + ";nio=" + nio);
			if (VALUE_YES.equalsIgnoreCase(start))
			{
				if(VALUE_YES.equalsIgnoreCase(nio))
				{
					SGIPServer.getInstance().startNIOSGIPService();
				}else
				{
					SGIPServer.getInstance().startSGIPService();
				}
			}
		} catch (Exception e)
		{
			logger.error(e);
		}
	}
}
