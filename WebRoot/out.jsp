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
	document.getElementById("ss").value=count.toFixed(2);

	}
</script>

</head>

	<body>
	${msg }
	<div style="margin:0 auto;width:70%;padding-top:60px">
	<div style="height:30px">
		<form  action="<%=path %>/OutListServlet" method="post">
			<div style="float:left;display:inline-block">
				<span>大米编号：</span><input class="form-control" style="width:150px;display:inline-block" name="bgnumber" type="text" value="${instad}">
				<button type="submit" class="btn btn-info" >查询</button>
			</div>
			
		</form>
		
		
		<form id="myform" onSubmit="return beforeSubmit(this)" name="form" action="<%=path %>/AddOlServlet" method="post">
		<div style="float:right;display:inline-block;padding-bottom:16px">
		<span>数量：</span><input class="form-control" style="width:150px;display:inline-block" id="ss" name="count" type="text" value="">
		<input type="hidden" name="outnumber" value="${instad}">
		<button type="submit" class="btn btn-info" >提交</button>
		</div>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
		
		<tr>
			<td>&nbsp;</td>
             <td>入库编号</td>
             <td>货物名称</td>
             <td>储量</td>
             <td>单价</td>
             <td>进货日期</td>
             <td>品级</td>
             <td>产地</td>
           </tr>
		
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
				<td width="100" align="center">${ins.bgprice}</td>
				<td width="100" align="center">${ins.pldate}</td>
				<td width="100" align="center">${ins.sysvalue}</td>
				<td width="100" align="center">${ins.bgchandi}</td>
				
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
             <td></td>
           </tr>
         </c:forEach>
     </c:otherwise>
   	</c:choose>
			
		</table>
		
		
		
		</form>
	</div>
	</body>
</html>