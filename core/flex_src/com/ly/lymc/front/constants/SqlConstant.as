/**
 * Create: [2011-01-12] by Daij
 *  Description: 保存表名常量和 SQL 语句常量
 */
package com.shangkang.front.constants
{
	public final class SqlConstant
	{
		public function SqlConstant()
		{
		}
		/********************** 表名       *********************************/
		public static const DJLXFA:String = "djlxfa";
		
		public static const KBXTABLE:String = "kbxtable";
		
		public static const MXTABLE:String = "mxtable";
		
		public static const LINETAB:String = "linetab";
			
		/********************** SQL语句 *********************************/
 		
		// zl_sel_fa表
		public static const SELECT_ZL_SEL_FA:String = "select * from zl_sel_fa where fangabh = ?"
			
		// fldlist表
		public static const SELECT_FLDLIST:String = "select fdname as fieldname,chnname as fieldtitle," +
								"fdtype,fdsize as fldlength,fddec,beizhu,nouse from fldlist where fdname in (";
	}
}