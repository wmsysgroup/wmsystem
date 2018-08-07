<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<form action="<%=path %>/InListServlet" method="post">
	<table border="1" align="center">
	
	  <c:choose>
    	 <c:when test="${rows!=null }">
		     <c:forEach var="ins" items="${rows }" varStatus="vs">
		     
				<tr>
					
					<td width="100" align="center">${ins.plid}</td>
					<td width="100" align="center">大米名称</td>
					<td width="100" align="center">数量</td>
					<td width="100" align="center">负责人</td>
					<td width="100" align="center">进货日期</td>
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
		</table>
		
		<input type="submit" value="查询">
		</form>
</body>
</html>