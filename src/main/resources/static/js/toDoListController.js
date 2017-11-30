$( document ).ready(function() {

    getToDoList();

    $('#submit-task').click(addNewToDo);

    $('#task-form').on('keydown', (function(e){
        if (e.keyCode == 13 || e.which == 13) {
            addNewToDo();
            return false;
        }
    }));

    function addNewToDo() {
       // event.preventDefault();
        var taskVal = $('#task').val();
        var assigneeVal = $('#assignee').val();
        if((assigneeVal == '' || taskVal == '') || (assigneeVal == '' && taskVal == '')) {
            toDoPostError("Please fill form!");
            return;
        }
        var postJson = jsonString(0, taskVal, 'NOT_STARTED', assigneeVal);
        $.ajax({
            url: window.location + 'list',
            type: 'POST',
            dataType: 'json',
            data: postJson,
            contentType: 'application/json',
            mimeType: 'application/json',
            success : function (data) {
                var newRow =  newTableRow(data.assignee, data.task, data.id);
                $('#todoTable tbody').append(newRow);
                clearForm('task-form');
                $('#task-form').children('span').fadeOut('slow', function () {
                    $(this).remove();
                });
            },
            error: function (e, data) {
                console.log(data);
                console.log('ERROR: ' + e.toString());
            }
        })
    }

    function getToDoList(){
        $.ajax({
            type : 'GET',
            url : window.location + 'list',
            success: function(result){
                //TODO need some improvements for error handling
                if(result){
                    $('#todoTable tbody').empty();
                    $.each(result, function(i, todo){
                        var newRow =  newTableRow(todo.assignee, todo.task, todo.id);
                        $('#todoTable tbody').append(newRow);
                        //TODO refactor switch to for loop without constants
                        setToDoProgress(todo);
                    });
                    console.log('Success: ', result);
                }else{
                    $('#getResultDiv').html('<strong>Error, no list</strong>');
                    console.log('Fail: ', result);
                }
            },
            error : function(e) {
                $('#getResultDiv').html('<strong>Error</strong>');
                console.log('ERROR: ', e);
            }
        });
    }

    //Maybe there is better solution for this? Previously nothing worked, because all those dropdowns are dynamically created
    $('#todoTable').on('change', '.select-progress', function(){
        event.preventDefault();
        var id = $(this).attr('id');
        var idVal =  $('#' + id).val();
        var putJson = jsonString(id, '', idVal, '');
        $.ajax({
            url: window.location + 'list/' + id,
            type: 'PUT',
            dataType: 'json',
            data: putJson,
            contentType: 'application/json',
            mimeType: 'application/json',
            success : function (data) {
                console.log(data);
            },
            error : function (data, e) {
                console.log(data);
                console.log(putJson);
                console.log(e);
            }
        })
    });

    $('#todoTable').on('click', '.delete-task', function(){
        event.preventDefault();
        var idString = $(this).attr('id');
        var id = grabAfterDash(idString);
        var removableTr = $(this).closest('tr');
       $.ajax({
            url: window.location + 'list/' + id,
            type: 'DELETE',
            success : function (data) {
                removableTr.fadeOut('slow', function () {
                    removableTr.remove();
                });
            },
            error : function (e) {
                console.log(e);
            }
        })
    });

    //TODO at moment, this function doesn't work properly, need to refactor this
    function getProgressValues() {
        $.ajax({
            type : 'GET',
            url : window.location + 'list/taskProgress',
            success : function (result) {
                if(result) {
                    return JSON.stringify(result);
                }
                else {
                    result = null;
                }
            },
            error : function(e) {
                console.log('ERROR: ', e);
                return null;
            }
        })
    }


});

function clearForm(formId) {
    $(':input','#' + formId)
        .removeAttr('checked')
        .removeAttr('selected')
        .not(':button, :submit, :reset, :hidden, :radio, :checkbox')
        .val('');
}

function newTableRow(assignee, task, id){
    return '<tr class="to-do-info"><td>' + assignee + '</td><td>' + task +
        '</td><td>' +
        '<form class=\'\' id=\'progress-form\'>' +
        '<select class=\'select-progress form-control\' id=\'' + id + '\'>' +
        '<option id=\'option-1-for-' + id + '\' value=\'NOT_STARTED\'>Not started</option>' +
        '<option id=\'option-2-for-' + id + '\' value=\'IN_PROGRESS\'>In progress</option>' +
        '<option id=\'option-3-for-' + id + '\' value=\'DONE\'>Done</option>' +
        '</select>' +
        '</form>' +
        '</td><td><button class="btn btn-danger delete-task" id="delete-' + id + '">Delete task</button></td>' +
        '</tr>';
}

function jsonString(id, taskVal, progress, assigneeVal) {
    return '{\"id\" : ' + id + ',' +
        '\"task\" : \"' + taskVal  + '\",' +
        '\"progress\" : \"' + progress  + '\",' +
        '\"assignee\" :\"' + assigneeVal + '\"}'
}

function grabAfterDash(text) {
    return text.substr(text.lastIndexOf('-') + 1);
}

function toDoPostError(message) {
    $('#submit-task').after($('<span />').addClass("post-error label label-warning").html(message));
}

function setToDoProgress(todo) {
    switch (todo.progress) {
        case 'Not started':
            $('#option-1-for-' + todo.id).attr('selected', 'selected');
            break;
        case 'In progress':
            $('#option-2-for-' + todo.id).attr('selected', 'selected');
            break;
        case 'Done':
            $('#option-3-for-' + todo.id).attr('selected', 'selected');
            break;
        default:
    }
}
