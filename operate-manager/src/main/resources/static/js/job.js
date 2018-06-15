var stompClient = null;
var connected = false;
var connectDelayTime = 2000;
var refreshJobSnapshotDelayTime =1000;
var job_state = "job_state_";
var job_start_time = "job_start_time_";
var job_end_time = "job_end_time_";
var job_queue_count = "job_queue_count_";
var job_total_process_count = "job_total_process_count_";
var job_total_result_count = "job_total_result_count_";
var job_exception_count = "job_total_process_count_";
var job_avg_process_time = "job_avg_process_time_";
var job_max_process_time = "job_max_process_time_";
var job_min_Process_time = "job_min_Process_time_";
var job_cronTrigger="job_cronTrigger_";
var job_queue_operation="job_queue_operation_";
var job_operation="job_operation_";
var job_scheduled="job_scheduledTd_";

$(function() {
	$("#search_job_bt").click(function(){
		$('#job_search').find('#pageIndex').val(0);
		searchJob();
	});
	$("#create_job_bt").click(function(){
		searchJob();
	});
	$("#upload_jobProfile").click(function() {
		uploadJobProfile();
	});
	$("#search_job_name").bind('keydown',function(event){  
	  if(event.keyCode == "13"){ 
		  $('#job_search').find('#pageIndex').val(0);
		  searchJob();
	  }  
	});  
	connect();
	// 加载数据
	searchJob();
});

function showDiv(showDivID){
	var showDivs=$("div[class='showDiv']");
	for (var i = 0; i < showDivs.length; i++) {
		var showDiv=$(showDivs[i]);
		var display=showDiv.css("display");
		var lastShowDivId=showDiv.attr("id");
		if(showDivID!=lastShowDivId&&("inline"==display||"inline-block"==display||"block"==display)){
			showDiv.css("display","none");
			var showDivIdRecordInput=$("<input type='text' name='showDivIdRecordInput' value='"+lastShowDivId+"' style='display: none' />");
			showDivIdRecordInput.appendTo($("#showDivIdRecordDiv"));
		}else if(showDivID==showDiv.attr("id")){
			showDiv.css("display","block");	
		}else{
			showDiv.css("display","none");
		}
	}	
}

function returnLastShowDiv(){
	var showDivIdRecordInputs=$("#showDivIdRecordDiv").find("input[name='showDivIdRecordInput']");
	if(showDivIdRecordInputs.length>0){
		var lastShowDivInput=$(showDivIdRecordInputs[showDivIdRecordInputs.length-1]);
		var lastShowDivId=lastShowDivInput.val();
		var showDivs=$("div[class='showDiv']");
		for (var i = 0; i < showDivs.length; i++) {
			var showDiv=$(showDivs[i]);
			if(lastShowDivId==showDiv.attr("id")){
				showDiv.css("display","block");	
			}else{
				showDiv.css("display","none");
			}
		}	
		lastShowDivInput.remove();
	}
}
/**
 * 与后台建立websocket连接
 * 
 * @returns
 */
function connect() {
	if(!connected){
		var socket = new SockJS('/crawler_websocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	connected=true;
	        stompClient.subscribe('/topic/job/jobSnapshot', function (responseMsg) {
	        	var msg=JSON.parse(responseMsg.body);
	        	if (msg.isOk == 1) {
	        		refreshJobSnapshots(msg.data);
	    		}
	        });
	    });
	    window.setTimeout(connect, connectDelayTime);
	}else{
		window.setTimeout(requestRefreshJobSnapshot,refreshJobSnapshotDelayTime);
	}
}

/**
 * 请求刷新job运行快照
 * 
 * @returns
 */
function requestRefreshJobSnapshot() {
	var jobTrs = $('#jobs').find("tr");
	var jobNames="";
	var workSpaceNames="";
	var requestMsg=new Object();
	for (var i = 0; i < jobTrs.length; i++) {
		var jobTr=$(jobTrs[i]);
		var jobSnapshot = new Object();
		jobNames+=jobTr.find("[name='name']").val()+",";
		workSpaceNames+=jobTr.find("[name='workSpaceName']").val()+",";
	}
	requestMsg.jobNames=jobNames;
	requestMsg.workSpaceNames=workSpaceNames;
    stompClient.send("/crawler/refreshJobSnapshot", {}, JSON.stringify(requestMsg));
    window.setTimeout(requestRefreshJobSnapshot,refreshJobSnapshotDelayTime);
}

/**
 * 动态显示job 运行信息
 * 
 * @param jobSnapshots
 * @returns
 */
function refreshJobSnapshots(jobSnapshots) {
	if(null!=jobSnapshots){
		for (var i = 0; i < jobSnapshots.length; i++) {
			var jobSnapshot = jobSnapshots[i];
			var jobName = jobSnapshot.name;
			var jobTr=$("tr[name='"+jobName+"']");
			if(null!=jobTr&&undefined!=jobTr){
				var oldStatus=jobTr.find("[name='state']").text();
				var newStatus=getState(jobSnapshot.status);
				// 只有当状态发生改变时才更新一下内容
				if(oldStatus!=newStatus){
					var color=getStateColor(newStatus);
					if(jobSnapshot.status==3||jobSnapshot.status==4){
						newStatus="<a  style='color:"+color+"' href=\"javascript:masterScheduled.showWorkerInfo('" + jobName+ "')\">"+newStatus+"</a>";	
					}
					jobTr.find("[name='state']").css("color",color).html(newStatus);
					var startTime=jobSnapshot.startTime;
					var startTimeTd =jobTr.find("[name='startTime']");
					var oldStartTime=startTimeTd.html();
					if(""==oldStartTime&&startTime!=null&&startTime!="null"&&startTime!=""){
						startTime=startTime.substring(5,startTime.length);
						startTimeTd.html(startTime);
					}
					if(""!=oldStartTime&&(startTime==null||startTime=="null"||startTime=="")){
						startTimeTd.html("");
					}
					
					var opetationHtml=masterScheduled.getOperation(jobSnapshot.name,jobSnapshot.status);
					$("#" + job_operation + jobName).html(opetationHtml);
				}
				var workSpaceShowStr=getWorkSpaceHtml(jobSnapshot.workSpaceName,
						jobSnapshot.totalProcessCount,
						jobSnapshot.workSpaceDoingSize,
						jobSnapshot.workSpaceErrSize);
				$("#" + job_queue_count + jobName).html(workSpaceShowStr);
				var errCountHtml=getErrCountHtml(jobSnapshot.name,jobSnapshot.id,jobSnapshot.errCount);
				$("#" + job_exception_count + jobName).html(errCountHtml);	
			}
		}
	}
}

