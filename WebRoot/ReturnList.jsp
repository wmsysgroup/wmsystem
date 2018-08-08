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
<title>退货清单</title>
<script type="text/javascript">
     function onEdit(obj)
     {
    	 with(document.forms['myform1'])
    	 {
    		 action="<%=path%>/GetToReturnInfo.html?sid="+obj;
    		 submit();
    	 }
     } 
</script>
</head>
<body>
	${msg }
	<br>
	<br>
	<!-- 表达式 -->
	<form id="myform1" action="<%=path%>/ReturnList.html" method="post">
		<!-- 查询条件 -->
		<div style="margin:0 auto;width:80%">
	
		<div style="margin:0 auto;width:75%">
		<table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:left">
			<caption>
				销售清单查询
				<hr width="160">
			</caption>
			<tr>
				<td>销售流水号</td>
				<td><e:text style="height:30px" name="saleid" autofocus="true" /></td>
				<td>产品名称</td>
				<td><e:text style="height:30px" name="bgname" autofocus="true"/></td>
			</tr>
			<tr>
				<td>日期</td>
				<td><e:date style="width:130px;height:30px" name="bsdate" /> ~ <e:date style="width:130px;height:30px" name="esdate" /></td>
				<td>购买方</td>
				<td><e:text style="height:30px"  name="spurchaser" autofocus="true" /></td>
			</tr>
		</table>
		</div>
		<!-- 数据迭代 -->
		<table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
			<tr align="center">
				<td>销售流水号</td>
				<td>产品编号</td>
				<td>货物名称</td>
				<td>销售日期</td>
				<td>购买方</td>
				<td>操作</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach var="ins" items="${rows }" varStatus="vs">
						<tr>
							<td>${ins.sid }</td>
							<td>${ins.bgnumber }</td>
							<td>${ins.bgname }</td>
							<td>${ins.sdate }</td>
							<td>${ins.spurchaser }</td>
							<td><a href="#" onclick="onEdit('${ins.sid}')"> 退货 </a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" step="1" end="10">
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		   <div style = "float:right">
        	<button type="submit" class="btn btn-info" >查询</button>
        </div>
		</div>
	</form>
</body>
</html>