<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script type="text/javascript">

    $(function () {
        //标题
        var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
        //状态
        var sta = /^[0-1]$/;
        //定义标题验证规则
        $.extend($.fn.validatebox.defaults.rules, {
            name: {
                validator: function (value) {
                    var s = reg.test(value);
                    return s;

                },
                message: "请输入正确格式"
            },
            status: {
                validator: function (value) {
                    return sta.test(value);
                }
            }

        })

        //定义保存按钮
        $("#confirmimg").linkbutton({
            iconCls: "icon-save",

            //单击保存按钮触发表单提交事件
            onClick: function () {
                //提交表单事件
                $("#addImage").form("submit", {
                    url: "${pageContext.request.contextPath}/image/addImage",
                    onSubmit: function () {
                        //进行验证
                        return $(this).form("validate");
                    },
                    success: function (data) {


                        //关闭对话框
                        $("#dialogImg").dialog("close");
                        //调出系统提示框
                        $.messager.show({
                            title: "添加成功",
                            msg: "恭喜！成功加入列表！",

                        });
                        //刷新datagrid表格
                        $("#dbImg").datagrid("reload")

                    }
                })
            },


        })

    })
</script>

<div>
    <h1>
        add Image info:
    </h1>
    <form id="addImage" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    title:
                </td>
                <td>
                    <input id="title" type="text" class="easyui-validatebox" name="title"
                           data-options="validType:'name',required:true,"/>
                </td>
            </tr>
            <tr>
                <td>
                    status:
                </td>
                <td>
                    <input id="status" type="text" class="easyui-validatebox" name="status"
                           data-options="validType:'status',required:true"/>
                </td>
                <td>请输入0或1</td>
            </tr>
            <tr>
                <td>
                    描述:
                </td>
                <td>
                    <input id="des" type="text" class="easyui-validatebox" name="description"/>
                </td>

            </tr>
            <tr>
                <td>
                <td align="left">
                    上传图片：
                </td>
                <td align="left"><input type="file" name="file1" value="上传"/></td>
            </tr>
        </table>
        <p>
            <a id="confirmimg" class="easyui-linkbutton">保存</a>
        </p>
    </form>
</div>
</body>