function getErrCountHtml(jobName,jobSnapshotId,errCount){
	var errCountHtml;
	if(errCount>0){
		errCountHtml="<a href=\"javascript:showErrMsg('" + jobName+ "','" + jobSnapshotId+ "')\">"+errCount+"</a>";
	}else{
		errCountHtml=errCount;
	}
	return errCountHtml;
}


function showErrMsg(jobName,jobSnapshotId){
	var workerErrMsgDiv=$("#job_worker_errMsg_div");
	var pageIndex=workerErrMsgDiv.find("input[id='pageIndex']").val();
	var url = "/crawler/job/jobSnapshot/queryErrMsg";
	$.post(url, {
		jobName:jobName,
		jobSnapshotId : jobSnapshotId,
		pageIndex : pageIndex
	}, function(responseMsg) {
		if (responseMsg.isOk == 1) {
			var data=responseMsg.data.list;
			var table = workerErrMsgDiv.find('table');
			workerErrMsgDiv.find('#jobName').val(jobName);
			workerErrMsgDiv.find('#jobSnapshotId').val(jobSnapshotId);
			var table=workerErrMsgDiv.find("table");
			table.find("tr[name='jobWorkerErrMsgDiv']").remove();
			if (null != data && data.length > 0) {
				workerErrMsgDiv.find("input[id='pageIndex']").val(pageIndex);
				for (var i = 0; i < data.length; i++) {
					var workerErrMsg = data[i];
					var tr = $("<tr name='jobWorkerErrMsgDiv'></tr>");
					$("<td>"+ workerErrMsg.workerName + "</td>").appendTo(tr);
					$("<td>"+ workerErrMsg.startTime + "</td>").appendTo(tr);
					var divId="workerErrMsg_"+workerErrMsg.id;
					$("<td><a href=\"javascript:showErrMsgDetail('" + divId+ "')\">"+ workerErrMsg.type + "</a></td>").appendTo(tr);
					$("<div id='"+divId+"' style='display: none;'><pre style='color:#FF0000;font-size:10px' >"+ workerErrMsg.msg + "</pre></div>").appendTo(tr);
					tr.appendTo(table);
				}
				pageInfo("#job_worker_errMsg_div",responseMsg,"lastSearchJobWorkerErrMsg","nextSearchWorkerErrMsg");
			}
			showDiv("job_worker_errMsg_div");
		}
	});
}

function showErrMsgDetail(divId){
	showLayer($("#"+divId));
}

function nextSearchWorkerErrMsg() {
	var workerErrMsgDiv=$('#job_worker_errMsg_div');
	var jobName=workerErrMsgDiv.find('#jobName').val();
	var jobSnapshotId=workerErrMsgDiv.find('#jobSnapshotId').val();
	var pageIndex=workerErrMsgDiv.find('#pageIndex').val();
	pageIndex=parseInt(pageIndex);
	pageIndex=pageIndex+1;
	workerErrMsgDiv.find('#pageIndex').val(pageIndex)
	showErrMsg(jobName,jobSnapshotId);
}

function lastSearchJobWorkerErrMsg() {
	var workerErrMsgDiv=$('#job_worker_errMsg_div');
	var jobName=workerErrMsgDiv.find('#jobName').val();
	var jobSnapshotId=workerErrMsgDiv.find('#jobSnapshotId').val();
	var pageIndex=workerErrMsgDiv.find('#pageIndex').val();
	pageIndex=parseInt(pageIndex);
	pageIndex=pageIndex-1;
	workerErrMsgDiv.find('#pageIndex').val(pageIndex)
	showErrMsg(jobName,jobSnapshotId);
}



function uploadJobProfile(url,file) {
	var url="/crawler/job/upload/profile";
	uploadFile(url,$("#jobProfile")[0].files[0]);
}


function searchJob() {
	var jobName = $('#search_job_name').val();
	var jobSearch=$('#job_search');
	var pageIndex=jobSearch.find('#pageIndex').val();
	var url="/crawler/job/query";
	$.post(url, {
		pageIndex:pageIndex,
		jobName : jobName
	}, function(responseMsg) {
		if (responseMsg.isOk == 1) {
			showJobTable(responseMsg);
		}
	});
}

function nextSearchJob() {
	var jobSearch=$('#job_search');
	var pageIndex=jobSearch.find('#pageIndex').val();
	pageIndex=parseInt(pageIndex);
	pageIndex=pageIndex+1;
	jobSearch.find('#pageIndex').val(pageIndex)
	searchJob();
}

function lastSearchJob() {
	var jobSearch=$('#job_search');
	var pageIndex=jobSearch.find('#pageIndex').val();
	pageIndex=parseInt(pageIndex);
	pageIndex=pageIndex-1;
	jobSearch.find('#pageIndex').val(pageIndex)
	searchJob();
}

