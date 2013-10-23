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
package com.core.sgip.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.core.sgip.SGIPMsg;
import com.core.sgip.body.command.Deliver;
import com.core.sgip.body.command.Report;
import com.core.sgip.client.SGIPClient;
import com.core.sgip.constant.ClientConstant;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.factory.SGIPFactory;
import com.core.sgip.head.SGIPMsgHead;
import com.core.sgip.interf.MessageHandler;

public class ConnectSocketHandler implements Runnable {

	private static Logger logger = Logger.getLogger(ConnectSocketHandler.class);
	
	private Socket socket;
	
	private MessageHandler messageHandler;
	
	public ConnectSocketHandler(Socket socket, MessageHandler messageHandler)
	{
		this.socket = socket;
		this.messageHandler = messageHandler;
	}

	@Override
	public void run()
	{
		InetAddress remoteAddress = socket.getInetAddress();
		String remoteIp = remoteAddress.getHostAddress();
		logger.debug("remote ip :" + remoteIp);
		// TODO  看是否需要做联通IP 验证
		if(!ClientConstant.PERMIT_IP.equalsIgnoreCase(remoteIp))
		{
			logger.debug("接受信息时IP不合法");
		}
		
		InputStream is = null;
		OutputStream os = null;
		try
		{
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			logger.debug("start receive command  -- bind");
			SGIPMsg sgipMsg = SGIPFactory.constructSGIPMsg(SGIPClient.getAvailableBytes(is));
			SGIPMsgHead head = sgipMsg.getHead();
			byte[] dataByte = null;
			logger.debug("start receive command head =" + head);
			if(null != head)
			{
				//接受到bind
				long commandId = head.getCommandId();
				if(SGIPConstant.SGIP_BIND == commandId)
				{
					//响应bindResp
					sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_BIND_RESP);
					dataByte = sgipMsg.getByteData();
					os.write(dataByte);
					os.flush();
					
					boolean notUnbind = true;
					while(notUnbind)
					{
						logger.debug("start receive deliver or report");
						sgipMsg = SGIPFactory.constructSGIPMsg(SGIPClient.getAvailableBytes(is));
						head = sgipMsg.getHead();
						logger.debug("end receive deliver or report head =" + head);
						if(null != head)
						{
							commandId = head.getCommandId();
							if(SGIPConstant.SGIP_DELIVER == commandId)
							{
								logger.debug("******************* receive deliver *******************");
								//处理Delivery
								messageHandler.handleDeliverMessage(head, (Deliver)sgipMsg.getCommand());
								
								//响应deliverResp
								sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_DELIVER_RESP);
								os.write(sgipMsg.getByteData());
								os.flush();
							}else if(SGIPConstant.SGIP_REPORT == commandId)
							{
								logger.debug("******************* receive report *******************");
								//处理report
								messageHandler.handleReportMessage(head, (Report)sgipMsg.getCommand());
								
								//响应reportResp
								sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_REPORT_RESP);
								os.write(sgipMsg.getByteData());
								os.flush();
							}else if(SGIPConstant.SGIP_UNBIND == commandId)
							{
								notUnbind = false;
								logger.debug("******************* receive unbind *******************");
								//响应UnbindResp
								sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_UNBIND_RESP);
								os.write(sgipMsg.getByteData());
								os.flush();
							}
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			if(os != null)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if(is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if(null != socket)
			{
				logger.debug("*********release connect socket");
				try
				{
					socket.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}

}
