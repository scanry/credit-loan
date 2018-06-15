$(function() {
	$("#saveBtn").click(saveJob);
});

function saveJob() {
	var name = $("#name").val();
	var siteCode = $("#siteCode").val();
	var triggerTime = $("#triggerTime").val();
	var cycleTime = $("#cycleTime").val();
	var needNodes = $("#needNodes").val();
	var threads = $("#threads").val();
	var level = $("#level").val();
	$.post("/crawler/job/save", {
		name : name,
		siteCode : siteCode,
		triggerTime : triggerTime,
		cycleTime : cycleTime,
		needNodes : needNodes,
		level : level,
		threads : threads
	}, function(result) {
		alert(result.msg);
	});
}
function add_job_parameters() {
	var div = $("#job_parameters");
	var job_parameters_divs = div.find("div[name=job_parameters]");
	var index = 1;
	if (null != job_parameters_divs) {
		index = job_parameters_divs.length + 1;
	}
	var addDiv = "<div name='job_parameters' class='form-group'><label class='col-sm-2 control-label' for='name'>参数key("
			+ index
			+ ")</label><div class='col-sm-4'>"
			+ "<input class='form-control' id='name' type='text' placeholder=''/></div>"
			+ "<label class='col-sm-2 control-label' for='siteCode'>参数value("
			+ index
			+ ")</label>"
			+ "<div class='col-sm-4'><input class='form-control' id='siteCode' type='text' placeholder='' /></div></div>";
	$(addDiv).appendTo(div);
}