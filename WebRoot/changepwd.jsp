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

	<form action = "<%=path %>/changepwd" method="post">
		<div style="margin:100px auto 0 auto;width:55%;">
        <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center;">
            <tr>
                <td>原密码</td>
                <td><input  type="password" name="oldpwd" required="required"></td>
            </tr>
            <tr>
                <td>新密码</td>
                <td><input  type="password" name="newpwd" required="required"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input  type="password" name="newpwd_confirm" required="required"></td>
            </tr>
         </table>
         <div style = "float:right">
        	<button type="submit" class="btn btn-info" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
         </div>
	</form>
	
</body>
</html>