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
package com.core.cache;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangkang.tools.UtilHelper;

public class BaseRuleValidated {

	private static Log			log							= LogFactory.getLog(BaseRuleValidated.class);
	
	public static boolean validateRequestUri(String url, List<String> regexList)
	{
		if(UtilHelper.isEmpty(regexList) || UtilHelper.isEmpty(url))
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
	
	public Rule validateRequestUri(String url, Map<String, Rule> ruleMap)
	{
		if(UtilHelper.isEmpty(ruleMap))
			return null;
		Pattern pattern;
		Matcher matcher;
		String key;
		Rule rule;
		
		for(Entry<String, Rule> regexEntry : ruleMap.entrySet())
		{
			key = regexEntry.getKey();
			rule = regexEntry.getValue();
			
			pattern = Pattern.compile(key);
		
			matcher = pattern.matcher(url);
			
			if(matcher.find())
			{
				return rule;
			}
			
		}
		return null;
	}
	
	public boolean validateRequestUri(String url, String forwardUri, List<String> regexList)
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
			
			if(!UtilHelper.isEmpty(forwardUri))
			{
				matcher = pattern.matcher(forwardUri);
				
				if(matcher.find())
					return true;
			}
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
	
	public boolean validateIncludeUri(String includeUri, String to)
	{
		Pattern pattern = Pattern.compile(to);
        Matcher matcher = pattern.matcher(includeUri);
        
        return matcher.find(0);
	}
	
	public String replacementUri(String queryString, String includeUri, String from, String to)
	{
		Pattern pattern = Pattern.compile(to);
        Matcher matcher = pattern.matcher(includeUri);
        if (!matcher.find(0))
        {
            return from;
        }
        String replacement = replace(matcher, from);
        if (queryString != null) {
            if (replacement.indexOf('?') > 0)
                replacement = replacement + '&' + queryString;
            else
                replacement = replacement + '?' + queryString;
        }
        
        log.debug("replacement :" + replacement);
        
        return replacement;
	}
	
	private String replace(Matcher matcher, String target) {
        StringBuilder cb = new StringBuilder(512);
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch != '$' || i == target.length() - 1)
                cb.append(ch);
            else {
                ch = target.charAt(i + 1);
                if (ch >= '0' && ch <= '9') {
                    int group = ch - '0';
                    cb.append(matcher.group(group));
                    i++;
                }
                else if (ch == '$') {
                    cb.append('$');
                    i++;
                }
                else
                    cb.append('$');
            }
        }
        return cb.toString();
    }
}
