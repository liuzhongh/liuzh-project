package org.code.generator.config;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.XmlElement;

public class JavaTypeResolverConfiguration extends TypedPropertyHolder {

    /**
	 *  
	 */
    public JavaTypeResolverConfiguration() {
        super();
    }

    public XmlElement toXmlElement() {
        XmlElement answer = new XmlElement("javaTypeResolver"); //$NON-NLS-1$
        if (getConfigurationType() != null) {
            answer.addAttribute(new Attribute("type", getConfigurationType())); //$NON-NLS-1$
        }
        
        if(this.getTypeResolverImpl() != null)
        {
        	answer.addAttribute(new Attribute("typeResolverImpl", getTypeResolverImpl()));
        }

        addPropertyXmlElements(answer);

        return answer;
    }
}
