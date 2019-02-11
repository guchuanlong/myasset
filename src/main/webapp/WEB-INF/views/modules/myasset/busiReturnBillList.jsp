<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产归还管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiReturnBill/">资产归还列表</a></li>
		<shiro:hasPermission name="myasset:busiReturnBill:edit"><li><a href="${ctx}/myasset/busiReturnBill/form">资产归还添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiReturnBill" action="${ctx}/myasset/busiReturnBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归还单编号：</label>
				<form:input path="returnBillNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归属公司：</label>
				<sys:treeselect id="company" name="company.id" value="${busiReturnBill.company.id}" labelName="company.name" labelValue="${busiReturnBill.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiReturnBill.office.id}" labelName="office.name" labelValue="${busiReturnBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归还人：</label>
				<form:input path="returnPerson" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>归还时间：</label>
				<input name="returnDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${busiReturnBill.returnDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>经办人：</label>
				<form:input path="operPerson" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>操作平台：</label>
				<form:select path="osPlatformId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_os_platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>归还单编号</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>归还人</th>
				<th>归还时间</th>
				<th>经办人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="myasset:busiReturnBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiReturnBill">
			<tr>
				<td><a href="${ctx}/myasset/busiReturnBill/form?id=${busiReturnBill.id}">
					${busiReturnBill.returnBillNo}
				</a></td>
				<td>
					${busiReturnBill.company.name}
				</td>
				<td>
					${busiReturnBill.office.name}
				</td>
				<td>
					${busiReturnBill.returnPerson}
				</td>
				<td>
					<fmt:formatDate value="${busiReturnBill.returnDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${busiReturnBill.operPerson}
				</td>
				<td>
					<fmt:formatDate value="${busiReturnBill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${busiReturnBill.remarks}
				</td>
				<shiro:hasPermission name="myasset:busiReturnBill:edit"><td>
    				<a href="${ctx}/myasset/busiReturnBill/form?id=${busiReturnBill.id}">修改</a>
					<a href="${ctx}/myasset/busiReturnBill/delete?id=${busiReturnBill.id}" onclick="return confirmx('确认要删除该资产归还吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>