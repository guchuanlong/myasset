<%@ page contentType="text/html;charset=UTF-8"%>

<div class="l-eject" style="width: 800px">
	<div class="eject-large pt-10" id="ej-manager">
		<div class="tips-tt tips-bd">
			<p>查询资产</p>
		</div>
		<form id="managerForm">
			<div class="new-fm">
				<ul>
					<li>
						<p class="bt">领用单号：</p>
						<p>
							<input name="paramBorrowBillNo" id="paramBorrowBillNo"
								type="text" class="int-text int-small">
						</p>
					</li>
					<li>
						<p class="bt">资产编码：</p>
						<p>
							<input name="paramAssetGlobalId" id="paramAssetGlobalId"
								type="text" class="int-text int-small">
						</p>
					</li>
					<li>
						<p class="bt">资产名称：</p>
						<p>
							<input name="paramAssetName" id="paramAssetName" type="text"
								class="int-text int-small">
						</p>
					</li>
					<li>
						<p>
							<input id="btnSubmit" class="btn btn-primary" type="button"
								onclick="_searchAsset()" value="查询" />
						</p>
					</li>
				</ul>
			</div>
		</form>

		<div class="m-dot-dist">
			<div class="m-xd-tab">
			<input type="hidden" name="paramHasChooseAsset" id="paramHasChooseAsset"/>
				<table class="table table-border table-bg  table-hover">
					<thead>
						<tr>
							<th>选择</th>
							<th>领用单号</th>
							<th>资产编码</th>
							<th>资产分类</th>
							<th>资产名称</th>
							<th>归属公司</th>
							<th>归属部门</th>
						</tr>
					</thead>
					<tbody id="canChooseAssetList">
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
				<input id="btnSubmit" class="btn btn-primary  ml-40" type="button"
					onclick="_confirmAssetList()" value="确认" /> <input id="btnSubmit"
					class="btn btn-primary  ml-40" type="button"
					onclick="_closeAssetList()" value="关闭" />
			</div>
		</div>

	</div>
	<div class="mask" id="eject-mask-manager"></div>
</div>


<script id="canChooseAssetTmpl" type="text/x-jsrender">
		<tr  >
				<td><input type="checkbox"  assetGlobalId="{{:assetGlobalId}}" categoryName="{{:category.name}}" 
			assetname="{{:assetname.name}}"  companyName="{{:company.name}}" companyId="{{:company.id}}"
			officeName="{{:company.name}}" officeId="{{:company.id}}" placeName="{{:place.name}}"
			borrowBillDtlId="{{:id}}" borrowBillId="{{:borrowBillId.id}}" borrowBillNo="{{:borrowBillId.borrowBillNo}}" / ></td>
				<td>
					{{:borrowBillId.borrowBillNo}}
				</td>
				<td>
					{{:assetGlobalId}}
				</td>
				<td>
					{{:category.name}}
				</td>
				<td>
					{{:assetname.name}}
				</td>
				<td>
					{{:company.name}}
				</td>
				<td>
					{{:office.name}}
				</td>
			</tr>
</script>

<script id="hasChooseAssetTmpl" type="text/x-jsrender">
		<tr  assetGlobalId="{{:assetGlobalId}}" categoryName="{{:category.name}}" 
			assetname="{{:assetname.name}}"  companyName="{{:company.name}}" companyId="{{:company.id}}"
			officeName="{{:company.name}}" officeId="{{:company.id}}" placeName="{{:place.name}}"
			borrowBillDtlId="{{:borrowBillDtlId}}" borrowBillId="{{:borrowBillId}}" borrowBillNo="{{:borrowBillNo}}" >
				<td>
					{{:borrowBillNo}}
				</td>				
				<td>
					{{:assetGlobalId}}
				</td>
				<td>
					{{:category.name}}
				</td>
				<td>
					{{:assetname.name}}
				</td>
				<td>
					{{:company.name}}
				</td>
				<td>
					{{:office.name}}
				</td>
			</tr>
</script>