function showJobTable(result) {
	var jobs=result.data.list;
	if(null!=jobs&&jobs.length>0){
		var jobDiv=$('#job_div');
		var table = jobDiv.find('table');
		table.find("tr[class=job]").remove();
		var job;
		for (var i = 0; i < jobs.length; i++) {
			job = jobs[i];
			var tr = $("<tr class='job' name='"+job.name+"' title='"+job.describe+"'></tr>");
			$("<input name='version' style='display:none' type='text' value='"+job.version+"' />").appendTo(tr);
			$("<input name='name' style='display:none' type='text' value='"+job.name+"' />").appendTo(tr);
			$("<input name='level' style='display:none' type='text' value='"+job.level+"' />").appendTo(tr);
			$("<input name='workSpaceName' style='display:none' type='text' value='"+job.workSpaceName+"' />").appendTo(tr);
			$("<input name='workerClass' style='display:none' type='text' value='"+job.workerClass+"' />").appendTo(tr);
			$("<input name='designatedNodeName' style='display:none' type='text' value='"+job.designatedNodeName+"' />").appendTo(tr);
			$("<input name='needNodes' style='display:none' type='text' value='"+job.needNodes+"' />").appendTo(tr);
			$("<input name='isScheduled' style='display:none' type='text' value='"+job.isScheduled+"' />").appendTo(tr);
			$("<input name='describe' style='display:none' type='text' value='"+job.describe+"' />").appendTo(tr);
			$("<td title='"+job.name+"'>"+job.name+"</td>").appendTo(tr);
			var cronTrigger=job.cronTrigger;
			var ctTd=$("<td></td");
			$("<input name='cronTrigger'  flag='"+job.name+"' style='display:none' type='text' value='"+cronTrigger+"' />").appendTo(ctTd);
			$("<input name='update_cronTrigger'  flag='"+job.name+"'  style='display:none' type='text' value='"+cronTrigger+"' />").appendTo(ctTd);
			if(cronTrigger==null||cronTrigger==""||cronTrigger=="null"){
				cronTrigger="#";
			}
			$("<a     name='cronTrigger'  style='color:#FF0000;text-decoration:none;' href=\"javascript:editCronTrigger('"+job.name+"')\">"+cronTrigger+"</a>").appendTo(ctTd);
			ctTd.appendTo(tr);
			
			var scheduledTd = $("<td name='isScheduled'></td>");
			var scheduledOperation=getIsScheduledShowHtml(job.name,job.isScheduled);
			$(scheduledOperation).appendTo(scheduledTd);
			scheduledTd.appendTo(tr);
			
			var status=getState(job.status);
			var color=getStateColor(status);
			$("<td name='state' style='font-weight:bold;color:"+color+"'>"+ status + "</td>").appendTo(tr);
			$("<td name='startTime'></td>").appendTo(tr);
			var queueHtml=getWorkSpaceHtml(job.queueName,0,0,0);
			$("<td id='" + job_queue_count + job.name + "'>"+queueHtml+"</td>").appendTo(tr);
	
			$("<td id='" + job_exception_count + job.name + "'>0</td>").appendTo(tr);
			
			
			var td = $("<td id='" + job_operation + job.name + "'></td>");
			var html=masterScheduled.getOperation(job.name,job.state);
			$(html).appendTo(td);
			td.appendTo(tr);
			var otherTd = $("<td></td>");
			var otherOperation="<a href=\"javascript:showHistoryJobSnapshot('" + job.name+ "')\">记录</a>&nbsp;";
			otherOperation += "<a href=\"javascript:showJobParams('" + job.name+ "')\">配置</a>&nbsp;";
			otherOperation += "<a href=\"javascript:showExtractItemTable('" + job.name+ "')\">抽取项</a>&nbsp;";
			otherOperation += "<a href=\"/crawler/job/download/profile/"+ job.name+"\"  >下载</a>";
			$(otherOperation).appendTo(otherTd);
			otherTd.appendTo(tr);
			tr.appendTo(table);
		}
		table.find("input[name='update_cronTrigger']").bind('keydown',function(event){  
			  if(event.keyCode == "13"){ 
				  var jobName=$(this).attr("flag");
				  updateCronTrigger(jobName);  
			  }  
		});  
		table.find("input[name='update_nextJobName']").bind('keydown',function(event){  
			  if(event.keyCode == "13"){ 
				  var jobName=$(this).attr("flag");
				  updateNextJobName(jobName);  
			  }  
		}); 
		pageInfo("#job_div",result,"lastSearchJob","nextSearchJob");
	}
}

function pageInfo(showDivId,responseMsg,lastSearch,nextSearch){
	var pageIndex=responseMsg.data.pageIndex;
	var pageSize=responseMsg.data.pageSize;
	var totalPage=responseMsg.data.totalPage;
	var totalSize=responseMsg.data.totalSize;
	var showDivId=$(showDivId);
	showDivId.find('#pageIndex').val(pageIndex);
	showDivId.find('#totalPage').val(totalPage);
	showDivId.find('#totalSize').val(totalSize);
	var pageInfoHtml="<div><span>";
	pageInfoHtml+="总共<span style='color:#FF0000;font-weight:bold'>"+totalSize+"</span>条记录";
	pageInfoHtml+="每页<span style='color:#FF0000;font-weight:bold'>"+pageSize+"</span>条";
	pageInfoHtml+="总页数:<span style='color:#FF0000;font-weight:bold'>"+totalPage+"</span>";
	pageInfoHtml+="当前页:<span style='color:#FF0000;font-weight:bold'>"+(parseInt(pageIndex)+1)+"</span>";
	pageInfoHtml+="</span>";
	if(totalPage>1){
		if(pageIndex>=totalPage-1){
			pageInfoHtml+="<a class='pull-right' style='color:#FF0000;font-weight:bold' href='javascript:"+lastSearch+"()'>上一页</a>";
		}else if(pageIndex==0){
			pageInfoHtml+="<a class='pull-right' style='color:#FF0000;font-weight:bold' href='javascript:"+nextSearch+"()'>下一页</a>";
		}else{
			pageInfoHtml+="<a class='pull-right' style='color:#FF0000;font-weight:bold' href='javascript:"+nextSearch+"()'>下一页</a>";
			pageInfoHtml+="<a class='pull-right' style='color:#FF0000;font-weight:bold' href='javascript:"+lastSearch+"()'>上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>";
		}
	}
	pageInfoHtml+="</div>";
	var pageInfoDiv=showDivId.find('#pageInfo');
	pageInfoDiv.find("div").remove();
	$(pageInfoHtml).appendTo(pageInfoDiv);
}

