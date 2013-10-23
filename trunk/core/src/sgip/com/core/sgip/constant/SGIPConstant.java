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
package com.core.sgip.constant;

import java.util.HashMap;
import java.util.Map;

public class SGIPConstant {

	/**
	 * 消息ID定义
	 */
	public static long SGIP_BIND = (long)0x1l;
	
	public static long SGIP_BIND_RESP = (long)0x80000001l;
	
	public static long SGIP_UNBIND = (long)0x2l;
	
	public static long SGIP_UNBIND_RESP = (long) 0x80000002l;
	
	public static long SGIP_SUBMIT = (long) 0x3l;
	
	public static long SGIP_SUBMIT_RESP = (long)0x80000003l;
	
	public static long SGIP_DELIVER = (long)0x4l;
	
	public static long SGIP_DELIVER_RESP = (long)0x80000004l;
	
	public static long SGIP_REPORT = (long)0x5l;
	
	public static long SGIP_REPORT_RESP = (long)0x80000005l;
	
	/**
	 * 消息编码格式
	 */
	public static byte MESSAGE_CODING = 8; 
	
	
	/**
	 * SP向SMG建立的连接，用于发送命令
	 */
	public static int LOGIN_TYPE_SP_SMG = 1;
	
	/**
	 * SMG向SP建立的连接，用于发送命令
	 */
	public static int LOGIN_TYPE_SMG_SP = 2;
	
	/**
	 * SP与SMG以及SMG之间建立的测试连接，用于跟踪测试
	 */
	public static int LOGIN_TYPE_SP_SMG_TEST = 11;
	
	
	public static final Map<String,String> ERROR_CODE = new HashMap<String,String>();
	
}
