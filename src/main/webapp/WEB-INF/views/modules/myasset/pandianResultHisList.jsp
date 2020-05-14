<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点结果历史管理</title>
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
		<li class="active"><a href="${ctx}/myasset/pandianResultHis/">盘点结果历史列表</a></li>
		<%-- <shiro:hasPermission name="myasset:pandianResultHis:edit"><li><a href="${ctx}/myasset/pandianResultHis/form">盘点结果历史添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianResultHis" action="${ctx}/myasset/pandianResultHis/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地市名称：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公司：</label>
				<sys:treeselect id="company" name="company.id" value="${pandianResultHis.company.id}" labelName="company.name" labelValue="${pandianResultHis.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>供电单位：</label>
				<sys:treeselect id="office" name="office.id" value="${pandianResultHis.office.id}" labelName="office.name" labelValue="${pandianResultHis.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>接线方式：</label>
				<form:input path="connectMethod" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>大类编码：</label>
				<form:input path="bigClassCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>大类名称：</label>
				<form:input path="bigClassName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>资产条形码：</label>
				<form:input path="assetBarcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>盘点任务号：</label>
				<form:input path="batchUuid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>盘点任务号</th>
				<th>地市名称</th>
				<th>公司</th>
				<th>接线方式</th>
				<th>供电单位</th>
				<th>资产条形码</th>
				<th>资产大类编码</th>
				<th>资产大类名称</th>
				<th>盘点状态</th>
				<th>导入时间</th>
				<th>盘点时间</th>
				<th>更新时间</th>
				<th>操作时间</th>
				<th>操作人</th>
				<%-- <shiro:hasPermission name="myasset:pandianResultHis:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianResultHis">
			<tr>
				<td>
					${pandianResultHis.batchUuid}
				</td>
				<td>
					${pandianResultHis.cityName}
				</td>
				<td>
					${pandianResultHis.company.name}
				</td>
				<td>
					${pandianResultHis.connectMethod}
				</td>
				<td>
					${pandianResultHis.office.name}
				</td>
				<td><a href="${ctx}/myasset/pandianResultHis/form?id=${pandianResultHis.id}">
					${pandianResultHis.assetBarcode}
				</a></td>
				<td>
					${pandianResultHis.bigClassCode}
				</td>
				<td>
					${pandianResultHis.bigClassName}
				</td>
				<td>
					${fns:getDictLabel(pandianResult.pandianStatusId, 'pandian_result_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${pandianResult.baseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pandianResult.resultDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pandianResult.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					<fmt:formatDate value="${pandianResultHis.historyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pandianResultHis.historyLoginName}
				</td>
				<%-- <shiro:hasPermission name="myasset:pandianResultHis:edit"><td>
    				<a href="${ctx}/myasset/pandianResultHis/form?id=${pandianResultHis.id}">修改</a>
					<a href="${ctx}/myasset/pandianResultHis/delete?id=${pandianResultHis.id}" onclick="return confirmx('确认要删除该盘点结果历史吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>