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

<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

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
<div style="margin:0 auto;width:90%">
	
	<div style="margin:0 auto;width:60%">
 <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
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
		        <e:select value="一级:1,二级:2,三级:3,特级:4" header="true" name="bglevel"/>
		        </td>
		     </tr>
		     <tr>
		        <td>产地</td>
		        <td>
		        <e:text name="bgchandi"/>
		        </td>
		        <td>种类</td>
		        <td>
		        <e:select value="粳米:1,五常米:2,香米:3,丝苗米:4" header="true" name="bgtype"/>
                </td>
              </tr>

  </table>
  </div>
   <div style = "float:right;padding-bottom:16px">
        	<button type="submit" class="btn btn-info" name="next">查询</button>
        	<button type="submit" class="btn btn-info" name="next" formaction="<%=path%>/AddRice.jsp">添加</button>
        </div>
  <!-- 数据迭代 -->
 <table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
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
 
 
   </div>
</form>

</body>
</html>