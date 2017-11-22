$( document ).ready(function() {

    // GET REQUEST
    $("#getList").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : window.location + "list",
            success: function(result){
                //TODO need some improvements for error handling
                if(result){
                    $('#getResultDiv ul').empty();
                    var List = "";
                    $.each(result, function(i, todo){
                        $('#getResultDiv ul').append(
                            $(document.createElement('li')).text(todo.todo)
                        );
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
})