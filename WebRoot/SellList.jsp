<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ page import="java.util.*"%>

<%
String path=request.getContextPath();
%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">  
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<title>销售清单</title>
<script type="text/javascript">
      //选中的checkbox的个数
      var count=0;
      function onSelect(obj)
      {
    	  with(document.forms['myform'])
    	  {
    		  obj?++count:--count;
    		  next[1].disabled=(count==0);
    	  }
      }
</script>
<script type="text/javascript">
     function onEdit(obj)
     {
    	 with(document.forms['myform'])
    	 {
    		 action="<%=path%>/getToSaleInfo.html?outid="+obj;
    		 submit();
    	 }
     } 
</script>
</head>
<body>
	${msg }
	<br>
	<br>
	<!-- 表达式 -->
	<form id="myform" action="<%=path%>/SellList.html" method="post">
		<!-- 查询条件 -->
		<div style="margin:0 auto;width:90%">
	
		<div style="margin:0 auto;width:90%">
		<table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
			<caption>
				出库清单查询
				<hr width="160">
			</caption>
			<tr>
				<td colspan="6">查询条件</td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td><e:text style="height:30px" name="pbgname" autofocus="true" /></td>
				<td>分级</td>
				<td><e:select style="height:30px;width:206px" name="pbglevel" value="一级:1,二级:2,三级:3,特级:4,==不限==:''"
						header="true" /></td>
				<td>单价(￥)</td>
				<td><e:text style="height:30px" name="pbgprice" autofocus="true" /></td>
			</tr>
			<tr>
				<td>种类</td>
				<td><e:select style="height:30px;width:206px" name="pbgtype" value="粳米:1,五常米:2,香米:3,丝苗米:4,籼米:5,==不限==:''"
						header="true" /></td>
				<td>数量</td>
				<td><e:text style="height:30px" name="poutquantity" autofocus="true" /></td>
			</tr>
		</table>
		</div>
		<!-- 数据迭代 -->
		<table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
			<tr align="center">
				<td>出库流水号</td>
				<td>产品编号</td>
				<td>名称</td>
				<td>分级</td>
				<td>种类</td>
				<td>产地</td>
				<td>单价（￥）</td>
				<td>数量</td>
				<td>操作</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach var="ins" items="${rows }" varStatus="vs">
						<tr>
							<td>${ins.outid }</td>
							<td>${ins.outnumber }</td>
							<td>${ins.bgname }</td>
							<td>${ins.level }</td>
							<td>${ins.type }</td>
							<td>${ins.bgchandi }</td>
							<td>${ins.bgprice }</td>
							<td>${ins.outquantity }</td>
							<td><a href="#" onclick="onEdit('${ins.outid}')">销售 </a></td>
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
		<!-- 功能按钮 -->
		   <div style = "float:right">
        	<button type="submit" class="btn btn-info" >查询</button>
        </div>
		</div>
	</form>
</body>
</html>