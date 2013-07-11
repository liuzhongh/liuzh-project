package com.shangkang.front.core.delegate
{
	import mx.rpc.AsyncToken;
	
	public class SimpleGenericDelegate extends AbstractGenericDelegate
	{
		private static var instance:SimpleGenericDelegate; 
		
		public static function getInstance():SimpleGenericDelegate
		{
			if(!instance) { 
				instance = new SimpleGenericDelegate(); 
			} 
			
			return instance; 
		}
		
		public function SimpleGenericDelegate()
		{
			super("simpleGenericFacade");
		}
		
		/**
		 * 根据数组（表名）查询所有记录
		 * @param tableNames
		 * @return
		 */
		public function listAll(tableNames:Array):AsyncToken
		{
			return remoteObject.listAll(tableNames);
		}
		
		/**
		 * 根据数据库表名及对应字段值插入数据库表记录。
		 * @param tableName：数据库表名
		 * @param parameters：字段键所对应的值
		 * @return
		 */
		public function insertRecord(tableName:String, object:Object):AsyncToken
		{
			return remoteObject.insertRecord(tableName, object);
		}
		
		/**
		 * 根据查询语句，参数值查询所有记录
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function listAllBySql(sql:String, obj:Object):AsyncToken
		{
			return remoteObject.listAll(sql, obj);
		}
		
		/**
		 * 根据表名、字段参数、查询子参数更新对应的表记录
		 * @param tableName：表名
		 * @param parameters：字段键所对应的值
		 * @param whereParameters：查询字段键所对应的值，表达式处为等于
		 * @return
		 */
		public function updateRecord(tableName:String,
				parameters:Object, whereParameters:Object):AsyncToken
		{
			return remoteObject.updateRecord(tableName, parameters, whereParameters);
		}
		
		/**
		 * 根据表名、字段参数、查询子句对表执行更新操作
		 * @param tableName：表名
		 * @param parameters：字段键所对应的值
		 * @param whereSubSql：查询子句
		 * @return
		 */
		public function updateRecordBySql(tableName:String,
				parameters:Object, whereSubSql:String):AsyncToken
		{
			return remoteObject.updateRecord(tableName, parameters, whereSubSql);
		}
		
		/**
		 * 根据表名、字段键值参数删除记录
		 * @param tableName
		 * @param parameters
		 * @return
		 */
		public function deleteRecord(tableName:String, parameters:Object):AsyncToken
		{
			return remoteObject.deleteRecord(tableName, parameters);
		}
		
		/**
		 * 根据表名、sql语句、字段键值删除表记录
		 * @param tableName
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function deleteRecordBySqlAndParameters(tableName:String, sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.deleteRecord(tableName, sql, parameters);
		}
	
		/**
		 * 根据表名、Sql语句删除记录
		 * @param tableName
		 * @param sql
		 * @return
		 */
		public function deleteRecordBySql(tableName:String, sql:String):AsyncToken
		{
			return remoteObject.deleteRecord(tableName, sql);
		}
	
		/**
		 * 根据sql语句、参数执行存储过程
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function executeProcedure(sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.executeProcedure(sql, parameters);
		}
	
		/**
		 * 根据存储过程名字、参数执行存储过程
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function executeProcedureByName(procedureName:String, parameters:Object):AsyncToken
		{
			return remoteObject.executeProcedureByName(procedureName, parameters);
		}	
		
		/**
		 * 根据SQL查询语句，参数查询单条记录
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function getSingleRecord(sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.getSingleRecord(sql, parameters);
		}
	
		/**
		 * 根据SQL、参数查询记录条数
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function getRecordCount(sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.getRecordCount(sql, parameters);
		}
		
		/**
		 * 根据SQL语句、参数键值查询所有记录
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function listAllByMap(sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.listAllByMap(sql, parameters);
		}
	
		/**
		 * 根据SQL查询语句，参数键值查询单条记录
		 * @param sql
		 * @param parameters
		 * @return
		 */
		public function getSingleRecordByMap(sql:String, parameters:Object):AsyncToken
		{
			return remoteObject.getSingleRecordByMap(sql, parameters);
		}
	}
}