<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点基础表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/myasset/pandianBase/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/myasset/pandianBase/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/myasset/pandianBase/">盘点基础表列表</a></li>
		<shiro:hasPermission name="myasset:pandianBase:edit"><li><a href="${ctx}/myasset/pandianBase/form">盘点基础表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pandianBase" action="${ctx}/myasset/pandianBase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务单号：</label>
				<form:input path="batchUuid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>地市名称：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公司：</label>
				<sys:treeselect id="company" name="company.id" value="${pandianBase.company.id}" labelName="company.name" labelValue="${pandianBase.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>接线方式：</label>
				<form:input path="connectMethod" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>供电单位：</label>
				<sys:treeselect id="office" name="office.id" value="${pandianBase.office.id}" labelName="office.name" labelValue="${pandianBase.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>资产条形码：</label>
				<form:input path="assetBarcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>导入时间：</label>
				<input name="baseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pandianBase.baseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
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
				<th>供电单位</th>
				<th>接线方式</th>
				<th>资产条形码</th>
				<th>资产大类编码</th>
				<th>资产大类名称</th>
				<th>导入时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="myasset:pandianBase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pandianBase">
			<tr>
				<td>
					${pandianBase.batchUuid}
				</td>
				<td>
					${pandianBase.cityName}
				</td>
				<td>
					${pandianBase.company.name}
				</td>
				<td>
					${pandianBase.office.name}
				</td>
				<td>
					${pandianBase.connectMethod}
				</td>
				<td><a href="${ctx}/myasset/pandianBase/form?id=${pandianBase.id}">
					${pandianBase.assetBarcode}
				</a></td>
				<td>
					${pandianBase.bigClassCode}
				</td>
				<td>
					${pandianBase.bigClassName}
				</td>
				<td>
					<fmt:formatDate value="${pandianBase.baseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pandianBase.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="myasset:pandianBase:edit"><td>
    				<a href="${ctx}/myasset/pandianBase/form?id=${pandianBase.id}">修改</a>
					<a href="${ctx}/myasset/pandianBase/delete?id=${pandianBase.id}" onclick="return confirmx('确认要删除该盘点基础表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>