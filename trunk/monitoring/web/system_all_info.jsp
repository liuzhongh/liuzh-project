<%@page import="com.shangkang.manager.SystemInfoManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>系统信息比较图表</title>
<script type="text/javascript" src="./js/jquery-2.0.3.min.js"></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/SystemInfoAction.js'></script>
<script type="text/javascript" src="./js/highcharts.js"></script>
<script type="text/javascript" src="./js/exporting.js"></script>

</head>
<body>

 <div id="processes_info" style="min-width: 400px; margin: 0 auto; text-align: center;display: none;"></div>

 <div id="memory_info" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
 
 <div id="current_connections" style="min-width: 400px; height: 400px; margin: 0 auto;display: none;"></div>
<script type="text/javascript">
<%
String filePath = application.getRealPath(SystemInfoManager.FILE_PATH);
filePath = filePath.replace("\\", "/");
%>

var filePath = "<%=filePath%>";
$(function(){
	SystemInfoAction.createCompareSystemInfo(filePath, function(data){
		//data = data.map;
		newHighcharts(data.keys, data.processes, 'line', 'processes_info', 'Processes info');
		newHighcharts(data.keys, data.memorys, 'line', 'memory_info', 'Memory info');
		newHighcharts(data.keys, data.connections, 'line', 'current_connections', 'Current Connections');
	});
});

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
					this.x + "【" + this.y + "】";
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
