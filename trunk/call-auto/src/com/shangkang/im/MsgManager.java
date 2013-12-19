/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年10月26日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.im;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;

import com.shangkang.im.constant.Constant;
import com.shangkang.im.helper.ConnectionHelper;
import com.shangkang.im.singleton.ConfigureSingleton;

public class MsgManager {

	private static Log log = LogFactory.getLog(MsgManager.class);
	
	public void callPhone(String chatUser, String phoneNo)
	{
		try
		{
			log.debug("chat user :" + chatUser + " phone no :" + phoneNo);
			ConnectionHelper.getInstance().init();
			XMPPConnection connection = ConnectionHelper.getInstance().getConnection();
			
			connection.connect();
			connection.login((String) ConfigureSingleton.getSingleton().getValue(Constant.DEFAULT_NAME), 
					(String) ConfigureSingleton.getSingleton().getValue(Constant.DEFAULT_PASSWORD));  
			log.debug(connection.getUser());   
			connection.getChatManager().createChat(chatUser + "@" + connection.getServiceName(), null).sendMessage("tel:" + phoneNo); 
		}catch(Exception e)
		{
			log.error(e);
		}finally
		{
			ConnectionHelper.getInstance().disconnect();
		}
	}
	
	/**
	 * 
	 * @param username(注册名）
	 * @param password（密码）
	 * @param aliases（别名，可空）
	 * @param email（邮箱，可空）
	 * @return （1、注册成功 0、服务器没有返回结果2、这个账号已经存在3、注册失败）
	 */
	public String regist(String username, String password, String aliases, String email)
	{
		try
		{
			ConnectionHelper.getInstance().init();
			XMPPConnection connection = ConnectionHelper.getInstance().getConnection();
			
			connection.connect();
			
			Registration reg = new Registration();
			
			reg.setType(IQ.Type.SET);
			reg.setTo(connection.getServiceName());
			
			Map<String, String> paramenter = new HashMap<String, String>();
			paramenter.put("username", username);
			paramenter.put("password", password);
			paramenter.put("name", aliases);
			paramenter.put("email", email);
			
			reg.setAttributes(paramenter);
			
			PacketFilter filter = new AndFilter(new PacketIDFilter(  
	                reg.getPacketID()), new PacketTypeFilter(IQ.class));  
	        PacketCollector collector = connection.createPacketCollector(filter);  
	        
	        connection.sendPacket(reg);
	        
	        IQ result = (IQ) collector.nextResult(SmackConfiguration  
	                .getPacketReplyTimeout());  
	        // Stop queuing results停止请求results（是否成功的结果）  
	        collector.cancel();  
	        
	        if (result == null) {  
	        	log.debug("服务器没有返回结果");
	            return "0";  
	        } else if (result.getType() == IQ.Type.RESULT) {
	        	log.debug("注册成功");
	            return "1";  
	        } else {   
	            if (result.getError().toString().equalsIgnoreCase("conflict(409)")) {  
	            	log.debug("该账号(" + username + ")已经存在, "  
	                        + result.getError().toString());  
	                return "2";  
	            } else {  
	            	log.debug("注册失败，"  
	                        + result.getError().toString());  
	                return "3";  
	            }  
	        }  
		}catch (XMPPException e)
		{
			log.error(e);
			return "3";
		}finally
		{
			ConnectionHelper.getInstance().disconnect();
		}
		
	}
	
	/**
	 * 
	 * @param username(用户名）
	 * @param oldPwd（原始密码）
	 * @param pwd（新密码）
	 * @return
	 */
	public boolean changePassword(String username, String oldPwd, String pwd) 
	{  
		try
		{
			ConnectionHelper.getInstance().init();
			XMPPConnection connection = ConnectionHelper.getInstance().getConnection();
			connection.connect();
			
			connection.login(username, oldPwd);
			connection.getAccountManager().changePassword(pwd);
			return true;
		} catch (XMPPException e)
		{
			log.error(e);
			return false;
		}finally
		{
			ConnectionHelper.getInstance().disconnect();
		}
	}
}
