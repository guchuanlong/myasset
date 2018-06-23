<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产存放地点管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiPlace/">资产存放地点列表</a></li>
		<shiro:hasPermission name="myasset:busiPlace:edit"><li><a href="${ctx}/myasset/busiPlace/form">资产存放地点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiPlace" action="${ctx}/myasset/busiPlace/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiPlace.office.id}" labelName="office.name" labelValue="${busiPlace.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>地点编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>地点名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>归属部门</th>
				<th>地点编码</th>
				<th>地点名称</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiPlace:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiPlace">
			<tr>
				<td><a href="${ctx}/myasset/busiPlace/form?id=${busiPlace.id}">
					${busiPlace.office.name}
				</a></td>
				<td>
					${busiPlace.code}
				</td>
				<td>
					${busiPlace.name}
				</td>
				<td>
					<fmt:formatDate value="${busiPlace.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiPlace:edit"><td>
    				<a href="${ctx}/myasset/busiPlace/form?id=${busiPlace.id}">修改</a>
					<a href="${ctx}/myasset/busiPlace/delete?id=${busiPlace.id}" onclick="return confirmx('确认要删除该资产存放地点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>