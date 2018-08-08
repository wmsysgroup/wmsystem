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
		if(form.inquantity.value!="${ins.plquantity }"){
			if(form.remarks.value==''){
				alert('备注不能为空');
				form.remarks.focus();
				return false;
			}
		}
		return true;
	}
</script>
	<body>
		${msg }
		<div style="margin:60px auto 0 auto;width:52%;">
		<table style="border-collapse:separate; border-spacing:0px 7px;">
			<tr>
				<td align="center" width="80px">进货编号</td>
				<td align="center" >:</td>
				<td align="center" width="146px">${ins.plid }</td>
				<td align="center" width="77px"></td>
				<td align="center" width="80px">大米名称</td>
				<td align="center">:</td>
				<td align="center" width="194px">${ins.bgname }</td>
			</tr>
		
			<tr>
				<td align="center" width="80px">数量 </td>
				<td align="center" >:</td>
				<td align="center" name="number" width="146px">${ins.plquantity }</td>
				<td align="center" width="77px"></td>
				<td align="center" width="80px">分级</td>
				<td align="center">:</td>
				<td align="center" width="146px">${ins.fvalue }</td>
			</tr>
			<br><br> 	
			<tr>
				<td align="center" width="80px">种类</td>
				<td align="center" >:</td>
				<td align="center" width="146px">${ins.svalue }</td>
				<td align="center" width="77px"></td>
				<td align="center" width="80px">负责人</td>
				<td align="center">:</td>
				<td align="center" width="146px">${ins.plprincipal }</td>
			</tr>
			
		</table>
		<br><br>
		<form name="form" onSubmit="return beforeSubmit(this);" action="<%=path %>/SaveInbServlet" method="post">
			<span>数量：</span><input class="form-control" style="width:150px;display:inline-block" name="inquantity" type="tel" value="${ins.plquantity }">
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓库：</span><select class="form-control" style="width:150px;display:inline-block" name="wnumber">
							<option value="1">1号</option>
							<option value="2">2号</option>
							<option value="3">3号</option>
							<option value="4">4号</option>
							<option value="5">5号</option>
							<option value="6">6号</option>
						</select>
						<br><br>
			<span>备注：</span><input class="form-control" style="width:150px;display:inline-block" name="remarks" type="text" value=""><br><br>
			<input type="hidden"  name="plid" value="${ins.plid }">
			<input type="hidden" name="innumber" value="${ins.plnumber }">
			<div style = "float:right">
			<button type="submit" class="btn btn-info" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</form>
		</div>
		
	</body>
</html>