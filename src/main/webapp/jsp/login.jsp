<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <%@include file="../page_header.jsp"%>
    <div class="container" style="display: flex; justify-content: center">
        <div class="card" style="flex-grow: 1; max-width: 400px">
            <div class="card-header">
                Login
            </div>
            <div class="card-body">
                <form action="login" method="post">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input id="name" name="name" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                    </div>
                    <!--<div style="margin-top: 5px" class="form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Remember me</label>
                    </div>-->
                    <button style="margin-top: 5px" id="login-button" type="submit" class="btn btn-primary">Log in</button>
                </form>
            </div>
            <div class="card-footer text-muted">
                Don't have an account? <a href="${pageContext.request.contextPath}/registration">Sign up</a>
            </div>
        </div>
    </div>
</body>
</html>
