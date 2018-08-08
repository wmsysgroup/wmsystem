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

	<form action = "<%=path %>/AddRiceServlet" method="get">
		<div style="margin:100px auto 0 auto;width:55%;">
        <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center;">
            <tr>
                <td>货物名称</td>
                <td><input  type="text" name="bgname" required="required"></td>
                <td>品级</td>
                <td>
                	<e:select  value=" 一级:1,二级:2,三级:3,特级:4" name="bglevel" />
                </td>
            </tr>
            <tr>
                <td>规格</td>
                <td>
                    <e:select  value="5kg/袋:1,10kg/袋:2" name="bgspec" />
                </td>
                <td>单价</td>
                <td>
                	<input  type="text" name="bgprice" required="required">
                </td>
            </tr>
            <tr>
                <td>种类</td>
                <td>
                    <e:select  value="粳米:1,五常米:2,香米:3,丝苗米:4,籼米:5" name="bgtype" />
                </td>
                <td>保质期</td>
                <td>
                   <e:select  value="6个月:6,12个月:12,18个月:18,24个月:24"   name="bgexp" />
                </td>
            </tr>
            <tr>
                <td>产地</td>
                <td><input  type="text" name="bgchandi" required="required"></td>
                <td>备注</td>
                <td><input  type="text" name="bgremarks"></td>
            </tr>
           
        </table>
        <div style = "float:right">
        	<button type="submit" class="btn btn-info" >添加</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        </div>
        
        
    </form>
</body>
</html>