/**
 * READY(1), WAITING_EXECUTED(2), EXECUTING(3), SUSPEND(4), STOP(5),
 * FINISHED(6);
 */
function getState(state) {
	var stateStr = "准备";
	if (0 == state) {
		stateStr = "准备";
	} else if (1 == state) {
		stateStr = "定时计划中";
	}  else if (2 == state) {
		stateStr = "等待被执行";
	} else if (3 == state) {
		stateStr = "初始化";
	} else if (4 == state) {
		stateStr = "运行";
	}else if (5 == state) {
		stateStr = "暂停";
	}else if (6 == state) {
		stateStr = "停止";
	}else if (7 == state) {
		stateStr = "完成";
	}
	return stateStr;
}
function getStateColor(state){
	var color = "#227700";
	if (0 == state) {
		color = "#227700";
	}else if (1 == state) {
		color = "#FF0000";
	} else if (2 == state) {
		color = "#FF8888";
	}else if (3 == state) {
		color = "#FF0000";
	} else if (4 == state) {
		color = "#FF0000";
	} else if (5 == state) {
		color = "#FF3333";
	} else if (6== state) {
		color = "#FF0000";
	} else if (7 == state) {
		color = "#FF0000";
	}
	return color;
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt){ // author: meizz
	var o = {   
			"M+" : this.getMonth()+1,                 // 月份
			"d+" : this.getDate(),                    // 日
			"h+" : this.getHours(),                   // 小时
			"m+" : this.getMinutes(),                 // 分
			"s+" : this.getSeconds(),                 // 秒
			"q+" : Math.floor((this.getMonth()+3)/3), // 季度
			"S"  : this.getMilliseconds()             // 毫秒
	};   
	if(/(y+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	}   	
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));  
		}
	}    
	return fmt;   
}

function getWorkSpaceHtml(workSpaceName,totalProcessCount,doingSize,errSize){
	var totalProcessCountHtml="<span title='任务处理数量' style='color:#FF0000;font-size: 14px; font-weight: bold;'>"
		+totalProcessCount+"</span>";
	var doingSizeHtml="0";
	if(doingSize>0){
		doingSizeHtml="<a  title='任务队列数量' href=\"javascript:showDoingDataDiv('" +workSpaceName+ "')\">" 
				+"<span style='color:#227700;font-size:14px; font-weight: bold;'>"+doingSize+"</span>"
				+"</a>&nbsp;";
	}else{
		doingSizeHtml="<span title='任务队列数量' style='color:#227700;font-size: 14px; font-weight: bold;'>"+doingSize+"</span>";
	}
	var errSizeHtml="0";
	if(errSize>0){
		errSizeHtml="<a  title='任务错误队列数量' href=\"javascript:showErrDataDiv('" + workSpaceName+ "')\">" 
				+"<span style='color:#FF0000;font-size: 14px; font-weight: bold;'>"+errSize+"</span>"
				+"</a>&nbsp;";
	}else{
		errSizeHtml="<span title='任务错误队列数量' style='color:#FF0000;font-size: 14px; font-weight: bold;'>"+errSize+"</span>&nbsp;";
	}
	var queueShowStr=totalProcessCountHtml+"/&nbsp;("+doingSizeHtml+"&nbsp;|&nbsp;"+errSizeHtml+")";
	return queueShowStr;
}


