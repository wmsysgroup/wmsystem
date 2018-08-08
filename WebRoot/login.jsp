<%@ page language="java" pageEncoding="GBK"%>
<html>
<head>
<title></title>
</head>
<body>
<%String path=request.getContextPath(); %>
${msg }
<pre>





</pre>
<form action="<%=path%>/login" method="post">
<table border="1" width="35%" align="center">
  <tr>
    <td>ำรปงร๛</td>
    <td>
      <input type="text" name="lname" autofocus="autofocus">
    </td>
  </tr>
  <tr>
    <td>รÜย๋</td>
    <td>
      <input type="password" name="pwd">
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">
       <input type="submit" name="next" value="ตวยผ">
    </td>
  </tr>
</table>
</form>
</body>
</html>