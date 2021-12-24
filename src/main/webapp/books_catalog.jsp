<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Books catalog</title>
</head>
<body>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="book" items="${ books }">

        <tr>
            <td>

                <c:out value="${book.id()}" />
            </td>
            <td>
                <c:out value="${book.name()}" />
            </td>


        </tr>
    </c:forEach>

    </tbody>

</table>
</body>
</html>
