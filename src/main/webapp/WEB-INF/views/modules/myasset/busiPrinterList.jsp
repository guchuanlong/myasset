<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>打印机管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiPrinter/">打印机列表</a></li>
		<shiro:hasPermission name="myasset:busiPrinter:edit"><li><a href="${ctx}/myasset/busiPrinter/form">打印机添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiPrinter" action="${ctx}/myasset/busiPrinter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属用户：</label>
				<sys:treeselect id="user" name="user.id" value="${busiPrinter.user.id}" labelName="user.name" labelValue="${busiPrinter.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiPrinter.office.id}" labelName="office.name" labelValue="${busiPrinter.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属区域：</label>
				<sys:treeselect id="area" name="area.id" value="${busiPrinter.area.id}" labelName="area.name" labelValue="${busiPrinter.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>打印机IP：</label>
				<form:input path="printerIp" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>打印机名称：</label>
				<form:input path="printerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>归属用户</th>
				<th>归属部门</th>
				<th>归属区域</th>
				<th>打印机IP</th>
				<th>打印机端口</th>
				<th>打印机名称</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiPrinter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiPrinter">
			<tr>
				<td><a href="${ctx}/myasset/busiPrinter/form?id=${busiPrinter.id}">
					${busiPrinter.user.name}
				</a></td>
				<td>
					${busiPrinter.office.name}
				</td>
				<td>
					${busiPrinter.area.name}
				</td>
				<td>
					${busiPrinter.printerIp}
				</td>
				<td>
					${busiPrinter.printerPort}
				</td>
				<td>
					${busiPrinter.printerName}
				</td>
				<td>
					<fmt:formatDate value="${busiPrinter.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiPrinter:edit"><td>
    				<a href="${ctx}/myasset/busiPrinter/form?id=${busiPrinter.id}">修改</a>
					<a href="${ctx}/myasset/busiPrinter/delete?id=${busiPrinter.id}" onclick="return confirmx('确认要删除该打印机吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>