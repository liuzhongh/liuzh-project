
package 
{
import com.shangkang.front.components.SimpleApplication;
import flash.accessibility.*;
import flash.debugger.*;
import flash.display.*;
import flash.errors.*;
import flash.events.*;
import flash.external.*;
import flash.filters.*;
import flash.geom.*;
import flash.media.*;
import flash.net.*;
import flash.printing.*;
import flash.profiler.*;
import flash.system.*;
import flash.text.*;
import flash.ui.*;
import flash.utils.*;
import flash.xml.*;
import mx.binding.*;
import mx.containers.Canvas;
import mx.controls.Button;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IFlexModuleFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import com.shangkang.front.components.SimpleApplication;
import mx.controls.Button;
import mx.controls.DateField;
import mx.controls.Label;

public class AppTest extends com.shangkang.front.components.SimpleApplication
{
	public function AppTest() {}

	[Bindable]
	public var cv : mx.containers.Canvas;
	[Bindable]
	public var lb : mx.controls.Label;
	[Bindable]
	public var password : mx.controls.TextInput;
	[Bindable]
	public var searchStr : mx.controls.TextInput;
	[Bindable]
	public var btn1 : mx.controls.Button;
	[Bindable]
	public var searchStr2 : mx.controls.TextInput;
	[Bindable]
	public var user_name : mx.controls.TextInput;
	[Bindable]
	public var user_password : mx.controls.TextInput;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/workspacenew/core/flex_src/AppTest.mxml:4,11";

}}
