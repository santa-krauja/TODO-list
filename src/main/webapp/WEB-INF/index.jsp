<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <title>To do list</title>
      <script type="text/javascript" src="${pageContext.request.contextPath}/node_modules/jquery/dist/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/toDoListController.js"></script>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/node_modules/bootstrap/dist/css/bootstrap.min.css" />
  </head>

  <header>
      <h1>To do list</h1>
  </header>

  <body>
      <div id="getResultDiv">
          <div class="table-responsive">
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
          </div>
          <br />
          <div>
              <label for="task-form">Create new task:</label>
              <form class="form-inline" id="task-form" name="task-form">
                  <div class="form-group">
                      <label for="assignee">Assignee</label>
                      <input type="text" id="assignee" name="assignee" />
                  </div>
                  <div class="form-group">
                      <label for="task">Task:</label>
                      <input type="text" id="task" name="task"  />
                  </div>
                    <input type="button" id="submit-task" name="Submit" value="Submit" class="btn btn-primary" />
              </form>
          </div>
      </div>
  </body>

</html>
