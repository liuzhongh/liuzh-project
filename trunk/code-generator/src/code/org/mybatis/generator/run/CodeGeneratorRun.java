/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: 2012-4-21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.mybatis.generator.run;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.code.generator.CodeGenerator;
import org.code.generator.config.Configuration;
import org.code.generator.dom.ConfigurationParser;

public class CodeGeneratorRun {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception
	{
		List<String> warnings = new ArrayList<String>();

		File configFile = new File("F:\\work\\workspace\\code-generator\\src\\conf\\codeGeneratorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.generate(config);
	}

}
