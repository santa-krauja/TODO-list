'use strict';

const Url = "/list/";
let progressValues;

$(document).ready(function () {

    $.ajax({
        type: 'GET',
        url: window.location + Url + 'taskProgress',
        success: function (result) {
            if (result) {
                progressValues = result;
            }
            else {
                result = null;
            }
        },
        error: function (e) {
            console.log('ERROR: ', e);
            return null;
        }
    });

    getToDoList();

    $('#submit-task').click(addNewToDo());

    $('#task-form').on('keydown', (function (e) {
        if (e.keyCode == 13 || e.which == 13) {
            addNewToDo();
            return false;
        }
    }));

    $('#todoTable').on('change', '.select-progress', function () {
        changeProgress.call(this, Url);
    });

    $('#todoTable').on('click', '.delete-task', function () {
        deleteTask.call(this, Url);
    });

});

function addNewToDo() {
    // event.preventDefault();
    const taskVal = $('#task').val();
    const assigneeVal = $('#assignee').val();
    if ((assigneeVal == '' || taskVal == '') || (assigneeVal == '' && taskVal == '')) {
        $('#post-error').css('display', 'none');
        return;
    }
    const postJson = jsonString(1, taskVal, 'NOT_STARTED', assigneeVal);
    $.ajax({
        url: window.location + Url,
        type: 'POST',
        dataType: 'json',
        data: postJson,
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function () {

            let newToDo = null;
            $.ajax({
                type: 'GET',
                url: window.location + Url,
                async: false,
                dataType: 'json',
                success: function (data) {
                    newToDo = data[((data.length) - 1)];
                },
                error: function (e, result) {
                    console.log('ERROR: ' + e.toString());
                    newToDo = e.toString();
                }
            });
            let newRow = newTableRow(newToDo.assignee, newToDo.task, newToDo.id);
            $('#todoTable tbody').append(newRow);
            setToDoProgress(newToDo);
            clearForm('task-form');
            hideElement("post-error");
            /*$('#task-form').children('span').fadeOut('slow', function () {
                $(this).remove();
            });*/
        },
        error: function (e, data) {
            console.log(data);
            console.log('ERROR: ' + e.toString());
        }
    })
}


function getToDoList() {
    $.ajax({
        type: 'GET',
        url: window.location + Url,
        success: function (result) {
            if (result) {
                $('#todoTable tbody').empty();
                $.each(result, function (i, todo) {
                    let newRow = newTableRow(todo.assignee, todo.task, todo.id);
                    $('#todoTable tbody').append(newRow);
                    setToDoProgress(todo);
                });
            } else {
                $('#getResultDiv').html('<strong>Error, no list</strong>');
                console.log('Fail: ', result);
            }
        },
        error: function (e) {
            $('#getResultDiv').html('<strong>Error</strong>');
            console.log('ERROR: ', e);
        }
    });
}

function changeProgress(Url) {
    event.preventDefault();
    const id = $(this).closest('tr').attr('id');
    const idVal = $(this).val();
    const putJson = jsonString(id, 's', idVal, 's');
    $.ajax({
        url: window.location + Url + id,
        type: 'PUT',
        dataType: 'json',
        data: putJson,
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
        },
        error: function (data, e) {
            console.log(data);
            console.log(e);
        }
    })
}

function deleteTask(Url) {
    event.preventDefault();
    const id = $(this).closest('tr').attr('id');
    const removableTr = $(this).closest('tr');
    $.ajax({
        url: window.location + Url + id,
        type: 'DELETE',
        success: function (data) {
            removableTr.fadeOut('slow', function () {
                removableTr.remove();
            });
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function clearForm(formId) {
    $(':input', '#' + formId)
        .removeAttr('checked')
        .removeAttr('selected')
        .not(':button, :submit, :reset, :hidden, :radio, :checkbox')
        .val('');
}

function newTableRow(assignee, task, id) {

    let options = "";
    let i = 1;
    Object.keys(progressValues).forEach(function (key) {
        options += '<option id=\'option-' + i + '-' + id + '\' value=\'' + key + '\'>' + progressValues[key] + '</option>';
        i++;
    });

    return '<tr class="to-do-info"  id=\'' + id + '\'><td>' + assignee + '</td><td>' + task +
        '</td><td>' +
        '<form class=\'progress-form\' id=\'\'>' +
        '<select class=\'select-progress form-control\'>' +
        options +
        '</select>' +
        '</form>' +
        '</td><td><button class="btn btn-danger delete-task">Delete task</button></td>' +
        '</tr>';
}

function jsonString(id, taskVal, progress, assigneeVal) {
    return '{\"id\" : ' + id + ',' +
        '\"task\" : \"' + taskVal + '\",' +
        '\"progress\" : \"' + progress + '\",' +
        '\"assignee\" :\"' + assigneeVal + '\"}'
}

function hideElement(elementId) {
    const element = $('#' + elementId);
    element.fadeOut('slow', function () {
        element.css('display', 'none');
    });
}

function setToDoProgress(todo) {
    let i = 1;
    Object.keys(progressValues).forEach(function (key) {
        if (todo.progress === progressValues[key]) {
            $('#option-' + i + '-' + todo.id).attr('selected', 'selected');
        }
        i++;
    });
}

