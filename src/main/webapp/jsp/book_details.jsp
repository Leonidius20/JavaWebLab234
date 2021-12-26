<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${ book.name() }</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

    <%@include file="../page_header.jsp"%>

    <div class="container">
        <h1>${ book.name() }</h1>
        Author: ${book.authorName() }<br>
        Year: ${book.year() }<br>
        Total number of copies: ${ book.numberOfCopies() }

        <c:if test="${ sessionScope.user != null && sessionScope.user.role() == 'ADMIN' }">
            <a href="${pageContext.request.contextPath}/edit-book?id=${book.id()}" id="edit-book" class="btn btn-primary" role="button">Edit book</a>
            <a href="${pageContext.request.contextPath}/delete-book?id=${book.id()}" id="delete-book" class="btn btn-danger" role="button">Delete book</a>
        </c:if>

        <c:if test="${ sessionScope.user != null && sessionScope.user.role() == 'USER' && !book_requested }">
            <a href="${pageContext.request.contextPath}/request-book?id=${book.id()}" id="request-book" class="btn btn-primary" role="button">Request book</a>
        </c:if>
    </div>

</body>
</html>
