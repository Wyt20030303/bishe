$(function () {
   // 点击表单 uploadForm 的点击按钮时，触发 upload 函数
   $("#uploadForm").submit(upload);
});

function upload() {
    $.ajax({
        url: "/discuss/uploadHand",
        method: "post",
        processData: false, // 不要把表单内容转换成字符串
        contentType: false, // 不要 Jquery 自动设置上传的类型
        data: new FormData($("#uploadForm")[0]),
        success: function(data) {
            if (data && data.code == 1) {
                // 更新头像访问路径
                $.post(
                    CONTEXT_PATH + "/user/header/url",
                    {
                        "fileName": data.url
                    },
                    function (data) {
                        data = $.parseJSON(data);
                        if (data.code == 0) {
                            // 刷新界面
                            window.location.reload();
                        }
                        else {
                            alert(data.msg)
                        }
                    }
                )
            }
            else {
                alert("上传失败");
            }
        }

    });
    return false; // 表示事件到此为止，不要再提交表单了了
}