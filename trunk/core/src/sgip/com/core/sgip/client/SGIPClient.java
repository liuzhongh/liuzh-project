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
package com.core.sgip.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.core.sgip.SGIPMsg;
import com.core.sgip.body.command.BindResp;
import com.core.sgip.body.command.Submit;
import com.core.sgip.body.command.SubmitResp;
import com.core.sgip.constant.ClientConstant;
import com.core.sgip.constant.SGIPConstant;
import com.core.sgip.factory.SGIPFactory;
import com.core.sgip.interf.MessageHandler;

public class SGIPClient {

	private static Logger	logger	= Logger.getLogger(SGIPClient.class);

	private static MessageHandler messageHandler = ClientConstant.SGIP_MSG_HANDLER;
	
	public static void sendMsg(List<String> listUserNumber, String content) throws Exception
	{
		// 开始通信
		if (ClientConstant.IS_NIO.equalsIgnoreCase("y"))
		{
			sendMsg(listUserNumber, content, true);
		} else
		{
			sendMsg(listUserNumber, content, false);
		}
	}
	
	/**
	 * 发送消息
	 * 
	 * @param listUserNumber
	 * @param content
	 * @throws Exception 
	 */
	public static void sendMsg(List<String> listUserNumber, String content,boolean isNIO) throws Exception
	{
		if (null == listUserNumber || 0 == listUserNumber.size()
				|| null == content || 0 == content.length())
		{
			return;
		}
		// 验证号码是否前面有86
		for (int i = 0 ; i < listUserNumber.size(); i++)
		{
			String un = listUserNumber.get(i);
			if(!un.startsWith("86"))
			{
				listUserNumber.set(i, "86" + un);
			}
		}
		if(isNIO)
		{
			startNioCommu(listUserNumber, content);
		}else
		{
			startCommunication(listUserNumber, content);
		}
	}
	
	
	private static void startNioCommu(List<String>listUserNumber,String content) throws Exception
	{
		int size = listUserNumber.size();
		int multi = 1;
		int fromIndex = 0;
		int toIndex = size;
		if(size > ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER)
		{
			multi = size / ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER + (size % ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER > 0 ? 1 : 0);
		}
		for(int i = 0; i < multi; i++)
		{
			toIndex = fromIndex + ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER;
			toIndex = toIndex > size ? size : toIndex;
			List<String> tempUserNumbers = listUserNumber.subList(fromIndex, toIndex);
			fromIndex = (i+1) * ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER;
			startNIOCommunication(tempUserNumbers, content);
			logger.debug("------------------------------");
		}
	}

