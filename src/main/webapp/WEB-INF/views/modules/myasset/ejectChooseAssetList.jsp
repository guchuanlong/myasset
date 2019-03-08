<%@ page contentType="text/html;charset=UTF-8"%>

	<div class="l-eject" style="width:800px">
		<div class="eject-large pt-10" id="ej-manager">
			<div class="tips-tt tips-bd">
				<p>查询资产</p>
			</div>
			<form id="managerForm">
			<div class="new-fm">
				<ul>
					<li>
						<p class="bt">资产编码：</p>
						<p><input name="name" type="text" class="int-text int-small"></p>
					</li>
					<li>
						<p class="bt">资产名称：</p>
						<p><input name="mobile" type="text" class="int-text int-small"></p>
					</li>
					<li>
						<p><input id="btnSubmit" class="btn btn-primary" type="button" onclick="queryAssetList()" value="查询"/></p>
					</li>
				</ul>
			</div>
		</form>
        
		<div class="m-dot-dist">
			<div class="m-xd-tab">
				<table class="table table-border table-bg  table-hover">
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
					<tbody id="canBorrowAssetList">
					</tbody>
				</table>
			</div>
			<!--分页-->
			<div class="m-pag">
				<div class="paging paging-large">
					<ul id="asset-pagination">
					</ul>
				</div>
			</div>
			<!-- /分页-->
			<div id="manager-showMessageDiv"></div>
			<div class="z-nextbtn mt-10 bd-top pt-10 text-c">
			<input id="btnSubmit" class="btn btn-primary  ml-40" type="button" onclick="_confirmAssetList()" value="确认"/> 
			<input id="btnSubmit" class="btn btn-primary  ml-40" type="button" onclick="_closeAssetList()" value="取消"/>
			</div>
		</div>
			
		</div>
		<div class="mask" id="eject-mask-manager"></div>
	</div>


	<script id="canBorrowAssetTmpl" type="text/x-jsrender">
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
			</tr>
	</script>


	<script type="text/javascript">
		$(function() {
			//queryAssetList();
		});
		function _showAssetList() {
        	var _this= this;
            $('#eject-mask-manager').fadeIn(100);
            $('#ej-manager').slideDown(100);
            _this._searchAsset();
        }

        function _confirmAssetList() {
        	_closeAssetList();
        }
        function _closeAssetList() {
            $('#eject-mask-manager').fadeOut(200);
            $('#ej-manager').slideUp(200);
			//this._managerBlur();
        }
		function _searchAsset() {

			$("#asset-pagination")
					.runnerPagination(
							{
								url : _base
										+ "/myasset/chooseBusiAssetMain/chooseAssetList"
										+ "?ajax_req_random="
										+ new Date().getTime(),
								method : "POST",
								dataType : "json",
								//processing: true,
								//renderId:"canBorrowAssetList",
								//messageId:"cmcChl-showMessageDiv",
								data : {},
								pageSize : 5,
								visiblePages : 5,
								message : "正在为您查询数据..",
								render : function(data) {
									if (data != null && data != 'undefined') {

										var template = $
												.templates("#canBorrowAssetTmpl");
										var htmlOutput = template
												.render(data.list);
										$("#canBorrowAssetList").html(
												htmlOutput);

									}
								}
							});
		}
	</script>

