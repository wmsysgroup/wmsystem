<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ page import="java.util.*"%>

<%
String path=request.getContextPath();
%>
<html>
<head>
<title>产品退货信息</title>
</head>
<body>
	${msg }
	<br>
	<br>
	<!-- 表达式 -->
	<form id="myform11" action="<%=path%>/Return.html" method="post">
		<!-- 显示获取信息 -->
		<table border="0" width="50%" align="center">
			<caption>
				退货产品信息
				<hr width="160">
			</caption>
			<tr>
				<td>销售编号</td>
				<td><e:text name="sid" defval="${row.sid }" readonly="true" />
				</td>
				<td>货物名称</td>
				<td><e:text name="bgname" defval="${row.bgname }"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>售价/斤(￥)</td>
				<td><e:text name="sprice" defval="${row.sprice }"
						readonly="true" /></td>
				<td>分级</td>
				<td><e:text name="level" defval="${row.level }"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>种类</td>
				<td><e:text name="type" defval="${row.type }"
						readonly="true" /></td>
				<td>购买方</td>
				<td><e:text name="spurchaser" defval="${row.spurchaser }"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>货物编号</td>
				<td>
					<e:text name="rnumber" defval="${row.snumber }"
						style="visibility:hidden" />
				</td>
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
			<tr></tr>
			<tr></tr>
			<tr>
				<td>数量</td>
				<td><e:text name="rquantity" required="true" /></td>
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