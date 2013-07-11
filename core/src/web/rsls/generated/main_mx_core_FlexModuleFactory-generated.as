package
{

import flash.display.LoaderInfo;
import flash.text.Font;
import flash.text.TextFormat;
import flash.text.engine.TextBlock;
import flash.text.engine.TextLine;
import flash.system.ApplicationDomain;
import flash.system.Security
import flash.utils.Dictionary;
import flash.utils.getDefinitionByName;
import flashx.textLayout.compose.ISWFContext;
import mx.core.IFlexModule;
import mx.core.IFlexModuleFactory;
import mx.preloaders.DownloadProgressBar;
import mx.preloaders.SparkDownloadProgressBar;
import mx.core.FlexVersion;
import mx.core.FlexModuleFactory;

public class main_mx_core_FlexModuleFactory
    extends mx.core.FlexModuleFactory
    implements IFlexModuleFactory, ISWFContext
{
    // Cause the CrossDomainRSLItem class to be linked into this application.
    import mx.core.CrossDomainRSLItem; CrossDomainRSLItem;

    public function main_mx_core_FlexModuleFactory()
    {

        super();
    }

    override     public function callInContext(fn:Function, thisArg:Object, argArray:Array, returns:Boolean=true):*
    {
        if (returns)
           return fn.apply(thisArg, argArray);
        else
           fn.apply(thisArg, argArray);
    }

    override     public function create(... params):Object
    {
        if (params.length > 0 && !(params[0] is String))
            return super.create.apply(this, params);

        var mainClassName:String = params.length == 0 ? "main" : String(params[0]);
        var mainClass:Class = Class(getDefinitionByName(mainClassName));
        if (!mainClass)
            return null;

        var instance:Object = new mainClass();
        if (instance is IFlexModule)
            (IFlexModule(instance)).moduleFactory = this;
        return instance;
    }

    override    public function info():Object
    {
        return {
            cdRsls: [{"rsls":["http://fpdownload.adobe.com/pub/swz/flex/4.0.0.14195/framework_4.0.0.14195.swz","framework_4.0.0.14195.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["f74fcd943bac79e6dadbf0307b55b0697c5907e49161e6970b8452e8dcd92d04","f74fcd943bac79e6dadbf0307b55b0697c5907e49161e6970b8452e8dcd92d04"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
},
{"rsls":["http://fpdownload.adobe.com/pub/swz/tlf/1.0.0.595/textLayout_1.0.0.595.swz","textLayout_1.0.0.595.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["7421c71f94db4f028e7528b2d278f3fe4dc21273e3cc1c663c569f102564811c","7421c71f94db4f028e7528b2d278f3fe4dc21273e3cc1c663c569f102564811c"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
},
{"rsls":["http://fpdownload.adobe.com/pub/swz/flex/4.0.0.14195/osmf_flex.4.0.0.13495.swz","osmf_flex.4.0.0.13495.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["c3306b26751d6a80eb1fcb651912469ae18819aba42869379acb17e49ec1f9f0","c3306b26751d6a80eb1fcb651912469ae18819aba42869379acb17e49ec1f9f0"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
},
{"rsls":["http://fpdownload.adobe.com/pub/swz/flex/4.0.0.14159/spark_4.0.0.14159.swz","spark_4.0.0.14159.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["33d9983bc427dd69df151e816fb0ab02c0b8d5cfa9105cf9a10282adc14a0e64","33d9983bc427dd69df151e816fb0ab02c0b8d5cfa9105cf9a10282adc14a0e64"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
},
{"rsls":["http://fpdownload.adobe.com/pub/swz/flex/4.0.0.14159/sparkskins_4.0.0.14159.swz","sparkskins_4.0.0.14159.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["67ba9f962eec4d8b413432afad5c88bb810426b995aa9721fc1b503d0c2d30e0","67ba9f962eec4d8b413432afad5c88bb810426b995aa9721fc1b503d0c2d30e0"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
},
{"rsls":["http://fpdownload.adobe.com/pub/swz/flex/4.0.0.14159/rpc_4.0.0.14159.swz","rpc_4.0.0.14159.swz"],
"policyFiles":["http://fpdownload.adobe.com/pub/swz/crossdomain.xml",""]
,"digests":["d796ac95bd6e16151b6d3c0019a52e648ced1fe1609272931a1aa6fe6c39d3a1","d796ac95bd6e16151b6d3c0019a52e648ced1fe1609272931a1aa6fe6c39d3a1"],
"types":["SHA-256","SHA-256"],
"isSigned":[true,true]
}]
,
            compiledLocales: [ "zh_CN" ],
            compiledResourceBundleNames: [ "collections", "components", "core", "effects", "layout", "logging", "messaging", "skins", "styles" ],
            currentDomain: ApplicationDomain.currentDomain,
            mainClassName: "main",
            mixins: [ "_main_Styles", "mx.messaging.config.LoaderConfig" ]
        }
    }


    /**
     *  @private
     */
    private var _preloadedRSLs:Dictionary; // key: LoaderInfo, value: RSL URL

    /**
     *  The RSLs loaded by this system manager before the application
     *  starts. RSLs loaded by the application are not included in this list.
     */
    override     public function get preloadedRSLs():Dictionary
    {
        if (_preloadedRSLs == null)
           _preloadedRSLs = new Dictionary(true);
        return _preloadedRSLs;
    }

    /**
     *  Calls Security.allowDomain() for the SWF associated with this IFlexModuleFactory
     *  plus all the SWFs assocatiated with RSLs preLoaded by this IFlexModuleFactory.
     *
     */
    override     public function allowDomain(... domains):void
    {
        Security.allowDomain(domains);

        for (var loaderInfo:Object in _preloadedRSLs)
        {
            if (loaderInfo.content && ("allowDomainInRSL" in loaderInfo.content))
            {
                loaderInfo.content["allowDomainInRSL"](domains);
            }
        }
    }

    /**
     *  Calls Security.allowInsecureDomain() for the SWF associated with this IFlexModuleFactory
     *  plus all the SWFs assocatiated with RSLs preLoaded by this IFlexModuleFactory.
     *
     */
    override     public function allowInsecureDomain(... domains):void
    {
        Security.allowInsecureDomain(domains);

        for (var loaderInfo:Object in _preloadedRSLs)
        {
            if (loaderInfo.content && ("allowInsecureDomainInRSL" in loaderInfo.content))
            {
                loaderInfo.content["allowInsecureDomainInRSL"](domains);
            }
        }
    }


}

}
