<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产入库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/myasset/busiAssetLibinBill/">资产入库列表</a></li>
		<shiro:hasPermission name="myasset:busiAssetLibinBill:edit"><li><a href="${ctx}/myasset/busiAssetLibinBill/form">资产入库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiAssetLibinBill" action="${ctx}/myasset/busiAssetLibinBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>入库单编号：</label>
				<form:input path="libinBillNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属公司：</label>
				<form:input path="companyId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiAssetLibinBill.office.id}" labelName="office.name" labelValue="${busiAssetLibinBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>存放地点：</label>
				<form:input path="placeId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>资产分类：</label>
				<sys:treeselect id="category" name="category.id" value="${busiAssetLibinBill.category.id}" labelName="category.name" labelValue="${busiAssetLibinBill.category.name}"
					title="资产分类" url="/myasset/busiCategory/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>资产名称：</label>
				<form:input path="assetnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入库单编号</th>
				<th>入库数量</th>
				<th>计量单位</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>存放地点</th>
				<th>资产分类</th>
				<th>资产名称</th>
				<th>生产厂家</th>
				<th>资产型号</th>
				<th>设备编号</th>
				<shiro:hasPermission name="myasset:busiAssetLibinBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiAssetLibinBill">
			<tr>
				<td><a href="${ctx}/myasset/busiAssetLibinBill/form?id=${busiAssetLibinBill.id}">
					${busiAssetLibinBill.libinBillNo}
				</a></td>
				<td>
					${busiAssetLibinBill.libinNum}
				</td>
				<td>
					${fns:getDictLabel(busiAssetLibinBill.measureUnitId, 'myasset_measure_unit', '')}
				</td>
				<td>
					${busiAssetLibinBill.companyId}
				</td>
				<td>
					${busiAssetLibinBill.office.name}
				</td>
				<td>
					${busiAssetLibinBill.placeId}
				</td>
				<td>
					${busiAssetLibinBill.category.name}
				</td>
				<td>
					${busiAssetLibinBill.assetnameId}
				</td>
				<td>
					${busiAssetLibinBill.produceFactory}
				</td>
				<td>
					${busiAssetLibinBill.modelFormat}
				</td>
				<td>
					${busiAssetLibinBill.deviceNo}
				</td>
				<shiro:hasPermission name="myasset:busiAssetLibinBill:edit"><td>
    				<a href="${ctx}/myasset/busiAssetLibinBill/form?id=${busiAssetLibinBill.id}">修改</a>
					<a href="${ctx}/myasset/busiAssetLibinBill/delete?id=${busiAssetLibinBill.id}" onclick="return confirmx('确认要删除该资产入库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>