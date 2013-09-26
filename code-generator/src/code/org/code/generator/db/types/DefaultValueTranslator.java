/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-4
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.db.types;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.code.generator.db.TableColumn;
import org.code.generator.util.StringUtility;

public class DefaultValueTranslator {

	private static Map<Integer, String> defaultValueMap;

    static {
    	defaultValueMap = new HashMap<Integer, String>();
    	
    	defaultValueMap.put(Types.ARRAY, "\"\"");
        defaultValueMap.put(Types.BIGINT, "-1l");
        defaultValueMap.put(Types.BINARY, "\"\""); //$NON-NLS-1$
        defaultValueMap.put(Types.BIT, "true");
        defaultValueMap.put(Types.BLOB, "\"\""); //$NON-NLS-1$
        defaultValueMap.put(Types.BOOLEAN, "true");
        defaultValueMap.put(Types.CHAR, "\"\"");
        defaultValueMap.put(Types.CLOB, "\"\"");
        defaultValueMap.put(Types.DATALINK, "\"\"");
        defaultValueMap.put(Types.DATE, "\"\"");
        defaultValueMap.put(Types.DISTINCT, "\"\"");
        defaultValueMap.put(Types.DOUBLE, "-1d");
        defaultValueMap.put(Types.FLOAT, "-1d");
        defaultValueMap.put(Types.INTEGER, "-1");
        defaultValueMap.put(Types.JAVA_OBJECT, "\"\"");
        defaultValueMap.put(Types.LONGVARBINARY, "\"\""); //$NON-NLS-1$
        defaultValueMap.put(Types.LONGVARCHAR, "\"\"");
        defaultValueMap.put(JavaTypeResolver.NCHAR, "\"\"");
        defaultValueMap.put(JavaTypeResolver.NCLOB, "\"\"");
        defaultValueMap.put(JavaTypeResolver.NVARCHAR, "\"\"");
        defaultValueMap.put(Types.NULL, "\"\"");
        defaultValueMap.put(Types.OTHER, "\"\"");
        defaultValueMap.put(Types.REAL, "-1f");
        defaultValueMap.put(Types.REF, "\"\"");
        defaultValueMap.put(Types.SMALLINT, "-1");
        defaultValueMap.put(Types.STRUCT, "\"\"");
        defaultValueMap.put(Types.TIME, "\"\"");
        defaultValueMap.put(Types.TIMESTAMP, "\"\"");
        defaultValueMap.put(Types.TINYINT, "\"\"");
        defaultValueMap.put(Types.VARBINARY, "\"\""); //$NON-NLS-1$
        defaultValueMap.put(Types.VARCHAR, "\"\"");
    }
    
    public static String getDefaultValue(TableColumn tableColumn, String defaultValue) {
    	
    	if(StringUtility.stringHasValue(defaultValue))
    		return defaultValue;
    	
        String answer = defaultValueMap.get(tableColumn.getJdbcType());
        
        if (answer == null) {
            switch (tableColumn.getJdbcType()) {
            case Types.DECIMAL:
            case Types.NUMERIC:
                if (tableColumn.getScale() > 0
                        || tableColumn.getLength() > 18) {
                    answer = "new java.math.BigDecimal(-1)";
                } else if (tableColumn.getLength() > 9) {
                    answer = "-1l";
                } else if (tableColumn.getLength() > 4) {
                    answer = "-1";
                } else {
                    answer = "-1";
                }
                break;

            default:
                answer = "null";
                break;
            }
        } 

        return answer;
    }
}
