<%@include file="includes/header.jsp"%>

<body>
<script type="text/javascript" src="/resources/js/tmp/toDoListController-compiled.js"></script>

<p>
    You are now connected to your Facebook account.
    Click <a href="/">here</a> to see some entries from your Facebook feed.
</p>
<div id="getResultDiv">
    <div class="table-responsive">
        <table class="table table-hover" id="todoTable">
            <thead>
            <tr>
                <th>Assignee</th>
                <th>Task</th>
                <th>Progress</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <br/>

    <div class="facebook-freinds">

    </div>



    <div id="footer-form" class="center">
        <label for="task-form">Create new task:</label>
        <form class="form-inline" id="task-form" name="task-form">
            <div class="form-group">
                <label for="assignee">Assignee</label>
                <input type="text" id="assignee" name="assignee"/>
            </div>
            <div class="form-group">
                <label for="task">Task:</label>
                <input type="text" id="task" name="task"/>
            </div>
            <input type="button" id="submit-task" name="Submit" value="Submit" class="btn btn-primary"
                   onsubmit="addNewToDo()"/>
            <span id="post-error" class="post-error label label-warning">Please fill form!</span>
        </form>
    </div>
</div>
</body>

</html>
