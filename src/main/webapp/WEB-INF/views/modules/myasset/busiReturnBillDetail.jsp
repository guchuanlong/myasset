<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产归还管理</title>
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
		<li><a href="${ctx}/myasset/busiReturnBill/">资产归还列表</a></li>
		<li class="active"><a href="${ctx}/myasset/busiReturnBill/detail?id=${busiReturnBill.id}">资产归还详情<shiro:hasPermission name="myasset:busiReturnBill:edit">${not empty busiReturnBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:busiReturnBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="busiReturnBill" action="${ctx}/myasset/busiReturnBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属公司：</label>
			<div class="controls">
				<sys:treeselect id="company" name="company.id" value="${busiReturnBill.company.id}" labelName="company.name" labelValue="${busiReturnBill.company.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${busiReturnBill.office.id}" labelName="office.name" labelValue="${busiReturnBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归还人：</label>
			<div class="controls">
				<form:input path="returnPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经办人：</label>
			<div class="controls">
				<form:input path="operPerson" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">资产归还明细表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>借用单id</th>
								<th>资产编码</th>
							    <th>资产分类</th>
							    <th>资产名称</th>
							    <th>归属公司</th>
							    <th>归属部门</th>
							</tr>
						</thead>
						<tbody id="hasChooseAssetDtlList">
						</tbody>
						
					</table>
					
					<script type="text/javascript">
						$(document).ready(function() {
							var data = ${fns:toJson(busiReturnBill.busiReturnBillDtlList)};
							//渲染已选列表
							if(data!=null&&data!=undefined){
								_loadChooseAssetFromDb(data);
							}
						}); 
					</script>
				</div>
			</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<%@include file="/WEB-INF/views/modules/myasset/ejectChooseToReturnAssetListDetail.jsp" %>
	</form:form>
</body>
</html>