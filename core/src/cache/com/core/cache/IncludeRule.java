/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-10-18
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cache;

public class IncludeRule {

	private String to;
	
	private String from;
	
	private boolean fromCaseSensitive;

	/**
	 * @return the to
	 */
	public String getTo()
	{
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to)
	{
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from)
	{
		this.from = from;
	}

	/**
	 * @return the fromCaseSensitive
	 */
	public boolean isFromCaseSensitive()
	{
		return fromCaseSensitive;
	}

	/**
	 * @param fromCaseSensitive the fromCaseSensitive to set
	 */
	public void setFromCaseSensitive(boolean fromCaseSensitive)
	{
		this.fromCaseSensitive = fromCaseSensitive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "IncludeRule [to=" + to + ", from=" + from
				+ ", fromCaseSensitive=" + fromCaseSensitive + "]";
	}

}
