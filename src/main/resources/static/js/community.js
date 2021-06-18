function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                //回复成功，关闭回复框
                location.reload();
            } else {
                if (response.code == 2002) {
                    //说明用户尚未登录，提醒其登录
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        //如果用户选择登录，则跳转登录并保存一个标志，以便于登录成功后在index页面的后续操作(即不在index页面停留，返回当前页面)
                        window.open("https://github.com/login/oauth/authorize?client_id=71bb76c35c478ffc94de&scope=user&state=1&redirect_uri=http://localhost:8887/callback");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    //提示用户出错信息
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}