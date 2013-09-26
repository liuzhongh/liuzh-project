/** 
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
 *
 **/
<#assign parameterType="${result.getFirstCharacterUppercase(result.getTableName())}">
<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">
<#assign tableRemark="${result.getTableRemarks() ! parameterType}">

$(function() {
	$(".datebox :text").attr("readonly","readonly");
});

function deleteRecords() {
	var ids = [];
	var rows = $('#list_tb').datagrid('getSelections');

	if (!rows || rows.length < 1) {
		alert('请选择要删除的记录!');
		return;
	}
	
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].${result.getFirstCharacterLowercase(result.getKey())});
	}

	sConfirm("确认删除所选记录?", null ,function(cf){
		if(cf)
		{
			$.ajax({
				type: "POST",
				url: contextPath + '/${parameterType}Action!delete${parameterType}.action',
				dataType:"json",
				data:JSON.stringify({primaryKeys: ids}),
				contentType:"application/json",
				success:function(){
					$('#list_tb').datagrid('reload');
					alert("删除记录成功!");
				}
			});
		}
	});
}

function openWindow(wind)
{
	$('#' + wind).window('open');
}

function closeWindow(wind)
{
	$('#' + wind).window('close');
}

var url;

function saveRecord()
{
	$('#form_add').form('submit', {  
	    url: contextPath + url,
	    onSubmit: function(){
			return $(this).form('validate');
		},  
	    success:function(data){ 
	    	var dt = eval('('+ data +')');
	    	if(dt.errorMsg)
	    		alert(dt.errorMsg);
	    	else{
		      	$('#window_add').dialog('close');
		        $('#list_tb').datagrid('reload');
		        alert('业务操作成功!');
	    	}
	    }  
	
	}); 
}

function editRecord()
{
	var row = $('#list_tb').datagrid('getSelected');
	
	if (row){
		$('#window_add').dialog('open').dialog('setTitle','修改记录');
		$('#form_add').form('clear');
		$('#form_add').form('load', row, '${tableLowercaseField}.');
		url = '/${parameterType}Action!update${parameterType}.action?ajaxErrorCode=update${parameterType}&${tableLowercaseField}.${result.getFirstCharacterLowercase(result.getKey())}=' + row['${result.getFirstCharacterLowercase(result.getKey())}'];
	}else{
		alert('请选择要修改的记录!');
		return;
	}
}

function addRecord()
{
	url = '/${parameterType}Action!add${parameterType}?ajaxErrorCode=add${parameterType}';
	openWindow('window_add');
	$('#window_add').dialog('open').dialog('setTitle','新增记录');
	$('#form_add').form('clear');
}

function doSearch()
{
	var queryParams = $('#list_tb').datagrid('options').queryParams;

    <#foreach prop in result.getColumns()>
	<#if !prop.pKey>
	<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
	queryParams["${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}"] = $.trim($('#i_${result.getFirstCharacterLowercase(prop.columnName)}').datebox('getValue'));
	<#else>
	queryParams["${tableLowercaseField}.${result.getFirstCharacterLowercase(prop.columnName)}"] = $.trim($('#i_${result.getFirstCharacterLowercase(prop.columnName)}').val());
	</#if>
	</#if>
	</#foreach>
    
	$('#list_tb').datagrid('reload');
}

function doClear()
{
	$('#form_search').form('clear');
}