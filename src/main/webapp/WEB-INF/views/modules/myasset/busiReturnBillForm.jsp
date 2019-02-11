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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/myasset/busiReturnBill/">资产归还列表</a></li>
		<li class="active"><a href="${ctx}/myasset/busiReturnBill/form?id=${busiReturnBill.id}">资产归还<shiro:hasPermission name="myasset:busiReturnBill:edit">${not empty busiReturnBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="myasset:busiReturnBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="busiReturnBill" action="${ctx}/myasset/busiReturnBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">借用单id：</label>
			<div class="controls">
				<form:input path="borrowBillId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
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
			<label class="control-label">归还时间：</label>
			<div class="controls">
				<input name="returnDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busiReturnBill.returnDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
								<th>资产id</th>
								<th>备注信息</th>
								<shiro:hasPermission name="myasset:busiReturnBill:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="busiReturnBillDtlList">
						</tbody>
						<shiro:hasPermission name="myasset:busiReturnBill:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#busiReturnBillDtlList', busiReturnBillDtlRowIdx, busiReturnBillDtlTpl);busiReturnBillDtlRowIdx = busiReturnBillDtlRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="busiReturnBillDtlTpl">//<!--
						<tr id="busiReturnBillDtlList{{idx}}">
							<td class="hide">
								<input id="busiReturnBillDtlList{{idx}}_id" name="busiReturnBillDtlList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="busiReturnBillDtlList{{idx}}_delFlag" name="busiReturnBillDtlList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="busiReturnBillDtlList{{idx}}_borrowBillId" name="busiReturnBillDtlList[{{idx}}].borrowBillId" type="text" value="{{row.borrowBillId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="busiReturnBillDtlList{{idx}}_assetGlobalId" name="busiReturnBillDtlList[{{idx}}].assetGlobalId" type="text" value="{{row.assetGlobalId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<textarea id="busiReturnBillDtlList{{idx}}_remarks" name="busiReturnBillDtlList[{{idx}}].remarks" rows="1" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="myasset:busiReturnBill:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#busiReturnBillDtlList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var busiReturnBillDtlRowIdx = 0, busiReturnBillDtlTpl = $("#busiReturnBillDtlTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(busiReturnBill.busiReturnBillDtlList)};
							for (var i=0; i<data.length; i++){
								addRow('#busiReturnBillDtlList', busiReturnBillDtlRowIdx, busiReturnBillDtlTpl, data[i]);
								busiReturnBillDtlRowIdx = busiReturnBillDtlRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="myasset:busiReturnBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>