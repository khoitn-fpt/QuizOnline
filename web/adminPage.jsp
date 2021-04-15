<%-- 
    Document   : adminPage
    Created on : Feb 5, 2021, 9:16:08 AM
    Author     : Nguyen Khoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            #badge badge-primary badge-pill,a{
                color: orange;
            }
            form{
                display: flex;
                width: fit-content;
            }
            #accordion {
                padding-top: 20px;
            }
            .list-question{
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
                    <li class="active"><a href="#">Find Question</a></li>
                </ul>
                <form action="search" class="navbar-form navbar-left">
                    <input type="hidden" name="index" value="1" />
                     
                    <select name="subject" class="form-control mx-sm-3">
                        <option value="all">All</option>
                        <c:forEach var="dto" items="${sessionScope.ListSubject}">
                            <c:choose>
                                <c:when test="${pageScope.dto.subjectID eq requestScope.subjectID}">
                                    <option value="${pageScope.dto.subjectID}" selected="">${pageScope.dto.subjectName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${pageScope.dto.subjectID}">${pageScope.dto.subjectName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <input type="text" name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}" class="form-control mx-sm-3" placeholder="Name of Question"/>
                    <select name="status" class="form-control">
                        <c:choose>
                            <c:when test="${requestScope.Status eq false}">
                                <option value="${true}">activate</option>
                                <option value="${false}" selected="">deactivate</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${true}" selected="">activate</option>
                                <option value="${false}">deactivate</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <input type="submit" value="Search" class="form-control mx-sm-3" />
                </form>
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
        ${requestScope.deleteStatus}

        
        <c:if test="${empty requestScope.ListSearch}">
            <div class="list-question">
                <h3 style="color: green;">List Of Question</h3>
            </div>
            <c:forEach var="dto" items="${requestScope.ListSearch}" varStatus="counter" >
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${counter.count}">
                                    ${dto.questionName}</a>
                                <span class="badge badge-primary badge-pill">${dto.subjectID}</span>
                                <c:if test="${dto.status == true}">
                                    <span class="badge badge-primary badge-pill">
                                        <c:url var="urlRewriting" value="delete">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="questionID" value="${dto.questionID}"/>
                                            <c:param name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}"/>
                                            <c:param name="subject" value="${param.subject}"/>
                                            <c:param name="status" value="${param.status}"/>
                                            <c:param name="index" value="${param.index}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Delete</a>
                                    </span>
                                </c:if>
                                <span class="badge badge-primary badge-pill">
                                    <c:url var="urlRewriting" value="update">
                                        <c:param name="btAction" value="update"/>
                                        <c:param name="questionID" value="${dto.questionID}"/>
                                        <c:param name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}"/>
                                        <c:param name="subject" value="${param.subject}"/>
                                        <c:param name="status" value="${param.status}"/>
                                        <c:param name="index" value="${param.index}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Update</a>
                                </span>
                            </h4>
                        </div>
                        <div id="collapse${counter.count}" class="panel-collapse collapse">
                            <c:forEach var="cdto" items="${requestScope.ListChoice}">
                                <c:if test="${cdto.questionID eq dto.questionID}">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                ${cdto.answer}
                                                <span class="badge badge-primary badge-pill">${cdto.isRight}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${end > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${end}" var="i">
                                <c:set var="index" value="${i}"/>
                                <li class="page-item"><a class="page-link" href="search?index=${index}&subject=${param.subject}&status=${param.status}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}">${index}</a></li>
                                </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </c:if>
     
        <c:if test="${not empty requestScope.ListSearch}">
            <div class="list-question">
                <h3 style="color: green;">List Of Question</h3>
            </div>
            <c:forEach var="dto" items="${requestScope.ListSearch}" varStatus="counter" >
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${counter.count}">
                                    ${dto.questionName}</a>
                                <span class="badge badge-primary badge-pill">${dto.subjectID}</span>
                                <c:if test="${dto.status == true}">
                                    <span class="badge badge-primary badge-pill">
                                        <c:url var="urlRewriting" value="delete">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="questionID" value="${dto.questionID}"/>
                                            <c:param name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}"/>
                                            <c:param name="subject" value="${param.subject}"/>
                                            <c:param name="status" value="${param.status}"/>
                                            <c:param name="index" value="${param.index}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Delete</a>
                                    </span>
                                </c:if>
                                <span class="badge badge-primary badge-pill">
                                    <c:url var="urlRewriting" value="update">
                                        <c:param name="btAction" value="update"/>
                                        <c:param name="questionID" value="${dto.questionID}"/>
                                        <c:param name="txtSearchValueByQuestionName" value="${param.txtSearchValueByQuestionName}"/>
                                        <c:param name="subject" value="${param.subject}"/>
                                        <c:param name="status" value="${param.status}"/>
                                        <c:param name="index" value="${param.index}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Update</a>
                                </span>
                            </h4>
                        </div>
                        <div id="collapse${counter.count}" class="panel-collapse collapse">
                            <c:forEach var="cdto" items="${requestScope.ListChoice}">
                                <c:if test="${cdto.questionID eq dto.questionID}">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                ${cdto.answer}
                                                <span class="badge badge-primary badge-pill">${cdto.isRight}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${end > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${end}" var="i">
                                <c:set var="index" value="${i}"/>
                                <li class="page-item"><a class="page-link" href="search?index=${index}&subject=${param.subject}&status=${param.status}&txtSearchValueByQuestionName=${param.txtSearchValueByQuestionName}">${index}</a></li>
                                </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </c:if>
    </body>
</html>
