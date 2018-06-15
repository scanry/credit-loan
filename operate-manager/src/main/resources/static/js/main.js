var map = {};
map["任务管理"] = "/html/job.html";
map["工作空间管理"] = "/html/workSpace.html";
map["集群管理"] = "/html/cluster.html";
map["站点管理"] = "/html/site.html";
map["抽取管理"] = "/html/extracter.html";
map["HTTP代理管理"] = "/html/httpProxy.html";
map["日志管理"] = "/html/log.html";
// map["日志管理"] = "/html/logs.html";
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}



$(function() {
	showSystemDate();
	$(".menu-a").click(function() {
		var key = $(this).text();
		key=key.trim();
		var url = map[key];
		if (null != url) {
			$("#content-iframe").attr("src", url);
		}
	});
	// 编辑表格
});

function showSystemDate() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;// 月份从0开始
	var day = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	var date = "" + year + "年" + month + "月" + day + "日 " + hours + ":"
			+ minutes + ":" + seconds + "";
	$(".system-data").text(date);
	var timeID = setTimeout(showSystemDate, 1000);
}