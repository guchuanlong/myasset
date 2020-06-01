<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产维护管理</title>
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
	<style>
		.my-modal {
    width: 800px;
    /* height: 800px; */
}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/myasset/busiMaintainBill/">资产维护列表</a></li>
		<li class="active"><a href="${ctx}/myasset/busiMaintainBill/form?id=${busiMaintainBill.id}">资产维护<shiro:hasPermission name="myasset:busiMaintainBill:edit">${not empty busiMaintainBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:busiMaintainBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="busiMaintainBill" action="${ctx}/myasset/busiMaintainBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">维护单编号：</label>
			<div class="controls">
				<form:input path="maintainBillNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人公司：</label>
			<div class="controls">
				<sys:treeselect id="company" name="company.id" value="${busiMaintainBill.company.id}" labelName="company.name" labelValue="${busiMaintainBill.company.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${busiMaintainBill.office.id}" labelName="office.name" labelValue="${busiMaintainBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护申请人：</label>
			<div class="controls">
				<form:input path="applyPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护数量：</label>
			<div class="controls">
				<form:input path="maintainNum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请维修日期：</label>
			<div class="controls">
				<input name="applyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiMaintainBill.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护单状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_maintain_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作平台：</label>
			<div class="controls">
				<form:select path="osPlatformId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_os_platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经办人：</label>
			<div class="controls">
				<form:input path="operPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">资产维护明细表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
							<th>资产编码</th>
							    <th>资产分类</th>
							    <th>资产名称</th>
							    <th>归属公司</th>
							    <th>归属部门</th>
							    <th>存放地点</th>
								<th>操作</th>
								<shiro:hasPermission name="myasset:busiMaintainBill:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="hasChooseAssetDtlList">
						</tbody>
						<shiro:hasPermission name="myasset:busiMaintainBill:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="_showAssetList();" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/javascript">
						$(document).ready(function() {
							var data = ${fns:toJson(busiMaintainBill.busiMaintainBillDtlList)};
							//渲染已选列表
							if(data!=null&&data!=undefined){
								_loadChooseAssetFromDb(data);
								
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="myasset:busiMaintainBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	<%@include file="/WEB-INF/views/modules/myasset/ejectChooseAssetList.jsp" %>
	</form:form>
</body>
</html>