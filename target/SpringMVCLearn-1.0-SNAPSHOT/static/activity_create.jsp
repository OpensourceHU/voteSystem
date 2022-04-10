<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/25 0025
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动创建</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">


    <!-- Bootstrap core CSS -->
    <link href="/SpringMVCLearn/ui/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/SpringMVCLearn/ui/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/SpringMVCLearn/ui/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/SpringMVCLearn/ui/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Console</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/SpringMVCLearn/static/forgetPassword.html">修改密码</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/SpringMVCLearn/static/myActivityOri.html">我的活动 </a></li>
                <li class="active"><a href="#">创建活动 <span class="sr-only">(current)</span> </a></li>
                <li><a href="#">活动详情 </a></li>
            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">创建新活动</h1>
            <!--            <h2 class="sub-header">Section title</h2>-->
            <form role="form" action="/SpringMVCLearn/createActivityServlet" method="post" onsubmit="return checkForm(this)">
                <div class="form-group">
                    <h4 class="sub-header">活动基本信息</h4>
                    <label for="name">活动名称</label>
                    <input id="name" name="name" class="form-control" type="text" placeholder="请输入活动名">
                    <label for="desc">简要描述</label>
                    <input id="desc" name="desc" class="form-control" type="text" placeholder="请用一句话描述你的活动">
                </div>
                <br><br>

                <h4 class="sub-header">选项信息</h4>
                <div class="form-group" id="optionInfo">

                    <div name="optionBlock" id="optionBlock">
                        <label for="option">选项</label>
                        <input id="option" name="option" type="text" class="form-control" placeholder="请输入备选项">
                    </div>
                </div>
                <button type="button" onclick="add()" class="btn-primary btn-block btn">添加选项</button>
                <script type="text/javascript">
                    function add() {
                        // index+=1;
                        var next = document.getElementById("optionBlock").cloneNode(true);
                        // next.attr("id","AddDiv"+index)
                        document.getElementById("optionInfo").appendChild(next);
                    }
                </script>

                <div class="form-group" id="judgeGroup">
                    <h4 class="sub-header">活动评委组</h4>
                    <div class="form-control" id="singleBox">
                        <label for="account"></label>
                        <input type="text" id="account" name="account" placeholder="Account (6位数字)">

                        <label for="password"></label>
                        <input type="text" id="password" name="password" placeholder="Password">
                    </div>
                </div>
                <button id="addJudgeButton" type="button" onclick="addJudge()" class="btn-primary btn-block btn">添加评委</button>
                <script type="text/javascript">
                    function addJudge() {
                        var append = document.getElementById("singleBox").cloneNode(true);
                        document.getElementById("judgeGroup").appendChild(append)
                    }
                </script>




                <br><br><br>
                <button type="submit" class="btn btn-block btn-success">创建</button>
                <script type="text/javascript">
                    function checkForm(form) {
                        var regExp = /^[0-9]{6}$/;
                        var accounts = document.getElementsByName("account");
                        var flag = true;
                        for (var i = 0; i < accounts.length; i++) {
                            if (!regExp.test(accounts[i].value)) {
                                flag = false;
                                break;
                            }
                        }
                        if(flag==false){
                            alert("请检查评委账号格式 必须为6位数字");
                            return false;
                        }
                        else {
                            alert("创建活动成功!")
                            return true;
                        }
                    }
                </script>

            </form>


        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script>window.jQuery || document.write('<script src="../ui/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../ui/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../ui/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
