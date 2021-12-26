<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request a book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <%@include file="../page_header.jsp" %>
    <div class="container" style="display: flex; justify-content: center">
        <div class="card" style="flex-grow: 1; max-width: 400px">
            <div class="card-header">
                Request a book
            </div>
            <div class="card-body">
                <form action="request-book" method="post">

                    <div class="form-group">
                        <label for="id">ID</label>
                        <input id="id" name="id" type="text" class="form-control" value="${param.id}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="borrowing_type">Request type</label>
                        <select id="borrowing_type" name="borrowing_type" class="form-control">
                            <option value="TAKE_HOME">Take home</option>
                            <option value="READ_AT_LIBRARY">Read at reading room</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="desired_date">Desired date</label>
                        <input id="desired_date" name="desired_date" type="date" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="end_date">End date</label>
                        <input id="end_date" name="end_date" type="date" class="form-control" >
                    </div>
                    <button style="margin-top: 5px" id="submit-button" type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
