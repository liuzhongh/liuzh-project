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

import com.core.sgip.body.command.Deliver;
import com.core.sgip.body.command.Report;
import com.core.sgip.body.command.Submit;
import com.core.sgip.head.SGIPMsgHead;

public interface MessageHandler {

	/**
	 * 处理联通发过来的消息
	 * @param deliver
	 */
	public void handleDeliverMessage(SGIPMsgHead head ,Deliver deliver);
	
	/**
	 * 处理联通发过来的消息报告
	 * @param report
	 */
	public void handleReportMessage(SGIPMsgHead head ,Report report);
	
	/**
	 * 发送给联通的
	 * @param head
	 * @param submit
	 */
	public void handleSubmitMessage(SGIPMsgHead head,Submit submit);
	
	
}
