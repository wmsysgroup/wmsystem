<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path=request.getContextPath(); %>
<html>
<head>
</head>
<body>
<table border="1" width="99%" align="center">
  <tr>
    <td height="30" colspan="2">[&nbsp;${USERINFO.uname }&nbsp;]归来
    <a href="changepwd.jsp">修改密码</a>
    </td>
  </tr>
  <tr>
    <td width="15%" align="center" valign="top">
      <c:if test="${menuList!=null }">
        <c:forEach var="menu" items="${menuList }">
          <a href="<%=path%>/${menu.mopenpath}" target="VIEW">${menu.mname }</a><br>
        </c:forEach>
      </c:if>
    </td>
    <td width="90%">
      <iframe name="VIEW" width="100%" height="482"></iframe>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">版权声明</td>
  </tr>
</table>
</body>
</html>