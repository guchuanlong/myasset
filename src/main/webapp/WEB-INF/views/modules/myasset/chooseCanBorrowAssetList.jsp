<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择资产</title>
</head>
<body >
	
	<form id="searchForm"  class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>资产编号：</label>
				<input  maxlength="64" class="input-medium"/>
			</li>
			<li><label>资产名称：</label>
				<input  maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="queryAssetList()" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>资产编码</th>
				<th>资产分类</th>
				<th>资产名称</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody id = "canBorrowAssetList">
			
		</tbody>
	</table>
	
	  <!--分页-->
	<div class="m-pag">
		<div class="paging paging-large">
		 	<ul id = "asset-pagination">
		  	 </ul>
		</div>
	</div>
   <!-- /分页-->
	
	
	
	<script id = "canBorrowAssetTmpl" type="text/x-jsrender">
		<tr>
				<td><input type="checkbox"/></td>
				<td>
					{{:assetGlobalId}}
				</td>
				<td>
					{{:category.name}}
				</td>
				<td>
					xxx
				</td>
				<td>
					xxx
				</td>
				<td>
					{{:office.name}}
				</td>
				<td>
					{{:assetnameId}}
				</td>
				<td>
					xxx
				</td>
			</tr>
	</script>
	
	
	<script type="text/javascript">
	$(function(){
		//queryAssetList();
	});
	$(document).ready(function() { 
		//queryAssetList();
	}); 
	window.onload = function(){ 
		queryAssetList();
	} 
	function queryAssetList(){

		$("#asset-pagination").runnerPagination({
			url: _base+"/myasset/chooseBusiAssetMain/chooseAssetList"+"?ajax_req_random="+new Date().getTime(),
			method: "POST",
			dataType: "json",
			//processing: true,
			//renderId:"canBorrowAssetList",
			//messageId:"cmcChl-showMessageDiv",
			data : {},
			pageSize:  5,
			visiblePages:5,
			message: "正在为您查询数据..",
			render: function (data) {
				if(data != null && data != 'undefined' ){
					
					var template = $.templates("#canBorrowAssetTmpl");
					var htmlOutput = template.render(data.list);
					$("#canBorrowAssetList").html(htmlOutput);
					
				}
			}
		});
	}
	</script>
</body>
</html>