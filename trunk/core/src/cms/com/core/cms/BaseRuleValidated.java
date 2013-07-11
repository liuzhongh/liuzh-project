/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-10-19
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cms;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangkang.tools.UtilHelper;

public class BaseRuleValidated {

	public boolean validateRequestUri(String url, List<String> regexList)
	{
		if(UtilHelper.isEmpty(regexList))
			return false;
		Pattern pattern;
		Matcher matcher;
		
		for(String regex : regexList)
		{
			pattern = Pattern.compile(regex);
		
			matcher = pattern.matcher(url);
			
			if(matcher.find())
				return true;
		}
		return false;
	}
	
	public boolean validateRequestUri(String matcherStr, String regex)
	{
		if(UtilHelper.isEmpty(regex))
			return false;
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matcherStr);
		
		if(matcher.find())
			return true;
		
		return false;
	}
	
	public void parseRule()
	{
		
	}
}
