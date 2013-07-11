/**
 * Create: [2010-12-23 11:06] by Daij
 * Ver 0.1
 * 
 * Description: 定义表格常用字段和字段值  
 */
package com.shangkang.front.constants
{
	import mx.messaging.channels.StreamingAMFChannel;

	public final class TableFieldConstant
	{
		public function TableFieldConstant()
		{
		}
		
		/****************** 系统通用值 ******************/
		
		// 值为  0
		public static const VALUE_ZERO:int = 0;
		
		// 值为  1
		public static const VALUE_ONE:int = 1;
		
		// 值为  2
		public static const VALUE_TWO:int = 2;
		
		// 值为  3
		public static const VALUE_THREE:int = 3;
		
		// 值为  4
		public static const VALUE_FOUR:int = 4;
		
		// 值为  "Y"
		public static const VALUE_Y:String = "Y";
		
		// 值为 "N"
		public static const VALUE_N:String = "N";
		
		// 值为 "A" 
		public static const VALUE_A:String = "A";
		
		// 值为 "B"
		public static const VALUE_B:String = "B";
		
		// 值为 "C"
		public static const VALUE_C:String = "C";
		
		// 值为 "D"
		public static const VALUE_D:String = "D";
		
		// 值为 "E"
		public static const VALUE_E:String = "E";
		
		// 值为 "F"
		public static const VALUE_F:String = "F";
		
		// 值为 "G"
		public static const VALUE_G:String = "G";
		
		// 值为 "H"
		public static const VALUE_H:String = "H";
		
		// 黑色
		public static const COLOR_BLACK:String = "0x000000";
		
		// 字段值 'rq'
		public static const FIELD_ID_RQ:String = "rq";
		
		// 实数
		public static const REAL_NUM:String = "实数"; 
		
		// 整数
		public static const INTEGER_NUM:String = "整数";
		
		// 序号
		public static const SERIAL_TEXT:String = "序  号";
		
		// 
		public static const POPUP_CAPTION:String = "资料选择方案 -- ";
		
		/******************* 表格字段  *******************/

		// 单据类型
		public static const DJLX:String = "djlx";
		
		// 明细表上部分颜色
		public static const UPMXCOLOR:String = "upmxcolor";
		
		// 明细表下部分颜色
		public static const DOWNMXCOLOR:String = "downmxcolor";
		
		// 明细表是否显示
		public static const MXACTIVE:String = "mxactive";
		
		// 是否在明细上方                          
		public static const UPMX:String = "upmx";
		
		// 组件类型
		public static const LX:String = "lx";
		
		// 固定文字内容                            
		public static const LABEL_TITLE:String = "labtitle";
		
		//
		public static const FIELD_TITLE:String = "fieldtitle";
		
		// 是否活动
		public static const IS_ACTIVE:String = "isactive";
		
		// 保留小数位数
		public static const FLD_DEC:String = "flddec";
		
		// 字段类型
		public static const FLD_TYPE:String = "fldtype";
		
		// 左上角横坐标                              
		public static const POS_X:String = "posx";
		
		// 左上角纵坐标
		public static const POS_Y:String = "posy";
		
		// 字段名称
		public static const FIELD_NAME:String = "fieldname";
		
		// 是否显示
		public static const UNVISIBLE:String = "vistable";
		
		// 是否允许为空
		public static const IS_NULL:String = "isnull";
		
		// 是否可编辑                              
		public static const CAN_EDIT:String = "canedit";
		
		// 执行焦点顺序                            
		public static const FIELD_TAB_ORDER:String = "fieldtaborder";
		
		// 对象宽度
		public static const FIELD_WIDTH:String = "fieldwidth";
		
		// 查询数据集合标识                        
		public static const DATA_SET_ID:String = "datasetid";
		
		// TDHEIGHT
		public static const TD_HEIGHT:String = "tdheight";
		
		// MXHEIGHT
		public static const MX_HEIGHT:String = "mxheight";
		
		// QTHEIGHT
		public static const QT_HEIGHT:String = "qtheight";
		
		// 双击事件处理方案
		public static const ON_DBL_FUNC:String = "ondblfunc";
		
		// 退出事件处理方案
		public static const ON_EXIT_FUNC:String = "onexitfunc";
		
		// 组件默认值
		public static const V_DEFAULT:String = "v_default";
		
		// 自定义SQL语句
		public static const ZDY_SQL:String = "zdysqls";
		
		// 查询数据集合中文说明                    
		public static const DATA_SET_CHN:String = "datasetchn";
		
		// 当前表格翻页方式
		public static const ORDER_TYPE:String = "order_type";

		// DGCHANGECOLOR
		public static const DGCHANGECOLOR:String = "dgchangecolor";
		
		// 颜色改变的方法
		public static const CHANGECOLORFUNC:String = "changecolorfunc";
		
		// COLOROBJID
		public static const COLOROBJID:String = "colorobjid";
		
		// 线条水平坐标位置
		public static const LINE_POS_X:String = "sposx";
		
		// 线条垂直坐标位置
		public static const LINE_POS_Y:String = "sposy";
		
		// 线条宽度
		public static const LINE_WIDTH:String = "lrbwidth"; 
	
		// 
		public static const ALIGN_STYLE:String = "alignstyle";
		
		// 弹出窗口高
		public static const DIALOG_HEI:String = "dialog_hei";
		
		// 弹出窗口宽
		public static const DIALOG_WID:String = "dialog_wid";
		
		// 弹出窗口标题
		public static const DIALOG_CAP:String = "dialog_cap";
		
		// 弹出框显示字段
		public static const DISPLAY_FLDS:String = "displyflds";
		
		// 明细方案编号
		public static const MXFANGABH:String = "mxfangabh";
		
	}
}