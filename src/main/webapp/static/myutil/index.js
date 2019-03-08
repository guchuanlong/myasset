    'use strict';
    
    //var processingDialog;
    
    var AjaxController = Base.extend({
    	attrs:{
    	},
        Statics: {
			AJAX_SUBMIT_CONTAINER: "_X_AJAX_SUBMIT_CONTAINER_DIV",
			SELECTOR_AJAX_SUBMIT_CONTAINER: "#_X_AJAX_SUBMIT_CONTAINER_DIV",
			AJAX_STATUS_SUCCESS: "000000",
			AJAX_STATUS_FAILURE: "999999",
			AJAX_STATUS_LOGIN: "2",
			RESULT_CODE: "resultCode",
			RESULT_MESSAGE: "resultMessage"
        },
        initialize: function (options) {
            AjaxController.superclass.initialize.call(this, options);
            /*$.i18n.properties({//加载资浏览器语言对应的资源文件
                name: ["commonRes"], //资源文件名称，可以是数组
                path: _i18n_res, //资源文件路径
                mode: 'both',
                language: 'zh_CN',
                cache: true,
                checkAvailableLanguages: true,
                async: false
            });*/

        },
        ajax: function(options){
        	var _this = this;
        	if(processingDialog==null){
                processingDialog = new Dialog({
                    closeIconShow:false,
                    icon:"loading",
                    height:"80",
					//正在处理中，请稍后...
                    content: '正在处理中，请稍后...',
                    innerHTML:
                    	'<div class="l-eject">'
                		+'<div style="position:fixed;top:50%;left:50%;margin-top:-160px;margin-left:-150px;z-index:999;padding:10px 20px 20px 20px;width:400px;min-height:200px;display:none;" i="showView" id="eject-pro1" style="display: block;">'
                			+'<div class="tips-tt">'
                				+'<p></p>'
                				+'<p class="right"><a href="#" id="p-cic1"></a></p>'
                			+'</div>'
                			+'<div class="prompt">'
                				+'<ul i="body">'
                					+'<li><div i="icon" src=""></div></li>'
                					+'<li class="tip" i="content"></li>'
                				+'</ul>'
                			+'</div>'
                		+'<div class="z-nextbtn text-c" i="button"><a href="#" class="btn btn-green btn-medium" id="tips-okbtn">确认</a></div>'
                		+'</div>'
                		+'<div class="mask" id="eject-mask"></div>'
                	    +'</div>'
                    	
                });
			}
        	var callbacks = {};
			if(typeof options.before == 'function'){
				callbacks["before"] = options.before;
				delete options.before;
			}
			if(typeof options.success=='function'){
				callbacks["success"] = options.success;
				delete options.success;
			}
			if(typeof options.failure=='function'){
				callbacks["failure"] = options.failure;
				delete options.failure;
			}
			if(typeof options.error=='function'){
				callbacks["error"] = options.error;
				delete options.error;
			}
        	var processing = options && options.processing==true?true:false;
        	var message =options.message;
			var postmode = options.postmode?options.postmode:"request";
        	var settings = {}; $.extend(settings,options); 
        	var custom=options.custom?options.custom:false;
        	settings["success"] = function(transport){ 
				if(processing)processingDialog.close();
				
				//自定义错误信息，不弹出“操作错误”提示框
				if(custom){
					callbacks["success"] && callbacks["success"].call(_this,transport);
				}
				//通用错误信息处理
				else{
					var status = transport[AjaxController.RESULT_CODE];
					var statusInfo = transport[AjaxController.RESULT_MESSAGE];
					if(status && status == AjaxController.AJAX_STATUS_FAILURE){
						if (_this._isJSON(statusInfo))
							statusInfo = "系统异常";
						var failureDialog = Dialog({
							// 操作错误
							title: '操作错误',
							icon: 'fail',
							content: statusInfo,
							cancel: false,
							// 确定
							okValue:'确定',
							ok: function () {
								callbacks["failure"] && callbacks["failure"].call(_this,transport); 
							}
						});
						failureDialog.showModal();
					} else if(status && status == AjaxController.AJAX_STATUS_LOGIN){
						//取得当前页面地址
						var winLocal = window.location.href;
						// var nowUrl = winLocal.substring(0,winLocal.indexOf('?'))
						// 	+".chk"+window.location.search;
						// var loginUrl = ssoLoginUrl+'?service='+ encodeURIComponent(nowUrl);
						var loginUrl = ssoLoginUrl+'?service='+ encodeURIComponent(winLocal);
						if(window.console)
							console.log(loginUrl);
						window.location = loginUrl;
					}
					else{
						if(postmode=="update")$(target).html(transport);
						callbacks["success"] && callbacks["success"].call(_this,transport);
					} 
					
				}
				
			}; 
			settings["beforeSubmit"] = function(){ 
				return callbacks["before"] && callbacks["before"].call(_this);  
			};
			settings["error"] = function(transport){
				if(transport.status=="0"&&transport.readyState=="0"){
					if(processing)processingDialog.close();
					//取得当前页面地址
					var winLocal = window.location.href;
					window.location = winLocal;
					return;
				}
				if(processing)processingDialog.close();
				var failureDialog = Dialog({
					//请求错误
				    title: '请求错误',

				    icon:'fail',
                    // 网络请求错误,错误码
				    content: '系统异常',
				    cancel: false,
					//确定按钮
				    okValue:'确定',
				    ok: function () {
				    	callbacks["error"] && callbacks["error"].call(_this,transport); 
				    }
				});
				failureDialog.showModal();
			};
			settings.data=options.data?options.data:{};
			settings.type=options.type?options.type:"post";
			var q="ajax_req_random="+new Date().getTime();
			settings.url += (settings.url.indexOf('?') >= 0 ? '&' : '?') + q;  
			if(processing){
				if(message){
					processingDialog.content(message);
				}
				processingDialog.showModal();	
			}
			if(options.postselectors && options.postselectors.length==1){ 
				settings.semantic=true; 
				var postContainerSelector=options.postselectors[0]; 
				if($(postContainerSelector).length){
					$(postContainerSelector).ajaxSubmit(settings);
				}
				else{
					this._processCombineParamContainer(options.postselectors);
					$(AjaxController.SELECTOR_AJAX_SUBMIT_CONTAINER).ajaxSubmit(settings);
				}
			}else{
				settings.semantic=true;
				this._processCombineParamContainer(options.postselectors);
				$(AjaxController.SELECTOR_AJAX_SUBMIT_CONTAINER).ajaxSubmit(settings);
			}  
		},
		_processCombineParamContainer: function(/**Array*/postContainerSelectors){
			this._createSubmitContainer();
			var submitContainer = $(AjaxController.SELECTOR_AJAX_SUBMIT_CONTAINER);
			if(postContainerSelectors && $.isArray(postContainerSelectors)){
				$(postContainerSelectors).each(function(index,selector){
					if($(selector).length){
						$(selector).clone().prependTo(submitContainer);
					} 
				}); 
			}  
		},
		_createSubmitContainer: function(){ 
			var xSubmitContainer = $(AjaxController.SELECTOR_AJAX_SUBMIT_CONTAINER);
			if(!xSubmitContainer.length){
				$(document.body).append("<div id='"+ AjaxController.AJAX_SUBMIT_CONTAINER +"' style='display:none'></div>");
			}else{
				xSubmitContainer.html("");
			}
		},
		_isJSON:function (str) {
			try {
				JSON.parse(str)
				if (str.indexOf("{")>-1) {
					return true;
				} else {
					return false;
				}
			} catch (e) {
				return false;
			}
		}
    });

