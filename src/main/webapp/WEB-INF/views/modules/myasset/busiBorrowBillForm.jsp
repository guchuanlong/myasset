<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产领用管理</title>
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
		<li><a href="${ctx}/myasset/busiBorrowBill/">资产领用列表</a></li>
		<li class="active"><a href="${ctx}/myasset/busiBorrowBill/form?id=${busiBorrowBill.id}">资产领用<shiro:hasPermission name="myasset:busiBorrowBill:edit">${not empty busiBorrowBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:busiBorrowBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="busiBorrowBill" action="${ctx}/myasset/busiBorrowBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">领用人归属公司：</label>
			<div class="controls">
				<sys:treeselect id="company" name="company.id" value="${busiBorrowBill.company.id}" labelName="company.name" labelValue="${busiBorrowBill.company.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领用人归属部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${busiBorrowBill.office.id}" labelName="office.name" labelValue="${busiBorrowBill.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领用人：</label>
			<div class="controls">
				<form:input path="borrowPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预期归还时间：</label>
			<div class="controls">
				<input name="expReturnDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiBorrowBill.expReturnDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">资产领用明细表：</label>
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
								<shiro:hasPermission name="myasset:busiBorrowBill:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="hasChooseAssetDtlList">
						</tbody>
						<shiro:hasPermission name="myasset:busiBorrowBill:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="_showAssetList();" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/javascript">
						$(document).ready(function() {
							var data = ${fns:toJson(busiBorrowBill.busiBorrowBillDtlList)};
							//渲染已选列表
							if(data!=null&&data!=undefined){
								_loadChooseAssetFromDb(data);
								
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="myasset:busiBorrowBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	<%@include file="/WEB-INF/views/modules/myasset/ejectChooseAssetList.jsp" %>
	</form:form>
	
	
</body>
</html>