function showJobParams(jobName) {
	var jobTr=$('#job_div').find("table").find("tr[name='"+jobName+"']");
	var jobParamsDiv = $("#job_params_div");

	jobParamsDiv.find("input[name='name']").val(jobName);
	
	var level=jobTr.find("input[name='level']").val();
	jobParamsDiv.find("input[name='level']").val(level);
	
	var workSpaceName=jobTr.find("input[name='workSpaceName']").val();
	jobParamsDiv.find("input[name='workSpaceName']").val(workSpaceName);
	
	var workerClass=jobTr.find("input[name='workerClass']").val();
	jobParamsDiv.find("input[name='workerClass']").val(workerClass);
	
	var designatedNodeName=jobTr.find("input[name='designatedNodeName']").val();
	jobParamsDiv.find("input[name='designatedNodeName']").val(designatedNodeName);
	
	var needNodes=jobTr.find("input[name='needNodes']").val();
	jobParamsDiv.find("input[name='needNodes']").val(needNodes);
	
	var describe=jobTr.find("input[name='describe']").val();
	jobParamsDiv.find("input[name='describe']").val(describe);

	var url = "/crawler/job/queryJobParams/" + jobName;
	$.get(url, function(result) {
		var jobParams=result.data;
		if(null!=jobParams){
			var jobParamsTable =jobParamsDiv.find("table[id='jobParams']");
			jobParamsTable.find("tr[class=jobParams]").remove();
			for (var i = 0; i < jobParams.length; i++) {
				var jobParam=jobParams[i];
				var tr = $("<tr class='jobParams' name='"+jobParam.id+"' ></tr>");
				$("<input name='version' style='display:none' type='text' value='"+jobParam.version+"' />").appendTo(tr);
				var jobParamNameTd=$("<td></td");
				$("<input name='name'  flag='"+jobParam.id+"' style='display:none' type='text' value='"+jobParam.name+"' />").appendTo(jobParamNameTd);
				$("<input name='update_name'  flag='"+jobParam.id+"' size='20'  style='display:none' type='text' value='"+jobParam.name+"' />").appendTo(jobParamNameTd);
				$("<a     name='name'  style='color:#FF0000;text-decoration:none;' href=\"javascript:editJobParams('input','"+jobParam.id+"','name')\">"+jobParam.name+"</a>").appendTo(jobParamNameTd);
				jobParamNameTd.appendTo(tr);
				// contenteditable="true"
				var jobParamValueTd=$("<td></td");
				$("<input name='value'  flag='"+jobParam.id+"' style='display:none' type='text' value='"+jobParam.value+"' />").appendTo(jobParamValueTd);
				var textarea=$("<textarea name='update_value'  flag='"+jobParam.id+"'  cols='145' style='display:none'></textarea>");
				textarea.html(jobParam.value);
				textarea.appendTo(jobParamValueTd);
				$("<a     name='value'  style='color:#FF0000;text-decoration:none;' href=\"javascript:editJobParams('textarea','"+jobParam.id+"','value')\">"+jobParam.value+"</a>").appendTo(jobParamValueTd);
				jobParamValueTd.appendTo(tr);
				
				tr.appendTo(jobParamsTable);
			}
			
			jobParamsTable.find("input[name='update_name']").bind('keydown',function(event){  
				  if(event.keyCode == "13"){ 
					  var jobParamId=$(this).attr("flag");
					  updateJobParam(jobParamId,"name");  
				  }  
			});  
			jobParamsTable.find("input[name='update_value']").bind('keydown',function(event){  
				  if(event.keyCode == "13"){ 
					  var jobParamId=$(this).attr("flag");
					  updateJobParam(jobParamId,"value");  
				  }  
			});
		}
		showDiv("job_params_div");
		// showLayer(jobParamsDiv);
	});
}

function editJobParams(tagName,jobParmaId,field){
	var allTr=$("tr[class=jobParams]");
	var selectTr=$("tr[name='"+jobParmaId+"']");
	
	var fieldInput=selectTr.find(tagName+"[name='"+field+"']");
	var updateFieldInput=selectTr.find(tagName+"[name='update_"+field+"']");
	
	var fieldA=selectTr.find("a[name='"+field+"']");
	var fieldValue=fieldInput.val();
	updateFieldInput.css("display","inline");
	fieldA.css("display","none");
	updateFieldInput.focus();
	var inputNames=new Array();
	var index=0;
	inputNames[index++]="name";
	inputNames[index++]="value";
	leaveAllInput(allTr,updateFieldInput,inputNames,field);
	leaveAllTextarea(allTr,updateFieldInput,inputNames,field);	
}

function updateJobParam(jobParamId){
	var jobParamsDiv = $("#job_params_div");
	var jobParamsTable =jobParamsDiv.find("table[id='jobParams']");
	
	var allTr=jobParamsTable.find("tr");
	var jobParamsTr=jobParamsTable.find("tr[name='"+jobParamId+"']");
	
	var nameInput=jobParamsTr.find("input[name='name']");
	var nameUpdateInput=jobParamsTr.find("input[name='update_name']");
	var nameShowA=jobParamsTr.find("a[name='name']");
	
	var nameNewValue=nameUpdateInput.val();
	var nameOldValue=nameInput.val();
	
	var valueInput=jobParamsTr.find("input[name='value']");
	var valueUpdateInput=jobParamsTr.find("input[name='update_value']");
	var valueShowA=jobParamsTr.find("a[name='value']");
	
	var valueNewValue=valueUpdateInput.val();
	var valueOldValue=valueInput.val();
	
	if(nameNewValue!=nameOldValue||valueNewValue!=valueOldValue){
		var version=jobParamsTr.find("input[name='version']").val();
		if (window.confirm("你确定要更新jobParams["+jobParamId+"]?")) {
			var url = "/crawler/job/updateJobParam";
			$.post(url, {
				version:version,
				jobParamId : jobParamId,
				name : nameNewValue,
				value : valueNewValue
			}, function(responseMsg) {
				if (responseMsg.isOk == 1) {
					nameInput.val(nameNewValue);
					nameUpdateInput.val(nameNewValue);
					nameShowA.html(nameNewValue);
					
					valueInput.val(valueNewValue);
					valueUpdateInput.val(valueNewValue);
					valueShowA.html(valueNewValue);
					
					
					var index=0;
					var inputNames=new Array();
					inputNames[index++]="name";
					inputNames[index++]="value";
					
					leaveAllInput(allTr,null,inputNames,"");
					leaveAllTextarea(allTr,null,inputNames,"");	

					var newVersion=responseMsg.data;
					updateVersion(newVersion,jobParamsTr);
				}
				alert(responseMsg.msg);
			});
		}	
	}
}

function editCronTrigger(jobName){
	var allTr=$('#jobs').find("tr");
	var tr=$("tr[name='"+jobName+"']");
	var ctInput=tr.find("input[name='cronTrigger']");
	var updateCtInput=tr.find("input[name='update_cronTrigger']");
	var ctA=tr.find("a[name='cronTrigger']");
	var cronTrigger=ctInput.val();
	updateCtInput.val(cronTrigger);
	ctA.css("display","none");
	updateCtInput.css("display","inline");
	updateCtInput.focus();
	liveInput(allTr,updateCtInput,"cronTrigger");
	liveInput(allTr,updateCtInput,"nextJobName");
}

