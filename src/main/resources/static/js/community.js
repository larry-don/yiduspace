function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("�ظ����ݲ���Ϊ��");
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
                //�ظ��ɹ����رջظ���
                location.reload();
            } else {
                if (response.code == 2002) {
                    //˵���û���δ��¼���������¼
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        //����û�ѡ���¼������ת��¼������һ����־���Ա��ڵ�¼�ɹ�����indexҳ��ĺ�������(������indexҳ��ͣ�������ص�ǰҳ��)
                        window.open("https://github.com/login/oauth/authorize?client_id=71bb76c35c478ffc94de&scope=user&state=1&redirect_uri=http://localhost:8887/callback");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    //��ʾ�û�������Ϣ
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}