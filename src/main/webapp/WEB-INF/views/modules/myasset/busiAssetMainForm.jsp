<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产主表管理</title>
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
		<li><a href="${ctx}/myasset/busiAssetMain/">资产主表列表</a></li>
		<li class="active"><a href="${ctx}/myasset/busiAssetMain/form?id=${busiAssetMain.id}">资产主表<shiro:hasPermission name="myasset:busiAssetMain:edit">${not empty busiAssetMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:busiAssetMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="busiAssetMain" action="${ctx}/myasset/busiAssetMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">库存标识：</label>
			<div class="controls">
				<form:input path="stockLibId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库单编号：</label>
			<div class="controls">
				<form:input path="libinBillNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量单位：</label>
			<div class="controls">
				<form:input path="measureUnitId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产编码：</label>
			<div class="controls">
				<form:input path="assetGlobalId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产RFID标识：</label>
			<div class="controls">
				<form:input path="assetRfidTagid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产条形码：</label>
			<div class="controls">
				<form:input path="assetBarCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产二维码：</label>
			<div class="controls">
				<form:input path="assetQrCode" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属公司：</label>
			<div class="controls">
				<form:input path="companyId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${busiAssetMain.office.id}" labelName="office.name" labelValue="${busiAssetMain.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">存放地点：</label>
			<div class="controls">
				<form:input path="placeId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产分类：</label>
			<div class="controls">
				<sys:treeselect id="category" name="category.id" value="${busiAssetMain.category.id}" labelName="category.name" labelValue="${busiAssetMain.category.name}"
					title="资产分类" url="/myasset/busiCategory/treeData" cssClass="" allowClear="true" notAllowSelectParent="false"/>		
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产名称：</label>
			<div class="controls">
				<form:input path="assetnameId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产厂家：</label>
			<div class="controls">
				<form:input path="produceFactory" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产型号：</label>
			<div class="controls">
				<form:input path="modelFormat" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备编号：</label>
			<div class="controls">
				<form:input path="deviceNo" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产配置：</label>
			<div class="controls">
				<form:input path="assetConfig" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出厂日期：</label>
			<div class="controls">
				<input name="produceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiAssetMain.produceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="unitPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">折旧方式：</label>
			<div class="controls">
				<form:input path="depreciationWayId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">折旧期限：</label>
			<div class="controls">
				<form:input path="depreciationPeriod" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签发行标识：</label>
			<div class="controls">
				<form:select path="tagIssueFlag" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('myasset_tag_issue_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<label class="control-label">ERP资产号：</label>
			<div class="controls">
				<form:input path="erpAssetCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物品品类编码：</label>
			<div class="controls">
				<form:input path="goodsType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任人：</label>
			<div class="controls">
				<form:input path="respPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签类别：</label>
			<div class="controls">
				<form:input path="tagClass" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用日期：</label>
			<div class="controls">
				<input name="activeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiAssetMain.activeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废日期：</label>
			<div class="controls">
				<input name="scrapTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiAssetMain.scrapTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购价格(元)：</label>
			<div class="controls">
				<form:input path="purchasePrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废年限：</label>
			<div class="controls">
				<form:input path="scrapYearLimit" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">净残值(元)：</label>
			<div class="controls">
				<form:input path="netSalvage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="myasset:busiAssetMain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>