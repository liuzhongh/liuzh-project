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
	<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">
    import com.shangkang.front.core.delegate.AbstractGenericDelegate;
	import ${flexBoPackage}.${tableField};
	
	import mx.rpc.AsyncToken;
    
    public class ${tableField}Delegate extends AbstractGenericDelegate
	{
		private static var instance:${tableField}Delegate; 
		
		public static function getInstance():${tableField}Delegate
		{
			if(!instance) { 
				instance = new ${tableField}Delegate(); 
			} 
			
			return instance; 
		}
	
		public function ${tableField}Delegate()
		{
			super("${tableLowercaseField}Facade");
		}
		
		/**
		 * 通过主键查询实体对象
		 * @param primaryKey
		 * @return
		 */
		public function getByPK(primaryKey :${result.getKeyType()}) :AsyncToken
		{
			return remoteObject.getByPK(primaryKey);
		}
	
		/**
		 * 查询所有记录
		 * @return
		 */
		public function list() :AsyncToken
		{
			return remoteObject.list();
		}
	
		/**
		 * 根据查询条件查询所有记录
		 * @return
		 */
		public function listByProperty(${tableLowercaseField} :${tableField}) :AsyncToken
		{
			return remoteObject.listByProperty(${tableLowercaseField});
		}
	
		/**
		 * 根据主键删除记录
		 * @param primaryKey
		 * @return
		 */
		public function deleteByPK(primaryKey :${result.getKeyType()}) :AsyncToken
		{
			return remoteObject.deleteByPK(primaryKey);
		}
	
		/**
		 * 根据传入参数删除记录
		 * @param ${tableLowercaseField}
		 * @return
		 */
		public function deleteByProperty(${tableLowercaseField} :${tableField}) :AsyncToken
		{
			return remoteObject.deleteByProperty(${tableLowercaseField});
		}
	
		/**
		 * 保存记录
		 * @param ${tableLowercaseField}
		 * @return
		 */
		public function save(${tableLowercaseField} :${tableField}) :AsyncToken
		{
			return remoteObject.save(${tableLowercaseField});
		}
	
		/**
		 * 更新记录
		 * @param ${tableLowercaseField}
		 * @return
		 */
		public function update(${tableLowercaseField} :${tableField}) :AsyncToken
		{
			return remoteObject.update(${tableLowercaseField});
		}
    }
}
