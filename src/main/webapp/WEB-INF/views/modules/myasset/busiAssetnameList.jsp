<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产名称管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiAssetname/">资产名称列表</a></li>
		<shiro:hasPermission name="myasset:busiAssetname:edit"><li><a href="${ctx}/myasset/busiAssetname/form">资产名称添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiAssetname" action="${ctx}/myasset/busiAssetname/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资产名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>

			</li>
			<li><label>资产分类：</label>
				<sys:treeselect id="category" name="category.id" value="${busiAssetname.category.id}" labelName="category.name" labelValue="${busiAssetname.category.name}"
					title="资产分类" url="/myasset/busiCategory/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资产名称</th>
				<th>资产分类</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiAssetname:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiAssetname">
			<tr>
				<td><a href="${ctx}/myasset/busiAssetname/form?id=${busiAssetname.id}">
					${busiAssetname.name}
				</a></td>
				<td>
					${busiAssetname.category.name}
				</td>
				<td>
					<fmt:formatDate value="${busiAssetname.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiAssetname:edit"><td>
    				<a href="${ctx}/myasset/busiAssetname/form?id=${busiAssetname.id}">修改</a>
					<a href="${ctx}/myasset/busiAssetname/delete?id=${busiAssetname.id}" onclick="return confirmx('确认要删除该资产名称吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>