<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"%>
<%
 String path=request.getContextPath(); 
%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<title>员工管理</title>
</head>
<body>
${msg }
<br>

<form action="<%=path%>/useradd.html" method="post">
	<div style="margin:100px auto 0 auto;width:55%;">
  <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:left;">
    <caption>
                员工管理
       <hr width="160">
    </caption>
  <tr>
  <td>名字</td>
  <td>
  <e:text name="uname" style="height:30px" required="true" defval="${ins.uname }"/>
  </td>
  <td>性别</td>
  <td>
  <e:radio name="usex" style="height:30px" value="男:1,女:2,保密:3" defval="${empty param.uaccount?'1':ins.usex }"/>
  </td>
  </tr>
  <tr>
  <td>入职日期</td>
  <td>
  <e:date name="udate" style="height:30px"  required="true" defval="${ins.udate}"/>
  </td>
  <td>工种</td>
  <td><e:select style="width:206px" value="进货人员:1,销售人员:2,库房人员:3,管理人员:4" name="utype" defval = "${empty param.uaccount?'1':ins.utype }"/>
  </td>
  </tr>
  <tr>
  <td>密码</td>
  <td><input style="height:30px" name="upassword" type="password" ></td>
  <td>备注</td>
  <td >
      <e:text style="height:30px" name="remarks" defval="${ins.remarks }"/>
  </td>
  </tr>
  
 
	<tr>
  <td colspan="4">
  <div style = "float:right">
        	
        	<button type="submit" class="btn btn-info" name ="next" formaction="<%=path%>/useradd.html">添加</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
 
  </table>
</div>
</form>

</body>
</html>
