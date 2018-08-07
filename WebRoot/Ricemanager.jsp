<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>

<!DOCTYPE html>
<html>
<head>
<title>大米管理</title>
<script type="text/javascript">
 function onEdit(obj)
     {
    	 with(document.forms['myform'])
    	 {
    		 action="<%=path %>/RiceEditServlet?bgnumber="+obj;
    		 //alert(action);
    		 submit();
    	 }
     } 
   </script>
</head>
<body>
${msg }
<br>
<br>
<br>
<form id = "myform" action="<%=path %>/RiceManagerServlet" method="post">
 <table border="1" width="95%" align="center">
   <caption>
       大米信息管理
   <hr width="160"> 
   </caption>
            <tr>
		        <td>货物名称</td>
		        <td>
		        <e:text name="bgname" autofocus="true"/>
		        </td>
		        <td>等级</td>
		        <td>
		        <e:select value="一级:1,二级:2,三级:3,特级:4" name="bglevel"/>
		        </td>
		     </tr>
		     <tr>
		        <td>产地</td>
		        <td>
		        <e:text name="bgchandi"/>
		        </td>
		        <td>种类</td>
		        <td>
		        <e:select value="粳米:1,五常米:2,香米:3,丝苗米:4" name="bgtype"/>
                </td>
              </tr>

  </table>
  <!-- 数据迭代 -->
 <table border="1" width="95%" align="center">
   <tr>
     <td></td>
     <td>编号</td>
     <td>名称</td>
     <td>等级</td>
     <td>保质期</td>
     <td>价格</td>
     <td>规格</td>
     <td>类型</td>
     <td>产地</td>
   </tr>
   <c:choose>
     <c:when test="${rows!=null }">
	     <c:forEach var="ins" items="${rows }" varStatus="vs">
	   		  <tr>
			     <td>${vs.count }</td>
			     <td>${ins.bgnumber }</td>
			     <td>
			       <!-- 空锚链接 -->
			       <a href="#" onclick="onEdit('${ins.bgnumber}')" >
			         ${ins.bgname }
			       </a> 
			     </td>
			     <td>${ins.bglevel }</td>
			     <td>${ins.bgexp }</td>
			     <td>${ins.bgprice }</td>
			     <td>${ins.bgspec }</td>
			     <td>${ins.bgtype } </td>
			     <td>${ins.bgchandi }</td>
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
 <table border="1" width="95%" align="center">
   <tr>
     <td align="center">
       <input type="submit" name="next" value="查询">
       <input type="submit" name="next" value="添加" formaction="<%=path%>/AddRice.jsp">
     </td> 
   </tr>
 </table>
   
</form>

</body>
</html>