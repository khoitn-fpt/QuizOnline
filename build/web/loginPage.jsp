<%-- 
    Document   : loginPage
    Created on : Feb 5, 2021, 9:13:27 AM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            body {
                padding-top: 15px;
                font-size: 12px;
                background-image: url(image/background.jpg);
                background-repeat: round;
            }
            .main {
                max-width: 320px;
                margin: 0 auto;
            }
            .login-or {
                position: relative;
                font-size: 18px;
                color: #aaa;
                margin-top: 10px;
                margin-bottom: 10px;
                padding-top: 10px;
                padding-bottom: 10px;
            }
            .span-or {
                display: block;
                position: absolute;
                left: 50%;
                top: -2px;
                margin-left: -25px;
                background-color: #fff;
                width: 50px;
                text-align: center;
            }
            .hr-or {
                background-color: #cdcdcd;
                height: 1px;
                margin-top: 0px !important;
                margin-bottom: 0px !important;
            }
            h3 {
                text-align: center;
                line-height: 300%;
            }
        </style>
    </head>
    <body>
         <div class="container">
            <div class="row">
                <div class="main">
                    <h3>Quiz Online</h3>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <a href="registrationPage" class="btn btn-lg btn-info btn-block">registration</a>
                        </div>
                    </div>
                    <div class="login-or">
                        <hr class="hr-or">
                        <span class="span-or">or</span>
                    </div>

                    <form role="form" action="login" method="POST">
                        <div class="form-group">
                            <label for="inputUsernameEmail">Username</label>
                            <input type="email" name="email" class="form-control" id="inputUsernameEmail">
                        </div>
                        <div class="form-group">
                            <label for="inputPassword">Password</label>
                            <input type="password" name="password" class="form-control" id="inputPassword">
                        </div>
                        <div class="form-group">
                            <font color="red">
                            ${requestScope.LoginFail}
                            </font>
                        </div>
                        <input type="submit" value="Login" name="btAction" class="btn btn btn-primary"/>
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>
