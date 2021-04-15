<%-- 
    Document   : createQuestion
    Created on : Feb 5, 2021, 11:14:52 AM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            form{
                text-align: center;
                display: inline-block;

            }
            .form-create{
                text-align: center;
            }

        </style>
        <title>Create Question Page</title>
    </head>
    <body style="background: pink;">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Quiz Online</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="createPage?subject=${param.subject}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}&status=${param.status}&index=${param.index}">Create Question</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="search?index=${param.index}&subject=${param.subject}&status=${param.status}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}">Find Question</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a><span class="glyphicon glyphicon-user"></span>
                            <font color="white">
                            Welcome, ${sessionScope.FULLNAME}
                            </font>
                        </a></li>
                    <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="form-create">
            <form action="create" >
                <input type="hidden" name="subject1" value="${param.subject}" />
                <input type="hidden" name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}" />
                <input type="hidden" name="status" value="${param.status}" />
                <input type="hidden" name="index" value="${param.index}" />
                <h2 style="font-family: monospace;color: white;">Question</h2>
                <a style="color: white">Subject</a><select name="subject" class="form-control mx-sm-3">
                    <c:forEach var="dto" items="${ListSubject}">
                        <option value="${dto.subjectID}">${dto.subjectName}</option>
                    </c:forEach>
                </select></br>
                <a style="color: white">Question Name</a><input type="text" name="questionName" value="${param.questionName}" size="100" class="form-control mx-sm-3"/><a style="color:red">${requestScope.name}</a>
                <h2 style="font-family: monospace;color: white;">Question Choice</h2>
                <h3 style="font-family: monospace;color: white;">Right Answer</h3>
                <a style="color: white">1</a><input type="text" name="choice1" value="${param.choice1}" size="100" class="form-control mx-sm-3"/><a style="color:red">${requestScope.choice1}</a></br>
                <h3 style="font-family: monospace;color: white;">Another Answer</h3>
                <a style="color: white">2</a><input type="text" name="choice2" value="${param.choice2}" size="100" class="form-control mx-sm-3"/><a style="color:red">${requestScope.choice2}</a></br>

                <a style="color: white">3</a><input type="text" name="choice3" value="${param.choice3}" size="100" class="form-control mx-sm-3"/><a style="color:red">${requestScope.choice3}</a></br>

                <a style="color: white">4</a><input type="text" name="choice4" value="${param.choice4}" size="100" class="form-control mx-sm-3"/><a style="color:red">${requestScope.choice4}</a></br>
                <input type="submit" value="Create" class="form-control mx-sm-3"/>
                <a class="badge badge-success" href="search?index=${param.index}&subject=${param.subject}&status=${param.status}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}">Return</a>
            </form>
        </div>

    </body>
</html>

