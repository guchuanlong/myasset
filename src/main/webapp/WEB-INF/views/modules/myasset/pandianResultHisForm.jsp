<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点结果历史管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/myasset/pandianResultHis/">盘点结果历史列表</a></li>
		<li class="active"><a href="${ctx}/myasset/pandianResultHis/form?id=${pandianResultHis.id}">盘点结果历史<shiro:hasPermission name="myasset:pandianResultHis:edit">${not empty pandianResultHis.id?'详情':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:pandianResultHis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pandianResultHis" action="${ctx}/myasset/pandianResultHis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">地市名称：</label>
			<div class="controls">
				<form:input path="cityName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司：</label>
			<div class="controls">
				<sys:treeselect id="company" name="company.id" value="${pandianResultHis.company.id}" labelName="company.name" labelValue="${pandianResultHis.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:input path="companyName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电单位：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${pandianResultHis.office.id}" labelName="office.name" labelValue="${pandianResultHis.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电单位名称：</label>
			<div class="controls">
				<form:input path="powerSupplyUnit" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接线方式：</label>
			<div class="controls">
				<form:input path="connectMethod" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产大类编码：</label>
			<div class="controls">
				<form:input path="bigClassCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产大类名称：</label>
			<div class="controls">
				<form:input path="bigClassName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">资产小类编码：</label>
			<div class="controls">
				<form:input path="smallClassCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产小类名称：</label>
			<div class="controls">
				<form:input path="smallClassName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">资产条形码：</label>
			<div class="controls">
				<form:input path="assetBarcode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">资产rfid epc：</label>
			<div class="controls">
				<form:input path="assetRfidEpc" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">盘点状态：</label>
			<div class="controls">
				<form:select path="pandianStatusId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pandian_result_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">盘点状态名称：</label>
			<div class="controls">
				<form:input path="pandianStatusName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">盘点任务号：</label>
			<div class="controls">
				<form:input path="batchUuid" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">基础数据导入时间：</label>
			<div class="controls">
				<input name="baseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pandianResultHis.baseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点结果时间：</label>
			<div class="controls">
				<input name="resultDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pandianResultHis.resultDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作时间：</label>
			<div class="controls">
				<input name="historyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pandianResultHis.historyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进入历史表备注：</label>
			<div class="controls">
				<form:input path="historyRemark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作人：</label>
			<div class="controls">
				<form:input path="historyLoginName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进入历史操作人id：</label>
			<div class="controls">
				<form:input path="historyUserId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="myasset:pandianResultHis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>