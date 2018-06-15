
/*HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询*/
/* 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果*/
	
//系统公共JS文件对象
var common_js_files = [
                       '../js/html5shiv.min.js',
                       '../js/jquery-2.2.4.min.js',
                       '../js/common.js',
                       '../js/bootstrap.min.js',
                       '../js/table/bootstrap-table.min.js',
                       '../js/table/bootstrap-table-zh-CN.min.js',
                       '../js/table/bootstrap-table-edit.js',
                       '../js/datatime/bootstrap-datetimepicker.min.js',
                       '../js/datatime/bootstrap-datetimepicker.zh-CN.js',
                       '../js/bootstrap-select.js',
                       '../js/layer.js',
                       '../js/sockjs.min.js',
                       '../js/stomp.min.js'
                   ];
//系统CSS文件对象
var common_css_files = [
                        '../css/bootstrap.min.css',
                        "../css/bootstrap-theme.min.css",
                        '../css/table/bootstrap-table.min.css',
                        '../css/datatime/bootstrap-datetimepicker.min.css'            
                   ];

/**
 * 导入CSS文件
 */
for(var i = 0; i < common_css_files.length; i++){
	document.write("<link rel='stylesheet' type='text/css' href='"+common_css_files[i]+"'>");
}
/**
 * 导入JS文件
 */
for(var i = 0; i < common_js_files.length; i++){
	document.write("<script type='text/javascript' src='"+common_js_files[i]+"'></script>");
}

