$(function() {
	// 加载数据
	loadTable("/crawler/httpPorxy/getAll", "get", "");
});

function loadTable(url, method, params) {
	if ("get" == method) {
		$.get(url, function(result) {
			if (1 == result.isOk) {
				showDataTable(result.data);
			}else{
				alert(result.msg);
			}
		});
	}
}

function showDataTable(httpProxys){
	var table = $('#httpProxys');
	table.find("tr").remove();
	var httpProxy;
	var i = 0;
	for (var i = 0; i < httpProxys.length; i++) {
		httpProxy = httpProxys[i];
		var tr = $("<tr></tr>");
		var httpProxyHostInputId="httpProxyHost_"+i;
		var httpProxyPortInputId="httpProxyPort_"+i;
		$("<td>"+checkStr(httpProxy.host)+"</td>")
				.appendTo(tr);
		$("<td>"+checkStr(httpProxy.port)+"</td>")
		.appendTo(tr);
		$("<td>"+checkStr(httpProxy.userName)+"</td>")
		.appendTo(tr);
		$("<td>"+checkStr(httpProxy.passWord)+"</td>")
		.appendTo(tr);
		$("<td>"+checkStr(httpProxy.expire)+"</td>")
		.appendTo(tr);
		$("<td>"+checkStr(httpProxy.describe)+"</td>")
		.appendTo(tr);
		var httpProxyOperationTd = $("<td></td>");
		var httpProxyOperation= "<a href=\"javascript:testHttpProxy('" + checkStr(httpProxy.host)+ "','" + checkStr(httpProxy.port)+ "','" +checkStr(httpProxy.userName)+ "','" + checkStr(httpProxy.passWord)+ "')\">测试</a>&nbsp;";
		httpProxyOperation += "<a href=\"javascript:delHttpProxy('" + httpProxy.host+ "','" + httpProxy.port+ "')\">删除</a>&nbsp;";
		$(httpProxyOperation).appendTo(httpProxyOperationTd);
		httpProxyOperationTd.appendTo(tr);
		tr.appendTo(table);
	}
	
	var tr = $("<tr></tr>");
	$("<td><input id='addHttpProxyHostInput' type='text' /></td>").appendTo(tr);
	$("<td><input id='addHttpProxyPortInput' type='text' /></td>").appendTo(tr);
	$("<td><input id='addHttpProxyUserNameInput' type='text' /></td>").appendTo(tr);
	$("<td><input id='addHttpProxyPassWordInput' type='text' /></td>").appendTo(tr);
	$("<td><input id='addHttpProxyExpireInput' type='text' /></td>").appendTo(tr);
	$("<td><input id='addHttpProxyDescribeInput' type='text' /></td>").appendTo(tr);
	var httpProxyOperationTd = $("<td></td>");
	var httpProxyOperation= "<a href=\"javascript:addHttpProxy()\">保存</a>&nbsp;";
	$(httpProxyOperation).appendTo(httpProxyOperationTd);
	httpProxyOperationTd.appendTo(tr);
	tr.appendTo(table);
}

function addHttpProxy(){
	var httpProxyHost = $('#addHttpProxyHostInput').val();
	if(""==httpProxyHost){
		alert("HttpProxy host must not be empty");
		return;
	}
	var httpProxyPort = $('#addHttpProxyPortInput').val();
	if(""==httpProxyPort){
		alert("HttpProxy port must not be empty");
		return;
	}
	var httpProxyUserName = $('#addHttpProxyUserNameInput').val();
	var httpProxyPassWord = $('#addHttpProxyPassWordInput').val();
	var httpProxyExpire = $('#addHttpProxyExpireInput').val();
	var httpProxyDescribe = $('#addHttpProxyDescribeInput').val();

	
	$.post("/crawler/httpPorxy/save", {
		host : httpProxyHost,
		port : httpProxyPort,
		userName:httpProxyUserName,
		passWord:httpProxyPassWord,
		expire:httpProxyExpire,
		describe:httpProxyDescribe
	}, function(result) {
		alert(result.msg);
		location.reload(true);
	});
}


function testHttpProxy(httpProxyHost,httpProxyPort,httpProxyUserName,httpProxyPassWord){
	$.post("/crawler/httpPorxy/test", {
		host : httpProxyHost,
		port : httpProxyPort,
		userName:httpProxyUserName,
		passWord:httpProxyPassWord
	}, function(result) {
		alert(result.msg);
	});
}


function delHttpProxy(httpProxyHost,httpProxyPort){
	$.post("/crawler/httpPorxy/del", {
		host : httpProxyHost,
		port : httpProxyPort
	}, function(result) {
		alert(result.msg);
		location.reload(true);
	});
}