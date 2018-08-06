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

	<form id="form" action="<%=path %>/DepotInquireServlet" method="get">
        <table border="1" style="border-collapse: collapse">
        	  <tr>
                <td>货物名称</td>
                <td>
                	<e:text name="bgname"/>
                </td>
                <td>货物编号</td>
                <td>
                	<e:text name="innumber"/>
                </td>
            </tr>
            <tr>
                <td>库房编号</td>
                <td>
                	<e:select value="wMenu" name="wnumber" header="true"/>
                </td>
                <td>品级</td>
                <td>
                	<e:select value="levelMenu" name="bglevel" header="true"/>
                </td>
            </tr>
            <tr>
                <td>种类</td>
                <td>
                    <e:select value="typeMenu" name="bgtype" header="true"/>
                </td>
                <td>规格</td>
                <td>
					 <e:select value="specMenu" name="bgspec" header="true"/>
				</td>
            </tr>
            <tr>
                <td>起始时间</td>
                <td>
                	<e:date name="bdate"/>
          
                </td>
                <td>截止时间</td>
                <td>
                	<e:date name="edate"/>
                    
                </td>
            </tr>
        </table>
        <input type="submit" value="查询">
        
        <table border="1" style="border-collapse: collapse">
		   <tr>
		     <td>序号</td>
		     <td>货物名称</td>
		     <td>货物编号</td>
		     <td>仓库编号</td>
		     <td>品级</td>
		     <td>种类</td>
		     <td>规格</td>
		     <td>余量</td>
		     <td>入库日期</td>
		     <td>负责人</td>
		     <td>备注</td>
		   </tr>
		   <c:choose>
		     <c:when test="${rows!=null }">
			     <c:forEach var="ins" items="${rows }" varStatus="vs">
			   		  <tr>
			   		  	 <td>${vs.count }</td>
			   		  	 <td>${ins.bgname }</td>
			   		  	 <td>${ins.innumber }</td>
					     <td>${ins.wnumber }</td>
					     <td>${ins.bglevel }</td>
					     <td>${ins.bgtype }</td>
					     <td>${ins.bgspec }</td>
					     <td>${ins.number }</td>
					     <td>${ins.indate }</td>
					     <td>${ins.inprincipal }</td>
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
		             <td></td>
		           </tr>
		         </c:forEach>
		     </c:otherwise>
		   </c:choose>
		 </table>

    </form>
</body>
</html>