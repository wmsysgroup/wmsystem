<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ page import="java.util.*"%>

<%
String path=request.getContextPath();
%>
<html>
<head>
<title>销售清单</title>
<script type="text/javascript">
      //选中的checkbox的个数
      var count=0;
      function onSelect(obj)
      {
    	  with(document.forms['myform'])
    	  {
    		  obj?++count:--count;
    		  next[1].disabled=(count==0);
    	  }
      }
</script>
<script type="text/javascript">
     function onEdit(obj)
     {
    	 with(document.forms['myform'])
    	 {
    		 action="<%=path%>/getToSaleInfo.html?outid="+obj;
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
	<form id="myform" action="<%=path%>/SellList.html" method="post">
		<!-- 查询条件 -->
		<table border="1" width="95%" align="center">
			<caption>
				出库清单查询
				<hr width="160">
			</caption>
			<tr>
				<td colspan="3">查询条件</td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td><e:text name="pbgname" autofocus="true" /></td>
				<td>分级</td>
				<td><e:select name="pbglevel" value="1:1,2:2,3:3,==不限==:''"
						header="true" /></td>
				<td>单价</td>
				<td><e:text name="pbgprice" autofocus="true" /></td>
			</tr>
			<tr>
				<td>种类</td>
				<td><e:select name="pbgtype" value="1:1,2:2,3:3,==不限==:''"
						header="true" /></td>
				<td>数量</td>
				<td><e:text name="poutquantity" autofocus="true" /></td>
			</tr>
		</table>
		<!-- 数据迭代 -->
		<table border="1" width="95%" align="center">
			<tr>
				<td>出库流水号</td>
				<td>产品编号</td>
				<td>名称</td>
				<td>分级</td>
				<td>种类</td>
				<td>产地</td>
				<td>单价</td>
				<td>数量</td>
				<td>操作</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach var="ins" items="${rows }" varStatus="vs">
						<tr>
							<td>${ins.outid }</td>
							<td>${ins.outnumber }</td>
							<td>${ins.bgname }</td>
							<td>${ins.bglevel }</td>
							<td>${ins.bgtype }</td>
							<td>${ins.bgchandi }</td>
							<td>${ins.bgprice }</td>
							<td>${ins.outquantity }</td>
							<td><a href="#" onclick="onEdit('${ins.outid}')">销售 </a></td>
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
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<!-- 功能按钮 -->
		<table border="0" width="95%" align="center">
			<tr>
				<td align="center"><input type="submit" name="next" value="查询">
			</tr>
		</table>
	</form>
</body>
</html>