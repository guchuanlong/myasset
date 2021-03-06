<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产主表管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiAssetMain/">资产主表列表</a></li>
		<shiro:hasPermission name="myasset:busiAssetMain:edit"><li><a href="${ctx}/myasset/busiAssetMain/form">资产主表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiAssetMain" action="${ctx}/myasset/busiAssetMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属公司：</label>
				<form:input path="companyId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiAssetMain.office.id}" labelName="office.name" labelValue="${busiAssetMain.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>存放地点：</label>
				<form:input path="placeId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>资产分类：</label>
				<sys:treeselect id="category" name="category.id" value="${busiAssetMain.category.id}" labelName="category.name" labelValue="${busiAssetMain.category.name}"
					title="资产分类" url="/myasset/busiCategory/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>资产名称：</label>
				<form:input path="assetnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>ERP资产号：</label>
				<form:input path="erpAssetCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>物品品类编码：</label>
				<form:input path="goodsType" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>责任人：</label>
				<form:input path="respPerson" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>资产状态</th>
				<th>计量单位</th>
				<th>资产编码</th>
				<th>EPC编码</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>存放地点</th>
				<th>资产分类</th>
				<th>资产名称</th>
				<th>出厂日期</th>
				<th>ERP资产号</th>
				<th>物品品类编码</th>
				<th>责任人</th>
				<th>启用日期</th>
				<shiro:hasPermission name="myasset:busiAssetMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiAssetMain">
			<tr>
				<td>
					${busiAssetMain.libinBillNo}
				</td>
				<td>
					${fns:getDictLabel(busiAssetMain.status, 'myasset_asset_status', '')}
				</td>
				<td>
					${fns:getDictLabel(busiAssetMain.measureUnitId, 'myasset_measure_unit', '')}
				</td>
				<td><a href="${ctx}/myasset/busiAssetMain/form?id=${busiAssetMain.id}">
					${busiAssetMain.assetGlobalId}
					</a>
				</td>
				<td>
					${busiAssetMain.assetRfidTagid}
				</td>
				<td>
					${fns:getOfficename(busiAssetMain.companyId)}
				</td>
				<td>
					${busiAssetMain.office.name}
				</td>
				<td>
					${fns:getPlacename(busiAssetMain.placeId)}
				</td>
				<td>
					${busiAssetMain.category.name}
				</td>
				<td>
					${fns:getAssetname(busiAssetMain.assetnameId)}
				</td>
				<td>
					<fmt:formatDate value="${busiAssetMain.produceDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${busiAssetMain.erpAssetCode}
				</td>
				<td>
					${busiAssetMain.goodsType}
				</td>
				<td>
					${busiAssetMain.respPerson}
				</td>
				<td>
					<fmt:formatDate value="${busiAssetMain.activeTime}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="myasset:busiAssetMain:edit"><td>
    				<a href="${ctx}/myasset/busiAssetMain/form?id=${busiAssetMain.id}">修改</a>
					<a href="${ctx}/myasset/busiAssetMain/delete?id=${busiAssetMain.id}" onclick="return confirmx('确认要删除该资产主表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>