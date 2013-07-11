

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import com.shangkang.front.bo.*;

class BindableProperty
    implements flash.events.IEventDispatcher
{
	/*
	 * generated bindable wrapper for property dbid (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'dbid' moved to '_3076633dbid'
	 */

    [Bindable(event="propertyChange")]
    public function get dbid():Number
    {
        return this._3076633dbid;
    }

    public function set dbid(value:Number):void
    {
    	var oldValue:Object = this._3076633dbid;
        if (oldValue !== value)
        {
            this._3076633dbid = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dbid", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property dbversion (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'dbversion' moved to '_1008490854dbversion'
	 */

    [Bindable(event="propertyChange")]
    public function get dbversion():int
    {
        return this._1008490854dbversion;
    }

    public function set dbversion(value:int):void
    {
    	var oldValue:Object = this._1008490854dbversion;
        if (oldValue !== value)
        {
            this._1008490854dbversion = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dbversion", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property id (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'id' moved to '_3355id'
	 */

    [Bindable(event="propertyChange")]
    public function get id():String
    {
        return this._3355id;
    }

    public function set id(value:String):void
    {
    	var oldValue:Object = this._3355id;
        if (oldValue !== value)
        {
            this._3355id = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "id", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property password (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'password' moved to '_1216985755password'
	 */

    [Bindable(event="propertyChange")]
    public function get password():String
    {
        return this._1216985755password;
    }

    public function set password(value:String):void
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
	 * generated bindable wrapper for property givenName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'givenName' moved to '_1469046696givenName'
	 */

    [Bindable(event="propertyChange")]
    public function get givenName():String
    {
        return this._1469046696givenName;
    }

    public function set givenName(value:String):void
    {
    	var oldValue:Object = this._1469046696givenName;
        if (oldValue !== value)
        {
            this._1469046696givenName = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "givenName", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property familyName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'familyName' moved to '_798554127familyName'
	 */

    [Bindable(event="propertyChange")]
    public function get familyName():String
    {
        return this._798554127familyName;
    }

    public function set familyName(value:String):void
    {
    	var oldValue:Object = this._798554127familyName;
        if (oldValue !== value)
        {
            this._798554127familyName = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "familyName", oldValue, value));
        }
    }

	/*
	 * generated bindable wrapper for property businessEmail (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'businessEmail' moved to '_605844412businessEmail'
	 */

    [Bindable(event="propertyChange")]
    public function get businessEmail():String
    {
        return this._605844412businessEmail;
    }

    public function set businessEmail(value:String):void
    {
    	var oldValue:Object = this._605844412businessEmail;
        if (oldValue !== value)
        {
            this._605844412businessEmail = value;
           if (this.hasEventListener("propertyChange"))
               this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "businessEmail", oldValue, value));
        }
    }


    //    IEventDispatcher implementation
    //
    private var _bindingEventDispatcher:flash.events.EventDispatcher =
        new flash.events.EventDispatcher(flash.events.IEventDispatcher(this));

	/**
	 * @inheritDoc
	 */
    public function addEventListener(type:String, listener:Function,
                                     useCapture:Boolean = false,
                                     priority:int = 0,
                                     weakRef:Boolean = false):void
    {
        _bindingEventDispatcher.addEventListener(type, listener, useCapture,
                                                 priority, weakRef);
    }

	/**
	 * @inheritDoc
	 */
    public function dispatchEvent(event:flash.events.Event):Boolean
    {
        return _bindingEventDispatcher.dispatchEvent(event);
    }

	/**
	 * @inheritDoc
	 */
    public function hasEventListener(type:String):Boolean
    {
        return _bindingEventDispatcher.hasEventListener(type);
    }

	/**
	 * @inheritDoc
	 */
    public function removeEventListener(type:String,
                                        listener:Function,
                                        useCapture:Boolean = false):void
    {
        _bindingEventDispatcher.removeEventListener(type, listener, useCapture);
    }

	/**
	 * @inheritDoc
	 */
    public function willTrigger(type:String):Boolean
    {
        return _bindingEventDispatcher.willTrigger(type);
    }

}
