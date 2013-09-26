<%-- 
 *
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
 * ************     ***********     *********************************************
 *
 **
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#assign parameterType="${result.getFirstCharacterUppercase(result.getTableName())}">
<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">
<#assign tableRemark="${result.getTableRemarks() ! parameterType}">
<title>${tableRemark}管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding:10px;border:1px solid #ccc;">
			<form id="form_add" method="post" novalidate>
				<table>
					<#assign idx=0>
					<#foreach prop in result.getColumns()>
					<#if !prop.pKey>
					<#assign cls="base-input">
					<#assign param="">
					<#assign required="">
					<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
						<#assign cls=cls + " easyui-datetimebox">
					<#elseif "java.lang.Long" == prop.columnType || "java.lang.Integer" == prop.columnType>
						<#assign cls=cls + " easyui-numberbox">
					<#elseif "java.math.BigDecimal" == prop.columnType>
						<#assign cls=cls + " easyui-numberbox">
						<#assign param=" data-options=\"precision:${prop.scale}\" ">
					</#if>
					<#if !prop.nullable>
						<#assign cls=cls + " easyui-validatebox">
						<#assign required=" required=\"true\"">
					</#if>
					
					<#if idx%2 == 0>
					<tr>
						<td nowrap="nowrap" align="right">
							<label for="${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}">${prop.remarks}:</label>
						</td>
						<td>
							<input type="text" class="${cls}"${param} name="${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}"${required}/>
						</td>
						<td width="20">
						</td>
					<#else>
						<td nowrap="nowrap" align="right">
							<label for="${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}">${prop.remarks}:</label>
						</td>
						<td>
							<input type="text" class="${cls}"${param} name="${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}"${required}/>
						</td>
					</tr>
					</#if>
					<#assign idx=idx + 1>
					</#if>
					</#foreach>
					<#if idx%2 != 0>
						<td></td><td></td>
					</tr>
					</#if>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveRecord()">提交</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="closeWindow('window_add')">关闭</a>
		</div>
	</div>
	
</body>
</html>