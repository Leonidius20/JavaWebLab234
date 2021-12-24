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
        Publisher: ${book.publisherName() }<br>
        Year: ${book.year() }<br>
        Total number of copies: ${ book.numberOfCopies() }
    </div>

</body>
</html>
