

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import mx.containers.Canvas;
import mx.controls.Button;
import mx.controls.TextInput;
import mx.controls.Label;
import com.shangkang.front.bo.Information;

class BindableProperty
{
	/*
	 * generated bindable wrapper for property btn1 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'btn1' moved to '_3034453btn1'
	 */

    [Bindable(event="propertyChange")]
    public function get btn1():mx.controls.Button
    {
        return this._3034453btn1;
    }

    public function set btn1(value:mx.controls.Button):void
    {
    	var oldValue:Object = this._3034453btn1;
        if (oldValue !== value)
        {
            this._3034453btn1 = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "btn1", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property cv (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'cv' moved to '_3187cv'
	 */

    [Bindable(event="propertyChange")]
    public function get cv():mx.containers.Canvas
    {
        return this._3187cv;
    }

    public function set cv(value:mx.containers.Canvas):void
    {
    	var oldValue:Object = this._3187cv;
        if (oldValue !== value)
        {
            this._3187cv = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cv", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property lb (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'lb' moved to '_3446lb'
	 */

    [Bindable(event="propertyChange")]
    public function get lb():mx.controls.Label
    {
        return this._3446lb;
    }

    public function set lb(value:mx.controls.Label):void
    {
    	var oldValue:Object = this._3446lb;
        if (oldValue !== value)
        {
            this._3446lb = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lb", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property password (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'password' moved to '_1216985755password'
	 */

    [Bindable(event="propertyChange")]
    public function get password():mx.controls.TextInput
    {
        return this._1216985755password;
    }

    public function set password(value:mx.controls.TextInput):void
    {
    	var oldValue:Object = this._1216985755password;
        if (oldValue !== value)
        {
            this._1216985755password = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "password", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property searchStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'searchStr' moved to '_1778196329searchStr'
	 */

    [Bindable(event="propertyChange")]
    public function get searchStr():mx.controls.TextInput
    {
        return this._1778196329searchStr;
    }

    public function set searchStr(value:mx.controls.TextInput):void
    {
    	var oldValue:Object = this._1778196329searchStr;
        if (oldValue !== value)
        {
            this._1778196329searchStr = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "searchStr", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property searchStr2 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'searchStr2' moved to '_710488599searchStr2'
	 */

    [Bindable(event="propertyChange")]
    public function get searchStr2():mx.controls.TextInput
    {
        return this._710488599searchStr2;
    }

    public function set searchStr2(value:mx.controls.TextInput):void
    {
    	var oldValue:Object = this._710488599searchStr2;
        if (oldValue !== value)
        {
            this._710488599searchStr2 = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "searchStr2", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property user_name (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'user_name' moved to '_339340927user_name'
	 */

    [Bindable(event="propertyChange")]
    public function get user_name():mx.controls.TextInput
    {
        return this._339340927user_name;
    }

    public function set user_name(value:mx.controls.TextInput):void
    {
    	var oldValue:Object = this._339340927user_name;
        if (oldValue !== value)
        {
            this._339340927user_name = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "user_name", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property user_password (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'user_password' moved to '_1267537039user_password'
	 */

    [Bindable(event="propertyChange")]
    public function get user_password():mx.controls.TextInput
    {
        return this._1267537039user_password;
    }

    public function set user_password(value:mx.controls.TextInput):void
    {
    	var oldValue:Object = this._1267537039user_password;
        if (oldValue !== value)
        {
            this._1267537039user_password = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "user_password", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property infor (private)
	 * - generated setter
	 * - generated getter
	 * - original private var 'infor' moved to '_100348292infor'
	 */

    [Bindable(event="propertyChange")]
    private function get infor():Information
    {
        return this._100348292infor;
    }

    private function set infor(value:Information):void
    {
    	var oldValue:Object = this._100348292infor;
        if (oldValue !== value)
        {
            this._100348292infor = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "infor", oldValue, value));
        }
    }



}
