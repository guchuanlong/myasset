<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点小类管理</title>
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
		<li class="active"><a href="${ctx}/myasset/pandianSmallclass/">盘点小类列表</a></li>
		<shiro:hasPermission name="myasset:pandianSmallclass:edit"><li><a href="${ctx}/myasset/pandianSmallclass/form">盘点小类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianSmallclass" action="${ctx}/myasset/pandianSmallclass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>小类code：</label>
				<form:input path="smallclassCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>小类名称：</label>
				<form:input path="smallclassName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>小类code</th>
				<th>小类名称</th>
				<th>归属的大类code</th>
				<th>归属的大类名称</th>
				<shiro:hasPermission name="myasset:pandianSmallclass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianSmallclass">
			<tr>
				<td><a href="${ctx}/myasset/pandianSmallclass/form?id=${pandianSmallclass.id}">
					${pandianSmallclass.smallclassCode}
				</a></td>
				<td>
					${pandianSmallclass.smallclassName}
				</td>
				<td>
					${pandianSmallclass.bigclassCode}
				</td>
				<td>
					${pandianSmallclass.bigclassName}
				</td>
				<shiro:hasPermission name="myasset:pandianSmallclass:edit"><td>
    				<a href="${ctx}/myasset/pandianSmallclass/form?id=${pandianSmallclass.id}">修改</a>
					<a href="${ctx}/myasset/pandianSmallclass/delete?id=${pandianSmallclass.id}" onclick="return confirmx('确认要删除该盘点小类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>