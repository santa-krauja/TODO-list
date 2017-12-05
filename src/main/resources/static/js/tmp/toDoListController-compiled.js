'use strict';

$(document).ready(function () {

    getToDoList();

    $('#submit-task').click(addNewToDo);

    $('#task-form').on('keydown', function (e) {
        if (e.keyCode == 13 || e.which == 13) {
            addNewToDo();
            return false;
        }
    });

    function addNewToDo() {
        // event.preventDefault();
        var taskVal = $('#task').val();
        var assigneeVal = $('#assignee').val();
        if (assigneeVal == '' || taskVal == '' || assigneeVal == '' && taskVal == '') {
            toDoPostError("Please fill form!");
            return;
        }
        var postJson = jsonString(1, taskVal, 'NOT_STARTED', assigneeVal);
        $.ajax({
            url: window.location + 'list',
            type: 'POST',
            dataType: 'json',
            data: postJson,
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function success() {

                var newToDo = null;
                $.ajax({
                    type: 'GET',
                    url: window.location + 'list',
                    async: false,
                    dataType: 'json',
                    success: function success(data) {
                        newToDo = data[data.length - 1];
                    },
                    error: function error(e, result) {
                        console.log('ERROR: ' + e.toString());
                        newToDo = e.toString();
                    }
                });
                var newRow = newTableRow(newToDo.assignee, newToDo.task, newToDo.id);
                $('#todoTable tbody').append(newRow);
                clearForm('task-form');
                $('#task-form').children('span').fadeOut('slow', function () {
                    $(this).remove();
                });
            },
            error: function error(e, data) {
                console.log(data);
                console.log('ERROR: ' + e.toString());
            }
        });
    }

    function getToDoList() {
        $.ajax({
            type: 'GET',
            url: window.location + 'list',
            success: function success(result) {
                if (result) {
                    $('#todoTable tbody').empty();
                    $.each(result, function (i, todo) {
                        var newRow = newTableRow(todo.assignee, todo.task, todo.id);
                        $('#todoTable tbody').append(newRow);
                        setToDoProgress(todo);
                    });
                    console.log('Success: ', result);
                } else {
                    $('#getResultDiv').html('<strong>Error, no list</strong>');
                    console.log('Fail: ', result);
                }
            },
            error: function error(e) {
                $('#getResultDiv').html('<strong>Error</strong>');
                console.log('ERROR: ', e);
            }
        });
    }

    //Maybe there is better solution for this? Previously nothing worked, because all those dropdowns are dynamically created
    $('#todoTable').on('change', '.select-progress', function () {
        event.preventDefault();
        var id = $(this).closest('tr').attr('id');
        console.log(id);
        var idVal = $(this).val();
        var putJson = jsonString(id, '', idVal, '');
        $.ajax({
            url: window.location + 'list/' + id,
            type: 'PUT',
            dataType: 'json',
            data: putJson,
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function success(data) {
                console.log(data);
            },
            error: function error(data, e) {
                console.log(data);
                console.log(e);
            }
        });
    });

    $('#todoTable').on('click', '.delete-task', function () {
        event.preventDefault();
        var id = $(this).closest('tr').attr('id');
        var removableTr = $(this).closest('tr');
        $.ajax({
            url: window.location + 'list/' + id,
            type: 'DELETE',
            success: function success(data) {
                removableTr.fadeOut('slow', function () {
                    removableTr.remove();
                });
            },
            error: function error(e) {
                console.log(e);
            }
        });
    });

    var progressValues = void 0;
    $.ajax({
        type: 'GET',
        url: window.location + 'list/taskProgress',
        success: function success(result) {
            if (result) {
                progressValues = result;
            } else {
                result = null;
            }
        },
        error: function error(e) {
            console.log('ERROR: ', e);
            return null;
        }
    });

    function clearForm(formId) {
        $(':input', '#' + formId).removeAttr('checked').removeAttr('selected').not(':button, :submit, :reset, :hidden, :radio, :checkbox').val('');
    }

    function newTableRow(assignee, task, id) {

        var options = "";
        var i = 1;
        Object.keys(progressValues).forEach(function (key) {
            options += '<option id=\'option-' + i + '-' + id + '\' value=\'' + key + '\'>' + progressValues[key] + '</option>';
            i++;
        });

        return '<tr class="to-do-info"  id=\'' + id + '\'><td>' + assignee + '</td><td>' + task + '</td><td>' + '<form class=\'progress-form\' id=\'\'>' + '<select class=\'select-progress form-control\'>' + options + '</select>' + '</form>' + '</td><td><button class="btn btn-danger delete-task">Delete task</button></td>' + '</tr>';
    }

    function jsonString(id, taskVal, progress, assigneeVal) {
        return '{\"id\" : ' + id + ',' + '\"task\" : \"' + taskVal + '\",' + '\"progress\" : \"' + progress + '\",' + '\"assignee\" :\"' + assigneeVal + '\"}';
    }

    function toDoPostError(message) {
        $('#submit-task').after($('<span />').addClass("post-error label label-warning").html(message));
    }

    function setToDoProgress(todo) {
        var i = 1;
        Object.keys(progressValues).forEach(function (key) {
            if (todo.progress === progressValues[key]) {
                $('#option-' + i + '-' + todo.id).attr('selected', 'selected');
            }
            i++;
            console.log(todo.progress + ' : todo progress');
            console.log(progressValues[key] + ': javascript progress');
        });
    }
});
//# sourceMappingURL=toDoListController-compiled.js.map
