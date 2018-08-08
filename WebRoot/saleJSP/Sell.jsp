<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ page import="java.util.*"%>

<%
String path=request.getContextPath();
%>
<html>
<head>
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
	<br>
	<br>
	<!-- 表达式 -->
	<form id="myform01" onSubmit="return beforeSubmit(this);" action="<%=path%>/Sell.html" method="post">
		<!-- 显示获取信息 -->
		<table border="0" width="50%" align="center">
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
				<td><e:text name="bgname" defval="${rows.bgname }"
						readonly="true" /></td>
				<td>编号</td>
				<td><e:text name="snumber" defval="${rows.bgnumber }"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>分级</td>
				<td><e:text name="level" defval="${rows.level }"
						readonly="true" /></td>
				<td>产地</td>
				<td><e:text name="bgchandi" defval="${rows.bgchandi }"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>种类</td>
				<td><e:text name="type" defval="${rows.type }"
						readonly="true" /></td>
				<td>保质期</td>
				<td><e:text name="bgexp" defval="${rows.bgexp }"
						readonly="true" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td>数量</td>
				<td><e:text name="squantity" defval="${rows.outquantity }" required="true"/></td>
				<td>购买方</td>
				<td><e:text name="spurchaser" required="true" /></td>
			</tr>
			<tr>
				<td>售价/斤(￥)</td>
				<td><e:text name="sprice" defval="${rows.bgprice }" required="true"/></td>
				<td>备注</td>
				<td><e:text name="remarks" /></td>
			</tr>
		</table>
		<!-- 功能按钮 -->
		<table border="0" width="50%" align="center">
			<tr>
				<td align="center"><input type="submit" name="next" value="提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>