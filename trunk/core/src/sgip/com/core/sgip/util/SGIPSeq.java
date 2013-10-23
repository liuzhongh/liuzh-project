/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-17
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.util;
/**
 * 命令循环序号 产生类
 * @author zzqiang
 *
 */
public class SGIPSeq {

	private static long MIN_SEQ = 0;
	
	private static long MAX_SEQ = Integer.MAX_VALUE;
	
	private static long CUR_SEQ = MIN_SEQ;
	
	public static synchronized long getSeq()
	{
		if(CUR_SEQ == MAX_SEQ)
		{
			CUR_SEQ = MIN_SEQ;
		}
		return CUR_SEQ++;
	}
	
}
