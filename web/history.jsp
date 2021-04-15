<%-- 
    Document   : history
    Created on : Feb 5, 2021, 11:15:59 AM
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
        <title>JSP Page</title>
    </head>
    <body style="background: pink;">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Quiz Online</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="studentPage">Take A Quiz</a></li>
                </ul>   
                <ul class="nav navbar-nav">
                    <li class="active"><a href="viewHistory?email=${sessionScope.EMAIL}&index=1&subject=all">History</a></li>
                </ul>   
                <form action="viewHistory" class="navbar-form navbar-left">
                    <input type="hidden" name="email" value="${sessionScope.EMAIL}" />
                    <input type="hidden" name="index" value="1" />
                    <select name="subject" class="form-control">
                        <option value="all">All</option>
                        <c:forEach var="dto" items="${sessionScope.ListSubject}">
                            <c:choose>
                                <c:when test="${pageScope.dto.subjectID eq requestScope.subject}">
                                    <option value="${pageScope.dto.subjectID}" selected="">${pageScope.dto.subjectName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${pageScope.dto.subjectID}" >${pageScope.dto.subjectName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Find" class="btn btn-default" />
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

        <c:if test="${not empty requestScope.result }">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" style="color: white">Email</th>
                        <th scope="col" style="color: white">Date</th>
                        <th scope="col" style="color: white">SubjectID</th>
                        <th scope="col" style="color: white">NumOfCorrect</th>
                        <th scope="col" style="color: white">Mark</th>
                        <th scope="col" style="color: white">View</th>
                    </tr>
                </thead>
                <c:forEach var="dto" items="${requestScope.result}">
                    <tbody>
                        <tr>
                            <th scope="row" style="color: white">${dto.email}</th>
                            <td style="color: white">${dto.quizDate}</td>
                            <td style="color: white">${dto.subjectID}</td>
                            <td style="color: white">${dto.numOfCorrect}</td>
                            <td style="color: white">${dto.mark}</td>
                            <td><a href="viewHistoryDetail?num=${dto.num}&index=${param.index}&subject=${param.subject}" style="color: white">view</a></td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${end > 1}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:forEach begin="1" end="${end}" var="i">
                        <c:set var="index" value="${i}"/>
                        <li class="page-item"><a class="page-link" href="viewHistory?index=${index}&subject=${param.subject}&email=${sessionScope.EMAIL}">${index}</a></li>
                        </c:forEach>
                </ul>
            </nav>
        </c:if>
        <c:if test="${requestScope.historyDetail != null}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" style="color: white">Question Name</th>
                        <th scope="col" style="color: white">User Choice</th>
                        <th scope="col" style="color: white">Right Choice</th>
                    </tr>
                </thead>
                <c:forEach var="r" items="${requestScope.historyDetail}">
                    <tbody>
                        <tr>
                            <th scope="row" style="color: white">${r.questionName}</th>
                            <td style="color: white">${r.userChoice}</td>
                            <td style="color: white">${r.isRightChoice}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
            <a href="viewHistory?index=${param.index}&subject=${param.subject}&email=${sessionScope.EMAIL}" class="badge badge-success" style="color: white">return</a>
        </c:if>

    </body>
</html>

