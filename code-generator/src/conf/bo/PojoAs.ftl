/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: ${user}
 * Created On: ${date}
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package ${package}
{
	<#assign tableField="${result.getFirstCharacterUppercase(result.getTableName())}">
    [Bindable]
    [RemoteClass(alias="${boPackage}.${tableField}")]
    public class ${tableField}
    {
    
    <#foreach prop in result.getColumns()>
		/**
		  *	${prop.remarks}
		  */
		private var _${result.getFirstCharacterLowercase(prop.columnName)} :${prop.columnType};
	
	</#foreach>
	<#foreach prop in result.getColumns()>
        /**
		  *	${prop.remarks}
		  */
        public function set ${result.getFirstCharacterLowercase(prop.columnName)}(_${result.getFirstCharacterLowercase(prop.columnName)}: ${prop.columnType}):void
        {
            this._${result.getFirstCharacterLowercase(prop.columnName)} = _${result.getFirstCharacterLowercase(prop.columnName)};
        }
        
        /**
		  *	${prop.remarks}
		  */
        public function get ${result.getFirstCharacterLowercase(prop.columnName)}():${prop.columnType}
        {
            return this._${result.getFirstCharacterLowercase(prop.columnName)};
        }
        
		</#foreach>
    }
}