	private static boolean headKey(SelectionKey key ,List<String> listUserNumber,String content) throws Exception
	{
		boolean quit = false;
		try
		{
			if (key.isConnectable())
			{
				SocketChannel socketChannel = (SocketChannel) key.channel();
				// 由于非阻塞模式，connect只管发起连接请求，finishConnect()方法会阻塞到链接结束并返回是否成功
				// 另外还有一个isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
				if (socketChannel.finishConnect())
				{
					// 链接成功了可以做一些自己的处理，略
					logger.debug("********* nio socket connect success **********");
					// 处理完后必须吧OP_CONNECT关注去掉，改为关注OP_READ
					key.interestOps(SelectionKey.OP_WRITE);
					key.attach(SGIPConstant.SGIP_BIND);
				}
			}
			if (key.isReadable())
			{
				logger.debug("****************nio socket into readable ********");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				SocketChannel socketChannel = (SocketChannel) key.channel();
				socketChannel.configureBlocking(false);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				try
				{
					byte[] bytes;
					int size = socketChannel.read(buffer);
					if (size >= 0)
					{
						buffer.flip();
						bytes = new byte[size];
						buffer.get(bytes);
						baos.write(bytes);
						buffer.clear();
					}
					bytes = baos.toByteArray();

					int result = 0;

					long receiveCommandId = (Long) key.attachment();
					if (receiveCommandId == SGIPConstant.SGIP_BIND_RESP)
					{
						SGIPMsg returnMsg = SGIPFactory.constructSGIPMsg(bytes);
						logger.debug("*********end receive bindResp*********returnMsg="
								+ returnMsg);
						if (null != returnMsg.getCommand()
								&& ((BindResp) returnMsg.getCommand())
										.getResult() == 0)
						{
							key.attach(SGIPConstant.SGIP_SUBMIT);
						} else
						{
							BindResp br = (BindResp) returnMsg.getCommand();
							result = br.getResult();
							logger.debug("****************** bindResp's result:	"
									+ result);
							String errorMsg = SGIPConstant.ERROR_CODE
									.get(result + "");
							logger.error("错误消息:" + errorMsg);
							quit = true;
							throw new Exception(errorMsg);
						}
					} else if (receiveCommandId == SGIPConstant.SGIP_SUBMIT_RESP)
					{
						SGIPMsg returnMsg = SGIPFactory.constructSGIPMsg(bytes);
						logger.debug("*********end receive submitResp*********returnMsg="
								+ returnMsg);
						if (null != returnMsg.getCommand()
								&& ((SubmitResp) returnMsg.getCommand())
										.getResult() == 0)
						{
							key.attach(SGIPConstant.SGIP_UNBIND);//判断集合是否处理完成 没有完成继续发送
						} else
						{
							SubmitResp br = (SubmitResp) returnMsg.getCommand();
							result = br.getResult();
							logger.debug("****************** SubmitResp's result:	"
									+ result);
							String errorMsg = SGIPConstant.ERROR_CODE
									.get(result + "");
							logger.error("错误消息:" + errorMsg);
							quit = true;
							throw new Exception(errorMsg);
						}
					} else if (receiveCommandId == SGIPConstant.SGIP_UNBIND_RESP)
					{
						SGIPMsg returnMsg = SGIPFactory.constructSGIPMsg(bytes);
						logger.debug("*********end receive unbindResp*********returnMsg="
								+ returnMsg);
					}
					if (receiveCommandId != SGIPConstant.SGIP_UNBIND_RESP)
					{
						key.interestOps(SelectionKey.OP_WRITE);
					} else
					{
						quit = true;
						socketChannel.close();
					}
					logger.debug("********* quit=" + quit);
				} catch (Exception e)
				{
					quit = true;
					logger.error("Error", e);
					throw e;
				} finally
				{
					baos.close();
					if (buffer != null)
					{
						buffer = null;
					}
				}
			}
			if (quit)
			{
				return quit;
			}
			if (key.isWritable())
			{
				logger.debug("****************nio socket into writable ********");
				SocketChannel socketChannel = (SocketChannel) key.channel();
				socketChannel.configureBlocking(false);

				long sendCommandId = (Long) key.attachment();
				if (sendCommandId == SGIPConstant.SGIP_BIND)
				{
					SGIPMsg sgipMsg = SGIPFactory
							.getSGIPMsg(SGIPConstant.SGIP_BIND);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					logger.debug("*********send bind *********sgipMsg="
							+ sgipMsg);
					socketChannel.write(block);
					key.attach(SGIPConstant.SGIP_BIND_RESP);
				} else if (sendCommandId == SGIPConstant.SGIP_SUBMIT)
				{
					SGIPMsg sgipMsg = SGIPFactory
							.getSGIPMsg(SGIPConstant.SGIP_SUBMIT);
					sgipMsg.setUserNumbers(listUserNumber, content);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					logger.debug("*********send submit *********sgipMsg="
							+ sgipMsg);
					socketChannel.write(block);
					key.attach(SGIPConstant.SGIP_SUBMIT_RESP);
					messageHandler.handleSubmitMessage(sgipMsg.getHead(),
							(Submit) sgipMsg.getCommand());
				} else if (sendCommandId == SGIPConstant.SGIP_UNBIND)
				{
					SGIPMsg sgipMsg = SGIPFactory
							.getSGIPMsg(SGIPConstant.SGIP_UNBIND);
					ByteBuffer block = ByteBuffer.wrap(sgipMsg.getByteData());
					logger.debug("*********send unbind *********sgipMsg="
							+ sgipMsg);
					socketChannel.write(block);
					key.attach(SGIPConstant.SGIP_UNBIND_RESP);
				}
				key.interestOps(SelectionKey.OP_READ);
			}
		} catch (Exception e)
		{
			logger.error("Error", e);
			quit = true;
			throw e;
		}
		return quit;
	}
	
