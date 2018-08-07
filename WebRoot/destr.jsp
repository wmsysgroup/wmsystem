<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<script type="text/javascript">
	function beforeSubmit(form){
			if(form.gdremarks.value==''){
				alert('损毁原因不能为空');
				form.gdremarks.focus();
				return false;
			}
		return true;
	}
</script>
	<body>
		<form name="form" onSubmit="return beforeSubmit(this);" action="<%=path %>/AddDamServlet" method="post">
				<span>入库编号</span>
				<select name="indid">
					<option>B00000000</option>
					<option>B000123456789</option>
				</select>
				<br>
				<input type="hidden" name="gdnumber" value="A1023">
				<span>货物名称</span><input  type="text" value=""><br><br>
				<span>数    量</span><input name="gdquantity" type="tel" value=""><br><br>
				<span>损毁原因</span><input name="gdremarks" type="text" value=""><br>
				<input type="submit"  value="提交">
		</form>
	</body>
</html>