



$( document ).ready(function() {

    var progressValues = getProgressValues();
   /*progressValues = $.getJSON( window.location + "list/taskProgress", function (data) {
       return data;
   });*/


    console.log(progressValues + " progressValues");

    ajaxGet();

    $("#getList").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

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

    function populateProgressDropDownList(progress, id) {
        $("#row-id-" + id + " :nth-child(3)").append(
            "<form><select id=\"select-id-" + id + "\"></select></form>"
        );
        jQuery.each(progress, function(key, val) {
            $("#select-id-" + id).append("<option value=\"" + key + "\">" + val + "</option>");

            console.log("Are you here?");
        });
        return true;
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
    function ajaxGet(){
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
                        console.log(todo.progress + " todo.progress, todo.id " + todo.id);
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