function editNextJob(jobName){
	var allTr=$('#jobs').find("tr");
	var tr=$("tr[name='"+jobName+"']");
	var nextJobInput=tr.find("input[name='nextJobName']");
	var updateNextJobInput=tr.find("input[name='update_nextJobName']");
	var nextJobA=tr.find("a[name='nextJobName']");
	var nextJob=nextJobInput.val();
	updateNextJobInput.val(nextJob);
	updateNextJobInput.css("display","inline");
	nextJobA.css("display","none");
	updateNextJobInput.focus();
	liveInput(allTr,updateNextJobInput,"cronTrigger");
	liveInput(allTr,updateNextJobInput,"nextJobName");
}

function updateCronTrigger(jobName){
	var allTr=$('#jobs').find("tr");
	var tr=$("tr[name='"+jobName+"']");
	var ctInput=tr.find("input[name='cronTrigger']");
	var updateCtInput=tr.find("input[name='update_cronTrigger']");
	var ctA=tr.find("a[name='cronTrigger']");
	var newCronTrigger=updateCtInput.val();
	var oldCronTrigger=ctInput.val();
	if(newCronTrigger!=oldCronTrigger){
		var version=tr.find("input[name='version']").val();
		if (window.confirm("你确定要保存job["+jobName+"]的调度时间["+newCronTrigger+"]")) {
			var url = "/crawler/job/updateCronTrigger";
			$.post(url, {
				version:version,
				name : jobName,
				cronTrigger : newCronTrigger
			}, function(responseMsg) {
				if (responseMsg.isOk == 1) {
					ctInput.val(newCronTrigger);
					updateCtInput.html(newCronTrigger);
					ctA.html(newCronTrigger);
					liveInput(allTr,null,"cronTrigger");
					liveInput(allTr,null,"nextJobName");
					
					var newVersion=responseMsg.data;
					updateVersion(newVersion,tr);
				}
				alert(responseMsg.msg);
			});
		}	
	}
}

function updateNextJobName(jobName){
	var allTr=$('#jobs').find("tr");
	var tr=$("tr[name='"+jobName+"']");
	var nextJobNameInput=tr.find("input[name='nextJobName']");
	var updateNextJobNameInput=tr.find("input[name='update_nextJobName']");
	var nextJobNameA=tr.find("a[name='nextJobName']");
	var newValue=updateNextJobNameInput.val();
	var oldValue=nextJobNameInput.val();
	if(newValue==jobName){
		alert("job["+jobName+"]下个执行任务不等于本身");
		return;
	}
	if(oldValue!=newValue){
		var version=tr.find("input[name='version']").val();
		if (window.confirm("你确定要保存job["+jobName+"]的下个执行任务为["+newValue+"]")) {
			var url = "/crawler/job/updateNextJobName";
			$.post(url, {
				version:version,
				name : jobName,
				nextJobName : newValue
			}, function(responseMsg) {
				if (responseMsg.isOk == 1) {
					nextJobNameInput.val(newValue);
					updateNextJobNameInput.val(newValue);
					if(""==newValue){
						newValue="#";
					}
					nextJobNameA.html(newValue);
					liveInput(allTr,null,"cronTrigger");
					liveInput(allTr,null,"nextJobName");
					var newVersion=responseMsg.data;
					updateVersion(newVersion,tr);
				}
				alert(responseMsg.msg);
			});
		}	
	}
}

function getIsScheduledShowHtml(jobName, isScheduled) {
	var scheduledOperation;
	if (isScheduled == 1) {
		scheduledOperation = "<a style='color:#FF0000' href=\"javascript:updateIsScheduled('"
				+ jobName + "')\">取消</a>&nbsp;";
	} else {
		scheduledOperation = "<a style='color:#227700' href=\"javascript:updateIsScheduled('"
				+ jobName + "')\">开启</a>&nbsp;";
	}
	return scheduledOperation;
}

function updateIsScheduled(jobName) {
	var jobTr = $("tr[name='" + jobName + "']");
	var version = jobTr.find("input[name='version']").val();
	var isScheduledInput = jobTr.find("input[name='isScheduled']");
	var isScheduledTd = jobTr.find("td[name='isScheduled']");
	var isScheduled = isScheduledInput.val();
	if (1 == isScheduled || "1" == isScheduled) {
		msg = '你确定要关闭任务[' + jobName + ']调度吗？';
		isScheduled=0;
	} else {
		msg = '你确定要开启任务[' + jobName + ']调度吗？';
		isScheduled=1
	}
	if (window.confirm(msg)) {
		var url = "/crawler/job/updateIsScheduled";
		$.post(url, {
			version : version,
			name : jobName,
			isScheduled:isScheduled
		}, function(responseMsg) {
			if (responseMsg.isOk == 1) {
				var scheduledOperation =getIsScheduledShowHtml(jobName,
						isScheduled);
				isScheduledTd.html(scheduledOperation);
				isScheduledInput.val(isScheduled);
				updateVersion(responseMsg.data,jobTr);
			}
			alert(responseMsg.msg);
		});
	}
}