<script type="text/javascript">
	$(function() {
		//queryAssetList();
	});
	$(document).on("click", "a[name='chooseAssetDel']", function () {
		$(this).parents("tr").remove();
		
		_computeHasChooseAsset();
	});
	function _showAssetList() {
		var _this = this;
		$('#eject-mask-manager').fadeIn(100);
		$('#ej-manager').slideDown(100);
		_this._searchAsset();
	}

	function _computeHasChooseAsset(){
		var hasAssets  = new Array();
		$("#hasChooseAssetDtlList tr").each(function () {
			var tmpObj = {};
			tmpObj.delFlag = "0";
			tmpObj.borrowBillDtlId = $(this).attr("borrowBillDtlId");
			tmpObj.borrowBillId = $(this).attr("borrowBillId");
			tmpObj.borrowBillNo = $(this).attr("borrowBillNo");
			tmpObj.assetGlobalId = $(this).attr("assetGlobalId");
			tmpObj.category = {"name": $(this).attr("categoryName")};
			tmpObj.assetname = {"name": $(this).attr("assetname")};
			tmpObj.company = {"name": $(this).attr("companyName"),"id": $(this).attr("companyId")};
			tmpObj.office = {"name": $(this).attr("officeName"),"id": $(this).attr("officeId")};
			tmpObj.place = {"name": $(this).attr("placeName")};
			hasAssets.push(tmpObj);
		});
		var tmpDtlListStr=JSON.stringify(hasAssets);
		console.log('toReturnDtlListStr:'+tmpDtlListStr);
		$("#paramHasChooseAsset").val(tmpDtlListStr);
	}
	function _loadChooseAssetFromDb(data){
		var template = $.templates("#hasChooseAssetTmpl");
		var htmlOutput = template.render(data);
		$("#hasChooseAssetDtlList").append(htmlOutput);
		
		_computeHasChooseAsset();
	}
	
	function _confirmAssetList() {
		
		var hasAssets  = new Array();
		$("#canChooseAssetList tr td input[type=checkbox]:checked").each(function () {
			var tmpObj = {};
			// tmpObj.govContactId = $(this).attr("govContactId");
			tmpObj.delFlag = "0";
			tmpObj.borrowBillDtlId = $(this).attr("borrowBillDtlId");
			tmpObj.borrowBillId = $(this).attr("borrowBillId");
			tmpObj.borrowBillNo = $(this).attr("borrowBillNo");
			tmpObj.assetGlobalId = $(this).attr("assetGlobalId");
			tmpObj.category = {"name": $(this).attr("categoryName")};
			tmpObj.assetname = {"name": $(this).attr("assetname")};
			tmpObj.company = {"name": $(this).attr("companyName"),"id": $(this).attr("companyId")};
			tmpObj.office = {"name": $(this).attr("officeName"),"id": $(this).attr("officeId")};
			tmpObj.place = {"name": $(this).attr("placeName")};
			hasAssets.push(tmpObj);
		});

		var template = $.templates("#hasChooseAssetTmpl");
		var htmlOutput = template.render(hasAssets);
		$("#hasChooseAssetDtlList").append(htmlOutput);
		
		_computeHasChooseAsset();
		
		_closeAssetList();
	}
	function _closeAssetList() {
		$('#eject-mask-manager').fadeOut(200);
		$('#ej-manager').slideUp(200);
		//this._managerBlur();
	}
	function _searchAsset() {

		$("#asset-pagination").runnerPagination(
				{
					url : _base
							+ "/myasset/chooseBusiAssetMain/queryBorrowAssetList"
							+ "?ajax_req_random=" + new Date().getTime(),
					method : "POST",
					dataType : "json",
					//processing: true,
					//renderId:"canChooseAssetList",
					//messageId:"cmcChl-showMessageDiv",
					data : {
						"paramBorrowBillNo" : $("#paramBorrowBillNo").val(),
						"paramAssetName" : $("#paramAssetName").val(),
						"paramAssetGlobalId" : $("#paramAssetGlobalId").val()
					},
					pageSize : 5,
					visiblePages : 5,
					message : "正在为您查询数据..",
					render : function(data) {
						if (data != null && data != 'undefined'
								&& data.list != null) {

							var template = $.templates("#canChooseAssetTmpl");
							var htmlOutput = template.render(data.list);
							$("#canChooseAssetList").html(htmlOutput);

						} else {
							$("#canChooseAssetList").html('');
						}
					}
				});
	}
</script>

