<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="display: flex; justify-content: center">
        <div class="card" style="flex-grow: 1; max-width: 400px">
            <div class="card-header">Error</div>
            <div class="card-body">
                ${ errorMessage }<br>
                <a href="${ backLink }" class="btn btn-primary" role="button">Go back</a>
            </div>
        </div>
    </div>
</body>
</html>
