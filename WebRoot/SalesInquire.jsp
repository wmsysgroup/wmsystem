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

<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${msg}

	<form action="<%=path %>/SalesInquireServlet" method="get">
	
	
	<div style="margin:0 auto;width:90%">
	
	<div style="margin:0 auto;width:60%">
        <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
        	  <tr>
                <td>销售编号</td>
                <td>
                	<e:text name="sid"/>
                </td>
                <td>出库编号</td>
                <td>
                	<e:text name="outid"/>
                </td>
            </tr>
            <tr>
                <td>货物编号</td>
                <td>
                	<e:text name="snumber"/>
                </td>
                <td>负责人</td>
                <td>
                	<e:text name="sprincipal"/>
                	
                </td>
            </tr>
            <tr>
                <td>购买方</td>
                <td>
                	<e:text name="spurchaser"/>
                   
                </td>
                <td>单价</td>
                <td>
                	<e:text name="sprice"/>
                	
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
       <div style = "float:right;padding-bottom:16px">
        	<button type="submit" class="btn btn-info" >查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        </div>
        <table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
		   <tr>
		     <td>序号</td>
		     <td>销售编号</td>
		     <td>出库编号</td>
		     <td>货物编号</td>
		     <td>单价</td>
		     <td>数量</td>
		     <td>购买方</td>
		     <td>销售日期</td>
		     <td>负责人</td>
		    
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
		 </div>
    </form>
</body>
</html>