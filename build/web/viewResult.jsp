<%-- 
    Document   : viewResult
    Created on : Feb 5, 2021, 11:22:01 AM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>View Result Page</title>
    </head>
    <body style="background: pink;">
        <h1 style="color: white">Result</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col" style="color: white">Email</th>
                    <th scope="col" style="color: white">Date</th>
                    <th scope="col" style="color: white">SubjectID</th>
                    <th scope="col" style="color: white">NumOfCorrect</th>
                    <th scope="col" style="color: white">Mark</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row" style="color: white">${requestScope.email}</th>
                    <td style="color: white">${requestScope.date}</td>
                    <td style="color: white"> ${requestScope.subjectID}</td>
                    <td style="color: white">${requestScope.numOfCorrect}</td>
                    <td style="color: white">${requestScope.mark}</td>
                </tr>
            </tbody>
        </table>
                <a href="studentPage" class="badge badge-success" style="color: white">OK</a>

    </body>
</html>

