<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产维护管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiMaintainBill/">资产维护列表</a></li>
		<shiro:hasPermission name="myasset:busiMaintainBill:edit"><li><a href="${ctx}/myasset/busiMaintainBill/form">资产维护添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiMaintainBill" action="${ctx}/myasset/busiMaintainBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>维护单编号：</label>
				<form:input path="maintainBillNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>申请人公司：</label>
				<sys:treeselect id="company" name="company.id" value="${busiMaintainBill.company.id}" labelName="company.name" labelValue="${busiMaintainBill.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请人部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiMaintainBill.office.id}" labelName="office.name" labelValue="${busiMaintainBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>维护申请人：</label>
				<form:input path="applyPerson" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>申请维修日期：</label>
				<input name="applyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${busiMaintainBill.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>维护单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_maintain_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>维护单编号</th>
				<th>申请人公司</th>
				<th>申请人部门</th>
				<th>维护申请人</th>
				<th>维护数量</th>
				<th>申请维修日期</th>
				<th>维护单状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiMaintainBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiMaintainBill">
			<tr>
				<td><a href="${ctx}/myasset/busiMaintainBill/form?id=${busiMaintainBill.id}">
					${busiMaintainBill.maintainBillNo}
				</a></td>
				<td>
					${busiMaintainBill.company.name}
				</td>
				<td>
					${busiMaintainBill.office.name}
				</td>
				<td>
					${busiMaintainBill.applyPerson}
				</td>
				<td>
					${busiMaintainBill.maintainNum}
				</td>
				<td>
					<fmt:formatDate value="${busiMaintainBill.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(busiMaintainBill.status, 'myasset_maintain_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${busiMaintainBill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiMaintainBill:edit"><td>
    				<a href="${ctx}/myasset/busiMaintainBill/form?id=${busiMaintainBill.id}">修改</a>
					<a href="${ctx}/myasset/busiMaintainBill/delete?id=${busiMaintainBill.id}" onclick="return confirmx('确认要删除该资产维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>