<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Books catalog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<%@include file="page_header.jsp"%>

<div class="container" style="padding: 5px">
    <form action="${pageContext.request.contextPath}/" method="get">
        <div class="input-group">
            <input class="form-control" type="text" name="q" id="q" placeholder="Search..." autocomplete="off">
            <div class="input-group-append">
                <select id="search_by" name="search_by" class="custom-select">
                    <option value="AUTHOR_OR_NAME">by name or author</option>
                    <option value="NAME">by name</option>
                    <option value="AUTHOR">by author</option>
                </select>
                <button id="submit-button" type="submit" class="btn btn-primary">Go</button>
            </div>
        </div>
    </form>

<table class="table table-bordered">
    <thead>
    <tr>
        <c:set var="searchParams" value="${empty param.q ? '' : '&q='.concat(param.q).concat('&search_by=').concat(param.search_by) }"/>
        <th><a href="${pageContext.request.contextPath}?sortBy=name${searchParams}">Name</a></th>
        <th><a href="${pageContext.request.contextPath}?sortBy=author${searchParams}">Author</a></th>
        <th><a href="${pageContext.request.contextPath}?sortBy=edition${searchParams}">Edition</a></th>
        <th><a href="${pageContext.request.contextPath}?sortBy=year${searchParams}">Year</a></th>
        <th>Number of copies</th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${ sessionScope.user != null && sessionScope.user.role() == 'ADMIN' }">
        <a href="${pageContext.request.contextPath}/edit-book" class="btn btn-primary" role="button">Add book</a>
    </c:if>

    <c:forEach var="book" items="${ books }">

        <tr>
            <td>
                <a href="book?id=${ book.id() }"><c:out value="${book.name()}"/></a>
            </td>
            <td>

                <c:out value="${book.authorName()}" />
            </td>
            <td>

                <c:out value="${book.edition()}" />
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
</div>
</body>
</html>
