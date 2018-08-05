<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${msg}

	<form action="<%=path %>/SalesInquireServlet" method="get">
        <table border="1" style="border-collapse: collapse">
        	  <tr>
                <td>销售编号</td>
                <td><input type="text" name="sid"></td>
                <td>出库编号</td>
                <td>
                	<input type="text" name="outid">
                </td>
            </tr>
            <tr>
                <td>货物编号</td>
                <td><input type="text" name="snumber"></td>
                <td>负责人</td>
                <td>
                	<input type="text" name="sprincipal">
                </td>
            </tr>
            <tr>
                <td>购买方</td>
                <td>
                   <input type="text" name="spurchaser">
                </td>
                <td>单价</td>
                <td><input type="number" name="sprice"></td>
            </tr>
            <tr>
                <td>起始时间</td>
                <td>
                    <input type="date" name="bdate">
                </td>
                <td>截止时间</td>
                <td>
                    <input type="date" name="edate"> 
                </td>
            </tr>
        </table>
        <input type="submit" value="查询">
        
        <table border="1" width="95%" align="center" style="border-collapse: collapse">
		   <tr>
		     <td></td>
		     <td>销售编号</td>
		     <td>出库编号</td>
		     <td>货物编号</td>
		     <td>单价</td>
		     <td>数量</td>
		     <td>购买方</td>
		     <td>销售日期</td>
		     <td>负责人</td>
		     <td>备注</td>
		   </tr>
		   <c:choose>
		     <c:when test="${rows!=null }">
			     <c:forEach var="ins" items="${rows }" varStatus="vs">
			   		  <tr>
			   		  	 <td>${vs.count }</td>
			   		  	 <td>${ins.sid }</td>
			   		  	 <td>${ins.outid }</td>
					     <td>${ins.snumber }</td>
					     <td>${ins.sprice }</td>
					     <td>${ins.squantity }</td>
					     <td>${ins.spurchaser }</td>
					     <td>${ins.sdate }</td>
					     <td>${ins.sprincipal }</td>
					     <td>${ins.remarks }</td>
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
		             <td></td>
		           </tr>
		         </c:forEach>
		     </c:otherwise>
		   </c:choose>
		 </table>
    </form>
</body>
</html>