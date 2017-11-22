<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>To do list</title>
  </head>

  <header>To do list:</header>

  <body>
    <form name="getList" method="post">
      <input type="text" name="facebookName" />
      <input type="button" value="Get Todo list" onclick="getToDoList()">
    </form>

    <script>
      function  getToDoList() {
          window.location.href = "/list";
      }
    </script>

  </body>
</html>