	private static void startNIOCommunication(List<String> listUserNumber,String content) throws Exception
	{
		Selector selector = Selector.open();
		// 创建一个套接字通道，注意这里必须使用无参形式
		SocketChannel channel = SocketChannel.open();
		// 设置为非阻塞模式，这个方法必须在实际连接之前调用(所以open的时候不能提供服务器地址，否则会自动连接)
		channel.configureBlocking(false);
		// 连接服务器，由于是非阻塞模式，这个方法会发起连接请求，并直接返回false(阻塞模式是一直等到链接成功并返回是否成功)
		channel.connect(new InetSocketAddress(ClientConstant.SERVER_IP,Integer.valueOf(ClientConstant.SERVER_PORT)));
		// 注册关联链接状态
		channel.register(selector, SelectionKey.OP_CONNECT);
		boolean quit = false;
		try
		{
			while(!quit)
			{
				selector.select();
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext())
				{
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					quit = headKey(key, listUserNumber, content);
				}
			}
		} catch (Exception e)
		{
			logger.error("Error", e);
			throw e;
		} finally
		{
			channel.close();
		}
	}
	
	
	private static void startCommunication(List<String> listUserNumber,
			String content) throws Exception
	{
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try{
			logger.debug("*********start build socket=" + ClientConstant.SERVER_IP + ":" + ClientConstant.SERVER_PORT);
			socket = new Socket(ClientConstant.SERVER_IP,
					Integer.valueOf(ClientConstant.SERVER_PORT));
			logger.debug("********* builded socket=" + socket);
			socket.setKeepAlive(true);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			// 发送bind
			logger.debug("*********send bind start*********");
			SGIPMsg msg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_BIND);
			byte[] dataByte = msg.getByteData();
			os.write(dataByte);
			os.flush();
			
			logger.debug("*********send bind end and start receive bindResp*********");
			// 接受bindResp
			SGIPMsg returnMsg = SGIPFactory.constructSGIPMsg(getAvailableBytes(is));
			logger.debug("*********end receive bindResp*********returnMsg=" + returnMsg);
			if (returnMsg != null && returnMsg.getHead() != null 
					&& returnMsg.getHead().getCommandId() == SGIPConstant.SGIP_BIND_RESP);
			{
				BindResp br = (BindResp) returnMsg.getCommand();
				if (null != br)
				{
					int result = br.getResult();
					logger.debug("****************** bindResp's result:	" + result);
					if (0 != result)
					{
						String errorMsg = SGIPConstant.ERROR_CODE.get(result + "");
						logger.error("错误消息:" + errorMsg);
						throw new Exception(errorMsg);
					} else
					{
						int size = listUserNumber.size();
						int multi = 1;
						int fromIndex = 0;
						int toIndex = size;
						if(size > ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER)
						{
							multi = size / ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER + (size % ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER > 0 ? 1 : 0);
						}
						boolean sendSubmitOver = false;
						for(int i = 0; i < multi; i++)
						{
							toIndex = fromIndex + ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER;
							toIndex = toIndex > size ? size : toIndex;
							List<String> tempUserNumbers = listUserNumber.subList(fromIndex, toIndex);
							fromIndex = (i+1) * ClientConstant.SGIP_SUBMIT_MAX_USER_NUMBER;
							// bind成功 发送Submit
							logger.debug("*********bind success start send submit*******listNumberSize:" + tempUserNumbers.size());
							msg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_SUBMIT);
							msg.setUserNumbers(tempUserNumbers, content);
							dataByte = msg.getByteData();
							logger.debug("send Submitmsg" + msg);
							//处理Submit
							messageHandler.handleSubmitMessage(msg.getHead(), (Submit)msg.getCommand());
							
							os.write(dataByte);
							os.flush();
							logger.debug("*********end send submit start receive submitResp*********");
							returnMsg = SGIPFactory
									.constructSGIPMsg(getAvailableBytes(is));
							logger.debug("************returnMsg=" + returnMsg);
							if (returnMsg != null && returnMsg.getHead() != null && returnMsg.getHead().getCommandId() == SGIPConstant.SGIP_SUBMIT_RESP)
							{
								SubmitResp sr = (SubmitResp) returnMsg.getCommand();
								if (null != sr)
								{
									result = sr.getResult();
									logger.debug("****************** submitResp's result:	"
											+ result);
									if (0 != result)
									{
										String errorMsg = SGIPConstant.ERROR_CODE.get(result + "");
										logger.error("错误消息:" + errorMsg);
										throw new Exception(errorMsg);
									} else
									{
										logger.debug("*********submit success *********");
									}
								}
							}
							if((i+1)  == multi)
							{
								sendSubmitOver  = true;
							}
						}
						if(sendSubmitOver)
						{
							//发送UnBind
							logger.debug("*********submit all send success start send Unbind *********");
							{
								msg = SGIPFactory.getSGIPMsg(SGIPConstant.SGIP_UNBIND);
								dataByte = msg.getByteData();
								os.write(dataByte);
								os.flush();
								logger.debug("*********end send Unbind start receive UnbindResp*********");
								returnMsg = SGIPFactory
										.constructSGIPMsg(getAvailableBytes(is));
								logger.debug("************returnMsg=" + returnMsg);
								if (returnMsg != null && returnMsg.getHead() != null 
										&& returnMsg.getHead().getCommandId() == SGIPConstant.SGIP_UNBIND_RESP)
								{
									logger.debug("*********Unbind success *********");
								}
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}finally
		{
			if(os != null)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					logger.error("Error", e);
				}
			}
			if(is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					logger.error("Error", e);
				}
			}
			if(null != socket)
			{
				logger.debug("*********release socket=" + ClientConstant.SERVER_IP + ":" + ClientConstant.SERVER_PORT);
				try
				{
					socket.close();
				} catch (IOException e)
				{
					logger.error("Error", e);
				}
			}
		}
	}

	public static byte[] getAvailableBytes(InputStream inputStream)
			throws Exception
	{
		Calendar cal = Calendar.getInstance();
		long startMillis = cal.getTimeInMillis();
		long endMillis = startMillis;
		int timeout = ClientConstant.RESPONSE_TIMEOUT;
		
		int count = 0;
		while (count == 0 && (endMillis - startMillis) <= timeout*1000)
		{
			endMillis = Calendar.getInstance().getTimeInMillis();
			count = inputStream.available();
		}
		if(count == 0 && (endMillis - startMillis) > timeout*1000)
		{
			throw new Exception("read server response time out !");
		}
		byte[] bytes = new byte[count];
		int readCount = 0; // 已经成功读取的字节的个数
		while (readCount < count)
		{
			readCount += inputStream.read(bytes, readCount, count - readCount);
		}
		return bytes;
	}
	
	public static void main(String[] args) throws Exception
	{
		List<String> listUserNumber = new ArrayList<String>();
		listUserNumber.add("18508429828");
		//listUserNumber.add("15074814855");
		sendMsg(listUserNumber, "just test test药交网http://yj.3kw.com短信发送测试-NIO方式 ",true);
		sendMsg(listUserNumber, "just test test药交网http://yj.3kw.com短信发送测试-SOCKET方式 ",false);
	}
	
	public static void sendTestMsg() throws Exception
	{
		List<String> listUserNumber = new ArrayList<String>();
		listUserNumber.add("18508429828");
		sendMsg(listUserNumber, "just test test药交网http://yj.3kw.com短信测试");
	}
}