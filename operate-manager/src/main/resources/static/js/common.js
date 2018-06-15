function showLayer(content){
	layer.open({
		type : 1, // page层
		area : [ '1200px', '800px' ],
		title : null,
		shade : 0.6, // 遮罩透明度
		moveType : 1, // 拖拽风格，0是默认，1是传统拖动
		shift : 1, // 0-6的动画形式，-1不开启
		content : content
	});
}

function updateVersion(newVersion,tr){
	$(tr).find("input[name='version']").val(newVersion);
}

function liveInput(allTr,input,inputName){
	var allUpdateInput=$(allTr).find("input[name='update_"+inputName+"']");
	var allShowA=$(allTr).find("a[name='"+inputName+"']");
	var inputName;
	var flag;
	if(null==input){
		inputName="###";
		flag="###";
	}else{
		inputName=$(input).attr("name");
		flag=$(input).attr("flag");
	}
	for (var i = 0; i < allUpdateInput.length; i++) {
		var updateInput=$(allUpdateInput[i]);
		var display=updateInput.css("display");
		var findName=updateInput.attr("name");
		var findFlag=updateInput.attr("flag");
		if("inline"==display||"inline-block"==display){
			if(findName!=inputName||findFlag!=flag){
				var value=updateInput.val();
				if(value==null||value==""||value=="null"){
					value="#";
				}
				$(allShowA[i]).val(value);
				$(allUpdateInput[i]).css("display","none");
				$(allShowA[i]).css("display","inline");
			}
		}
	}
}

function leaveAllInput(allTr,input,inputNames,srcInputName){
	for(var j=0;j<inputNames.length;j++){
		var inputName=inputNames[j];
		if(srcInputName==inputName){
			var allUpdateInput=$(allTr).find("input[name='update_"+inputName+"']");
			var allShowA=$(allTr).find("a[name='"+inputName+"']");
			var inputName;
			var flag;
			if(null==input){
				inputName="###";
				flag="###";
			}else{
				inputName=$(input).attr("name");
				flag=$(input).attr("flag");
			}
			for (var i = 0; i < allUpdateInput.length; i++) {
				var updateInput=$(allUpdateInput[i]);
				var display=updateInput.css("display");
				var findName=updateInput.attr("name");
				var findFlag=updateInput.attr("flag");
				if("inline"==display||"inline-block"==display){
					if(findName!=inputName||findFlag!=flag){
						var value=updateInput.val();
						if(value==null||value==""||value=="null"){
							value="#";
						}
						$(allShowA[i]).val(value);
						$(allUpdateInput[i]).css("display","none");
						$(allShowA[i]).css("display","inline");
					}
				}
			}
		}else{
			var allUpdateInput=$(allTr).find("input[name='update_"+inputName+"']");
			var allShowA=$(allTr).find("a[name='"+inputName+"']");
			for (var i = 0; i < allUpdateInput.length; i++) {
				var updateInput=$(allUpdateInput[i]);
				var display=updateInput.css("display");
				var findName=updateInput.attr("name");
				var findFlag=updateInput.attr("flag");
				if("inline"==display||"inline-block"==display){
					var value=updateInput.val();
					if(value==null||value==""||value=="null"){
						value="#";
					}
					$(allShowA[i]).val(value);
					$(allUpdateInput[i]).css("display","none");
					$(allShowA[i]).css("display","inline");
				}
			}
		}
	}
}

function leaveAllTextarea(allTr,input,inputNames,srcInputName){
	for(var j=0;j<inputNames.length;j++){
		var inputName=inputNames[j];
		if(srcInputName==inputName){
			var allUpdateInput=$(allTr).find("textarea[name='update_"+inputName+"']");
			var allShowA=$(allTr).find("a[name='"+inputName+"']");
			var inputName;
			var flag;
			if(null==input){
				inputName="###";
				flag="###";
			}else{
				inputName=$(input).attr("name");
				flag=$(input).attr("flag");
			}
			for (var i = 0; i < allUpdateInput.length; i++) {
				var updateInput=$(allUpdateInput[i]);
				var display=updateInput.css("display");
				var findName=updateInput.attr("name");
				var findFlag=updateInput.attr("flag");
				if("inline"==display||"inline-block"==display){
					if(findName!=inputName||findFlag!=flag){
						var value=updateInput.val();
						if(value==null||value==""||value=="null"){
							value="#";
						}
						$(allShowA[i]).val(value);
						$(allUpdateInput[i]).css("display","none");
						$(allShowA[i]).css("display","inline");
					}
				}
			}
		}else{
			var allUpdateInput=$(allTr).find("textarea[name='update_"+inputName+"']");
			var allShowA=$(allTr).find("a[name='"+inputName+"']");
			for (var i = 0; i < allUpdateInput.length; i++) {
				var updateInput=$(allUpdateInput[i]);
				var display=updateInput.css("display");
				var findName=updateInput.attr("name");
				var findFlag=updateInput.attr("flag");
				if("inline"==display||"inline-block"==display){
					var value=updateInput.val();
					if(value==null||value==""||value=="null"){
						value="#";
					}
					$(allShowA[i]).val(value);
					$(allUpdateInput[i]).css("display","none");
					$(allShowA[i]).css("display","inline");
				}
			}
		}
	}
}


function doNullString(string){
	if(null==string||"null"==string){
		string="";
	}
	return string;
}
function checkStr(str){
	if(null==str||"null"==str){
		return "";
	}else{
		return str;
	}
}

function uploadFile(url, file) {
	var formData = new FormData();
	formData.append("file", file);
	$.ajax({
		url : url,
		type : 'POST',
		data : formData,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend : function() {
			console.log("正在进行，请稍候");
		},
		success : function(responseStr) {
			alert(responseStr.msg);
		},
		error : function(responseStr) {
			console.log("error");
		}
	});
}