
<%@ page language="java" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery.min.js"></script>
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href='http://fonts.useso.com/css?family=Source+Sans+Pro:200,300,400,600,700,900' rel='stylesheet' type='text/css'>
    <style type="text/css">

        body{
            background: url("images/login1.png");
            animation-name:myfirst;
            animation-duration:12s;
            animation-delay:2s;
            animation-iteration-count:infinite;
            animation-play-state:running;
        }
/*
        @keyframes myfirst
        {
            0%   {background:url("images/login1.jpg");}
            34%  {background:url("images/2.jpg");}
            67%  {background:url("images/3.jpg");}
            100% {background:url("images/login1.jpg");}
        }
*/
        .form{background: rgba(255,255,255,0.2);width:400px;margin:120px auto;}
        /*阴影*/
        .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
        input[type="text"],input[type="password"]{padding-left:26px;}
        .checkbox{padding-left:21px;}
    </style>

</head>

	
<body>
		<%String path=request.getContextPath(); %>
		  ${msg }
<div >
    <div class="container" >
        <div class="form row">
            <div class="form-horizontal" id="login_form">
                <br>
                <!--
                <h3 class="form-title" style="text-align: center">登录</h3>
                -->
				<form action="<%=path%>/login" method="post">
                <div class="col-md-offset-2">
                <div class="col-md-10">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="用户名" id="username" name="lname" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="密码" id="password" name="pwd" maxlength="12"/>
                    </div>
                    <br>
					<br>
					<br>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success" style="width:100%;background-color: #7cc4cc;border-color: #7cc4cc" >登陆</button>
                    </div>
					<br>
					
                    <br>
                </div>
                </div>
				</form>
            </div>
        </div>
    </div>

    <div class="footer_bottom" style="background-color: rgba(0,0,0,0);text-align:center;">
        <div class="copy">
            <p>Copyright &copy; 2018 AWESOME RESUME by 1st group</p>
        </div>
    </div>
</div>
</body>
</html>