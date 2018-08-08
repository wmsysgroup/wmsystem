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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>

${msg}

	<form action="<%=path %>/DepotInquireServlet" method="get">
	
	<div style="margin:0 auto;width:90%">
	
	<div style="margin:0 auto;width:60%">
        <table style="width:100%;border-collapse:separate;border-spacing:0px 10px;text-align:center">
        	  <tr>
                <td>货物名称</td>
                <td>
                	<e:text name="bgname"/>
                </td>
                <td>货物编号</td>
                <td>
                	<e:text name="innumber"/>
                </td>
            </tr>
            
            <tr>
                <td>库房编号</td>
                <td>
                	<e:select  value="1号仓库:1,2号仓库:2,3号仓库:3,4号仓库:4,5号仓库:5,6号仓库:6" name="wnumber" header="true"/>
                </td>
                <td>品级</td>
                <td>
                	<e:select  value="一级:1,二级:2,三级:3,特级:4" name="bglevel" header="true"/>
                </td>
            </tr>
            
            <tr>
                <td >种类</td>
                <td>
                    <e:select  value="粳米:1,五常米:2,香米:3,丝苗米:4,籼米:5" name="bgtype" header="true"/>
                </td>
                <td>规格</td>
                <td>
					 <e:select  value="5kg/袋:1,10kg/袋:2" name="bgspec" header="true"/>
				</td>
            </tr>
            
            <tr>
                <td>起始时间</td>
                <td>
                	<e:date  name="bdate"/>
          
                </td>
                <td>截止时间</td>
                <td>
                	<e:date name="edate"/>
                    
                </td>
            </tr>
        </table>
        <div style = "float:right">
        	<button type="submit" class="btn btn-info" >查询</button>&nbsp;&nbsp;&nbsp;
        </div>
        </div>
        
        <div style="margin-top:40px">
        <table class="table table-striped table-bordered table-hover table-condensed" style="width:100%;text-align:center">
        	<thead>
		   <tr>
		     <td>序号</td>
		     <td>货物名称</td>
		     <td>货物编号</td>
		     <td>仓库编号</td>
		     <td>品级</td>
		     <td>种类</td>
		     <td>规格</td>
		     <td>余量</td>
		     <td>入库日期</td>
		     <td>负责人</td>
		     
		   </tr>
		   <thead>
		   
		   <tbody>
		   <c:choose>
		     <c:when test="${rows!=null }">
			     <c:forEach var="ins" items="${rows }" varStatus="vs">
			    
			   		  <tr>
			   		  	 <td>${vs.count }</td>
			   		  	 <td>${ins.bgname }</td>
			   		  	 <td>${ins.innumber }</td>
					     <td>${ins.wnumber }</td>
					     <td>${ins.bglevel }</td>
					     <td>${ins.bgtype }</td>
					     <td>${ins.bgspec }</td>
					     <td>${ins.number }</td>
					     <td>${ins.indate }</td>
					     <td>${ins.inprincipal }</td>
					     
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
		             <td></td>
		  
		           </tr>
		       
		         </c:forEach>
		     </c:otherwise>
		   </c:choose>
		   <tbody>
		 </table>
		</div>
		</div>
    </form>
</body>
</html>