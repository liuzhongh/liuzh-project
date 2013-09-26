/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-24
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator;

import static org.code.generator.util.ClassloaderUtility.getCustomClassloader;

import org.code.generator.config.Configuration;
import org.code.generator.db.ObjectFactory;

public class CodeGenerator {

	private Configuration configuration;
	
	public void generate(Configuration configuration) throws Exception
	{
		if (configuration == null) {
            throw new IllegalArgumentException("RuntimeError.2"); //$NON-NLS-1$
        } else {
            this.configuration = configuration;
        }
		configuration.validate();
		
		// setup custom classloader if required
        if (this.configuration.getClassPathEntries().size() > 0) {
            ClassLoader classLoader = getCustomClassloader(configuration.getClassPathEntries());
            ObjectFactory.setExternalClassLoader(classLoader);
        }
        
        configuration.getContext().execute();
	}
}
