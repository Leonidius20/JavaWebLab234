<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book requests</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@include file="../page_header.jsp" %>

<table class="table table-bordered">
  <thead>
  <tr>
    <th>Book name</th>
    <th>User name</th>
    <th>Request type</th>
    <th>Status</th>
  </tr>
  </thead>
  <tbody>


  <c:forEach var="order" items="${ orders }">

    <tr>
      <td>

        <c:out value="${order.bookName()}" />
      </td>
      <td>

        <c:out value="${order.userName()}" />
      </td>
      <td>

        <c:out value="${order.borrowingType().getName()}" />
      </td>
      <td>

        <form action="${pageContext.request.contextPath}/set-request-status" method="get">
          <div class="input-group">
            <input type="number" name="id" value="${order.id()}" hidden/>
            <div class="input-group-append">
              <select id="status" name="status" class="custom-select">
                <option value="PENDING" ${order.status() == 'PENDING' ? 'selected' : ''}>Pending</option>
                <option value="TAKEN" ${order.status() == 'TAKEN' ? 'selected' : ''}>Taken</option>
                <option value="RETURNED" ${order.status() == 'RETURNED' ? 'selected' : ''}>Returned</option>
              </select>
              <button id="submit-button" type="submit" class="btn btn-primary">Change</button>
            </div>
          </div>
        </form>
      </td>
    </tr>
  </c:forEach>

  </tbody>

</table>
</body>
</html>
