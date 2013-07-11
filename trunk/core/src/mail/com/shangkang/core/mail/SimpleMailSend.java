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
package com.shangkang.core.mail;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.resources.MessageResource;
import com.shangkang.tools.PropertyHelper;
import com.shangkang.tools.UtilHelper;

public class SimpleMailSend {

	private Properties emailProperties;
	private IMailContext context;
	private Authenticator authenticator;
	
	public void setIMailContext(IMailContext context)
	{
		this.context = context;
	}
	
	public void setAuthenticator(Authenticator authenticator)
	{
		this.authenticator = authenticator;
	}
	
	public void setEmailProperties(Properties emailProperties)
	{
		this.emailProperties = emailProperties;
	}

	public SimpleMailSend()
	{
		super();
	}

	public SimpleMailSend(Properties emailProperties, IMailContext context,
			Authenticator authenticator)
	{
		super();
		this.emailProperties = emailProperties;
		this.context = context;
		this.authenticator = authenticator;
	}

	public void send() throws ServiceException
	{
		try
		{
			if(emailProperties == null)
			{
				emailProperties = PropertyHelper.getProperty("mail.properties"); // 创建Properties对象
				
				if(null == emailProperties)
					throw new ServiceException(MessageResource.ERROR_MESSAGE_FILE_REQUIRE_ERROR, "mail.properties");
			}
	
			Session session = null;
			
			String needAuth = (String) emailProperties.get("mail.smtp.auth");
			
			if("true".equalsIgnoreCase(needAuth))
			{
				if(authenticator == null)
				{
					String usr = (String) emailProperties.get("authenticator.user");
					String password = (String) emailProperties.get("authenticator.password");
					
					authenticator = new SimpleAuthenticator(usr, password);// 使用验证，创建一个Authenticator
				}
				
				session = Session.getDefaultInstance(emailProperties, authenticator);
			}
			else
			{
				session = Session.getDefaultInstance(emailProperties);
			}
			
			MimeMessage message = createMessage(session);
	
			Transport.send(message);
		}catch(MessagingException e)
		{
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据传入的 Seesion 对象创建混合型的 MIME消息
	 * @throws ServiceException 
	 */
	private MimeMessage createMessage(Session session) throws ServiceException 
	{
		try
		{
			String emailRegex = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
			Pattern regex = Pattern.compile(emailRegex);  
			 
			MimeMessage msg = new MimeMessage(session);
			String from = context.createFrom();
			String subject = context.createSubject();
			String recipient = context.createRecipient();
			String[] recipients = context.createRecipients();
			String[] bccRecipients = context.createBCCRecipients();
			String[] ccRecipients = context.createCCRecipients();
			MimeBodyPart mimeBodyPart = context.createMimeBodyPart();
			MimeBodyPart[] mimeBodyParts = context.createMimeBodyParts();
			
			if(UtilHelper.isEmpty(from))
				throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_FROM_REQUIRE_ERROR);
			
			if(!regex.matcher(from).matches())
				throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_FROM_ERROR, from);
			
			if(UtilHelper.isEmpty(recipient) && UtilHelper.isEmpty(recipients))
				throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_RECIPIENT_REQUIRE_ERROR);
			
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			
			if(!UtilHelper.isEmpty(recipient))
			{
				if(!regex.matcher(recipient).matches())
					throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_RECIPIENT_ERROR, recipient);
				
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}
			
			InternetAddress[] addresses;
			if(!UtilHelper.isEmpty(recipients))
			{
				int length = recipients.length;
				addresses = new InternetAddress[length];
				
				for(int i = 0; i < length; i ++)
				{
					if(!regex.matcher(recipients[i]).matches())
						throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_RECIPIENT_ERROR, recipients[i]);
					
					addresses[i] = new InternetAddress(recipients[i]);
				}
				
				msg.setRecipients(Message.RecipientType.TO, addresses);
			}
			
			if(!UtilHelper.isEmpty(ccRecipients))
			{
				int length = ccRecipients.length;
				InternetAddress[] ccAddresses = new InternetAddress[length];
				
				for(int i = 0; i < length; i ++)
				{
					if(!regex.matcher(ccRecipients[i]).matches())
						throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_CC_RECIPIENT_ERROR, ccRecipients[i]);
					
					ccAddresses[i] = new InternetAddress(ccRecipients[i]);
				}
				
				msg.setRecipients(Message.RecipientType.CC, ccAddresses);
			}
			
			if(!UtilHelper.isEmpty(bccRecipients))
			{
				int length = bccRecipients.length;
				InternetAddress[] bccAddresses = new InternetAddress[length];
				
				for(int i = 0; i < length; i ++)
				{
					if(!regex.matcher(bccRecipients[i]).matches())
						throw new ServiceException(MessageResource.ERROR_MESSAGE_EMAIL_BCC_RECIPIENT_ERROR, bccRecipients[i]);
					
					bccAddresses[i] = new InternetAddress(bccRecipients[i]);
				}
				
				msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
			}
	
			// 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象
			MimeMultipart allPart = new MimeMultipart("mixed");
			
			if(mimeBodyPart != null)
			{
				allPart.addBodyPart(mimeBodyPart);
			}
			
			if(!UtilHelper.isEmpty(mimeBodyParts))
			{
				for(MimeBodyPart part : mimeBodyParts)
				{
					allPart.addBodyPart(part);
				}
			}
	
			// 将上面混合型的 MimeMultipart 对象作为邮件内容并保存
			msg.setContent(allPart);
			msg.saveChanges();
			
			return msg;
		}catch(MessagingException e)
		{
			throw new ServiceException(e);
		}
	}
	
	class SimpleAuthenticator extends Authenticator {
		private String	user;
		private String	pwd;

		public SimpleAuthenticator(String user, String pwd)
		{
			this.user = user;
			this.pwd = pwd;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(user, pwd);
		}
	}
}
