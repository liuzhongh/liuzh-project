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
<%@include file="/include/jsp_headers.jsp"%>
<script type="text/javascript" src="<%=commonJsPath %>/base/${parameterType}.js"></script>
</head>
<body>
	<table id="list_tb" class="easyui-datagrid" data-options="fit:true,title:'${tableRemark}管理',iconCls:'icon-save',nowrap:true,
		autoRowHeight:false,striped:true,collapsible:true,url:'<%=ctxtPath %>/${parameterType}Action!listPg${parameterType}.action',fitCloumns:true,remoteSort:false,
		idField:'${result.getFirstCharacterLowercase(result.getKey())}',rownumbers:true,pagination:true,toolbar:'#tbar'">
		<thead>  
            <tr>  
            	<th data-options="field:'ck',checkbox:true,align:'center'"></th>  
            	<#foreach prop in result.getColumns()>
				<#if !prop.pKey>
				<th data-options="field:'${result.getFirstCharacterLowercase(prop.columnName)}',width:120,sortable:true,
					<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>align:'center'
					<#elseif "java.lang.Long" == prop.columnType || "java.lang.Integer" == prop.columnType || "java.math.BigDecimal" == prop.columnType>align:'right'
					<#else>align:'left'</#if>">${prop.remarks}</th>  
				</#if>
				</#foreach>
            </tr>  
        </thead>  
	</table>
	
	<div id="tbar" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" onclick="addRecord()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="#" onclick="editRecord()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="#" onclick="deleteRecords()" class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
			<a href="#" onclick="doSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a href="#" onclick="doClear()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">清除</a>
		</div>
		<div>
			<form id="form_search">
			<table>
				<#assign idx=0>
				<#foreach prop in result.getColumns()>
				<#if !prop.pKey>
				<#assign cls="base-input">
				<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
					<#assign cls=cls + " easyui-datetimebox">
				<#elseif "java.lang.Long" == prop.columnType || "java.lang.Integer" == prop.columnType>
					<#assign cls=cls + " easyui-numberbox">
				<#elseif "java.math.BigDecimal" == prop.columnType>
					<#assign cls=cls + " easyui-numberbox">
				</#if>
				
				<#if idx%4 == 0>
				<tr>
					<td nowrap="nowrap" align="right">
						<label>${prop.remarks}:</label>
					</td>
					<td>
						<input type="text" class="${cls}" id="i_${result.getFirstCharacterLowercase(prop.columnName)}"/>
					</td>
					<td width="20">
					</td>
				<#elseif idx%4 == 1>
					<td nowrap="nowrap" align="right">
						<label>${prop.remarks}:</label>
					</td>
					<td>
						<input type="text" class="${cls}" id="i_${result.getFirstCharacterLowercase(prop.columnName)}"/>
					</td>
					<td width="20">
					</td>
				<#elseif idx%4 == 2>
					<td nowrap="nowrap" align="right">
						<label>${prop.remarks}:</label>
					</td>
					<td>
						<input type="text" class="${cls}" id="i_${result.getFirstCharacterLowercase(prop.columnName)}"/>
					</td>
					<td width="20">
					</td>
				<#elseif idx%4 == 3>
					<td nowrap="nowrap" align="right">
						<label>${prop.remarks}:</label>
					</td>
					<td>
						<input type="text" class="${cls}" id="i_${result.getFirstCharacterLowercase(prop.columnName)}"/>
					</td>
				</tr>
				</#if>
				<#assign idx=idx + 1>
				</#if>
				</#foreach>
				<#if idx%4 != 0>
					<td rowspan="8">
				</tr>
				<#elseif idx%4 == 1>
					<td rowspan="5">
				</tr>
				<#elseif idx%4 == 2>
					<td rowspan="2">
				</tr>
				</#if>
				
			</table>
			</form>
		</div>
	</div>
	<div id="window_add" class="easyui-window" data-options="title:'新增记录',iconCls:'icon-save',modal:true,href:'<%=ctxtPath %>/base/${parameterType}PopWin.jsp'" closed="true" style="width:500px;height:500px;padding:5px;">
	</div>
	
</body>
</html>