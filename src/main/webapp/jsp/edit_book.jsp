<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <%@include file="../page_header.jsp" %>
    <div class="container" style="display: flex; justify-content: center">
        <div class="card" style="flex-grow: 1; max-width: 400px">
            <div class="card-header">
                Edit book info
            </div>
            <div class="card-body">
                <form action="edit-book" method="post">

                    <div class="form-group">
                        <label for="id">ID</label>
                        <input id="id" name="id" type="text" class="form-control" value="${book == null ? -1 : book.id()}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input id="name" name="name" type="text" class="form-control" value="${book == null ? '' : book.name()}" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label for="author">Author</label>
                        <input id="author" name="author" type="text" class="form-control" value="${book == null ? '' : book.authorName()}" placeholder="Enter author">
                    </div>
                    <div class="form-group">
                        <label for="year">Year</label>
                        <input id="year" name="year" type="number" class="form-control" value="${book == null ? '' : book.year()}" placeholder="Enter year">
                    </div>
                    <div class="form-group">
                        <label for="edition">Edition</label>
                        <input id="edition" name="edition" type="number" class="form-control" value="${book == null ? '' : book.edition()}" placeholder="Enter edition">
                    </div>
                    <div class="form-group">
                        <label for="number_of_copies">Number of copies</label>
                        <input id="number_of_copies" name="number_of_copies" type="number" class="form-control" value="${book == null ? '' : book.numberOfCopies()}" placeholder="Enter number of copies">
                    </div>
                    <button style="margin-top: 5px" id="submit-button" type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