function showHistoryJobSnapshot(jobName) {
	var url = "/crawler/job/jobSnapshot/getHistoryJobSnapshot";
	var jobSnapshotDiv = $("#job_jobSnapshot_div");
	var table=jobSnapshotDiv.find("table");
	var pageIndex=jobSnapshotDiv.find('#pageIndex').val();
	$.post(url, {
		jobName:jobName,
		pageIndex : pageIndex
	}, function(responseMsg) {
		var jobSnapshots=responseMsg.data.list;
		table.find("tr[class='jobSnapshot']").remove();
		jobSnapshotDiv.find("span[id='jobName']").html("任务[<span style='color:#FF0000'>"+jobName+"</span>]运行历史记录");
		for (var i = 0; i < jobSnapshots.length; i++) {
			var jobSnapshot=jobSnapshots[i];
			var tr = $("<tr class='jobSnapshot' name='"+jobSnapshot.id+"' ></tr>");
			$("<input name='version' style='display:none' type='text' value='"+jobSnapshot.version+"' />").appendTo(tr);
			var startTime = new Date(jobSnapshot.startTime);
			var endTime = new Date(jobSnapshot.endTime);
			$("<td>" + startTime.Format("yyyy/MM/dd hh:mm:ss") + "</td>").appendTo(tr);
			$("<td>" + endTime.Format("yyyy/MM/dd hh:mm:ss") + "</td>").appendTo(tr);
			var statusTd=$("<td></td>");
			var status=jobSnapshot.status;
			$("<input name='status'  flag='"+jobSnapshot.id+"' style='display:none' type='text' value='"+status+"' />").appendTo(statusTd);
			$("<input name='update_status'  flag='"+jobSnapshot.id+"' style='display:none' type='text' value='"+status+"' />").appendTo(statusTd);
			$("<a name='status' href=\"javascript:editJobSnapshot('"+jobSnapshot.id+"')\" style='color:#FF0000;font-size: 14px; font-weight: bold;'>" + getState(status) + "</a>").appendTo(statusTd);
			statusTd.appendTo(tr);
			
			$("<td>" + jobSnapshot.avgProcessTime + "</td>").appendTo(tr);
			$("<td>" + jobSnapshot.maxProcessTime + "</td>").appendTo(tr);
			$("<td>" + jobSnapshot.minProcessTime + "</td>").appendTo(tr);
			$("<td>" + jobSnapshot.totalProcessCount + "</td>").appendTo(tr);
			var errCountHtml=getErrCountHtml(jobSnapshot.name,jobSnapshot.id,jobSnapshot.errCount);
			$("<td>" + errCountHtml + "</td>").appendTo(tr);
			$("<td>查看异常</td>").appendTo(tr);
			tr.appendTo(table);
		}
		table.find("input[name='update_status']").bind('keydown',function(event){  
			  if(event.keyCode == "13"){ 
				  var jobSnapshotId=$(this).attr("flag");
				  updateJobSnapshotStatus(jobSnapshotId);  
			  }  
		}); 
		pageInfo("#job_jobSnapshot_div",responseMsg,"lastSearchJob","nextSearchJob");
		showDiv("job_jobSnapshot_div");
	});
}

function editJobSnapshot(jobSnapshotId){
	var allTr=$("tr[class=jobSnapshot]");
	var statusInput=$("tr[name='"+jobSnapshotId+"']").find("input[name='status']");
	var updateStatusInput=$("tr[name='"+jobSnapshotId+"']").find("input[name='update_status']");
	var statusA=$("tr[name='"+jobSnapshotId+"']").find("a[name='status']");
	var status=statusInput.val();
	updateStatusInput.val(status);
	updateStatusInput.css("display","inline");
	statusA.css("display","none");
	updateStatusInput.focus();
	liveInput(allTr,updateStatusInput,"status");
	
}

function updateJobSnapshotStatus(jobSnapshotId){
	var allTr=$("tr[class=jobSnapshot]");
	var tr=$("tr[name='"+jobSnapshotId+"']");
	var statusInput=tr.find("input[name='status']");
	var updateStatusInput=tr.find("input[name='update_status']");
	var statusA=tr.find("a[name='status']");
	var newValue=updateStatusInput.val();
	if(newValue<1||newValue>6){
		alert("input status is invalid");
		return;
	}
	var oldValue=statusInput.val();
	if(oldValue!=newValue){
		var version=tr.find("input[name='version']").val();
		if (window.confirm("你确定要保存jobSnapshot["+jobSnapshotId+"]的状态为["+newValue+"]")) {
			var url = "/crawler/job/updateJobSnapshotStatus";
			$.post(url, {
				version:version,
				id : jobSnapshotId,
				status : newValue
			}, function(responseMsg) {
				if (responseMsg.isOk == 1) {
					statusInput.val(newValue);
					updateStatusInput.val(newValue);
					statusA.html(getState(newValue));
					liveInput(allTr,null,"status");
					var newVersion=responseMsg.data;
					updateVersion(newVersion,tr);
				}
				alert(responseMsg.msg);
			});
		}	
	}
}

function searchExtractItem(){
	var jobName = $('#search_job_name').val();
	if(null==jobName||""==jobName){
		jobName="*";
	}
	var jobSearch=$('#job_search');
	var pageIndex=jobSearch.find('#pageIndex').val();
	var pageSize=jobSearch.find('#pageSize').val();
	var url="/crawler/extractItem/query/"+jobName;
	$.get(url, function(result) {
		showExtractItemTable(result.data);
	});
}

