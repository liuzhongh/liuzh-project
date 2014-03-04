/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2014年2月25日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.news.services.impl;

import org.apache.thrift.TException;
import org.news.bis.SorlHelper;
import org.news.services.NewsService.Iface;

public class NewsServiceImpl implements Iface {

	@Override
	public String findResult(String keyword) throws TException
	{
		SorlHelper helper = new SorlHelper();
		
		return helper.findResult(keyword);
	}

}
