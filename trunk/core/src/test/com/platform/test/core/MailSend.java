/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-6-6
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test.core;

import com.shangkang.core.mail.SimpleMailSend;

public class MailSend {

	// 测试生成邮件
	public static void main(String[] args) throws Exception
	{
		SimpleMailSend mail = new SimpleMailSend();

		mail.setIMailContext(new SimpleMailContext());
		
		mail.send();
		
//		String emailRegex = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
//		Pattern regex = Pattern.compile(emailRegex);  
//		
//		System.out.println(regex.matcher("sfsfsf@sfsf.cfdf").matches());
//		System.out.println(regex.matcher("sfsfsf@sfsf@cfdf.bb").matches());
//		System.out.println(regex.matcher("sfsfsf7@8sfsf.10.fd").matches());
//		System.out.println(regex.matcher("").matches());

	}

}

