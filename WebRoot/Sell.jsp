<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ page import="java.util.*"%>

<%
String path=request.getContextPath();
%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>产品销售信息</title>
<script type="text/javascript">  
function beforeSubmit(myform01){   
	if(myform01.squantity.value!="${rows.outquantity }"
			&& myform01.remarks.value=='')
	{    
			alert('备注不能为空');     
			myform01.remarks.focus();     
			return false;    
	}
	return true;  
}
</script>
</head>
<body>
	${msg}

	<!-- 表达式 -->
	<form id="myform01" onSubmit="return beforeSubmit(this);" action="<%=path%>/Sell.html" method="post">
		<!-- 显示获取信息 -->
		<div style="margin:100px auto 0 auto;width:55%;">
		<table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center;">
			<caption>
				销售产品信息
				<hr width="160">
			</caption>
			<tr style="visibility:hidden">
				<td>出货流水号</td>
				<td>
					<e:text name="outid"/>
				</td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td>${rows.bgname }
						</td>
				<td>编号</td>
				<td>${rows.bgnumber }
						</td>
			</tr>
			<tr>
				<td>分级</td>
				<td>${rows.level }
						</td>
				<td>产地</td>
				<td>${rows.bgchandi }
						</td>
			</tr>
			<tr>
				<td>种类</td>
				<td>${rows.type }
						</td>
				<td>保质期</td>
				<td>${rows.bgexp }
						</td>
			</tr>
			<tr></tr>
		
			<tr>
				<td>数量</td>
				<td><e:text style="height:30px" name="squantity" defval="${rows.outquantity }" required="true"/></td>
				<td>购买方</td>
				<td><e:text style="height:30px" name="spurchaser" required="true" /></td>
			</tr>
			<tr>
				<td>售价/袋(￥)</td>
				<td><e:text style="height:30px" name="sprice" defval="${rows.bgprice }" required="true"/></td>
				<td>备注</td>
				<td><e:text style="height:30px" name="remarks" /></td>
			</tr>
		</table>
		<!-- 功能按钮 -->
		<div style = "float:right">
			<input type="hidden" name="snumber" value="${rows.bgnumber }">
        	<button type="submit" class="btn btn-info" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
		</div>
	</form>
</body>
</html>