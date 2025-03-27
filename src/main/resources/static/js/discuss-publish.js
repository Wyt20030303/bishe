$(function () {
    $("#publishBtn").click(publish);
    $("#caogao").click(caogaoxiang);
    $("#backIndexBtn").click(backIndex);
    $("#addFile").click(addFileBtn);
    $('.btn_file').bind('input propertychange', btnFileUpload);
});
var fileUrl = "";

function onPropChanged() {
    if ($('#toUserId').val() != '') {
        $.get('checkUser?toUser=' + $('#toUserId').val(), function (res) {
            if (res.code == 0) {
                $('#toUserTips').html(res.msg);
                $('#toUserTips').css("color","green");
            } else {
                $('#toUserTips').html(res.msg);
                $('#toUserTips').css("color","red");
            }
        })
    }
    console.log($('#toUserId').val())
}

function publish() {
    $("#publishModal").modal("hide");
    // 获取标题和内容
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    var toUserId = $("#toUserId").val();
    var mailType = $('input[name="mailType"]:checked').val();
    var weichatPush = $('input[name="weichatPush"]:checked').val();



    if (title == '') {
        $("#hintBody").text("邮件主题不能为空");
        // 显示提示框
        $("#hintModal").modal("show");
        return;
    }
    if (content == '') {
        $("#hintBody").text("邮件内容不能为空");
        // 显示提示框
        $("#hintModal").modal("show");
        return;
    }
    if (toUserId == '') {
        $("#hintBody").text("收件人不能为空");
        // 显示提示框
        $("#hintModal").modal("show");
        return;
    }
    var fileUrl = [];
    var fileName = [];
    $(".fileUrlA").each(function () {
        fileUrl.push($(this).attr('href'));
        fileName.push($(this).html());
    })

    if (content.indexOf("1") !== -1) {
        $("#hintBody").text("邮件包含敏感词，不能发送");
        $("#hintModal").modal("show");
    }else{
        // 发送异步请求
        $.post(
            CONTEXT_PATH + "/discuss/add",
            {
                "title": title,
                "content": content,
                "toUserId": toUserId,
                "mailType": mailType,
                "weichatPush": weichatPush,
                "fileUrl": JSON.stringify(fileUrl),
                "fileName": JSON.stringify(fileName)
            },
            // 处理服务端返回的数据
            function (data) {
                // String -> Json 对象
                data = $.parseJSON(data);
                if (data.code == 0) {
                    // 在提示框 hintBody 显示服务端返回的消息
                    $("#hintBody").text(data.msg);
                    // 显示提示框
                    $("#hintModal").modal("show");
                    // 2s 后自动隐藏提示框
                    setTimeout(function () {
                        $("#hintModal").modal("hide");
                        // 操作完成后，跳转到首页
                        if (data.code == 0) {
                            location.href = CONTEXT_PATH + "/index";
                        }
                    }, 2000);
                } else {
                    // 在提示框 hintBody 显示服务端返回的消息
                    $("#hintBody").text(data.msg);
                    // 显示提示框
                    $("#hintModal").modal("show");
                }


            }
        )
    }

}

function caogaoxiang() {
    $("#publishModal").modal("hide");
    // 获取标题和内容
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    var toUserId = $("#toUserId").val();
    var postId = $("#postId").val();
    var mailType = $('input[name="mailType"]:checked').val();

    var fileUrl = [];
    var fileName = [];
    $(".fileUrlA").each(function () {
        fileUrl.push($(this).attr('href'));
        fileName.push($(this).html());
    })
    // 发送异步请求
    $.post(
        CONTEXT_PATH + "/discuss/drafts",
        {
            "title": title,
            "content": content,
            "toUserId": toUserId,
            "postId": postId,
            "mailType": mailType,
            "fileUrl": JSON.stringify(fileUrl),
            "fileName": JSON.stringify(fileName)
        },
        // 处理服务端返回的数据
        function (data) {
            // String -> Json 对象
            data = $.parseJSON(data);
            if (data.code == 0) {
                // 在提示框 hintBody 显示服务端返回的消息
                $("#hintBody").text(data.msg);
                // 显示提示框
                $("#hintModal").modal("show");
                // 2s 后自动隐藏提示框
                setTimeout(function () {
                    $("#hintModal").modal("hide");
                    // 操作完成后，跳转到首页
                    if (data.code == 0) {
                        location.href = CONTEXT_PATH + "/index";
                    }
                }, 2000);
            } else {
                // 在提示框 hintBody 显示服务端返回的消息
                $("#hintBody").text(data.msg);
                // 显示提示框
                $("#hintModal").modal("show");
            }
        }
    )
}

function delete_file(dom) {
    console.log(dom.parentNode.parentNode.parentNode.remove());
}

function backIndex() {
    location.href = CONTEXT_PATH + "/index";
}

function addFileBtn() {
    $('#btn_file').click();
}

function btnFileUpload() {
    if ($('#btn_file').val() == '') {
        console.log('2333');
    } else if ($('#btn_file').val() == fileUrl) {
        console.log(fileUrl);
    } else {
        fileUrl = $('#btn_file').val();
        //文件上传动作
        var formData = new FormData();
        formData.append("file", $("#btn_file").get(0).files[0]);  //上传一个file对象
        formData.append("operatorId", "1");//需要上传的多个参数
        $.ajax({//jQuery方法，此处可以换成其它请求方式
            url: 'fileUpload',
            dataType: "json",
            type: "post",
            data: formData,
            processData: false,//不去处理发送的数据
            contentType: false,//不去设置Content-Type请求头
            error: function (res) {
                alert("上传失败");
                return;
            },
            success: function (res) {
                if (res.code == 1) {
                    res.url;
                    fileUrl = $('#btn_file').val();
                    $("#fileUl").append('<div class="row"><div class="col-lg-6"><li class="li-item "><a target="_blank" class="fileUrlA" href="' + res.url + '">' + res.fileName + '</a><span onclick="delete_file(this)" style="color: red; margin-left: 20px; cursor:pointer;">删除</span></li></div></div>');
                    alert("上传成功");
                } else {
                    alert("上传失败");
                }
                return;
            }
        })
    }
}
