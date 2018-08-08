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

<form action="<%=path %>/PurchaseListServlet" method="get">
	<div style="margin:0 auto;width:90%">
	
	<div style="margin:0 auto;width:60%">
    <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center"">
        <tr>
            <td>货物名称</td>
            <td>
                <input type="text" name="bgname">
            </td>
            <td>产地</td>
            <td>
                <input type="text" name="bgchandi">
            </td>
        </tr>
        <tr>
            <td>种类</td>
            <td>
                <e:select value="粳米:1,五常米:2,香米:3,丝苗米:4,籼米:5" name="bgtype" header="true"/>
            </td>
            <td>品级</td>
            <td>
                <e:select value="一级:1,二级:2,三级:3,特级:4" name="bglevel" header="true"/>
            </td>
        </tr>
    </table>
    	<div style = "float:right;padding-bottom:16px">
        	<button type="submit" class="btn btn-info" >查询</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
	</div>
    <table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
        <tr>
            <td>序号</td>
            <td>货物编号</td>
            <td>货物名称</td>
            <td>品级</td>
            <td>种类</td>
            <td>产地</td>
            <td>价格</td>
            <td>保质期</td>
            <td>订货</td>
        </tr>
        <c:choose>
            <c:when test="${rows!=null }">
                <c:forEach var="ins" items="${rows }" varStatus="vs">
                    <tr>
                        <td>${vs.count }</td>
                        <td>${ins.bgnumber }</td>
                        <td>${ins.bgname }</td>
                        <td>${ins.bglevel }</td>
                        <td>${ins.bgtype }</td>
                        <td>${ins.bgchandi }</td>
                        <td>${ins.bgprice }</td>
                        <td>${ins.bgexp }</td>
                        <td><a href='Order.jsp?number=${ins.bgnumber }&name=${ins.bgname }&level=${ins.bglevel }&type=${ins.bgtype }&chandi=${ins.bgchandi }
                        &price=${ins.bgprice }&exp=${ins.bgexp }'>购买</a>
                        </td>
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