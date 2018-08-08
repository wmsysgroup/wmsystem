<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"%>

<%
    String path=request.getContextPath();
%>
<%
    /*浏览器提交的数据在提交给服务器之前设置编码方式为UTF-8*/
    request.setCharacterEncoding("UTF-8");
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

<form action = "<%=path %>/OrderServlet" method="get">
	<div style="margin:100px auto 0 auto;width:55%;">
    <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
        <tr>
            <td>货物名称</td>
            <td><%=request.getParameter("name")%>
            </td>
            <td>货物编号</td>
            <td><%=request.getParameter("number")%>
            </td>
        </tr>
        <tr>
            <td>单价</td>
            <td><%=request.getParameter("price")%>                
            </td>
            <td>分级</td>
            <td><%=request.getParameter("level")%></td>
        </tr>
        <tr>
            <td>产地</td>
            <td><%=request.getParameter("chandi")%></td>
            <td>种类</td>
            <td><%=request.getParameter("type")%></td>
        </tr>
        <tr>
          
            
        </tr>
        <tr>
            <td>数量</td>
            <td><input class="form-control" style="width:150px" type="number" name="plquantity" required="required"></td>
            <td>进货日期</td>
            <td><input class="form-control" style="width:150px" type="date" name="pldate" required="required"></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td><input class="form-control" style="width:150px" type="text" name="plsupplier" required="required"></td>
            <td>备注</td>
            <td><input class="form-control" style="width:150px" type="text" name="remarks"></td>
        </tr>
       
    </table>
    	<div style = "float:right">
        	<button type="submit" class="btn btn-info" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
	</div>
	<input type="hidden" name="plnumber" value="<%=request.getParameter("number")%>">
</form>
</body>
</html>