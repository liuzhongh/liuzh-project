/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.interf;

import org.apache.log4j.Logger;

import com.core.sgip.body.command.Deliver;
import com.core.sgip.body.command.Report;
import com.core.sgip.body.command.Submit;
import com.core.sgip.head.SGIPMsgHead;
import com.core.sgip.server.ConnectSocketHandler;

public class DefaultMessageHandler implements MessageHandler {

	private static Logger logger = Logger.getLogger(DefaultMessageHandler.class);
	
	@Override
	public void handleDeliverMessage(SGIPMsgHead head ,Deliver deliver)
	{
		logger.debug("handle message recieve sgipMsgHead is " + head + " deliver detail:" + deliver);
		
		
	}

	@Override
	public void handleReportMessage(SGIPMsgHead head ,Report report)
	{
		logger.debug("handle message recieve sgipMsgHead is " + head + " report detail:" + report);
		
		
	}
	
	@Override
	public void handleSubmitMessage(SGIPMsgHead head, Submit submit)
	{
		logger.debug("handle message send sgipMsgHead is " + head + " submit detail:" + submit);
	}

}
