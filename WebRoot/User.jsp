<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"%>
<%
 String path=request.getContextPath(); 
%>
<html>
<head>
<title>员工管理</title>
</head>
<body>
${msg }
<br>
<br>
<br>
<br>
<form action="<%=path%>/user.html" method="post">
  <table border="1" width="55%" align="center">
    <caption>
                员工管理
       <hr width="160">
    </caption>
  <tr>
    <td>账户</td>
    <td> 
    <e:text name="uaccount" required="true" defval="${ins.uaccount}"/>
    </td>
    <td>
    <input type="submit" name="next" value="查询" formaction="<%=path%>/user.html" formnovalidate="formnovalidate">
    </td>
  </tr>
  <tr>
  <td>名字</td>
  <td>
  <e:text name="uname" required="true" defval="${ins.uname }"/>
  </td>
  <td>性别</td>
  <td>
  <e:radio name="usex" value="男:1,女:2,保密:3" defval="${empty param.uaccount?'1':ins.usex }"/>
  </td>
  </tr>
  <tr>
  <td>入职日期</td>
  <td>
  <e:date name="udate"  required="true" defval="${ins.udate}"/>
  </td>
  <td>工种</td>
  <td><e:select value="进货人员:1,销售人员:2,库房人员:3,管理人员:0" name="utype" defval = "${empty param.uaccount?'1':ins.utype }"/>
  </td>
  </tr>
  <tr>
  <td>状态</td>
  <td><e:select value="在职:1,离职:2" name="ustate" defval = "${ins.ustate}"/></td>
  </tr>
  <tr>
  <td>备注</td>
  <td colspan="3">
      <e:textarea rows="5" cols="65" name="remarks" defval="${ins.remarks }"/>
  </td>
  </tr>
  <tr>
  <td colspan="4" align ="center">
  <input type="submit" name ="next" value="提交" formaction="<%=path%>/userc.html">
  </table>

</form>

</body>
</html>