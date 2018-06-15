var masterScheduled = {

	getOperation : function(jobName, state) {
		var html = "";
		var executeDisplay = "inline";
		var suspendDisplay = "none";
		var goOnDisplay = "none";
		var stopDisplay = "none";
		if (state == 1) {// 当job.state=1 没有调度或者调度状态下 只显示 执行 操作
			executeDisplay = "inline";
			suspendDisplay = "none";
			goOnDisplay = "none";
			stopDisplay = "none";
		}
		if (state == 2) {// 当job.state=2 处于等待被执行 不显示任何操作
			executeDisplay = "none";
			suspendDisplay = "none";
			goOnDisplay = "none";
			stopDisplay = "none";
		} else if (state == 3) { // 当job.state=3 正在执行状态下 显示 暂停 停止 操作
			executeDisplay = "none";
			suspendDisplay = "inline";
			goOnDisplay = "none";
			stopDisplay = "inline";
		} else if (state == 4) {// 当job.state=4 正在暂停状态下 显示 继续 停止 操作
			executeDisplay = "none";
			suspendDisplay = "none";
			goOnDisplay = "inline";
			stopDisplay = "inline";
		}
		html += "<a style='display:"+ executeDisplay + ";' href=\"javascript:masterScheduled.execute('" + jobName+ "')\">执行</a>&nbsp;" 
			  + "<a style='display:" + suspendDisplay+ ";' href=\"javascript:masterScheduled.suspend('" + jobName+ "')\">暂停</a>&nbsp;" 
			  + "<a style='display:" + goOnDisplay+ ";'    href=\"javascript:masterScheduled.goOn('" + jobName + "')\">继续</a>&nbsp;"
			  + "<a style='display:"+ stopDisplay + ";'    href=\"javascript:masterScheduled.stop('" + jobName+ "')\">终止</a>&nbsp;";
		return html;
	},

	/**
	 * 执行后只显示 暂停和停止
	 * 
	 * @param jobName
	 * @returns
	 */
	execute : function(jobName) {
		if (window.confirm('你确定要执行任务[' + jobName + ']吗？')) {
			var url = "/crawler/master/scheduled/execute/" + jobName;
			$.get(url, function(result) {
				$("#execute_job_bt_" + jobName).hide();
				$("#suspend_job_bt_" + jobName).show();
				$("#goon_job_bt_" + jobName).hide();
				$("#stop_job_bt_" + jobName).show();
				var state = "运行";
				var color = getStateColor(state);
				$("#" + job_state + jobName).css("color", color).html(state);
				alert(result.msg);
			});
		}
	},
	
	/**
	 * 暂停后只显示 继续和停止
	 * 
	 * @param jobName
	 * @returns
	 */
	suspend : function(jobName) {
		if (window.confirm('你确定要暂停任务[' + jobName + ']吗？')) {
			var url = "/crawler/master/scheduled/suspend/" + jobName;
			$.get(url, function(result) {
				$("#execute_job_bt_" + jobName).hide();
				$("#suspend_job_bt_" + jobName).hide();
				$("#goon_job_bt_" + jobName).show();
				$("#stop_job_bt_" + jobName).show();
				var state = "暂停";
				var color = getStateColor(state);
				$("#" + job_state + jobName).css("color", color).html(state);
				alert(result.msg);
			});
		}
	},

	/**
	 * goon 后 只显示 暂停 和 停止功能
	 * 
	 * @param jobName
	 * @returns
	 */
	goOn : function(jobName) {
		if (window.confirm('你确定要继续执行任务[' + jobName + ']吗？')) {
			var url = "/crawler/master/scheduled/goon/" + jobName;
			$.get(url, function(result) {
				$("#execute_job_bt_" + jobName).hide();
				$("#goon_job_bt_" + jobName).hide();
				$("#suspend_job_bt_" + jobName).show();
				$("#stop_job_bt_" + jobName).show();
				var state = "运行";
				var color = getStateColor(state);
				$("#" + job_state + jobName).css("color", color).html(state);
				alert(result.msg);
			});
		}
	},
	/**
	 * stop 后只显示 执行 功能
	 * 
	 * @param jobName
	 * @returns
	 */
	stop : function(jobName) {
		if (window.confirm('你确定要终止任务[' + jobName + ']吗？')) {
			var url = "/crawler/master/scheduled/stop/" + jobName;
			$.get(url, function(result) {
				$("#execute_job_bt_" + jobName).show();
				$("#goon_job_bt_" + jobName).hide();
				$("#suspend_job_bt_" + jobName).hide();
				$("#stop_job_bt_" + jobName).hide();
				var state = "准备";
				var color = getStateColor(state);
				$("#" + job_state + jobName).css("color", color).html(state);
				alert(result.msg);
			});
		}
	},
	showWorkerInfo : function(jobName) {
		var url = "/crawler/master/scheduled/getWorkerInfo/" + jobName;
		var job_worker_div = $("#job_worker_div");
		var job_worker_div_table = job_worker_div.find("table");
		$.get(url, function(result) {
			var workerSnapshots = result.data;
			if (null != workerSnapshots && workerSnapshots.length > 0) {
				job_worker_div_table.find("[name='job_worker']").remove();
				job_worker_div.find("[id='job_worker_div_name']").html(
						"任务[<span style='color:#FF0000'>" + jobName
								+ "</span>]worker信息");
				for (var i = 0; i < workerSnapshots.length; i++) {
					var workerSnapshot = workerSnapshots[i];
					var tr = $("<tr name='job_worker'></tr>");
					$(
							"<td><span style='color:#FF0000;font-size:16px;'>"
									+ workerSnapshot.name + "</span></td>")
							.appendTo(tr);
					$("<td><span>" + workerSnapshot.localNode + "</span></td>")
							.appendTo(tr);
					var state = masterScheduled.getWorkerStateText(workerSnapshot.state);
					$("<td><span>" + state + "</span></td>").appendTo(tr);
					$("<td><span>" + workerSnapshot.startTime + "</span></td>")
							.appendTo(tr);
					$("<td><span>" + workerSnapshot.endTime + "</span></td>")
							.appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.totalProcessCount
									+ "</span></td>").appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.totalResultCount
									+ "</span></td>").appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.totalProcessTime
									+ "</span></td>").appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.maxProcessTime
									+ "</span></td>").appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.minProcessTime
									+ "</span></td>").appendTo(tr);
					$(
							"<td><span>" + workerSnapshot.avgProcessTime
									+ "</span></td>").appendTo(tr);
					$("<td><span>" + workerSnapshot.errCount + "</span></td>")
							.appendTo(tr);
					tr.appendTo(job_worker_div_table);
				}
				showLayer(job_worker_div);
			}
		});
	},
	getWorkerStateText : function(state) {
		var stateText;
		if ("READY" == state) {
			stateText = "准备";
		} else if ("STARTED" == state) {
			stateText = "运行";
		} else if ("WAITED" == state) {
			stateText = "等待";
		} else if ("SUSPENDED" == state) {
			stateText = "暂停";
		} else if ("STOPED" == state) {
			stateText = "停止";
		} else if ("FINISHED" == state) {
			stateText = "完成";
		} else if ("DESTROYED" == state) {
			stateText = "销毁";
		}
		return stateText;
	},

}
