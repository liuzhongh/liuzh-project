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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.core.sgip.SGIPMsg;
import com.core.sgip.body.command.Deliver;
import com.core.sgip.body.command.Report;
import com.core.sgip.constant.ClientConstant;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.factory.SGIPFactory;
import com.core.sgip.head.SGIPMsgHead;
import com.core.sgip.interf.MessageHandler;

public class SGIPServer {

	private static Logger		logger			= Logger.getLogger(SGIPServer.class);

	private MessageHandler		messageHandler	= ClientConstant.SGIP_MSG_HANDLER;

	private static SGIPServer	instance		= new SGIPServer();

	public static SGIPServer getInstance()
	{
		return instance;
	}

	public void startSGIPService()
	{
		try
		{
			logger.debug("*********startSGIPService ");
			ServerSocket server = new ServerSocket(
					ClientConstant.LOCALHOST_SGIP_PORT);
			while (true)
			{
				Socket socket = server.accept();
				new Thread(new ConnectSocketHandler(socket, messageHandler))
						.start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void startNIOSGIPService()
	{
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try
		{
			logger.debug("*********startNIOSGIPService ");
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(
					new InetSocketAddress(ClientConstant.LOCALHOST_SGIP_PORT));

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true)
			{
				if (selector.select() == 0)
				{
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator(); 
				while(iter.hasNext())
				{
					SelectionKey key = (SelectionKey)iter.next();  
                    iter.remove();
                    this.handleKey(key);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void handleKey(SelectionKey key) throws IOException
	{
		if (key.isAcceptable())
		{
			// 得到与客户端的套接字通道
			SocketChannel channel = ((ServerSocketChannel) key
					.channel()).accept();
			channel.configureBlocking(false);
			channel.register(key.selector(), SelectionKey.OP_READ);
		}
		if (key.isReadable())
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			SocketChannel socketChannel = (SocketChannel) key.channel();
			socketChannel.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			try
			{
				byte[] bytes;
				int size = 0;
				while ((size = socketChannel.read(buffer)) >= 0)
				{
					buffer.flip();
					bytes = new byte[size];
					buffer.get(bytes);
					baos.write(bytes);
					buffer.clear();
				}
				bytes = baos.toByteArray();
				
				SGIPMsg sgipMsg = SGIPFactory.constructSGIPMsg(bytes);
				logger.debug("receive sgipMsg:" + sgipMsg);
				SGIPMsgHead head = sgipMsg.getHead();
				if(null != head)
				{
					long commandId = head.getCommandId();
					if(SGIPConstant.SGIP_BIND == commandId)
					{
						logger.debug("******************* receive bind *******************");
						key.attach(SGIPConstant.SGIP_BIND_RESP);
					}else if(SGIPConstant.SGIP_DELIVER == commandId)
					{
						logger.debug("******************* receive deliver *******************");
						messageHandler.handleDeliverMessage(head, (Deliver)sgipMsg.getCommand());
						key.attach(SGIPConstant.SGIP_DELIVER_RESP);
					}else if(SGIPConstant.SGIP_REPORT == commandId)
					{
						logger.debug("******************* receive report *******************");
						messageHandler.handleReportMessage(head, (Report)sgipMsg.getCommand());
						key.attach(SGIPConstant.SGIP_REPORT_RESP);
					}else if(SGIPConstant.SGIP_UNBIND == commandId)
					{
						logger.debug("******************* receive unbind *******************");
						key.attach(SGIPConstant.SGIP_UNBIND_RESP);
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally{
				baos.close();
				if(buffer != null)
				{
					buffer = null;
				}
			}
			key.interestOps(SelectionKey.OP_WRITE);
		}
		if (key.isWritable())
		{
			boolean isClose = false;
			SocketChannel channel = (SocketChannel) key.channel();
			channel.configureBlocking(false);
			SGIPMsg sgipMsg= null;
			try
			{
				long sendCommandId = (Long) key.attachment();
				if (SGIPConstant.SGIP_BIND_RESP == sendCommandId)
				{
					sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_BIND_RESP);
					logger.debug("******************* send bind resp *******************sgipMsg="+sgipMsg);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					channel.write(block);
				}else if(SGIPConstant.SGIP_DELIVER_RESP == sendCommandId)
				{
					sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_DELIVER_RESP);
					logger.debug("******************* send deliver resp *******************sgipMsg="+sgipMsg);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					channel.write(block);
				}else if(SGIPConstant.SGIP_REPORT_RESP == sendCommandId)
				{
					sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_REPORT_RESP);
					logger.debug("******************* send report resp *******************sgipMsg="+sgipMsg);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					channel.write(block);
				}else if(SGIPConstant.SGIP_UNBIND_RESP == sendCommandId)
				{
					sgipMsg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_UNBIND_RESP);
					logger.debug("******************* send unbind resp *******************sgipMsg="+sgipMsg);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					channel.write(block);
					isClose = true;
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			if(isClose)
			{
				channel.close();
				return;
			}else
			{
				key.interestOps(SelectionKey.OP_READ);
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		getInstance().startNIOSGIPService();
	}

}
