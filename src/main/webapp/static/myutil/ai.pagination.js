define("app/common/ai-paging/ai.pagination", ["jquery","twbs-pagination/jquery.twbsPagination"], function(require, exports, module){
	 var AjaxController=require('app/common/ai-ajax/1.0.0/index');
	//实例化AJAX控制处理对象
	 var ajaxController = new AjaxController();
	require("twbs-pagination/jquery.twbsPagination");
/*!
 * jQuery runner pagination plugin v1.0.0
 * based on jquery.pagController.js  jquery.twbsPagination.js
 * Copyright 2014, Asiainfo
 * Released under Apache 2.0 license
 * http://apache.org/licenses/LICENSE-2.0.html
 */
(function ($, window, document, undefined) {
//初始化国际化
    'use strict';

    var old = $.fn.runnerPagination;

    // PROTOTYPE AND CONSTRUCTOR

    var RunnerPagination = function (element, options) {
        this.$element = $(element);
        this.options = options ? options:{};
        this.setup();
        return this;
    };

    RunnerPagination.prototype = {
        constructor: RunnerPagination,
        
        setup: function () {
           var _this = this;
           this.destroy();
           //查看详情页返回初始页
			var data = this.options.data? this.options.data:{};
           var pageNo = this.getQueryString('pageNo');
			if (null == undefined)  {
				pageNo = data.pageNo?data.pageNo:1;
			}

			if('1' != pageNo  && null!=pageNo ){
        	   this.loadData(pageNo);
           } else{
        	   this.loadData(1);        	   
           };
        },
        
        destroy: function () {
            this.$element.empty();
            this.$element.removeData('twbs-pagination');
            this.$element.unbind('page');
            return this;
        },
        isArray: function(obj) {
        	return Object.prototype.toString.call(obj) === '[object Array]';
        },
        loadData: function(currentPage) {
        	var _this = this;
        	var opt = _this.options;
        	if(opt.processing){
        		this.showLoadingMessage();
        	}
        	var renderId = opt.renderId;
        	if(renderId){
            	$("#"+renderId).html("");
        	}
        	var data = opt.data?opt.data:{};
        	var currentPageSize = opt.pageSize?opt.pageSize:10;
        	var _pagesize={name:"pageSize",value: currentPageSize};
    		var _pageno={name:"pageNo",value: currentPage};
        	if(_this.isArray(data)){
        		//如果参数是数组
        		data.push(_pagesize);
        		data.push(_pageno);
        	}
        	else{     
        		//如果参数是对象
        		data.pageSize = currentPageSize;
        		data.pageNo = currentPage;        		
        	}
        	ajaxController.ajax({
        		url: opt.url,
 	 			method: opt.method,
 	 			dataType: opt.dataType,
 	            showWait: opt.showWait,
 	            data: data,
 	            //message: opt.message,
 	            processing: false,
 	            success: function (resp) {
 	            	_this.hiddenLoadingMessage();
 	            	var d = (resp && resp.result)?resp:{};
 	            	// var d = resp;
					if (d.result == null) {
						_this.showNotResultMessage();
						opt.render && opt.render.call(_this,d.result);
					} else {
						if(d.result.result && d.result.result.length > 0){
							_this.hiddenNotResultMessage();
							opt.render && opt.render.call(_this,d.result);
						}else {
							_this.showNotResultMessage();
							opt.render && opt.render.call(_this,d.result);
						}
					}
 	                opt.callback && opt.callback.call(_this,d);
 	                var pager = d.result?d.result:{};
 	                //var totalPages=d.pageCount?d.pageCount:1;
 	                if(pager.pageCount>0){
 	                	_this.setupTwbsPagination(pager.pageCount,currentPage);
 	                }
 	            },
 	            failure:function(){
 	            	_this.hiddenLoadingMessage();
 	            },
 	            error:function(){
 	        	   _this.hiddenLoadingMessage();
 	            }
        	});
        	
        	if(_this.isArray(data)){
         		//如果参数是数组，则移除最后连个对象（pageSize、pageNo）
	           		data.pop();//移除pageSize
	           		data.pop();//移除pageNo
	        }
        },
        
        /**
         * 显示加载信息
         */
        showLoadingMessage:function(){
        	var messageId = this.options.messageId;
        	if(messageId){
        		$("#"+messageId).html( "<li class='dialog-icon-loading'></li>");
        		$("#"+messageId).attr("class","not-query pt-20 pb-20");
        	}
        },
        
        /**
         * 隐藏加载信息
         */
        hiddenLoadingMessage:function(){
        	var messageId = this.options.messageId;
        	if(messageId){
        		$("#"+messageId).html("");
        		$("#"+messageId).attr("class","");
        	}
        	
        },
        /**
         * 显示无返回信息
         */
        showNotResultMessage:function(){
        	this.$element.removeClass("pagination");
        	var imageType = this.options.resultImageType;
        	var messageId = this.options.messageId;
        	if(messageId){
	        	if(imageType == "1"){
	        		$("#"+messageId).html("<li class='dialog-icon-notquery-1'></li><li>抱歉没有找到相关商品，更换搜索词试一试吧！</li>");
	        		$("#"+messageId).attr("class", "query-product-msgimage not-query");
	
	        	}else{
	        		$("#"+messageId).html("<li class='dialog-icon-notquery'></li><li>抱歉没有查询到相关数据</li>");
	        		$("#"+messageId).attr("class","not-query pt-20 pb-20");
	        		
	        	}
        	}
        },
        
        /**
         * 隐藏无返回信息
         */
        hiddenNotResultMessage: function(){
        	this.$element.addClass("pagination");
        	var imageType = this.options.resultImageType;
        	var messageId = this.options.messageId;
        	if(messageId){
        		$("#"+messageId).html("");
        		$("#"+messageId).attr("class","");
        	}
        	
        },
        
        /**
         * 获取当前url的请求参数
         */
        getQueryString: function(name) {
    		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    		var r = window.location.search.substr(1).match(reg);
    		if (r != null) {
    			return decodeURIComponent(r[2]);
    		}
    		return null;
    	},
        
        setupTwbsPagination: function(totalPages,currentPage){
        	var _this = this;
        	var currentPageNo = parseInt(currentPage);
        	var opt = _this.options;
        	if(this._tp){
        		return;
        	}

            var _tp = this.$element.twbsPagination({
                totalPages: totalPages,
                startPage: currentPageNo,
                visiblePages: opt.visiblePages,
                first: opt.first,
                prev: opt.prev?opt.prev:"上一页",
                next: opt.next?opt.next:"下一页",
				nextClass:opt.visiblePages==0?"next-down ml-30":"next-down",
				prevClass:"prev-up",
                last: opt.last,
                loop: opt.loop,
                paginationClass: opt.paginationClass?opt.paginationClass:"pagination",
                onPageClick: function (event, pageNo) {
                    _this.loadData(pageNo);
                }
            });
        	this._tp= _tp;
        }
    };

    // PLUGIN DEFINITION

    $.fn.runnerPagination = function (option) {
        var args = Array.prototype.slice.call(arguments, 1);
        var methodReturn;
        var $this = $(this);
        var options = typeof option === 'object' && option;
        var data = new RunnerPagination(this, options) ;
        if (typeof option === 'string') methodReturn = data[ option ].apply(data, args);
        return ( methodReturn === undefined ) ? $this : methodReturn;
    };

    $.fn.runnerPagination.defaults = {
    };

    $.fn.runnerPagination.Constructor = RunnerPagination;

    $.fn.runnerPagination.noConflict = function () {
        $.fn.runnerPagination = old;
        return this;
    };

})(jQuery, window, document);




});