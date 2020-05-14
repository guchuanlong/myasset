<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供电单位管理</title>
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
		<li class="active"><a href="${ctx}/myasset/pandianPowerunit/">供电单位列表</a></li>
		<shiro:hasPermission name="myasset:pandianPowerunit:edit"><li><a href="${ctx}/myasset/pandianPowerunit/form">供电单位添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianPowerunit" action="${ctx}/myasset/pandianPowerunit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>city_name：</label>
				<form:input path="cityName" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>powerunit_code：</label>
				<form:input path="powerunitCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>powerunit_name：</label>
				<form:input path="powerunitName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>city_name</th>
				<th>powerunit_code</th>
				<th>powerunit_name</th>
				<th>创建时间</th>
				<shiro:hasPermission name="myasset:pandianPowerunit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianPowerunit">
			<tr>
				<td><a href="${ctx}/myasset/pandianPowerunit/form?id=${pandianPowerunit.id}">
					${pandianPowerunit.cityName}
				</a></td>
				<td>
					${pandianPowerunit.powerunitCode}
				</td>
				<td>
					${pandianPowerunit.powerunitName}
				</td>
				<td>
					<fmt:formatDate value="${pandianPowerunit.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:pandianPowerunit:edit"><td>
    				<a href="${ctx}/myasset/pandianPowerunit/form?id=${pandianPowerunit.id}">修改</a>
					<a href="${ctx}/myasset/pandianPowerunit/delete?id=${pandianPowerunit.id}" onclick="return confirmx('确认要删除该供电单位吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>