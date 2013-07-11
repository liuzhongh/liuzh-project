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

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.shangkang.core.mail.IMailContext;

public class SimpleMailContext implements IMailContext {

	//发件人
	private String from = "louhtian@163.com";
	
	//多个收件人
	private String[] recipients = new String[]{"liuzhonghua@shangkang.com",
			 "louhtian@yahoo.com.cn"};
	
	//单个收件人
	private String recipient;
	
	//主题
	private String subject = "it is test for mail";

	@Override
	public String createFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	@Override
	public String[] createRecipients()
	{
		return recipients;
	}

	public void setRecipients(String[] recipients)
	{
		this.recipients = recipients;
	}

	@Override
	public String createRecipient()
	{
		return recipient;
	}

	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
	}

	@Override
	public String createSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	@Override
	public MimeBodyPart createMimeBodyPart() throws MessagingException
	{
		MimeBodyPart attachmentPart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource("F:\\release\\Login.swf");
		
		attachmentPart.setDataHandler(new DataHandler(fds));
		attachmentPart.setFileName(fds.getName());
		
		return attachmentPart;
	}
	
	@Override
	public MimeBodyPart[] createMimeBodyParts() throws MessagingException
	{
		MimeBodyPart attachment02 = createAttachment("F:\\release\\Login.html");
		MimeBodyPart content = createContent("it is emailTest", "F:\\work\\workspace\\3kw\\src\\web\\include\\images\\gh1.gif");
		
		return new MimeBodyPart[]{attachment02, content};
	}
	
	/**
	 * 根据传入的文件路径创建附件并返回
	 * @throws MessagingException 
	 */
	private MimeBodyPart createAttachment(String fileName) throws MessagingException
	{
		MimeBodyPart attachmentPart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(fileName);
		attachmentPart.setDataHandler(new DataHandler(fds));
		attachmentPart.setFileName(fds.getName());
		return attachmentPart;
	}
	
	/**
	 * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分
	 * @throws MessagingException 
	 */
	private MimeBodyPart createContent(String body, String fileName) throws MessagingException
	{
		// 用于保存最终正文部分
		MimeBodyPart contentBody = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");

		// 正文的文本部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(body, "text/html;charset=gbk");
		contentMulti.addBodyPart(textBody);

		// 正文的图片部分
		MimeBodyPart jpgBody = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(fileName);
		jpgBody.setDataHandler(new DataHandler(fds));
		jpgBody.setContentID("logo_jpg");
		contentMulti.addBodyPart(jpgBody);

		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		contentBody.setContent(contentMulti);
		return contentBody;
	}

	@Override
	public String[] createCCRecipients()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] createBCCRecipients()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
