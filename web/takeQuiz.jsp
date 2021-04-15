<%-- 
    Document   : takeQuiz
    Created on : Feb 5, 2021, 11:20:05 AM
    Author     : Nguyen Khoi
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script> 
        <script type="text/javascript" src="js/paging.js"></script>
        <script type="text/javascript" src="js/paging.min.js"></script>
        <title>Take Quiz Page</title>
        <style>
            div{
                background-color:#419D78;
                color:#EFD0CA;
                font-size:20px;
                text-align:center;
            }
            .title{
                display: flex;
            }
            .fpt{
                margin-left: 1400px;
            }
        </style>
    </head>
    <body>
        <div class="title">
            <div><h1>Quiz Online</h1></div>
            <div class="fpt"><img alt="govno" src="<c:url value="/image/logo.png"></c:url>" style="width: 310px; height: 80px;;"></div>
            </div>
            <div><h2> Time = <span id="timer"></span> </h2></div>
            <script>
                document.getElementById('timer').innerHTML =
                        30 + ":" + 00;
                startTimer();

                function startTimer() {
                    var presentTime = document.getElementById('timer').innerHTML;
                    var timeArray = presentTime.split(/[:]+/);
                    var m = timeArray[0];
                    var s = checkSecond((timeArray[1] - 1));
                    if (s == 59) {
                        m = m - 1
                    }
                    document.getElementById('timer').innerHTML =
                            m + ":" + s;
                    console.log(m)
                    setTimeout(startTimer, 1000);
                }

                function checkSecond(sec) {
                    if (sec < 10 && sec >= 0) {
                        sec = "0" + sec
                    }
                    ;
                    if (sec < 0) {
                        sec = "59"
                    }
                    ;
                    return sec;
                }

            </script>
            <script>
                window.onload = function () {
                    var button = document.getElementById('clickButton');
                    setInterval(function () {
                        button.click();
                    }, 60000 * 30);
                };
            </script>
            <script>
                window.addEventListener("beforeunload", function (e) {
                    var confirmationMessage = "";
                    (e || window.event).returnValue = confirmationMessage;
                    return confirmationMessage;
                });
            </script>
            <h2>
                <img alt="govno" src="<c:url value="/image/Warning-Symbol.jpg"></c:url>" style="width: 80px; height: 60px;">
                <font color="red">Dangerous: Don't Reload page. If not you will be lost Quiz test and will test again!!!! </font>
            </h2>

            <p>
            <div style="text-align:center;">
                <input type="button" id="previous" onclick="previousPage()" value="previous" />
                <input type="button" id="next" onclick="nextPage()" value="next" />
                <div id="list"></div>
            </div>
        </p>


        <form id="wrappper" action="submit" method="POST">
            <input type="hidden" name="numOfQuestion" value="${param.numOfQuestion}"/>
        <input type="hidden" name="subjectID" value="${param.subjectID}" />

        <c:forEach var="listQuestion" items="${sessionScope.ListQuiz}" varStatus="counter">
            <div>
                <h2><strong>${counter.count}: ${listQuestion.questionName}</strong></h2>
                <c:forEach var="ListQuizChoice" items="${sessionScope.ListQuizChoice}">
                    <c:if test="${ListQuizChoice.questionID eq listQuestion.questionID}">
                        <input type="radio" name="${listQuestion.questionID}" value="${ListQuizChoice.choiceID}"/>${ListQuizChoice.answer}</br>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>

        <input type="submit" value="Submit" id="clickButton"/>

    </form>






</body>
</html>
