$(function() {
	
	$("#edit_div_bt").click(function() {
		showEditDiv("","");
	});
	
	$("#testExtractPath_bt").click(function() {
		testExtractPath();
	});
	$("#addExtractPath_bt").click(function() {
		addExtractPath();
	});
	$("#search_siteCode").bind('keydown',function(event){  
		  if(event.keyCode == "13"){ 
			  searchExtractPath();
		  }  
	});  
	$("#search_bt").click(function() {
		var display=$("#extractPath_list_div").css("display");
		var siteCode=$("#search_siteCode").val();
		if("none"==display){
			$("#extractPath_list_div").css("display","block");
			$("#extractPath_edit_div").css("display","none");
		}else{
			if(null==siteCode||siteCode==""){
				alert("请输入搜索站点code");
				return;
			}
		}
		searchExtractPath();
	});
});

function showEditDiv(ranking,pathName){
	if(""!=ranking&&""!=pathName){
		var trName=pathName+ranking;
		var tableDiv=$("#extractPath_list_div");
		var editDiv=$("#extractPath_edit_div");
		var pathTr=tableDiv.find("tr[name='"+trName+"']");
		
		var name=pathTr.find("input[name='name']").val();
		editDiv.find("input[id='name']").val(name);
		
		var siteCode=pathTr.find("input[name='siteCode']").val();
		editDiv.find("input[id='siteCode']").val(siteCode);
		
		var ranking=pathTr.find("input[name='ranking']").val();
		editDiv.find("input[id='ranking']").val(ranking);
		
		var path=pathTr.find("div[name='path']").html();
		editDiv.find("input[id='path']").val(path);
		
		var filterPath=pathTr.find("div[name='filterPath']").html();
		editDiv.find("input[id='filterPath']").val(filterPath);

		var extractAttName=pathTr.find("input[name='extractAttName']").val();
		editDiv.find("input[id='extractAttName']").val(extractAttName);
		
		var substringStart=pathTr.find("input[name='substringStart']").val();
		editDiv.find("input[id='substringStart']").val(substringStart);
		
		var substringEnd=pathTr.find("input[name='substringEnd']").val();
		editDiv.find("input[id='substringEnd']").val(substringEnd);
		
		var compareAttName=pathTr.find("input[name='compareAttName']").val();
		editDiv.find("input[id='compareAttName']").val(compareAttName);
	
		var containKeyWord=pathTr.find("input[name='containKeyWord']").val();
		editDiv.find("input[id='containKeyWord']").val(containKeyWord);
		
		var replaceWord=pathTr.find("input[name='replaceWord']").val();
		editDiv.find("input[id='replaceWord']").val(replaceWord);

		var replaceValue=pathTr.find("input[name='replaceValue']").val();
		editDiv.find("input[id='replaceValue']").val(replaceValue);
		
		var appendHead=pathTr.find("input[name='appendHead']").val();
		editDiv.find("input[id='appendHead']").val(appendHead);
		
		var appendEnd=pathTr.find("input[name='appendEnd']").val();
		editDiv.find("input[id='appendEnd']").val(appendEnd);
		
		var extractEmptyCount=pathTr.find("input[name='extractEmptyCount']").val();
		editDiv.find("input[id='extractEmptyCount']").val(extractEmptyCount);
	}
	$("#extractPath_list_div").css("display","none");
	$("#extractPath_edit_div").css("display","block");
}

