<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>


<div style="width:60%; margin:100px auto">
<form action="<%=path%>/InbAddServlet" method="post">
	<table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
		<thead>
		 <tr>
             <td>进货编号</td>
             <td>货物名称</td>
             <td>数量</td>
             <td>供应商</td>
             <td>进货日期</td>
             <td>&nbsp;&nbsp;</td>
           </tr>
		<thead>
		<tbody>
	  <c:choose>
    	 <c:when test="${rows!=null }">
		     <c:forEach var="ins" items="${rows }" varStatus="vs">
		     
				<tr>
					<td width="100" align="center">${ins.plid}</td>
					<td width="100" align="center">${ins.bgname}</td>
					<td width="100" align="center">${ins.plquantity}</td>
					<td width="100" align="center">${ins.plsupplier}</td>
					<td width="100" align="center">${ins.pldate}</td>
					<td width="100" align="center"><button type="submit" name="next"  formaction="<%=path%>/InbAddServlet?plid=${ins.plid }">入库</button></td>
				</tr>
			</c:forEach>
			</c:when>
     <c:otherwise>
         <c:forEach begin="1" step="1" end="5">
           <tr>
             <td>&nbsp;</td>
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
   	<tbody>
		</table>
		</form>
		</div>


</body>
</html>