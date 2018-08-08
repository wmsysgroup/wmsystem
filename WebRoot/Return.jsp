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

<title>产品退货信息</title>
</head>
<body>
	${msg }
	<br>
	<br>
	<!-- 表达式 -->
	<form id="myform11" action="<%=path%>/Return.html" method="post">
		<!-- 显示获取信息 -->
		<div style="margin:100px auto 0 auto;width:65%;">
		<table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center;">
			<caption>
				退货产品信息
				<hr width="160">
			</caption>
			<tr>
				<td>销售编号</td>
				<td>${row.sid }
				</td>
				<td>货物名称</td>
				<td>${row.bgname }
						</td>
			</tr>
			<tr>
				<td>售价/袋(￥)</td>
				<td>${row.sprice }
						</td>
				<td>分级</td>
				<td>${row.level }
						</td>
			</tr>
			<tr>
				<td>种类</td>
				<td>${row.type }
						</td>
				<td>购买方</td>
				<td>${row.spurchaser }
						</td>
			</tr>
			<tr>
				<td>货物编号</td>
				<td>
					<e:text name="rnumber" defval="${row.snumber }"
						style="visibility:hidden" />
				</td>
			</tr>
			<tr></tr>
	
			<tr>
				<td>数量</td>
				<td><e:text style="height:30px" name="rquantity" required="true" /></td>
				<td>备注</td>
				<td><e:text style="height:30px" name="remarks" /></td>
			</tr>
		</table>
		<!-- 功能按钮 -->
		 <div style = "float:right">
		 	<input type="hidden" name="sid" value="${row.sid }">
        	<button type="submit" class="btn btn-info" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
		</div>
	</form>
</body>
</html>