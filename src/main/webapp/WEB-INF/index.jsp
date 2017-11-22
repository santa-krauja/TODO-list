<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>To do list</title>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
  </head>

  <header>To do list</header>

  <body>

      <button type="button" id="getList" class="btn btn-primary">List 2</button>

          <br />
          <br />
          <br />

          <div id="getResultDiv" style="padding:20px 10px 20px 50px">
              <ul class="list-group">
              </ul>
          </div>

          <br />
          <br />
          <br />


      <c:forEach var="todo" items="${todos}">
        <div class="row" style="border:1px solid green;padding:10px">
          <div class="col-md-4 text-center">${todo.id}</div>
          <div class="col-md-4 text-center" >${todo.todo}</div>
        </div>
      </c:forEach>

    <script>
      function  getToDoList() {
          window.location.href = "/list";
      }
    </script>

  </body>
</html>
