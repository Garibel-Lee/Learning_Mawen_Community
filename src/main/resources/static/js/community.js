function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": commentContent,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_session").hide();
            } else {
                if (response.code == 2003) {
                    var isAccept = confirm(response.message);
                    if (isAccept) {
                        window.open("https://github.com/login/oauth/authorize?client_id=353705c353a909271afe&redirect_uri=&scope=user&state=1")
                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json"
    });

}