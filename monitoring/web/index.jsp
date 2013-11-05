<%@page import="com.shangkang.manager.SystemInfoManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>系统信息图表</title>
<script type="text/javascript" src="./js/jquery-2.0.3.min.js"></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/SystemInfoAction.js'></script>
<script type="text/javascript" src="./js/highcharts.js"></script>
<script type="text/javascript" src="./js/exporting.js"></script>

</head>
<body>
<label>选择资源文件:</label>
<select id="fileName" onchange='load($("#fileName").val())'>
	<c:forEach items="${fileNames }" var="n">
		<option value="${n.key }">${n.value }</option>
	</c:forEach>
</select>

 <div id="users" style="min-width: 400px; margin: 0 auto; text-align: center;display: none;"></div>

 <div id="disks_usage" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
 
 <div id="processes_info" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
 
 <div id="memory_info" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
 
 <div id="current_connections" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
<script type="text/javascript">
<%
	String filePath = application.getRealPath(SystemInfoManager.FILE_PATH);
	filePath = filePath.replace("\\", "/");
%>

var filePath = "<%=filePath%>";
var $fileName = document.getElementById("fileName");
var fileName = $fileName.value;
$(function(){
	SystemInfoAction.findSystemResources(filePath, function(data){
		var str = "";
		for (var i = 0; i < data.length; i++) {
			str = str + '<option value="'+data[i]+'">'+data[i]+'</option>';
		}
		
		$fileName.insertAdjacentHTML("beforeEnd", str);
		
		if(data[0])
		{
			load(data[0]);
		}
	});
	
});

function load(fileName)
{
	SystemInfoAction.createSystemInfo(filePath, fileName, success);
}

function success(data){
	var str = "<h2>Users</h2>";
	for(var i=0;i<data.users.length;i++)
	{
		str += (data.users[i] + " ");
	}
	
	$("#users").html(str).show();
	
	newHighcharts(data.disks.type, 
	[{
		name: 'Size(G)',
		data: data.disks["Size"]
	},{
		name: 'Used(G)',
		data: data.disks["Used"]
	},{
		name: 'Avail(G)',
		data: data.disks["Avail"]
	},{
		name: 'Use(%)',
		data: data.disks["Use%"]
	}], 'column', 'disks_usage', 'Disks usage');
	newHighcharts(data.processes.type, [{name: '%CPU', data: data.processes["%CPU"]}, {name: '%MEM', data: data.processes["%MEM"]}], 'column', 'processes_info', 'Processes info');
	newHighcharts(data.memorys.key, [{name: '内存', data: data.memorys.value}], 'column', 'memory_info', 'Memory info');
	newHighcharts(data.connections.key, [{name: '连接数', data: data.connections.value}], 'column', 'current_connections', 'Current Connections');
}

function newHighcharts(keys, data, type, id, title)
{
	$("#"+id).show();
	var h = new Highcharts.Chart({
		chart: {
			renderTo: id,
			type: type,
			marginRight: 150,
			marginBottom: 50
		},
		title: {
			text: title,
			x: -20 //center
		},
/* 			subtitle: {
			text: 'Source: WorldClimate.com',
			x: -20
		}, */
		xAxis: {
			categories: keys
		},
		yAxis: {
			title: {
				text: ''
			},
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
					this.x + ":" + this.y;
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right',
 			verticalAlign: 'top',
			x: -10,
			y: 100, 
			borderWidth: 0
		},
		series: data
	});
	
	return h;
}
</script>

</body>
</html>
