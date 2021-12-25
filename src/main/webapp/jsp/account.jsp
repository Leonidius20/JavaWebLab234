<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <%@include file="../page_header.jsp"%>
    <div class="container"  style="display: flex; justify-content: center">
        <div class="card" style="flex-grow: 1; max-width: 400px; padding: 5px">
            <div class="card-header">
                Your account
            </div>
            <div class="card-body">
                <p>Username: ${sessionScope.user.name()}</p>
                <p>Passport number: ${sessionScope.user.passportNumber()}</p>
                <p>Role: ${sessionScope.user.role().toString() }</p>
            </div>
        </div>
        <div class="card" style="flex-grow: 1; max-width: 400px; padding: 5px">
            <div class="card-header">
                Your books
            </div>
            <div class="card-body">
                <!--<p>Username: User</p>
                <div style="display: flex; justify-content: space-between">
                    <p>Email: Email</p>
                    <button id="change-emrail" class="btn btn-primary">Change</button>
                </div>
                <p>Premium expiry date: rr</p>
                <button id="delete-accounrt" class="btn btn-danger">Delete account</button> -->
            </div>
        </div>
    </div>
</body>
</html>
