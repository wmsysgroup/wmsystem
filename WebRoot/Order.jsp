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
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

${msg}

<form action = "<%=path %>/OrderServlet" method="get">
    <table border="1" style="border-collapse: collapse">
        <tr>
            <td>货物名称</td>
            <td><input type="text"
                       value =<%=request.getParameter("name")%> required="required" readonly>
            </td>
            <td>货物编号</td>
            <td><input type="text" name="plnumber"
                value =<%=request.getParameter("number")%> required="required" readonly>
            </td>
        </tr>
        <tr>
            <td>单价</td>
            <td><input type="text"
                       value =<%=request.getParameter("price")%>
                               required="required" readonly>
            </td>
            <td>分级</td>
            <td><input type="text" value =<%=request.getParameter("level")%> required="required" readonly></td>
        </tr>
        <tr>
            <td>产地</td>
            <td><input type="text" value =<%=request.getParameter("chandi")%> required="required" readonly></td>
            <td>种类</td>
            <td><input type="text" value =<%=request.getParameter("type")%> required="required"  readonly></td>
        </tr>
        <tr>
            <td>状态</td>
            <td><input type="number" name="plstate" value = 1 required="required" readonly></td>
            <td>入库流水号</td>
            <td><input type="text" name="plid" required="required" readonly></td>
        </tr>
        <tr>
            <td>数量</td>
            <td><input type="number" name="plquantity" required="required"></td>
            <td>进货日期</td>
            <td><input type="date" name="pldate" required="required"></td>
        </tr>
        <tr>
            <td>供应商</td>
            <td><input type="text" name="plsupplier" required="required"></td>
            <td>负责人</td>
            <td><input type="text" name="plprincipal" required="required"></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" name="remarks"></td>
        </tr>
    </table>
    <input type="submit" value="订货">

</form>
</body>
</html>