<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产库存管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiStockLib/">资产库存列表</a></li>
		<shiro:hasPermission name="myasset:busiStockLib:edit"><li><a href="${ctx}/myasset/busiStockLib/form">资产库存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiStockLib" action="${ctx}/myasset/busiStockLib/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属公司：</label>
				<form:input path="companyId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiStockLib.office.id}" labelName="office.name" labelValue="${busiStockLib.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>资产分类：</label>
				<sys:treeselect id="category" name="category.id" value="${busiStockLib.category.id}" labelName="category.name" labelValue="${busiStockLib.category.name}"
					title="资产分类" url="/myasset/busiCategory/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>资产名称：</label>
				<form:input path="assetnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>存放地点：</label>
				<form:input path="placeId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>资产型号：</label>
				<form:input path="modelFormat" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>资产分类</th>
				<th>资产名称</th>
				<th>存放地点</th>
				<th>资产型号</th>
				<th>计量单位</th>
				<th>生产厂家</th>
				<th>设备编号</th>
				<shiro:hasPermission name="myasset:busiStockLib:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiStockLib">
			<tr>
				<td><a href="${ctx}/myasset/busiStockLib/form?id=${busiStockLib.id}">
					${busiStockLib.companyId}
				</a></td>
				<td>
					${busiStockLib.office.name}
				</td>
				<td>
					${busiStockLib.category.name}
				</td>
				<td>
					${busiStockLib.assetnameId}
				</td>
				<td>
					${busiStockLib.placeId}
				</td>
				<td>
					${busiStockLib.modelFormat}
				</td>
				<td>
					${busiStockLib.measureUnitId}
				</td>
				<td>
					${busiStockLib.produceFactory}
				</td>
				<td>
					${busiStockLib.deviceNo}
				</td>
				<shiro:hasPermission name="myasset:busiStockLib:edit"><td>
    				<a href="${ctx}/myasset/busiStockLib/form?id=${busiStockLib.id}">修改</a>
					<a href="${ctx}/myasset/busiStockLib/delete?id=${busiStockLib.id}" onclick="return confirmx('确认要删除该资产库存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>