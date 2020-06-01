<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产报废管理</title>
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
		<li class="active"><a href="${ctx}/myasset/busiScrapBill/">资产报废列表</a></li>
		<shiro:hasPermission name="myasset:busiScrapBill:edit"><li><a href="${ctx}/myasset/busiScrapBill/form">资产报废添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busiScrapBill" action="${ctx}/myasset/busiScrapBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报废单编号：</label>
				<form:input path="scrapBillNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>申请人公司：</label>
				<sys:treeselect id="company" name="company.id" value="${busiScrapBill.company.id}" labelName="company.name" labelValue="${busiScrapBill.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请人部门：</label>
				<sys:treeselect id="office" name="office.id" value="${busiScrapBill.office.id}" labelName="office.name" labelValue="${busiScrapBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="applyPerson" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报废单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_scrap_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>报废单编号</th>
				<th>申请人公司</th>
				<th>申请人部门</th>
				<th>申请人</th>
				<th>报废数量</th>
				<th>报废单状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:busiScrapBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busiScrapBill">
			<tr>
				<td><a href="${ctx}/myasset/busiScrapBill/form?id=${busiScrapBill.id}">
					${busiScrapBill.scrapBillNo}
				</a></td>
				<td>
					${busiScrapBill.company.name}
				</td>
				<td>
					${busiScrapBill.office.name}
				</td>
				<td>
					${busiScrapBill.applyPerson}
				</td>
				<td>
					${busiScrapBill.scrapNum}
				</td>
				<td>
					${fns:getDictLabel(busiScrapBill.status, 'myasset_scrap_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${busiScrapBill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:busiScrapBill:edit"><td>
    				<a href="${ctx}/myasset/busiScrapBill/form?id=${busiScrapBill.id}">修改</a>
					<a href="${ctx}/myasset/busiScrapBill/delete?id=${busiScrapBill.id}" onclick="return confirmx('确认要删除该资产报废吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>