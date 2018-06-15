$(function() {
	nodesTable('#nodes',"/crawler/cluster/info","get");
	// 编辑表格
});



function nodesTable(tableid,url,method){
	if("get"==method){
		$.get(url, function(result){
			var errCode=result.errCode;
			if(null==errCode){
				var data=result.data;
				var table=$(tableid);
				var node;
				for(var i=0;i<data.length;i++){
					node=data[i];
					var tr=$("<tr></tr>");
					$("<td>"+node.name+"</td>").appendTo(tr);
					$("<td>"+node.type+"</td>").appendTo(tr);
					$("<td>"+node.host+"</td>").appendTo(tr);
					$("<td>"+node.port+"</td>").appendTo(tr);
					$("<td>"+node.cpu+"</td>").appendTo(tr);
					$("<td>"+node.mem+"</td>").appendTo(tr);
					$("<td>"+node.runningWorkerMaxSize+"</td>").appendTo(tr);
					$("<td>"+node.totalJobSize+"</td>").appendTo(tr);
					$("<td>"+node.totalScheduleJobSize+"</td>").appendTo(tr);
					$("<td>"+node.totalNoScheduleJobSize+"</td>").appendTo(tr);
					$("<td>"+node.runningWorkerSize+"</td>").appendTo(tr);
					tr.appendTo(table);
				}
			}
		  });
	}
}