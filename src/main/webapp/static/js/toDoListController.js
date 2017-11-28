



$( document ).ready(function() {

    getToDoList();

    $("input#submit-task").click(function () {
        event.preventDefault();
        var taskVal = $("#task").val();
        var assigneeVal = $("#assignee").val();
        var jsonString = "{\"id\" : 0," +
            "\"task\" : \"" + taskVal  + "\"," +
            "\"progress\" : \"NOT_STARTED\"," +
            "\"assignee\" :\"" + assigneeVal + "\"}";
        $.ajax({
            url: window.location + "list",
            type: "POST",
            dataType: "json",
            data: jsonString,
            contentType: 'application/json',
            mimeType: 'application/json',
            success : function (data) {
                var newRow =  newTableRow(data.assignee, data.task, data.id);
                $("#todoTable tbody").append(
                    newRow
                );
                clearForm("task-form");
            },
            error: function (e, data) {
                console.log(data);
                console.log("ERROR: " + e.toString());
            }

        })
    });

    function clearForm(formId) {
        $(':input','#' + formId)
            .removeAttr('checked')
            .removeAttr('selected')
            .not(':button, :submit, :reset, :hidden, :radio, :checkbox')
            .val('');
    }

    function getProgressValues() {
        $.ajax({
            type : "GET",
            url : window.location + "list/taskProgress",
            success : function (result) {
                if(result) {
                    return JSON.stringify(result);
                }
                else {
                    result = null;
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
               return null;
            }
        })
    }

    function newTableRow(assignee, task, id){

        return "<tr><td>" + assignee + "</td><td>" + task +
            "</td><td><select class=\'form-control\'>" +
            "<option id=\'option-1-for-" + id + "\' value='NOT_STARTED\'>Not started</option>" +
            "<option id=\'option-2-for-" + id + "\' value='IN_PROGRESS\'>In progress</option>" +
            "<option id=\'option-3-for-" + id + "\' value=\'DONE\'>Done</option>" +
            "</select></td></tr>";
        /*return "<tr id=\"row-id-" + id + "\"><td>" + assignee + "</td><td>" + task + "</td>" +
            "<td></td>"*/

    }



    // DO GET
    function getToDoList(){
        $.ajax({
            type : "GET",
            url : window.location + "list",
            success: function(result){
                //TODO need some improvements for error handling
                if(result){
                    $("#todoTable tbody").empty();
                    var List = "";
                    $.each(result, function(i, todo){
                        var newRow =  newTableRow(todo.assignee, todo.task, todo.id);
                        $("#todoTable tbody").append(
                           newRow
                    );
                        //TODO refactor to for loop without constants
                        switch (todo.progress) {
                            case "Not started":
                                $("#option-1-for-" + todo.id ).attr("selected", "selected");
                                break;
                            case "In progress":
                                $("#option-2-for-" + todo.id ).attr("selected", "selected");
                                break;
                            case "Done":
                                $("#option-3-for-" + todo.id).attr("selected", "selected");
                                break;
                            default:
                        }
                });
                    console.log("Success: ", result);
                }else{
                    $("#getResultDiv").html("<strong>Error, no list</strong>");
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
});
