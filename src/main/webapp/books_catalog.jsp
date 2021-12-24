<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Books catalog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<%@include file="page_header.jsp"%>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Name</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Year</th>
        <th>Number of copies</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="book" items="${ books }">

        <tr>
            <td>
                <a href="book?id=${ book.id() }"><c:out value="${book.name()}"/></a>
            </td>
            <td>

                <c:out value="${book.authorName()}" />
            </td>
            <td>

                <c:out value="${book.publisherName()}" />
            </td>
            <td>

                <c:out value="${book.year()}" />
            </td>
            <td>

                <c:out value="${book.numberOfCopies()}" />
            </td>


        </tr>
    </c:forEach>

    </tbody>

</table>
</body>
</html>