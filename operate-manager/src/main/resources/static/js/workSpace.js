function loadQueueTable() {
	var url="/crawler/workSpace/getWorkSpaces";
	$.get(url, function(result) {
		showWorkSpaceDiv(result.data);
	});
}

function showWorkSpaceDiv(data) {
	var div=$("#workSpace_div");
	var table =div.find("table")
	table.find("tr[name='workSpaceInfo']").remove();
	var workSpaceInfo;
	var i = 0;
	for (var i = 0; i < data.length; i++) {
		workSpaceInfo = data[i];
		var tr = $("<tr class='workSpaceInfo' ></tr>");
		$(
				"<td name='queueName' id='" + workSpaceInfo.workSpaceName + "' >"
						+ workSpaceInfo.workSpaceName + "</td>").appendTo(tr);
		$("<td>" + workSpaceInfo.doingSize + "</td>").appendTo(tr);
		$("<td>" + workSpaceInfo.errSize + "</td>").appendTo(tr);
		$("<td>" + workSpaceInfo.doneSize + "</td>").appendTo(tr);
		$("<td><a href=\"javascript:clearDoing('"+ workSpaceInfo.workSpaceName+ "')\">清理doing</a>" +
				"&nbsp;&nbsp;<a href=\"javascript:clearErr('"+ workSpaceInfo.workSpaceName+ "')\">清理Err</a>" +
				"&nbsp;&nbsp;<a href=\"javascript:clearDone('"+ workSpaceInfo.workSpaceName + "')\">清理done</a></td>")
				.appendTo(tr);
		tr.appendTo(table);
	}
}

function showDoingDataDiv(workSpaceName) {
	var workSpaceDiv=$("#job_workSpace_div");
	var workSpace_table = workSpaceDiv.find("table");
	var doingDataCursor = workSpaceDiv.find("input[id='doingDataCursor']").val();
	var doingDataCursorInputs=workSpaceDiv.find("input[id='errDataCursor_"+workSpaceName+"']");
	var doingDataCursor = "0";
	if(null!=doingDataCursorInputs&&doingDataCursorInputs.length>0){
		doingDataCursor=doingDataCursorInputs.val();
	}
	var url = "/crawler/workSpace/getDoingData/" + workSpaceName + "/" + doingDataCursor;
	$.get(url, function(result) {
		var dataMap = result.data;
		var cursor = dataMap.cursor;
		var data = dataMap.list;
		workSpaceDiv.find("[id='job_workSpace_name']").html("工作空间[<span style='color:#FF0000'>"+ workSpaceName+ "</span>]信息" +
				"<a  href=\"javascript:clearDoing('"+ workSpaceName+ "')\">全部删除</a>&nbsp;");
		if (null != data && data.length > 0) {
			workSpaceDiv.find("input[id='doingDataCursor']").val(cursor);
			workSpace_table.find("[name='data_page']").remove();
			workSpace_table.find("[name='dataCursor']").remove();
			for (var i = 0; i < data.length; i++) {
				var page = data[i];
				var tr = $("<tr name='data_page'></tr>");
				$("<td><span style='color:#FF0000;font-size:6px;'>"
						+ page.originalUrl + "</span></td>").appendTo(tr);
				var operationTd = $("<td></td>");
				var operation = "<a  href=\"javascript:clearDoing('"+ workSpaceName+ "')\">全部删除</a>&nbsp;";
				$(operation).appendTo(operationTd);
				operationTd.appendTo(tr);
				tr.appendTo(workSpace_table);
			}
			$("<input type='text' name='dataCursor' id='doingDataCursor_"+workSpaceName+"' value='"+cursor+"' style='display:none'/>").appendTo(workSpace_table);
		}
		showLayer(workSpaceDiv);
	});
}

function showErrDataDiv(workSpaceName) {
	var workSpaceDiv=$("#job_workSpace_div");
	var workSpace_table = workSpaceDiv.find("table");
	var errDataCursorInputs=workSpaceDiv.find("input[id='errDataCursor_"+workSpaceName+"']");
	var errDataCursor = "0";
	if(null!=errDataCursorInputs&&errDataCursorInputs.length>0){
		errDataCursor=errDataCursorInputs.val();
	}
	var url = "/crawler/workSpace/getErrData/" + workSpaceName + "/" + errDataCursor;
	$.get(url, function(result) {
		var dataMap = result.data;
		var cursor = dataMap.cursor;
		var data = dataMap.list;
		workSpaceDiv.find("[id='job_workSpace_name']").html(
				"工作空间[<span style='color:#FF0000'>" + workSpaceName+ "</span>]信息"+
				"<a  href=\"javascript:clearErr('"+workSpaceName+ "')\">全部删除</a>&nbsp;"+
				"<a href=\"javascript:againDoErrQueue('"+workSpaceName+"')\">全部处理</a>&nbsp;");
		if (null != data && data.length > 0) {
			workSpaceDiv.find("input[id='errDataCursor']").val(cursor);
			workSpace_table.find("[name='data_page']").remove();
			workSpace_table.find("[name='dataCursor']").remove();
			for (var i = 0; i < data.length; i++) {
				var page = data[i];
				var tr = $("<tr name='data_page'></tr>");
				$("<td><span style='color:#FF0000;font-size:6px;'>"
						+ page.url + "</span></td>").appendTo(tr);
				var operationTd = $("<td></td>");
				var operation = "<a  href=\"javascript:clearErr('"+workSpaceName+ "')\">全部删除</a>&nbsp;";
				operation += "<a href=\"javascript:againDoErrQueue('"+workSpaceName+"')\">全部处理</a>&nbsp;";
				$(operation).appendTo(operationTd);
				operationTd.appendTo(tr);
				tr.appendTo(workSpace_table);
			}
			$("<input type='text' name='dataCursor' id='errDataCursor_"+workSpaceName+"' value='"+cursor+"' style='display:none'/>").appendTo(workSpace_table);
		}
		showLayer(workSpaceDiv);
	});
}

function clearDoing(workSpaceName) {
	if (window.confirm("do you clear workSpace["+workSpaceName+"] doing data")) {
		var url = "/crawler/workSpace/clearDoing/" + workSpaceName;
		$.get(url, function(result) {
			alert(result.msg);
		});
	}
}

function clearErr(workSpaceName) {
	if (window.confirm("do you clear workSpace["+workSpaceName+"] err data")) {
		var url = "/crawler/workSpace/clearErr/" + workSpaceName;
		$.get(url, function(result) {
			alert(result.msg);
		});
	}
}

function clearDone(workSpaceName) {
	if (window.confirm("do you clear workSpace["+workSpaceName+"] done data")) {
		var url = "/crawler/workSpace/clearDone/" + workSpaceName;
		$.get(url, function(result) {
			alert(result.msg);
		});
	}
}

function doErrQueue(pageKey) {

}

function repairQueue(queueName) {
	if (window.confirm("do you repair queue:" + queueName)) {
		var url = "/crawler/workSpace/repairQueue/" + queueName;
		$.get(url, function(result) {
			alert(result.msg);
		});
	}
}


function againDoErrQueue(queueName) {
	if (window.confirm("do you againDoErrQueue queue:" + queueName)) {
		var url = "/crawler/workSpace/againDoErrQueue/" + queueName;
		$.get(url, function(result) {
			alert(result.msg);
		});
	}
}
