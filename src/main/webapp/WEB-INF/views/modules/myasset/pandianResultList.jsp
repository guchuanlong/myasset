<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点结果管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出盘点结果吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/myasset/pandianResult/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
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
		<li class="active"><a href="${ctx}/myasset/pandianResult/">盘点结果列表</a></li>
		<shiro:hasPermission name="myasset:pandianResult:edit"><li><a href="${ctx}/myasset/pandianResult/form">盘点结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianResult" action="${ctx}/myasset/pandianResult/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地市名称：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公司：</label>
				<sys:treeselect id="company" name="company.id" value="${pandianResult.company.id}" labelName="company.name" labelValue="${pandianResult.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>接线方式：</label>
				<form:input path="connectMethod" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>供电单位：</label>
				<sys:treeselect id="office" name="office.id" value="${pandianResult.office.id}" labelName="office.name" labelValue="${pandianResult.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>盘点状态：</label>
				<form:select path="pandianStatusId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pandian_result_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>导入时间：</label>
				<input name="baseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pandianResult.baseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>盘点时间：</label>
				<input name="resultDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pandianResult.resultDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
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
				<shiro:hasPermission name="myasset:pandianResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianResult">
			<tr>
				<td>
					${pandianResult.batchUuid}
				</td>
				<td>
					${pandianResult.cityName}
				</td>
				<td>
					${pandianResult.company.name}
				</td>
				<td>
					${pandianResult.connectMethod}
				</td>
				<td>
					${pandianResult.office.name}
				</td>
				<td><a href="${ctx}/myasset/pandianResult/form?id=${pandianResult.id}">
					${pandianResult.assetBarcode}
				</a></td>
				<td>
					${pandianResult.bigClassCode}
				</td>
				<td>
					${pandianResult.bigClassName}
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
				
				<shiro:hasPermission name="myasset:pandianResult:edit"><td>
    				<a href="${ctx}/myasset/pandianResult/form?id=${pandianResult.id}">修改</a>
					<a href="${ctx}/myasset/pandianResult/delete?id=${pandianResult.id}" onclick="return confirmx('确认要删除该盘点结果吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>