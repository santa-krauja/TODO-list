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
                    $('.list-group').empty();
                    var List = "";
                    $.each(result, function(i, todo){
                        $('.list-group').append(
                            $(document.createElement('li')).text(todo.task)
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