




package
{
import flash.system.Security;
import flash.text.Font;
import mx.core.mx_internal;
import mx.modules.ModuleBase;
import mx.styles.CSSCondition;
import mx.styles.CSSSelector;
import mx.styles.CSSStyleDeclaration;
import mx.styles.StyleManager;
import mx.styles.IStyleManager2;
import mx.styles.IStyleModule;



[ExcludeClass]
public class main extends ModuleBase implements IStyleModule
{
    /**
     * @private
     */
    private var selectors:Array = [];
    /**
     * @private
     */
    private var overrideMap:Object = {};
    /**
     * @private
     */
    private var effectMap:Object = {};
    /**
     * @private
     */
    private var unloadGlobal:Boolean;
    /**
     * @private
     */
    private var styleManager:IStyleManager2;
    /**
     * @private
     */
    private static var domainsAllowed:Boolean = allowDomains();

    /**
     * @private
     */
    private static function allowDomains():Boolean
    {
		// allowDomain not allowed in AIR
		if (Security.sandboxType != "application")
			Security.allowDomain("*");
        return true;
    }
    

    public function main()
    {
        super();
    }
    
    public function setStyleDeclarations(styleManager:IStyleManager2):void
    {
        if (styleManager == null)
            styleManager = StyleManager.getStyleManager(null);

        this.styleManager = styleManager;
        
        var conditions:Array;
        var condition:CSSCondition;
        var selector:CSSSelector;
        var style:CSSStyleDeclaration;
        var keys:Array;
        var selectorString:String;
        var effects:Array;
        var addedEffects:Array;

        selector = null;
        conditions = null;
        conditions = [];
        condition = new CSSCondition("class", "btn");
        conditions.push(condition); 
        selector = new CSSSelector("", conditions, selector);
        // .btn
        selectorString = ".btn";
        style = styleManager.getStyleDeclaration(selectorString);
        if (!style)
        {
            style = new CSSStyleDeclaration(selector, styleManager);
            selectors.push(selectorString);
        }

        keys = overrideMap[selectorString];

        if (keys == null)
        {
            keys = [];
            overrideMap[selectorString] = keys;
        }

        style.mx_internal::setLocalStyle('fillColors', [0x00cc33, 0xff0000, 0x666600, 0xeeeeee]);
        keys.push("fillColors");



    }

    public function unload():void
    {
        unloadOverrides();
        unloadStyleDeclarations();

        if (unloadGlobal)
        {
            styleManager.stylesRoot = null;
            styleManager.initProtoChainRoots();
        }
    }

    /**
     * @private
     */
    private function unloadOverrides():void
    {
        for (var selector:String in overrideMap)
        {
            var style:CSSStyleDeclaration = styleManager.getStyleDeclaration(selector);

            if (style != null)
            {
                var keys:Array = overrideMap[selector];
                var numKeys:int;
                var i:uint;

                if (keys != null)
                {
                    numKeys = keys.length;

                    for (i = 0; i < numKeys; i++)
                    {
                        style.mx_internal::clearOverride(keys[i]);
                    }
                }

                keys = effectMap[selector];

                if (keys != null)
                {
                    numKeys = keys.length;
                    var index:uint;
                    var effects:Array = style.mx_internal::effects;

                    for (i = 0; i < numKeys; i++)
                    {
                        index = effects.indexOf(numKeys[i]);
                        if (index >= 0)
                        {
                            effects.splice(index, 1);
                        }
                    }                    
                }
            }
        }

        overrideMap = null;
        effectMap = null;
    }

    /**
     * @private
     */
    private function unloadStyleDeclarations():void
    {
        var numSelectors:int = selectors.length;

        for (var i:int = 0; i < numSelectors; i++)
        {
            var selector:String = selectors[i];
            styleManager.clearStyleDeclaration(selector, false);
        }

        selectors = null;
    }
}

}
