/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-26
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

public class FlexTypeResolver implements ColumnTypeResolver {

	private Map<Integer, String> typeMap;
	
	static final int NVARCHAR = -9;
    static final int NCHAR = -15;
    static final int NCLOB = 2011;
	
	public FlexTypeResolver()
	{
		super();
		
		typeMap = new HashMap<Integer, String>();
		
		typeMap.put(Types.ARRAY, "Object");
        typeMap.put(Types.BIGINT, "Number");
        typeMap.put(Types.BINARY, "int"); //$NON-NLS-1$
        typeMap.put(Types.BIT, "Boolean");
        typeMap.put(Types.BLOB, "int"); //$NON-NLS-1$
        typeMap.put(Types.BOOLEAN, "Boolean");
        typeMap.put(Types.CHAR, "String");
        typeMap.put(Types.CLOB, "String");
        typeMap.put(Types.DATALINK, "Object");
        typeMap.put(Types.DATE, "String");
        typeMap.put(Types.DISTINCT, "Object");
        typeMap.put(Types.DOUBLE, "Number");
        typeMap.put(Types.FLOAT, "Number");
        typeMap.put(Types.INTEGER, "int");
        typeMap.put(Types.JAVA_OBJECT, "Object");
        typeMap.put(Types.LONGVARBINARY, "int"); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, "String");
        typeMap.put(NCHAR, "String");
        typeMap.put(NCLOB, "String");
        typeMap.put(NVARCHAR, "String");
        typeMap.put(Types.NULL, "Object");
        typeMap.put(Types.OTHER, "Object");
        typeMap.put(Types.REAL, "Number");
        typeMap.put(Types.REF, "Object");
        typeMap.put(Types.SMALLINT, "int");
        typeMap.put(Types.STRUCT, "Object");
        typeMap.put(Types.TIME, "String");
        typeMap.put(Types.TIMESTAMP, "String");
        typeMap.put(Types.TINYINT, "int");
        typeMap.put(Types.VARBINARY, "int"); //$NON-NLS-1$
        typeMap.put(Types.VARCHAR, "String");
	}

	@Override
	public String calculateJavaType(TableColumn tableColumn)
	{
		String answer;
		
		String jdbcTypeInformation = typeMap
                .get(tableColumn.getJdbcType());
		
		if (jdbcTypeInformation == null) {
            switch (tableColumn.getJdbcType()) {
            case Types.DECIMAL:
            case Types.NUMERIC:
                if (tableColumn.getScale() > 0
                        || tableColumn.getLength() > 18) {
                    answer = "Number";
                } else if (tableColumn.getLength() > 9) {
                    answer = "Number";
                } else if (tableColumn.getLength() > 4) {
                    answer = "int";
                } else {
                    answer = "int";
                }
                break;

            default:
                answer = "Object";
                break;
            }
        } else {
            answer = jdbcTypeInformation;
        }
		
		return answer;
	}

}
