<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>To do list</title>
      <script type="text/javascript" src="${pageContext.request.contextPath}/node_modules/jquery/dist/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/toDoListController.js"></script>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/node_modules/bootstrap/dist/css/bootstrap.min.css" />
  </head>

  <header>To do list</header>

  <body>

     <%-- <button type="button" diplay="none" id="getList" class="btn btn-primary">List</button>--%>
      <div id="getResultDiv1" style="padding:20px 10px 20px 50px">
          <ul class="list-group">
          </ul>
      </div>
      
      <div id="getResultDiv" class="table-responsive ">
          <table class="table table-hover" id="todoTable">
              <thead>
                  <tr>
                    <th>Assignee</th>
                    <th>Task</th>
                    <th>Progress</th>
                  </tr>
                </thead>
              <tbody>
              </tbody>
          </table>

            <br />
            <br />
            <br />
            <br />
          <label for="taskProgress">Task progress example</label><select class="form-control col-xs-5" id="taskProgress" name="taskProgress">

              <c:forEach items="${TaskProgress}" var="value">
                <option value="${value}">${value.toString()}</option>
              </c:forEach>
          </select>
          
      
      </div>


  </body>
</html>
