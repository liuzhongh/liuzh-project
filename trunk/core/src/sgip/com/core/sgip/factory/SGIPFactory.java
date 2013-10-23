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
package com.core.sgip.factory;

import java.io.IOException;

import com.core.sgip.SGIPMsg;
import com.core.sgip.body.SGIPCommand;
import com.core.sgip.body.command.Bind;
import com.core.sgip.body.command.BindResp;
import com.core.sgip.body.command.Deliver;
import com.core.sgip.body.command.DeliverResp;
import com.core.sgip.body.command.Report;
import com.core.sgip.body.command.ReportResp;
import com.core.sgip.body.command.Submit;
import com.core.sgip.body.command.SubmitResp;
import com.core.sgip.body.command.UnBind;
import com.core.sgip.body.command.UnBindResp;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.head.SGIPMsgHead;

public class SGIPFactory {

	public static SGIPMsg constructSGIPMsg(byte[] source) throws IOException
	{
		SGIPMsg sgipMsg = new SGIPMsg();
		if(source != null && source.length >= 20)
		{
			sgipMsg.initPropertiesByBytes(source);
		}
		return sgipMsg;
	}

	public static SGIPMsg getSGIPMsg(long commandId) throws Exception
	{
		SGIPMsg sgipMsg = new SGIPMsg();
		SGIPMsgHead head = new SGIPMsgHead(commandId);
		sgipMsg.setHead(head);
		SGIPCommand command = getCommandByCommandId(commandId);
		sgipMsg.setCommand(command);
		return sgipMsg;
	}

	public static SGIPCommand getCommandByCommandId(long commandId) throws Exception
	{
		SGIPCommand command = null;
		if (SGIPConstant.SGIP_BIND == commandId)
		{
			command = new Bind();
		} else if (SGIPConstant.SGIP_BIND_RESP == commandId)
		{
			command = new BindResp();
		} else if (SGIPConstant.SGIP_SUBMIT == commandId)
		{
			command = new Submit();
		} else if (SGIPConstant.SGIP_SUBMIT_RESP == commandId)
		{
			command = new SubmitResp();
		} else if (SGIPConstant.SGIP_DELIVER == commandId)
		{
			command = new Deliver();
		}else if (SGIPConstant.SGIP_DELIVER_RESP == commandId)
		{
			command = new DeliverResp();
		}else if (SGIPConstant.SGIP_REPORT == commandId)
		{
			command = new Report();
		} else if (SGIPConstant.SGIP_REPORT_RESP == commandId)
		{
			command = new ReportResp();
		} else if (SGIPConstant.SGIP_UNBIND == commandId)
		{
			command = new UnBind();
		} else if (SGIPConstant.SGIP_UNBIND_RESP == commandId)
		{
			command = new UnBindResp();
		}else
		{
			throw new Exception("command is not exist ; commandId:" + commandId);
		}
		return command;
	}

}
