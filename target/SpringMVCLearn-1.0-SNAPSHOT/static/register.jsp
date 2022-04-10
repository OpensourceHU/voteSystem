<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>邮箱注册</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script type="text/javascript" src="/structs2_day01_atm/js/jquery-1.11.3.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <style type="text/css">
        #register {
            width: 450px;
            height: 100px;
            margin: 50px auto;
        }
    </style>
    <script type="text/javascript">
        var send = function(){
            alert("已发送邮件")
            if ($("#email").val()) {
                $.ajax({
                    type: "POST",
                    dataType:"json",
                    url: "/SpringMVCLearn/SendEmailServlet?random=" + Math.random(),
                    data: {
                        email: $("#email").val(),
                    },
                    success: function (result) {
                        console.log(result)
                        if(result.code==200)
                        alert("验证码发送成功，请注意查收。");
                    }
                })
            } else {
                alert("邮箱发送失败");
                $("#notice").html("请填写邮箱");
                setTimeout(function () {
                    $("#notice").hide();
                }, 1000);
            }
        }
        $(function () {
            //  判断用户是否可以注册
            $("#submit").click(function () {
                if ($("#email").val()) {
                    $("#RegistForm").submit();
                } else { //  如果没有值
                    $("#notice").html("请填写完整信息");
                    setTimeout(function () {
                        $("#notice").hide();
                    }, 1000);
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div id="register">
        <form class="form-horizontal" id="RegistForm" method="post"
              action="/SpringMVCLearn/registered">
            <fieldset>
                <div id="legend" class="">
                    <legend class="">用户注册</legend>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-5">
                        <input type="email" placeholder="请填写邮箱地址..." class="form-control"
                               id="email" name="email" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-5">
                        <input type="password" placeholder="Password" required
                               class="form-control" name="password">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-5">
                        <input type="text" name="code" id="code" placeholder="请输入邮箱的验证码"
                               class="form-control" required>
                        <br>
                        <button id="btn" name="btn" class="form-control btn btn-lg btn-primary btn-block" type="button" onclick="send()">
                            发送验证码
                        </button>
                    </div>
                </div>
                <span id="notice" class="hide">请先完成邮箱验证</span><br/>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="login.html" class="btn btn-success">返回登录</a>
                        <input class="btn btn-info" type="submit" id="submit" value="注册"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
