function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

//
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("空消息无法回复");
        return;
    }

    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_session").hide();
                location.reload();
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


//展开二级评论
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var collapse = e.getAttribute("data-collapse")
    if (collapse) {
        //折叠
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开
            comments.addClass("in");
            //标记二级评论状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            //通过api去后台获取数据处理渲染到页面
            $.getJSON("/comment/" + id, function (data) {
                console.log(data);

                $.each(data.data.reverse(), function (index, comment) {


                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded media-object-ep",
                        "src": comment.user.avatarUrl
                    }));

                    var meidaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        html: comment.user.name
                    })).append($("<div/>", {
                        html: comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        html:moment(comment.gmtCreate).format('YYYY-MM-YY')
                    })));


                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(meidaBodyElement);


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement);


                    subCommentContainer.prepend(commentElement);
                });
                //展开
                comments.addClass("in");
                //标记二级评论状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

/*            $.each(data.data, function (common) {
                $("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    "id": "comment-" + id,
                    html: common.content
                }).appendTo(commonBody);*/
/*       items.push("<li id='" + key + "'>" + val + "</li>");
   });*/

/*            commonBody.append($("<div/>", {
                "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comments",
                "id": "comment-" + id,
                html: items.join(commonBody)
            }));*/
/*         $("<div/>", {
             "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comments",
             "id": "comment-" + id,
             html: items.join(commonBody)
         }).appendTo(commonBody);*/


