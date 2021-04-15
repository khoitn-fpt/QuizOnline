<%-- 
    Document   : updatePage
    Created on : Feb 5, 2021, 11:21:16 AM
    Author     : Nguyen Khoi
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Update Page</title>
        <style>
            form{
                display: inline-block;
                text-align: center;
            }
            .form-update{
                text-align: center; 
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
                    <li><a href="createPage?subject=${param.subject}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}&status=${param.status}&index=${param.index}">Create Question</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="search?index=${param.index}&subject=${param.subject}&status=${param.status}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}">Find Question</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Update Question</a></li>
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
        <div class="form-update">
            <h1 style="font-family: monospace;color: white;">Update Question Page</h1>
            <font color="red">
            <h3>
                ${requestScope.name}
                ${requestScope.choice1}
                ${requestScope.choice2}
                ${requestScope.choice3}
                ${requestScope.choice4}
            </h3>
            </font>
            <form action="confirm" method="POST">
                <input type="hidden" name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}" />
                <input type="hidden" name="subject" value="${param.subject}" />
                <input type="hidden" name="status" value="${param.status}" />
                <input type="hidden" name="index" value="${param.index}" />
                <input type="hidden" name="questionID" value="${param.questionID}" />
                <h2 style="font-family: monospace;color: white;">Question</h2>
                <a style="color: white">Subject</as><select name="subjectIDUpdate" class="form-control mx-sm-3">
                    <c:forEach var="dto" items="${ListSubject}">
                        <c:choose>
                            <c:when test="${sessionScope.question.subjectID eq dto.subjectID}">
                                <option value="${dto.subjectID}" selected="">${dto.subjectName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${dto.subjectID}">${dto.subjectName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select></br>
                <a style="color: white">Question Name</a><input type="text" name="questionName" value="${sessionScope.question.questionName}" size="100" class="form-control mx-sm-3"/>
                <a style="color: white">status</a> <input type="checkbox" name="chkstatus" value="" class="form-check-input"
                              <c:if test="${sessionScope.question.status}">
                                  checked ="checked"
                              </c:if> />
                <h2 style="font-family: monospace;color: white;" >Question Choice</h2>
                <h3 style="font-family: monospace;color: white;" >Right Answer</h3>
                <c:forEach var="dto" items="${sessionScope.result}">
                    <c:if test="${dto.isRight == true}">
                        <input type="hidden" name="choiceID1" value="${dto.choiceID}" />
                        <a style="color: white">choice 1</a><input type="text" name="choice1" value="${dto.answer}" size="100" class="form-control mx-sm-3"/></br>
                    </c:if>
                </c:forEach>
                <h3 style="font-family: monospace;color: white;">Another Answer</h3>
                <c:forEach var="dto" items="${sessionScope.result}" varStatus="counter">
                    <c:if test="${dto.isRight == false}">
                        <input type="hidden" name="choiceID${counter.count}" value="${dto.choiceID}" />
                        <a style="color: white">choice ${counter.count}</a><input type="text" name="choice${counter.count}" value="${dto.answer}" size="100" class="form-control mx-sm-3"/></br>
                    </c:if>
                </c:forEach>
                <input type="submit" name="btAction" value="Save" class="form-control mx-sm-3"/>
                <input type="submit" name="btAction" value="Cancel" class="form-control mx-sm-3"/>
            </form>
        </div>
    </body>
</html>