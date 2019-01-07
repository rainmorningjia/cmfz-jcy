<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <script type="text/javascript">

        $(function () {
            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/img/createImg?time=" + new Date());
            });
            //姓名的正则表达式
            var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
            var regs=/^[a-zA-Z\/ ]{2,20}$/;
            //密码的正则表达式
            var pPattern = /[0-9a-zA-Z]{6,16}/;
            //  form 表单提交

            //验证姓名的格式与是否存在方法
            var validName = function () {
                var name = $("#name").val();
                if (!reg.test(name)&&!regs.test(name)) {
                    $("#nameMessage").text("姓名格式不正确！");
                } else {
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/manager/loginManagerName",
                        data: {
                            name: name
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data == "success") {
                                $("#nameMessage").text("验证成功！");
                            } else {
                                $("#nameMessage").text(data);
                            }
                        }
                    })
                }

            }
            var password=function(){
                var password=$("#password").val();
                if(!pPattern.test(password)){
                    $("#passwordMessage").text("密码格式不正确");
                    b=false;
                }else {
                    b=true;
                }
            }
            $("#name").blur(function () {
                validName();

            })
        });
    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" action="${pageContext.request.contextPath}/manager/loginManager" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input id="name" type="text" name="name" class="text" value="xxx" maxlength="15"/>
                </td>
                <td><span id="nameMessage" style="font-size: 3px"></span></td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input id="password" type="password" name="password" class="text" value="xxx" maxlength="15"
                           autocomplete="off"/>
                </td>
                <td><span id="passwordMessage"></span></td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="code" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/img/createImg"
                         title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit"
                                                                                                        class="loginButton"
                                                                                                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>