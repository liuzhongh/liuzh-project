/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-25
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.PropertySet;
import org.code.generator.CodeGenerator;
import org.code.generator.config.Configuration;
import org.code.generator.dom.ConfigurationParser;
import org.code.generator.util.StringUtility;

public class GeneratorAntTask extends Task {

	private String	configfile;
	private PropertySet	propertyset;

	/**
     * 
     */
    public GeneratorAntTask() {
        super();
    }
    
    
	public String getConfigfile()
	{
		return configfile;
	}


	public void setConfigfile(String configfile)
	{
		this.configfile = configfile;
	}


	public PropertySet getPropertyset()
	{
		return propertyset;
	}


	public void setPropertyset(PropertySet propertyset)
	{
		this.propertyset = propertyset;
	}


	/*
     * (non-Javadoc)
     * 
     * @see org.apache.tools.ant.Task#execute()
     */
    @Override
    public void execute() throws BuildException {
        if (!StringUtility.stringHasValue(configfile)) {
            throw new BuildException("RuntimeError.0"); //$NON-NLS-1$
        }

        List<String> warnings = new ArrayList<String>();

        File configurationFile = new File(configfile);
        if (!configurationFile.exists()) {
            throw new BuildException(configfile); //$NON-NLS-1$
        }

        try {
            Properties p = propertyset == null ? null : propertyset
                    .getProperties();

    		ConfigurationParser cp = new ConfigurationParser(p, warnings);
    		Configuration config = cp.parseConfiguration(configurationFile);
    		CodeGenerator codeGenerator = new CodeGenerator();
    		codeGenerator.generate(config);

        } catch (Exception e) {
        	e.printStackTrace();
            log(e.getMessage(), Project.MSG_ERR);
            throw new BuildException(e.getMessage());
        } 

        for (String error : warnings) {
            log(error, Project.MSG_WARN);
        }
    }
}
