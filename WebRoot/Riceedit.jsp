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
<title>大米修改</title>
</head>
<body>
${msg}

	<form action = "<%=path %>/RiceEdit2Servlet" method="post">
		<div style="margin:100px auto 0 auto;width:55%;">
        <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center;">
            <tr>
                <td>货物名称</td>
                <td>
                <e:text styleClass="form-control" style="width:150px" name="bgname" required="required" defval="${ins.bgname}"/>
                </td>
                <td>单价</td>
                <td><e:number styleClass="form-control" style="width:150px" step="0.01" name="bgprice"  required="true" defval="${ins.bgprice }"/></td>
                
            </tr>
            <tr>
                <td>规格</td>
                <td>
                 <e:select styleClass="form-control" style="width:150px" value="5kg每袋:1,10kg每袋:2,20kg每袋:3,25kg每袋:4,50kg每袋:5"  defval="${ins.bgspec }" name="bgspec" />
                </td>
                <td>品级</td>
                <td>
                <e:select styleClass="form-control" style="width:150px" value="一级:1,二级:2,三级:3,特级:4" name="bglevel" defval="${ins.bglevel }"/>
                </td>
            </tr>
            <tr>
                <td>种类</td>
                <td>
                    <e:select styleClass="form-control" style="width:150px" value="粳米:1,五常米:2,香米:3,丝苗米:4" name="bgtype" required="true" defval="${ins.bgtype }"/>
                </td>
                <td>保质期</td>
                <td>
                   <e:select styleClass="form-control" style="width:150px" value="6个月:6,12个月:12,18个月:18" name="bgexp" required="true" defval="${ins.bgexp }" />
                </td>
            </tr>
            <tr>
                <td>产地</td>
                <td>
                <e:text styleClass="form-control" style="width:150px" name="bgchandi" required="required" defval="${ins.bgchandi }"/>
                </td>
                <td>备注</td>
                <td>
                <e:text styleClass="form-control" style="width:150px" name="remarks" defval="${ins.remarks}"/>
                </td>
            </tr>
            <tr>
                
            </tr>
        </table>
        <div style = "float:right">
        	<button type="submit" class="btn btn-info" >提交</button>
        	<button type="submit" class="btn btn-info" name="next" formaction="<%=path%>/RiceManagerServlet" formnovalidate="formnovalidate">返回</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        
        </div>
   <e:hidden name="bg"/>
   <e:hidden name="bgnumber"/>
   
    </form>

</body>
</html>