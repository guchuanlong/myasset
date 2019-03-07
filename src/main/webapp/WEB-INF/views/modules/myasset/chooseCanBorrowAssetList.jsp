<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择资产</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${_base }/jsasset/chooseCanBorrowAssetList.js"></script>
</head>
<body>
	
	<form id="searchForm"  class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资产编号：</label>
				<form:input path="borrowBillNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属公司：</label>
				<sys:treeselect id="company" name="company.id" value="${busiBorrowBill.company.id}" labelName="company.name" labelValue="${busiBorrowBill.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiBorrowBill.office.id}" labelName="office.name" labelValue="${busiBorrowBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
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
	<%-- <div class="pagination">${page}</div> --%>
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
					{{:borrowPerson}}
				</td>
				<td>
					xxx
				</td>
			</tr>
	</script>
</body>
</html>