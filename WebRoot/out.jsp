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

<script type="text/javascript">
function beforeSubmit(obj){		
    obj = document.getElementsByName("number");
	var count=0;
    for(k in obj){   	
        if(obj[k].checked){
        	count++;
        	 
        }      	
    }
    if(count==0)
    {
		alert("请选中至少一个出库货物");
		return false;
	}
    return true;

	}
</script>

<script type="text/javascript">
function sumall(){		
    
    obj = document.getElementsByName("number");
	var count=0;
    for(k in obj){
        if(obj[k].checked)
            count=Number(obj[k].value)+count;
    }
	document.getElementById("ss").value=count;

	}
</script>

</head>

	<body>
	${msg }
		<form  action="<%=path %>/OutListServlet" method="post">
			<span>大米编号：</span><input name="bgnumber" type="text" value="${instad}"><br>
			<input type="submit" value="查询">
		</form>
		<br>
		
		<form id="myform" onSubmit="return beforeSubmit(this)" name="form" action="<%=path %>/AddOlServlet" method="post">
		<table border="1" align="center">
		
		<c:choose>
    	 <c:when test="${rows!=null }">
		     <c:forEach var="ins" items="${rows }" varStatus="vs">
			<tr>
				<td><input type="checkbox" name="number" value="${ins.number}" onclick="sumall()">
					<input type="hidden"  name="idlist" value="${ins.inid}">
					
					</td>
				<td width="100" align="center">${ins.inid} </td>
				<td width="100" align="center">${ins.bgname}</td>
				<td width="100" align="center">${ins.number}</td>
				<td width="100" align="center">单价</td>
				<td width="100" align="center">进货日期</td>
				<td width="100" align="center">分级</td>
				<td width="100" align="center">产地</td>
				
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
		
		<span>数量：</span><input id="ss" name="count" type="text" value="">
		<br><br>
		<input type="hidden" name="outnumber" value="${instad}">
		<input type="submit" value="提交">
		</form>
	
	</body>
</html>