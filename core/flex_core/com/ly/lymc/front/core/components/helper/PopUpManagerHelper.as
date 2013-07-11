package com.shangkang.front.core.components.helper
{
	import flash.display.DisplayObject;
	
	import mx.core.FlexGlobals;
	import mx.core.IFlexDisplayObject;
	import mx.core.IFlexModuleFactory;
	import mx.managers.PopUpManager;

	public class PopUpManagerHelper
	{
		/**
		 * @param className 要为弹出窗口创建的对象的类。该类必须实现 IFlexDisplayObject。
		 * @param modal 如果为 true，则该窗口为模态窗口，也就是说在删除该窗口之前，用户将无法与其它弹出窗口交互。
		 * @param childList 要将弹出窗口添加到的子项列表。PopUpManagerChildList.APPLICATION、PopUpManagerChildList.POPUP 或 PopUpManagerChildList.PARENT（默认）中的任意一项。
		 * @param moduleFactory 此弹出窗口应在其中查找其嵌入字体和样式管理器的 moduleFactory。
		 * @return 对新的顶级窗口的引用。
		 */
		public static function createPopUp(className:Class,
							   modal:Boolean = false,
							   childList:String = null,
							   moduleFactory:IFlexModuleFactory = null):IFlexDisplayObject
		{
			return PopUpManager.createPopUp(FlexGlobals.topLevelApplication as DisplayObject,
				className, modal, childList, moduleFactory);	
		}
		
		/**
		 * 弹出顶级窗口。最好调用 removePopUp() 来删除使用 addPopUp() 方法创建的弹出窗口。
		 * 如果该类实现了 IFocusManagerContainer，则该窗口将拥有自己的 FocusManager；
		 * 因此，当用户使用 Tab 键在各个控件之间导航时，将只能访问此窗口中的控件。 
		 *  <p><b>Example</b></p> 
	     *
	     *  <pre>var tw:TitleWindow = new TitleWindow();
	     *    tw.title = "My Title";
	     *    com.shangkang.front.core.components.helper.PopUpManagerHelper.addPopUp(tw, false);</pre>
		 *  <p>使用 TitleWindow 类的<code> tw </code>实例创建一个弹出窗口，
		 * 		并使用<code> pnl </code>作为 Sprite 来确定放置此弹出窗口的位置。此窗口将定义为非模态窗口。
		 *  </p>
		 * @param window 要弹出的 IFlexDisplayObject。
		 * @param modal 如果为 true，则该窗口为模态窗口，也就是说在删除该窗口之前，用户将无法与其它弹出窗口交互。
		 * @param childList 要将弹出窗口添加到其中的子项列表。PopUpManagerChildList.APPLICATION、PopUpManagerChildList.POPUP 或 PopUpManagerChildList.PARENT（默认）中的任意一项。
		 * @param moduleFactory 此弹出窗口应在其中查找其嵌入字体和样式管理器的 moduleFactory。
		 */
		public static function addPopUp(window:IFlexDisplayObject,
										modal:Boolean = false,
										childList:String = null,
										moduleFactory:IFlexModuleFactory = null):void
		{
			PopUpManager.addPopUp(window, FlexGlobals.topLevelApplication as DisplayObject, modal, childList, moduleFactory);
		}
		
		/**
		 * 使位于调用 createPopUp() 或 addPopUp() 方法期间使用的任何窗口上面的弹出窗口居中显示。 
		 * 请注意，由于 Flex 可能会在居中弹出窗口前对其进行测量和布局，因此弹出窗口的位置可能不会在完成此调用后立即更改。
		 * @param popUp 表示弹出窗口的 IFlexDisplayObject。
		 */
		public static function centerPopUp(popUp:IFlexDisplayObject):void
		{
			PopUpManager.centerPopUp(popUp);
		}
		
		/**
		 * 删除由 createPopUp() 或 addPopUp() 方法弹出的弹出窗口。
		 * @param popUp 表示弹出窗口的 IFlexDisplayObject。
		 */
		public static function removePopUp(popUp:IFlexDisplayObject):void
		{
			PopUpManager.removePopUp(popUp);
		}
		
		/**
		 * 请确保弹出窗口高于其子项列表中的其它对象。
		 * 如果该弹出窗口是顶级窗口并且位于鼠标下，则 SystemManager 会自动将其设置为满足此要求；
		 * 否则，您必须自行处理。
		 * @param popUp 表示弹出窗口的 IFlexDisplayObject。
		 */
		public static function bringToFront(popUp:IFlexDisplayObject):void
		{
			PopUpManager.bringToFront(popUp);
		}
	}
}