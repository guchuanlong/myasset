<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点大类管理</title>
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
		<li class="active"><a href="${ctx}/myasset/pandianBigclass/">盘点大类列表</a></li>
		<shiro:hasPermission name="myasset:pandianBigclass:edit"><li><a href="${ctx}/myasset/pandianBigclass/form">盘点大类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianBigclass" action="${ctx}/myasset/pandianBigclass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>大类编码：</label>
				<form:input path="bigclassCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>大类名称：</label>
				<form:input path="bigclassName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>大类编码</th>
				<th>大类名称</th>
				<th>创建时间</th>
				<shiro:hasPermission name="myasset:pandianBigclass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianBigclass">
			<tr>
				<td><a href="${ctx}/myasset/pandianBigclass/form?id=${pandianBigclass.id}">
					${pandianBigclass.bigclassCode}
				</a></td>
				<td>
					${pandianBigclass.bigclassName}
				</td>
				<td>
					<fmt:formatDate value="${pandianBigclass.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:pandianBigclass:edit"><td>
    				<a href="${ctx}/myasset/pandianBigclass/form?id=${pandianBigclass.id}">修改</a>
					<a href="${ctx}/myasset/pandianBigclass/delete?id=${pandianBigclass.id}" onclick="return confirmx('确认要删除该盘点大类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>