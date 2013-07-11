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

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public interface IMailContext {

	/**
	 * 创建邮件内容
	 * @return
	 * @throws MessagingException
	 */
	public abstract MimeBodyPart[] createMimeBodyParts() throws MessagingException;

	/**
	 * 创建多个邮件内容
	 * @return
	 * @throws MessagingException
	 */
	public abstract MimeBodyPart createMimeBodyPart() throws MessagingException;

	/**
	 * 创建邮件主题
	 * @return
	 */
	public abstract String createSubject();

	/**
	 * 邮件收件人
	 * @return
	 */
	public abstract String createRecipient();

	/**
	 * 多位邮件收件人
	 * @return
	 */
	public abstract String[] createRecipients();
	
	/**
	 * 邮件抄送人
	 * @return
	 */
	public abstract String[] createCCRecipients();
	
	/**
	 * 邮件暗送人
	 * @return
	 */
	public abstract String[] createBCCRecipients();

	/**
	 * 邮件发件人
	 * @return
	 */
	public abstract String createFrom();

}
