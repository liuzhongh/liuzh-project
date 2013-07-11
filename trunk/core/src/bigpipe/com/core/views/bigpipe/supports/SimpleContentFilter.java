/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-1-9
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.views.bigpipe.supports;


public class SimpleContentFilter implements IContentFilter {

	@Override
	public void filterContent(String source, StringBuffer fiter,
			StringBuffer dest)
	{
//		Pattern pattern = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", Pattern.CASE_INSENSITIVE);
		
		source = source.replace("&", "&amp;").replace("</textarea", "&lt;/textarea");
//		Matcher matcher = pattern.matcher(source);
//		
//		while(matcher.find())
//		{
//			String group = matcher.group();
//			
//			fiter.append(group);
//			matcher.appendReplacement(dest, "");
//		}
//		matcher.appendTail(dest);
		dest.append(source);
	}

}
