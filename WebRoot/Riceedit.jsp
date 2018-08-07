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

<title>大米修改</title>
</head>
<body>
${msg}

	<form action = "<%=path %>/RiceEdit2Servlet" method="post">
        <table border="1" style="border-collapse: collapse">
            <tr>
                <td>货物名称</td>
                <td>
                <e:text name="bgname" required="required" defval="${ins.bgname}"/>
                </td>
                <td>品级</td>
                <td>
                <e:select value="一级:1,二级:2,三级:3,特级:4" name="bglevel" defval="${ins.bglevel }"/>
                </td>
            </tr>
            <tr>
                <td>规格</td>
                <td>
                 <e:select value="5kg每袋:1,10kg每袋:2,20kg每袋:3,25kg每袋:4,50kg每袋:5"  defval="${ins.bgspec }" name="bgspec" />
                </td>
                <td>单价</td>
                <td><e:number step="0.01" name="bgprice"  required="true" defval="${ins.bgprice }"/></td>
            </tr>
            <tr>
                <td>种类</td>
                <td>
                    <e:select value="粳米:1,五常米:2,香米:3,丝苗米:4" name="bgtype" required="true" defval="${ins.bgtype }"/>
                </td>
                <td>保质期</td>
                <td>
                   <e:select value="6个月:6,12个月:12,18个月:18" name="bgexp" required="true" defval="${ins.bgexp }" />
                </td>
            </tr>
            <tr>
                <td>产地</td>
                <td colspan="3">
                <e:text name="bgchandi" required="required" defval="${ins.bgchandi }"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3">
                <e:text name="remarks" defval="${ins.remarks}"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="提交">
        <input type="submit" name="next" value="返回" formaction="<%=path%>/RiceManagerServlet" formnovalidate="formnovalidate">
   <e:hidden name="bg"/>
   <e:hidden name="bgnumber"/>
   
    </form>

</body>
</html>