<%-- 
    Document   : studentPage
    Created on : Feb 5, 2021, 11:18:44 AM
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
        <title>Student Page Page</title>
        <style>
            form{
                display: flex;
                width: 700px;
            }
            #accordion {
                padding-top: 20px;
            }

        </style>
    </head>
    <body style="background: pink;">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Quiz Online</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Take A Quiz</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="viewHistory?email=${sessionScope.EMAIL}&index=1&subject=all">History</a></li>
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

        <form action="quiznow">
            <input type="number" name="numOfQuestion" value="${param.numOfQuestion}" min="10" max="40" placeholder="${requestScope.error}" size="10" class="form-control mx-sm-3"/>
            <select name="subjectID" class="form-control">
                <c:forEach var="dto" items="${sessionScope.ListSubject}">
                    <option value="${dto.subjectID}">${dto.subjectName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Quiz Now" class="form-control mx-sm-3" />
        </form>
        <font color="red">
        ${requestScope.NotEnoughQuestion}
        </font>
    </body>
</html>

