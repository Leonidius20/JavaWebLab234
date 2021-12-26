<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Readers list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <%@include file="../page_header.jsp" %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Passport</th>
            <th>Requests</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach var="user" items="${ users }">

            <tr>
                <td>

                    <c:out value="${user.user().name()}" />
                </td>
                <td>

                    <c:out value="${user.user().passportNumber()}" />
                </td>
                <td>
                    <c:forEach var="request" items="${ user.requests() }">
                        <div class="card-body">
                            Book: <a href="${pageContext.request.contextPath}/book?id=${request.bookId()}">${request.bookName()}</a><br>
                            Request type: ${request.borrowingType().getName()}<br>
                            Start date: ${request.desiredDate()}<br>
                            End date: ${request.endDate()}<br>
                            Status: ${request.status()}<br>
                            <c:if test="${request.status() == 'TAKEN'}">
                                <c:set var="days_remaining" value="${ChronoUnit.DAYS.between(LocalDate.now(), request.endDate())}"/>
                                <c:if test="${days_remaining >= 0}">
                                    Days remaining: ${days_remaining}<br>
                                </c:if>
                                <c:if test="${days_remaining < 0}">
                                    Days past the deadline: ${-days_remaining}<br>
                                    Fine: ${-days_remaining * 10}&#8372;
                                </c:if>
                            </c:if>
                        </div>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
</body>
</html>
