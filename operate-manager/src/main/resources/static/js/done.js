
$(function() {
	// 加载数据
	loadTable("/crawler/job/queue/done", "get", "");
});

function loadTable(url, method, params) {
	if ("get" == method) {
		$.get(url, function(result) {
			var errCode = result.errCode;
			if (null == errCode) {
				showQueueDoneTable(result.data);
			}
		});
	}
}


function showQueueDoneTable(data) {
	var table = $('#done_table');
	table.find("tr").remove();
	var doneInfo;
	var i = 0;
	for (var i = 0; i < data.length; i++) {
		doneInfo = data[i];
		var tr = $("<tr></tr>");
		$("<td name='queueName' id='" + doneInfo.queueName + "' >" + doneInfo.queueName + "</td>").appendTo(tr);
		$("<td>" + doneInfo.size + "</td>").appendTo(tr);
		$("<td><a href=\"javascript:cleanQueueDone('"+doneInfo.queueName+"')\">clean</a></td>").appendTo(tr);
		tr.appendTo(table);
	}
}


function cleanQueueDone(queueName) {
	if (window.confirm("do you clean queueDone:"+queueName)) {
		var url = "/crawler/job/queue/done/clean/" + queueName;
		$.get(url, function(result) {
			alert(result.msg);
			location.reload(true); 
		});
	}
}