function showExtractItemTable(jobName){
	var url="/crawler/extractItem/query/"+jobName;
	$.get(url, function(result) {
		var extractItems=result.data;
		if(null!=extractItems&&extractItems.length>0){
			var div=$('#job_extractItem_div');
			var table = div.find('table');
			table.find("tr[class=jobExtractItem]").remove();
			var extractItem;
			var i = 0;
			for (var i = 0; i < extractItems.length; i++) {
				extractItem = extractItems[i];
				var tr = $("<tr class='jobExtractItem' name='"+extractItem.serialNub+"' title='"+extractItem.describe+"'></tr>");
				$("<input name='id' style='display:none' type='text' value='"+extractItem.id+"' />").appendTo(tr);
				$("<input name='version' style='display:none' type='text' value='"+extractItem.version+"' />").appendTo(tr);
				$("<td>"+extractItem.serialNub+"</td>").appendTo(tr);
		
				var pathNameTd=$("<td></td");
				$("<input name='pathName'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.pathName+"' />").appendTo(pathNameTd);
				$("<input name='update_pathName'  flag='"+extractItem.serialNub+"'  size='20' style='display:none' type='text' value='"+extractItem.pathName+"' />").appendTo(pathNameTd);
				$("<a     name='pathName'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','pathName')\">"+extractItem.pathName+"</a>").appendTo(pathNameTd);
				pathNameTd.appendTo(tr);
		
				var primaryTd=$("<td></td");
				$("<input name='primary'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.primary+"' />").appendTo(primaryTd);
				$("<input name='update_primary'  flag='"+extractItem.serialNub+"'  size='2' style='display:none' type='text' value='"+extractItem.primary+"' />").appendTo(primaryTd);
				$("<a     name='primary'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','primary')\">"+extractItem.primary+"</a>").appendTo(primaryTd);
				primaryTd.appendTo(tr);
				
				
				var typeTd=$("<td></td");
				$("<input name='type'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.type+"' />").appendTo(typeTd);
				$("<input name='update_type'  flag='"+extractItem.serialNub+"'  size='2' style='display:none' type='text' value='"+extractItem.type+"' />").appendTo(typeTd);
				$("<a     name='type'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','type')\">"+extractItem.type+"</a>").appendTo(typeTd);
				typeTd.appendTo(tr);
				
				
				var outputTypeTd=$("<td></td");
				$("<input name='outputType'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.outputType+"' />").appendTo(outputTypeTd);
				$("<input name='update_outputType'  flag='"+extractItem.serialNub+"'  size='2' style='display:none' type='text' value='"+extractItem.outputType+"' />").appendTo(outputTypeTd);
				$("<a     name='outputType'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','outputType')\">"+extractItem.outputType+"</a>").appendTo(outputTypeTd);
				outputTypeTd.appendTo(tr);
				
				var outputKeyTd=$("<td></td");
				$("<input name='outputKey'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.outputKey+"' />").appendTo(outputKeyTd);
				$("<input name='update_outputKey'  flag='"+extractItem.serialNub+"'  size='20' style='display:none' type='text' value='"+extractItem.outputKey+"' />").appendTo(outputKeyTd);
				$("<a     name='outputKey'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','outputKey')\">"+extractItem.outputKey+"</a>").appendTo(outputKeyTd);
				outputKeyTd.appendTo(tr);
				
				var mustHaveResultTd=$("<td></td");
				$("<input name='mustHaveResult'  flag='"+extractItem.serialNub+"' style='display:none' type='text' value='"+extractItem.mustHaveResult+"' />").appendTo(mustHaveResultTd);
				$("<input name='update_mustHaveResult'  flag='"+extractItem.serialNub+"'  size='2' style='display:none' type='text' value='"+extractItem.mustHaveResult+"' />").appendTo(mustHaveResultTd);
				$("<a     name='mustHaveResult'  " +
						"style='color:#FF0000;text-decoration:none;' " +
						"href=\"javascript:editExtractItem('"+jobName+"','"+extractItem.serialNub+"','mustHaveResult')\">"+extractItem.mustHaveResult+"</a>").appendTo(mustHaveResultTd);
				mustHaveResultTd.appendTo(tr);
				
				// contenteditable="true"
				$("<td><a href=\"javascript:delExtractItem('"+jobName+"','"+extractItem.serialNub+"')\">删除</a>&nbsp;&nbsp;" +
						"<a href=\"javascript:upExtractItem('"+jobName+"','"+extractItem.serialNub+"')\">up</a>&nbsp;&nbsp;" +
						"<a href=\"javascript:downExtractItem('"+jobName+"','"+extractItem.serialNub+"')\">down</a></td>").appendTo(tr);
				tr.appendTo(table);
			}
			table.find("input[name='update_cronTrigger']").bind('keydown',function(event){  
				  if(event.keyCode == "13"){ 
					  var jobName=$(this).attr("flag");
					  updateCronTrigger(jobName);  
				  }  
			});  
			table.find("input[name='update_nextJobName']").bind('keydown',function(event){  
				  if(event.keyCode == "13"){ 
					  var jobName=$(this).attr("flag");
					  updateNextJobName(jobName);  
				  }  
			}); 
			showLayer(div);
		}else{
			alert("job["+jobName+" has not extractItem]");
		}
	});
}

function delExtractItem(jobName,serNum){
	
}

function upExtractItem(jobName,serNum){
	
}

function downExtractItem(jobName,serNum){
	
}



function editExtractItem(jobName,serNum,field){
	var allTr=$("tr[class=jobExtractItem]");
	var selectTr=$("tr[name='"+serNum+"']");
	var fieldInput=selectTr.find("input[name='"+field+"']");
	var updateFieldInput=selectTr.find("input[name='update_"+field+"']");
	var fieldA=selectTr.find("a[name='"+field+"']");
	var fieldValue=fieldInput.val();
	updateFieldInput.val(fieldValue);
	updateFieldInput.css("display","inline");
	fieldA.css("display","none");
	updateFieldInput.focus();
	var inputNames=new Array();
	var index=0;
	inputNames[index++]="pathName";
	inputNames[index++]="type";
	inputNames[index++]="primary";
	inputNames[index++]="outputType";
	inputNames[index++]="outputKey";
	inputNames[index++]="mustHaveResult";
	leaveAllInput(allTr,updateFieldInput,inputNames,field);	
}





