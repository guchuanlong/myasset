/**
 * Created by gucl on 2019/3/23.
 */
var StringUtil={
		/**
		 * 序列数据转为json
		 * @param serializeArray
		 * @returns {{}}
		 */
	getFormJson:function(serializeArray) {
			var o = {};
			$.each(serializeArray, function () {
				//金钱*100
				if ($("input[name="+this.name+"]").attr("data-type") == "money")
					this.value = parseFloat(this.value) * 100;
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		},

		/**
		 * 时间戳格式化为 yyyy-MM-dd
		 */
		formatDate:function(ns) {
			var d = new Date(ns);
			var m = d.getMonth() + 1;
			var day = d.getDate();
			m = m < 10 ? "0" + m : m;
			day = day < 10 ? "0" + day : day;
			var dformat = [ d.getFullYear(), m, day ].join('-');
			// + ' ' + [ d.getHours(), d.getMinutes(), d.getSeconds() ].join(':');
			return dformat;
		},

		/**
		 * 获取当前url的请求参数
		 */
		getQueryString:function(name) {
			var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
			var r = window.location.search.substr(1).match(reg);
			if (r != null) {
				return decodeURIComponent(r[2]);
			}
			return null;
		},

		/**
		 * 获取当前url的请求参数
		 */
		isBlank:function(str) {
			if (str == undefined || str == null || str == '')
				return true;
			return false;
		},

		//计算月份之差
		getMonths:function(sTimestamp, eTimestamp){
			//规则：（当前年份-起始年份)*12+当前月份-起始月份
			var stime = new Date(sTimestamp);
			var etime = new Date(eTimestamp);
			var count = (etime.getYear() - stime.getYear())*12 + etime.getMonth() - stime.getMonth();
			if (etime.getDate() - stime.getDate() > 0)
				count += 1;
			console.log(count + " mmm");
			return count;
		}
		
};


	