function searchExtractPath(){
	var siteCode=$("#search_siteCode").val();
	var pathName=$("#search_pathName").val();
	var url="/crawler/extracter/queryExtractPaths";
	$.post(url, {
		siteCode:siteCode,
		pathName : pathName
	}, function(responseMsg) {
		if (responseMsg.isOk == 1) {
			var extractPaths=responseMsg.data;
			if(null!=extractPaths&&extractPaths.length>0){
				var extractPath_div = $("#extractPath_list_div");
				var extractPath_div_table=extractPath_div.find("table");
				extractPath_div_table.find("tr[class='extractPaths']").remove();
				extractPath_div.find("[id='siteCode']").html("站点[<span style='color:#FF0000'>"+siteCode+"</span>]抽取路径");
				for(var i=0;i<extractPaths.length;i++){
					var extractPath = extractPaths[i];
					var trName=extractPath.name+extractPath.ranking;
					var tr = $("<tr class='extractPaths' name='"+trName+"' title='"+doNullString(extractPath.describe)+"'></tr>");
					$("<input name='name' style='display:none' type='text' value='"+doNullString(extractPath.name)+"' />").appendTo(tr);
					$("<input name='siteCode' style='display:none' type='text' value='"+doNullString(extractPath.siteCode)+"' />").appendTo(tr);
					$("<input name='ranking' style='display:none' type='text' value='"+doNullString(extractPath.ranking)+"' />").appendTo(tr);
					$("<div name='path' style='display:none'>"+doNullString(extractPath.path)+"</div>").appendTo(tr);
					$("<div name='filterPath' style='display:none'>"+doNullString(extractPath.filterPath)+"</div>").appendTo(tr);
					$("<input name='extractAttName' style='display:none' type='text' value='"+doNullString(extractPath.extractAttName)+"' />").appendTo(tr);
					$("<input name='substringStart' style='display:none' type='text' value='"+doNullString(extractPath.substringStart)+"' />").appendTo(tr);
					$("<input name='substringEnd' style='display:none' type='text' value='"+doNullString(extractPath.substringEnd)+"' />").appendTo(tr);
					$("<input name='compareAttName' style='display:none' type='text' value='"+doNullString(extractPath.compareAttName)+"' />").appendTo(tr);
					$("<input name='containKeyWord' style='display:none' type='text' value='"+doNullString(extractPath.containKeyWord)+"' />").appendTo(tr);
					$("<input name='replaceWord' style='display:none' type='text' value='"+doNullString(extractPath.replaceWord)+"' />").appendTo(tr);
					$("<input name='replaceValue' style='display:none' type='text' value='"+doNullString(extractPath.replaceValue)+"' />").appendTo(tr);
					$("<input name='appendHead' style='display:none' type='text' value='"+doNullString(extractPath.appendHead)+"' />").appendTo(tr);
					$("<input name='appendEnd' style='display:none' type='text' value='"+doNullString(extractPath.appendEnd)+"' />").appendTo(tr);
					$("<input name='extractEmptyCount' style='display:none' type='text' value='"+doNullString(extractPath.extractEmptyCount)+"' />").appendTo(tr);
					$("<td>" + extractPath.name + "</td>").appendTo(tr);
					$("<td>" + extractPath.ranking + "</td>").appendTo(tr);
					$("<td>"+doNullString(extractPath.path)+"</td>").appendTo(tr);
					$("<td>" + "" + "</td>").appendTo(tr);
					$("<td>" + "" + "</td>").appendTo(tr);
					$("<td>" + "" + "</td>").appendTo(tr);
					$("<td><a href=\"javascript:showEditDiv('"+extractPath.ranking+"','"+ extractPath.name + "')\">编辑</a>&nbsp;</td>").appendTo(tr);
					tr.appendTo(extractPath_div_table);
				}
			}else{
				alert("查询异常")
			}
		}
	});
}
function del(siteCode,pathName){
	
}

function validExtractPath(){
	var extractPath=new Object();
	extractPath.name = $("#name").val();
	extractPath.siteCode = $("#siteCode").val();
	extractPath.ranking = $("#ranking").val();
	if(null==extractPath.ranking||""==extractPath.ranking){
		extractPath.ranking=-1;
	}
	extractPath.path = $("#path").val();
	extractPath.filterPath = $("#filterPath").val();
	extractPath.extractAttName = $("#extractAttName").val();
	extractPath.substringStart = $("#substringStart").val();
	extractPath.substringEnd = $("#substringEnd").val();
	extractPath.compareAttName = $("#compareAttName").val();
	extractPath.containKeyWord = $("#containKeyWord").val();
	extractPath.replaceWord = $("#replaceWord").val();
	extractPath.replaceValue = $("#replaceValue").val();
	extractPath.appendHead = $("#appendHead").val();
	extractPath.appendEnd = $("#appendEnd").val();
	extractPath.describe = $("#describe").val();
	extractPath.testHtml = $("#testHtml").val();
	extractPath.testUrl = $("#testUrl").val();
	return extractPath;
}

function testExtractPath() {
	var extractPath=validExtractPath();
	if(null!=extractPath){
		var url = "/crawler/extracter/testExtract";
		$.post(url, {
			name:extractPath.name,
			siteCode:extractPath.siteCode,
			ranking:extractPath.ranking,
			path:extractPath.path,
			filterPath:extractPath.filterPath,
			extractAttName:extractPath.extractAttName,
			substringStart:extractPath.substringStart,
			substringEnd:extractPath.substringEnd,
			compareAttName:extractPath.compareAttName,
			containKeyWord:extractPath.containKeyWord,
			replaceWord:extractPath.replaceWord,
			replaceValue:extractPath.replaceValue,
			appendHead:extractPath.appendHead,
			appendEnd:extractPath.appendEnd,
			describe:extractPath.describe,
			testUrl:extractPath.testUrl,
			testHtml:extractPath.testHtml
		}, function(data) {
			showTestExtractPathResult(data.data);
		});
	}
}

function showTestExtractPathResult(testExtractPathResults){
	if(null!=testExtractPathResults&&testExtractPathResults.length>0){
		var show_div = $("#test_extractPath_result_div");
		var show_div_table=show_div.find("table");
		show_div_table.find("[name='test_extractPath_result']").remove();
		for (var i = 0; i < testExtractPathResults.length; i++) {
			var testExtractPathResult=testExtractPathResults[i];
			var tr = $("<tr name='test_extractPath_result'></tr>");
			$("<td><span style='color:#FF0000;font-size:16px;'>" + testExtractPathResult + "</span></td>").appendTo(tr);
			tr.appendTo(show_div_table);
		}
		showLayer(show_div);
	}
}

function addExtractPath() {

}