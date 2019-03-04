/*!
 * jQuery runner pagination plugin v1.0.0
 * based on jquery.pagController.js  jquery.twbsPagination.js
 * Copyright 2014, Asiainfo
 * Released under Apache 2.0 license
 * http://apache.org/licenses/LICENSE-2.0.html
 */
;
(function ($, window, document, undefined) {

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
           this.destroy();
           this.loadData(1);
        },
        
        destroy: function () {
            this.$element.empty();
            this.$element.removeData('twbs-pagination');
            this.$element.unbind('page');
            return this;
        },
        
        loadData: function(currentPage) {
        	var _this = this;
        	var opt = _this.options;
        	var data = opt.data?opt.data:{};
        	var pageSize = opt.pageSize?opt.pageSize:10;
        	data.pageSize = pageSize;
        	data.currentPage = currentPage;
        	ajaxController.ajax({
        		url: opt.url,
 	 			method: opt.method,
 	 			dataType: opt.dataType,
 	            showWait: opt.showWait,
 	            data: data,
 	            message: opt.message,
 	            success: function (resp) {
 	            	var d = (resp && resp.data)?resp.data:{};
 	                opt.render && opt.render.call(_this,d.result);
 	                opt.callback && opt.callback.call(_this,d);
 	                var pager = d.pager?d.pager:{};
 	                _this.setupTwbsPagination(pager.totalPages);
 	            }
        	});
        },
        
        setupTwbsPagination: function(totalPages){
        	var _this = this;
        	var opt = _this.options;
        	if(this._tp){
        		return;
        	}
        	var _tp = this.$element.twbsPagination({
                totalPages: totalPages,
                startPage: opt.startPage,
                visiblePages: opt.visiblePages,
                first: opt.first,
                prev: opt.prev,
                next: opt.next,
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
