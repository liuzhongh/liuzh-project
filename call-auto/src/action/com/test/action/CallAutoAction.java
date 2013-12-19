/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年9月29日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.test.action;

import com.shangkang.im.MsgManager;
import com.shangkang.im.constant.Constant;
import com.shangkang.im.singleton.ConfigureSingleton;

public class CallAutoAction {

	public void callPhone(String chatUser, String phoneNo)
	{
		MsgManager manager = new MsgManager();
		
		chatUser = (chatUser == null || chatUser.trim().equals("")) ? (String) ConfigureSingleton.getSingleton().getValue(Constant.DEFAULT_TO_NAME) : chatUser;
		
		manager.callPhone(chatUser, phoneNo);
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
		MsgManager manager = new MsgManager();
		
		return manager.regist(username, password, aliases, email);
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
		MsgManager manager = new MsgManager();
		
		return manager.changePassword(username, oldPwd, pwd);
	}
}
