<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of ${role == 'LIBRARIAN' ? 'librarians' : 'users'}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <%@include file="../page_header.jsp" %>

    <c:if test="${role == 'LIBRARIAN'}">
        <a href="${pageContext.request.contextPath}/create-librarian" class="btn btn-primary" role="button">Add librarian</a>
    </c:if>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Passport</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach var="user" items="${ users }">

            <tr>
                <td>

                    <c:out value="${user.name()}" />
                </td>
                <td>

                    <c:out value="${user.passportNumber()}" />
                </td>
                <td>
                    <c:if test="${role == 'LIBRARIAN'}">
                        <a href="${pageContext.request.contextPath}/delete-user?id=${user.id()}" id="delete-user" class="btn btn-danger" role="button">Delete librarian</a>
                    </c:if>
                    <c:if test="${role == 'USER'}">
                        <c:if test="${!user.isBanned()}">
                            <a href="${pageContext.request.contextPath}/ban-user?id=${user.id()}" id="ban-user" class="btn btn-danger" role="button">Ban user</a>
                        </c:if>
                        <c:if test="${user.isBanned()}">
                            <a href="${pageContext.request.contextPath}/unban-user?id=${user.id()}" id="unban-user" class="btn btn-info" role="button">Unban user</a>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
</body>
</html>
