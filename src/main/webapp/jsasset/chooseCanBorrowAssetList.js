$(document).ready(function() {
			
});
// 移动渠道分页
function chooseAssetList() {
	var _this = this;

	$("#asset-pagination").runnerPagination({
		url: _base+"/myasset/chooseBusiAssetMain/chooseAssetList",
		method: "POST",
		dataType: "json",
		processing: true,
		renderId:"cmcChl-list",
		messageId:"cmcChl-showMessageDiv",
		data : {},
		pageSize:  10,
		visiblePages:5,
		message: "正在为您查询数据..",
		render: function (data) {
			if(data != null && data != 'undefined' ){
				//将草稿标志写入每一条渠道
                $.each( data.result, function( key, value ) {
                    value.flag = dragFlag;
                });
				var template = $.templates("#cmcChlTmpl");
				var htmlOutput = template.render(data.result);
				$("#cmcChl-list").html(htmlOutput);
				//模板渲染后添加权限
                batchAuthButton("btn:mgmt:chlindex:add,btn:mgmt:chlindex:modify,btn:mgmt:chlindex:export," +
                    "btn:mgmt:chlindex:approv,btn:mgmt:chlindex:terminate,btn:mgmt:chlindex:freeze,btn:mgmt:chlindex:unfreeze,btn:mgmt:chlindex:refresh,btn:mgmt:chlindex:backcleanup,btn:mgmt:chlindex:expcleanup");
                if(1 == dragFlag){
                    $('#addChannel').css("display","none");
                    $('#exportChannel').css("display","none");
                    $('#exportChannelCleanup').css("display","none");
				}
                $("#title-count").html(data.count);
				if (queryDataCmc instanceof Array)
					queryDataCmc = _this.array2Json(queryDataCmc);
				queryDataCmc.pageNo = data.pageNum;
				documentCookie.setCookie("chlQueryRe", JSON.stringify(queryDataCmc));
				
				//导出
				 if (chlKindName == "all") {
				 	$('#new-table ul').eq(0).find(".number").html(data.count);
				 }
				// 详情-->首页使用
				 pageNo = data.pageNum;
				 queryDataPageSize = data.pageSize;
				 queryDataParam = queryDataCmc;
				 //标记是否从第一页查询
				 queryDataFlag = '1';
				 
				 $(".chldot").dotdotdot({
		                height:20
		            });
			}
		}
	});
}