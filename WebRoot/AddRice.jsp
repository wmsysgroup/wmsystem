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

	<form action = "<%=path %>/AddRiceServlet" method="get">
        <table border="1" style="border-collapse: collapse">
            <tr>
                <td>货物名称</td>
                <td><input type="text" name="bgname" required="required"></td>
                <td>品级</td>
                <td>
                	<e:select value="levelMenu" name="bglevel" />
                </td>
            </tr>
            <tr>
                <td>规格</td>
                <td>
                    <e:select value="specMenu" name="bgspec" />
                </td>
                <td>单价</td>
                <td><input type="number" name="bgprice" required="required"></td>
            </tr>
            <tr>
                <td>种类</td>
                <td>
                    <e:select value="typeMenu" name="bgtype" />
                </td>
                <td>保质期</td>
                <td>
                   <e:select value="expMenu" name="bgexp" />
                </td>
            </tr>
            <tr>
                <td>产地</td>
                <td colspan="3"><input type="text" name="bgchandi" required="required"></td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3"><input type="text" name="bgremarks"></td>
            </tr>
        </table>
        <input type="submit" value="添加">
        
    </form>
</body>
</html>