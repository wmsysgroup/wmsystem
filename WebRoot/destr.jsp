<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.*"%>

<%
 String path=request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
	${msg }
		<form name="form" onSubmit="return beforeSubmit(this);" action="<%=path %>/AddDamServlet" method="post">
		
		<div style="margin:100px auto 0 auto;width:55%;">
		
				 <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
				 <tr>
				 	<td width="100px">入库编号</td>
				 	<td width="150px"><input class="form-control" style="width:150px" type="text" name="indid" required="required"></td>
				 	<td width="100px">货物编号</td>
				 	<td width="150px"><input class="form-control" style="width:150px" type="text" name="gdnumber" required="required"></td>
				 </tr>
				  <tr>
				 	<td>数    量</td>
				 	<td><input class="form-control" style="width:150px" type="text" name="gdquantity" required="required"></td>
				 	<td>损毁原因</td>
				 	<td><input class="form-control" style="width:150px" type="text" name="gdremarks" ></td>
				 </tr>
				 
				 </table>
				<div style = "float:right">
					<button type="submit" class="btn btn-info" >提交</button>
				</div>
		</div>
		</form>
	</body>
</html>