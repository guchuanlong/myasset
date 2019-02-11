<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产领用管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiBorrowBill/">资产领用列表</a></li>
		<shiro:hasPermission name="myasset:busiBorrowBill:edit"><li><a href="${ctx}/myasset/busiBorrowBill/form">资产领用添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiBorrowBill" action="${ctx}/myasset/busiBorrowBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>领用单编号：</label>
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
			<li><label>领用人：</label>
				<form:input path="borrowPerson" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>领用单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_borrow_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>领用单编号</th>
				<th>领用人归属公司</th>
				<th>领用人归属部门</th>
				<th>领用人</th>
				<th>领用单状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiBorrowBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiBorrowBill">
			<tr>
				<td><a href="${ctx}/myasset/busiBorrowBill/form?id=${busiBorrowBill.id}">
					${busiBorrowBill.borrowBillNo}
				</a></td>
				<td>
					${busiBorrowBill.company.name}
				</td>
				<td>
					${busiBorrowBill.office.name}
				</td>
				<td>
					${busiBorrowBill.borrowPerson}
				</td>
				<td>
					${fns:getDictLabel(busiBorrowBill.status, 'myasset_borrow_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${busiBorrowBill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiBorrowBill:edit"><td>
    				<a href="${ctx}/myasset/busiBorrowBill/form?id=${busiBorrowBill.id}">修改</a>
					<a href="${ctx}/myasset/busiBorrowBill/delete?id=${busiBorrowBill.id}" onclick="return confirmx('确认要删除该资产领用吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>