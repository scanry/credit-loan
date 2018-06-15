$(function() {
	$("#upload_siteProfile").click(function() {
		uploadSiteProfile();
	});
	// 加载数据
	loadTable();
});

function uploadSiteProfile() {
	var url="/crawler/site/upload/profile";
	uploadFile(url,$("#siteProfile")[0].files[0]);
}

function loadTable() {
	var url = "/crawler/site/query/1/0";
	$.get(url, function(result) {
		var errCode = result.errCode;
		if (null == errCode) {
			showSiteTable(result.data);
		}
	});
}

function showSiteTable(data) {
	var table = $('#sites');
	table.find("tr").remove();
	var site;
	var i = 0;
	for (var i = 0; i < data.length; i++) {
		site = data[i];
		var tr = $("<tr></tr>");
		$("<td>" + site.code + "</td>").appendTo(tr);
		$("<td>" + site.mainUrl + "</td>").appendTo(tr);
		$("<td>" + site.describe + "</td>").appendTo(tr);
		var siteOperationTd = $("<td></td>");
		var siteOperation= "<a href=\"/crawler/site/download/profile/"+ site.code + "\">下载配置</a>";
		$(siteOperation).appendTo(siteOperationTd);
		siteOperationTd.appendTo(tr);
		tr.appendTo(table);
	}
}

