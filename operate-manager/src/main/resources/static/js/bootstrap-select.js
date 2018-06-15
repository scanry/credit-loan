/**
 * bootstrap 下拉框插件
 * @author lizx <851697971@qq.com>
 * @version 1.0
 * @date 2015-09-30
 */
(function($){
	 //'use strict';//开启严格模式
	 
	 /**
	  * 调用入口:$('#order_status1').bootstrapSelect({});
	  * options 为对象时是属性设置参数，为字符串时，是调用具体方法，
	  * agrs 为调用方法时的参数
	  */
	 $.fn.bootstrapSelect = function(option,agrs){
		 var value = null;
		 this.each(function () {
             var $this = $(this),
             data = $this.data('bootstrap.select'),
             options = $.extend({},$.fn.bootstrapSelect.defaults,$this.data(),
                    typeof option === 'object' && option);
             
             if (typeof option === 'string') {
            	 if (!data) {
                     return;
                 }
            	 value = $.fn.bootstrapSelect.methods[option](this,data.options,agrs);
            	 if (option === 'destroy') {
                     $this.removeData('bootstrap.select');
                 }
             }
             if (!data) {
            	 //为元素添加data属性
            	//判断标签类型,如果是select标签则转换成input标签
            	 if($this[0].tagName != 'INPUT'){
            		var data = [];
            		$this.find('option').each(function(){
            			var obj = {};
            			obj[options.valueField] = $(this).val();
            			obj[options.textField] = $(this).text();
            			data.push(obj);
            		});
            		if(!options.data || options.data.length == 0){
            			options['data'] = data;
            		}
                 	var inputEl = $('<input name=""  placeholder="请选择"/>');
                 	inputEl.insertAfter($this);
                 	var newEl = $this.next();
                 	newEl.attr('id',$this.attr('id'));
                 	newEl.attr('name',$this.attr('name'));
                 	newEl.attr('value',$this.attr('value'));
                 	newEl.attr('style',$this.attr('style'));
                 	$this.remove();
                 	$this = $(newEl);
                 }
                 $this.data('bootstrap.select', (data = new BootstrapSelect($this,options)));
             }
        });
		 
		return typeof value === 'undefined' || value == null ? this : value;
	 };
	 
	 /**
	 * 定义默认配置信息
	 * data：格式{data:[{title:null,result:{}},{title:null,result:{}}]}
	 * url和data参数只有一个生效，url优先
	 * 如果有option选项，则它的优先级低于data
	 */
	$.fn.bootstrapSelect.defaults = {
			url    : null, //请求路径
			params : {},   //请求参数
			paramsType : '',//参数默认是以表单形势传递，为json时是以json格式传递到后台
			data   : [],   //数据[{key:value},{key:value}]
	        method : 'get',//请求方法
	        textField  : 'text',//显示文本字段
	        valueField : 'id',//隐藏文本字段
	        relationId : null,//级联id
	        emptyText  : null,//空选项文本，该属性为null或undefined时不创建空选项，默认不创建
	        emptyValue : '',//空选项值
	        separator  : ',',//多选时返回值的分割符
	        editable	 : true,//是否可编辑
	        multiple : false,//多选
	        disabled : false,//禁用
	        downBorder : false,//下拉按钮是否带边框
	        cls:'',//自定义样式,多个样式用逗号隔开 class1,class2
	        formatter:function(rec){},//格式化节点	
	        onSelect : function(val,rec){},
	        unSelect : function(val,rec){},//反选
	        onBeforeLoad: function(param){},//param 请求参数
			onLoadSuccess: function(data){},//data加载成功后返回的数据
			onLoadError: function(){}
	};
	
	/**
	 * 控件方法属性
	 */
	$.fn.bootstrapSelect.methods = {
			/**
			 * 获取下拉框选中值,多选的时候返回的是按指定分割符分割的字符串
			 */
			getValue:function(target,options){
				return $(target).parent().find('input[type="hidden"]').val();
			},
			/**
			 * 获取下拉框选中文本,多选的时候返回的是按指定分割符分割的字符串
			 */
			getText:function(target,options,args){
				return $(target).parent().find('input[type="text"]').val();
			},
			/**
			 * 设置选中，如果是多选 value 格式：2，3，4
			 */
			select:function(target,options,values){
				if($(target).data('bootstrap.select').$contentDownList){
					setValues($(target).data('bootstrap.select'),values);
				}
				else{
					var int = setInterval(function(){
						if($(target).data('bootstrap.select').$contentDownList){
							clearInterval(int);
							setValues($(target).data('bootstrap.select'),values);
						}
					},1000);
				}
			},
			/**
			 * 获取所有下拉节点的数据集，返回值是数组类型
			 */
			getData:function(target,options){
				return options.data;
			},
			/**
			 * 根据id获取文本值
			 */
			getTextForVal:function(target,options,values){
				return $(target).data('bootstrap.select').getTextForVal(values);
			},
			/**
			 * 重新加载，可重定向url和params
			 */
			reload:function(target,options,args){
				args = args || {};
				if(args.url){
					options['url'] = args.url;
				}
				if(args.params){
					options['params'] = args.params;
				}
				$(target).data('bootstrap.select').reload();
			},
			/**
			 * 刷新
			 */
			refresh:function(target){
				$(target).data('bootstrap.select').reload();
			},
			/**
			 * 清空节点
			 */
			clear:function(target){
				$(target).data('bootstrap.select').clear();
			},
			destroy:function(target){
				$(target).parent().remove();
			},
			/**
			 * 隐藏下拉框
			 */
			hide:function(target){
				$(target).parent().hide();
			},
			/**
			 * 显示下拉框
			 */
			show:function(target){
				$(target).parent().show();
			}
	};
	
	var BootstrapSelect = function (el,options) {
        this.options = options;//配置项
        this.$el = el;//文本框
        this.$el_ = this.$el.clone();
        this.timeoutId_ = 0;
        this.icoUrl = 'bootstrap/plus/images/menu_arrows.png';//下拉图标路径
        //键盘上功能键键值数组
        this.functionalKeyArray = [9,20,13,16,17,18,91,92,93,45,36,33,34,35,37,39,112,113,114,115,116,117,118,119,120,121,122,123,144,19,145,40,38,27];
        this.$contentDownList = null;//下拉框
        this.options.downListIdPrefix = '_bootstrap_combox_il_';//下拉框id前缀
        this.lastSelText = [];//保存最后一次选择的内容
        this.options.downListCls = 'combox-downlist';//下拉框样式，目前此样式为空，预留
        this.options.selItemCls = 'combox-item-selected',//被选择节点样式,目前犹豫没有引用样式文件，所以此样式只是个空的
        this.options.selItemColor = '#ffe48d';//被选中节点背景色，暂时使用这个
        this.contentDownId = null;//下拉框id
        this.init();
    };
    
    //初始化控件
    BootstrapSelect.prototype.init = function(){
    	this.createSelect();//创建文本选择框
    	this.initServer();//数据加载，并创建下拉节点
    	this.initEvent();//添加事件
    };
    //向服务器请求数据
    BootstrapSelect.prototype.initServer = function(){
    	var $this = this;
    	if($this.options.url){
    		$this.options.onBeforeLoad.call(this,$this.options.params);
        	$.ajax({
        		url:$this.options.url,
        		type:$this.options.method,
        		data:$this.options.paramsType == 'json' ? JSON.stringify($this.options.params) : $this.options.params,
        		dataType:'json',
        		success: function(jsonLst) {
        			$this.options.onLoadSuccess.call(this,jsonLst);
        			$this.createDownList(jsonLst);
    			},
    			error: function(xhr, textStatus, errorThrown){
    				$this.options.onLoadError.call(this);
    				$this.createDownList([]);
    				throw 'ajax 数据加载失败';
    		    }
        	});
    	}
    	else if(!$this.options.data || typeof $this.options.data != 'object'){
    		throw 'data 数据类型有误，必须为数组';
    	}
    	else{
    		$this.createDownList();
    	}
    };
    /**
     * 创建选择框
	 */
    BootstrapSelect.prototype.createSelect = function(){
    	var $this = this;
    	var $input = $this.$el;
    	var options = $this.options;
    	//添加样式
    	$input.addClass('form-control bootstrap-select');
    	$input.addClass(options.cls.replace(/,/g,' '));
    	//获取元素宽、高
    	var width = $input.outerWidth() - 1;
    	var height = $input.outerHeight();
    	var name = $input.attr('name');//name属性
    	$input.removeAttr('name');//删除name属性，最终把name属性移到隐藏域上
    	$input.wrap('<span style="height:'+height+'px;border-radius:4px;border: 1px solid #ccc;display:block;overflow:hidden;width:'+width+'px;"></span>');
    	$input.css('padding-left','17px');
    	//$input.css('display','inline-block');//20151012 去掉该属性
    	$input.css('float','left');//20151012 增加该属性
    	$input.attr('autocomplete','off');
    	$input.attr('type','text');
    	
    	if(!$input.attr('placeholder')){
    		$input.attr('placeholder','请选择');
    	}
    	
    	if(options.emptyText != undefined && options.emptyText != null){
    		$input.val(options.emptyText);
    	}
    	//20151012 修改a高度-1，改变属性position 为 relative，增加float
    	if($this.options.downBorder){
    		$input.css('border-bottom-right-radius',0);
        	$input.css('border-top-right-radius',0);
    		$input.css('width',(width-20)+'px');
    		$input.parent().css('border',0);
    		$('<a href="javascript:;" style="float:right;position:relative;display:inline-block;'
        			+'width:20px;height:'+(height)+'px;border-radius:4px;border:solid 1px #ccc;border-left:0;border-bottom-left-radius: 0; border-top-left-radius: 0;'
        			+'background-color:#eee;">'
        			+'<span style="margin: 14px 4.5px 13px;display: inline-block;'
    					+'width: 0px;height: 0px;border-top: 5px solid #555;'
    					+'border-right: 5px solid transparent;border-left: 5px solid transparent">'
    				+'</span>'
    			+'</a>').insertAfter($input);
    	}
    	else{
    		$input.css('border','0');
    		$input.css('width',(width-22)+'px');
    		$('<a href="javascript:;" style="float:right;position:relative;display:inline-block;'
        			+'width:20px;height:'+(height)+'px;'
        			+'">'
        			+'<span style="margin: 14px 4.5px 13px;display: inline-block;'
        				+'width: 0px;height: 0px;border-top: 5px solid #555;'
        				+'border-right: 5px solid transparent;border-left: 5px solid transparent">'
        			+'</span>'
        	+'</a>').insertAfter($input);
    	}
    	$('<input type="hidden" name="'+name+'" value="'+options.emptyValue+'">').insertAfter($input);
    };

    /**
     * 事件初始化
     */
    BootstrapSelect.prototype.initEvent = function(){
    	var $this = this;
    	var $input = $this.$el;
    	
    	if($this.options.disabled){
    		$input.attr('readonly',true);
    		return;
    	}
    	//a按钮点击事件
    	$input.parent().find('a').unbind('click').click(function(){
    		if(!$this.$contentDownList.is(":visible")){
    			$this.showDownList();
    		}
    		else{
    			$this.hideDownList();
    		}
    	});
		//文本框点击事件,
    	$input.unbind('click').click(function(){
    		$this.showDownList();
    		if(!$this.options.editable){
    			$(this).blur();
        	}
    	});
    	//文本框得到焦点
    	$input.unbind('focus').focus(function(){
    		$(this).css('outline','none');
    		$this.showDownList();
		});
    	//文本框失去焦点
    	$input.unbind('blur').blur(function(){
    		//$this.hideDownList();
		});
    	
    	//添加按键事件
    	$input.unbind('keyup').keyup(function(event){
    		$this.keyUp(event);
    	});
    	//添加按键事件
    	$input.unbind('keydown').keydown(function(event){
    		$this.keyDown(event);
    	});
    };
    /**
     * 创建下拉列表
	 */
    BootstrapSelect.prototype.createDownList = function(data){
    	var $this = this;
    	if(data){
    		$this.options['data'] = data;
    	}
    	var width = $this.$el.outerWidth();
    	var height = $this.$el.outerHeight();
    	var style = 'display: none;'
    		+'background-color: #FFFFFF;'
    		+'border: 1px solid '+($this.options.downBorder?'#ccc;':'#66afe9;')
    		+'position: absolute;'//relative absolute
    		+'z-index: 10000;'
    		+'float:left;'
    		//+'height:25px;'
    		+'width:'+(width+20)+'px;'
    		+'margin-top:'+height+'px;'//20151012 增加该属性
    		+'max-height: 220px;'
    		+'overflow-x: hidden;'
    		+'overflow-y: auto;';
    	var comboIndex = $('div[id^="'+this.options.downListIdPrefix+'"]').length+1;
    	var _id = $this.options.downListIdPrefix+comboIndex;
    	$this.contentDownId = _id;
    	$('<div style="'+style+'" id="'+_id+'"></div>').appendTo($this.$el.parent());
    	var $div = $('#'+_id);
    	$(document).bind('mousedown',function(event){
			var $target = $(event.target);
			if(!($target.parents().andSelf().is($div)) && !($target.parents().andSelf().is($this.$el.parent()))){
				$this.hideDownList();
			};
		});
		//鼠标悬停时选中当前行
    	$('#'+_id).delegate('div', 'mouseover', function() {//鼠标经过，设置背景色
			if(!$(this).hasClass($this.options.selItemCls)){//已被选择的除外
				$(this).css('background','none repeat scroll 0 0 #0080FF');
				$(this).css('color','#FFFFFF');
			}
			
		}).delegate('div', 'mouseout', function() {//鼠标移出，清除背景色
			if(!$(this).hasClass($this.options.selItemCls)){//已被选择的除外
				$(this).css('background','');
				$(this).css('color','#000000');
			}
		}).delegate('div', 'click', function(){//单击选择
			
			$this.select($(this));
		});
		$this.$contentDownList = $div;
		makeContAndShow($this);
		
    };
    /**
     * 选择文本框keyup事件
     */
    BootstrapSelect.prototype.keyUp = function(event){
    	var $this = this;
    	var functionalKeyArray = $this.functionalKeyArray;
    	var $contentDownList = $this.$contentDownList;
		$contentDownList.show();
		//输入框keyup事件
		var k = event.keyCode;
		var ctrl = event.ctrlKey;
		var isFunctionalKey = false;//按下的键是否是功能键
		for(var i=0;i<functionalKeyArray.length;i++){
			if(k == functionalKeyArray[i]){
				isFunctionalKey = true;
				break;
			}
		}
		//k键值不是功能键或是ctrl+c、ctrl+x时才触发自动补全功能
		if(!isFunctionalKey && (!ctrl || (ctrl && k == 67) || (ctrl && k == 88)) ){
			var keyword_ = $.trim($this.$el.val());
			if(keyword_ == null || keyword_ == ''){
				//为空时显示所以节点,并移除选择属性
				$contentDownList.find('div').css('display','block')
											.css('background','')
											.css('color','#000000')
											.removeClass($this.options.selItemCls);
				$this.$el.parent().find('input[type=hidden]').val('');
				return;
			}
			var keywords = (keyword_ ? keyword_ : '').split($this.options.separator);
			//获取当前光标所在位置
			var cursorIndex = $this.getCursorIndex();
			var vals = [];//按删除键删除时，用于存放新值
			//获取选择项跟输入框中的值进行匹配
			$contentDownList.find('.'+$this.options.selItemCls).each(function(){
				if($.inArray($(this).html(),keywords) == -1){
					//取消选择
					$(this).removeClass($this.options.selItemCls);
					$(this).css('background','');
					$(this).css('color','#000000');
				}
				else{
					vals.push($(this).data('jsonData')[$this.options.valueField]);
				}
			});
			//重新设置隐藏值
			$this.$el.parent().find('input[type=hidden]').val(vals.join($this.options.separator));
			//下拉框显示模糊匹配到的项
			$contentDownList.find('div:not(:contains("'+keywords[cursorIndex]+'"))').css('display','none');
			$contentDownList.find('div:contains("'+keywords[cursorIndex]+'")').css('display','block');
			
		}
		//回车键
		if(k == 13){
			if($this.$contentDownList.css('display') != 'none'){
				$this.hideDownList();
				event.keyCode = 9;
			}
		}
    };
    
    /**
     * 选择文本框keydown事件
     */
	BootstrapSelect.prototype.keyDown = function(){
		var $this = this;
    	var $contentDownList = $this.$contentDownList;
    	//输入框keydown事件
		switch (event.keyCode) {
			case 40://向下键
				if($contentDownList.css('display') == 'none')return;
				var $nextSibling = $contentDownList.find('.'+$this.options.selItemCls);
				if($nextSibling.length <= 0){//没有选中行时，选中第一行
					$nextSibling = $contentDownList.find("div:first");
				}else{
					$nextSibling = $($nextSibling[$nextSibling.length-1]).next();
				}
				if($nextSibling.length > 0){//有下一行时（不是最后一行）
					/*$nextSibling.addClass($this.options.selItemCls);//选中的行加背景
					//设置选中背景
					$nextSibling.css('background','none repeat scroll 0 0 '+$this.options.selItemColor);
					$nextSibling.css('color','#000000');
					$this.$el.val($nextSibling.html());//选中行内容设置到输入框中*/	
					$this.select($nextSibling,true);
					//div滚动到选中的行,jquery-1.6.1 $nextSiblingTr.offset().top 有bug，数值有问题
					$contentDownList.scrollTop($nextSibling[0].offsetTop - $contentDownList.height() + $nextSibling.height() );
					
				}else{
					$this.$el.val($this.lastSelText.join($this.options.separator));//输入框显示用户原始输入的值
				}
				break;
				
			case 38://向上键
				if($contentDownList.css('display') == 'none')return;
				var $prevSibling = $contentDownList.find('.'+$this.options.selItemCls);
				if($prevSibling.length <= 0){//没有选中行时，选中最后一行行
					$prevSibling = $contentDownList.find('div:last');
				}else{
					$prevSibling = $($prevSibling[0]).prev();
				}
				if($prevSibling.length > 0){//有上一行时（不是第一行）
					/*$prevSibling.addClass($this.options.selItemCls);//选中的行加背景
					//设置选中背景
					$prevSibling.css('background','none repeat scroll 0 0 '+$this.options.selItemColor);
					$prevSibling.css('color','#000000');
					$this.val($prevSibling.html());//选中行内容设置到输入框中*/				
					$this.select($prevSibling,true);
					//div滚动到选中的行,jquery-1.6.1 $$previousSiblingTr.offset().top 有bug，数值有问题
					$contentDownList.scrollTop($prevSibling[0].offsetTop - $prevSibling.height() + $prevSibling.height());
				}else{
					$this.$el.val($this.lastSelText.join($this.options.separator));//输入框显示用户原始输入的值
				}
				break;
			case 27://ESC键隐藏下拉框
				$this.hideDownList();
			break;
			case 13://回车
				if($this.$contentDownList.css('display') != 'none'){
					$this.hideDownList();
				}
			break;
		}
    };
    
    /**
     * 重新加载
     */
    BootstrapSelect.prototype.reload = function(){
    	var $this = this;
    	if($this.options.url){
    		$this.options.onBeforeLoad.call(this,$this.options.params);
        	$.ajax({
        		url:$this.options.url,
        		type:$this.options.method,
        		data:$this.options.paramsType == 'json' ? JSON.stringify($this.options.params) : $this.options.params,
        		dataType:'json',
        		success: function(jsonLst) {
        			$this.options.onLoadSuccess.call(this,jsonLst);
        			//创建option节点
        			$this.options['data'] = jsonLst;
        			makeContAndShow($this,true);
    			},
    			error: function(xhr, textStatus, errorThrown){
    				$this.options.onLoadError.call(this);
    				throw 'ajax 数据加载失败';
    		    }
        	});
    	}
    	else{
    		//创建option节点
	    	makeContAndShow($this,true);
    	}
    };
    
    /**
     * 隐藏下拉列表
     */
    BootstrapSelect.prototype.hideDownList = function(){
		var $contentDownList = this.$contentDownList;
		if($contentDownList.css('display') != 'none'){
			var items = $contentDownList.find('div');
			items.css('background','');//清除背景色
			items.css('color','#000000');//恢复字体颜色
			this.$el.parent().css('box-shadow','');
			this.$el.parent().css('border-color','#ccc');
			$contentDownList.hide();
		}		
	};
	/**
     * 显示下拉列表
     * @param showMatch 为true时，显示模糊匹配，默认显示所以节点
     */
	BootstrapSelect.prototype.showDownList = function(showMatch){
		var $this = this;
		var $contentDownList = $this.$contentDownList; 
		showMatch = showMatch || false;
		if(!showMatch){
			$contentDownList.find('div').css('display','block');
		}
		else{
			var keyword_ = $.trim($this.$el.val());
			if(keyword_ == null || keyword_ == ''){
				return;
			}	
			var keywords = (keyword_ ? keyword_ : '').split($this.options.separator);
			$contentDownList.find('div:not(:contains("'+keywords[(keywords.length-1)]+'"))').css('display','none');
			$contentDownList.find('div:contains("'+keywords[(keywords.length-1)]+'")').css('display','block');
		}
		//为选中的添加背景色
		$contentDownList.find('.'+$this.options.selItemCls).css({'background':'none repeat scroll 0 0 '+$this.options.selItemColor},{'color':'#FFFFFF'});
		//下拉框显示时设置span获取焦点表框样式
		if(!$this.options.downBorder){
			$this.$el.parent().css('box-shadow','rgba(0, 0, 0, 0.0745098) 0px 1px 1px inset, rgba(102, 175, 233, 0.6) 0px 0px 8px');
			$this.$el.parent().css('border-color','#66afe9');
		}
		$contentDownList.show();		
	};

	/**
     * 选择时触发
     * @param selItem 被选择的节点
     * @param keyEvent 是否是按上下键触发的选择
	 */
	BootstrapSelect.prototype.select = function(selItem,keyEvent){
		keyEvent = keyEvent || false;
		var $this = this;
		var options = $this.options;
		var selRecord = [];//被选择项数据
		var vals = [];//值
		var text = [];//文本
		var $selItem = selItem;
		var unSelect = false;//是否反选
		if($this.options.multiple){//多选
			if($selItem.hasClass($this.options.selItemCls)){//选择时如果该项已被选择，则做反选动作
				$selItem.removeClass($this.options.selItemCls);
				$selItem.css('background','');
				unSelect = true;
			}
			else{
				$selItem.addClass($this.options.selItemCls);
				$selItem.css({'background':'none repeat scroll 0 0 '+$this.options.selItemColor},{'color':'#FFFFFF'});
			}
		}
		else{
			$this.$contentDownList.find('div:not(this)').removeClass($this.options.selItemCls); 
			$this.$contentDownList.find('div:not(this)').css('background',''); 
			$selItem.addClass($this.options.selItemCls);
			$selItem.css({'background':'none repeat scroll 0 0 '+$this.options.selItemColor},{'color':'#FFFFFF'});
			if(!keyEvent){
				$this.hideDownList();
			}
		}
		
		this.$contentDownList.find('.'+options.selItemCls).each(function(i,v){
			$(this).css('color','#000000');//被选择的项字体设置成黑色
			selRecord.push($(this).data('jsonData'));
			vals.push($(this).data('jsonData')[options.valueField]);
			text.push($(this).data('jsonData')[options.textField]);
		});
		$this.$el.parent().find('input[type="hidden"]').val(vals.join(options.separator));
		$this.$el.parent().find('input[type="text"]').val(text.join(options.separator));
		$this.lastSelText = text;
		
		if(unSelect){
			options.unSelect.call(this,$selItem.data('jsonData')[$this.options.valueField],$selItem.data('jsonData'));
		}
		else{
			options.onSelect.call(this,$selItem.data('jsonData')[$this.options.valueField],$selItem.data('jsonData'));
		}
	};
	/**
	 * 清空下拉节点
	 */
	BootstrapSelect.prototype.clear = function(){
		var $this = this;
		var itemsHtml = '';
		if($this.options.emptyText != undefined && $this.options.emptyText != null){
			var nullItem = {};
	    	nullItem[$this.options.textField] = $this.options.emptyText;
	    	nullItem[$this.options.valueField] = '';
			itemsHtml = '<div style="padding-left:17px;width:100%;height:20px;">' + $this.options.emptyText + '</div>'; 
			//每行tr绑定数据，返回给回调函数
			$this.$contentDownList.find('div').each(function(index,val){
				$(this).data('jsonData',nullItem);
			});
		}
		$this.$el.parent().find('input').val('');
		$this.$contentDownList.html(itemsHtml);
	};
	
	/**
	 * 根据值获取文本
	 */
	BootstrapSelect.prototype.getTextForVal = function(value){
		var $this = this;
		var retVal = '';
		$.each($this.options.data,function(i,data){
			if(data[$this.options.textField] == value){
				retVal = data[$this.options.valueField];
				return false;
			}
		});
		return retVal;
	};
	/**
     * 获取光标在字符串中的位置
     */
	BootstrapSelect.prototype.getCursorIndex = function(){
		var $this = this;
		var index = 0;
    	var textObj = $this.$el.get(0);
    	if(!textObj.value){
    		return index;
    	}
    	
        if(document.all && textObj.createTextRange && textObj.caretPos){
            var caretPos = textObj.caretPos;
            index = caretPos.text.split($this.options.separator).length -1;
        }
        else if(textObj.setSelectionRange){
            var rangeStart=textObj.selectionStart;
            //var rangeEnd=textObj.selectionEnd;
            var tempStr = textObj.value.substring(0,rangeStart);
            index = tempStr.split($this.options.separator).length -1;
        }
        return index;
	};
	
	/**
	 * 设置选中
	 * @param target 下拉框列表
	 * @param options 配置项
	 * @param value 值
	 */
	function setValues(bootstrapSelect,value){
		var $this = bootstrapSelect;
		var options = $this.options;
		var $input = $this.$el;
		//如果没有传入value，则根据value属性来赋值
		var values = value == undefined || value == null ?'':value;
		var inputText = [];//存放被选中的文本
		$input.parent().find('input[type="hidden"]').val(values);//隐藏域设置值
		$this.$contentDownList.find('div').each(function(index){
			var item = $(this);//节点
			var itemVal = item.data('jsonData')[options.valueField];//节点值
			var itemText = item.data('jsonData')[options.textField];//节点文本
			item.removeClass(options.selItemCls);//清除原来选择
			//设置选中
			$.each((values+'').split(options.separator),function(i,val){
    			if(val == itemVal){
    				item.addClass(options.selItemCls);
    				inputText.push(itemText);
    				return false;//break
    			}
    		});
		});
		$this.lastSelText = inputText;
		$input.val(inputText.join(options.separator));//文本框设置文本
	}
    
	
	/**
	 * 创建下拉框选项
	 * @param inputDownList 
	 * @param isReload
	 */
    function makeContAndShow(inputDownList,isReload){
    	var nullItem = {};
    	nullItem[inputDownList.options.textField] = inputDownList.options.emptyText;
    	nullItem[inputDownList.options.valueField] = '';
    	var data_ = inputDownList.options.data;
    	if(inputDownList.options.emptyText != undefined && inputDownList.options.emptyText != null){
    		data_.splice(0,0,nullItem); 
    	}
    	var options = inputDownList.options;
    	var $input = inputDownList.$el;
    	//var offset = $input.offset();
		//var h = $input.outerHeight() ;
    	var $this = inputDownList.$contentDownList;
    	//$this.width(options.width);
    	//设置下拉框位置

		if(data_ == null || data_.length <= 0 ){
			$this.html('');
			return;
		}
		var itemsHtml = '';
		for(var i=0;i<data_.length;i++){
			itemsHtml += '<div style="padding-left:17px;width:100%;height:20px;line-height:20px;">' + data_[i][options.textField] + '</div>';
		}
		$this.html(itemsHtml);
    	
		//每行tr绑定数据，返回给回调函数
		$this.find('div').each(function(index,val){
			$(this).data('jsonData',data_[index]);
		});
		if(isReload){
			//重新加载时设置空选项被选择
			setValues(inputDownList,'');
		}
		else{
			//第一次加载时根据value属性设置选中
			setValues(inputDownList,$input.attr('value'));
		}
	}	
})(jQuery);