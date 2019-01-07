<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script type="text/javascript">

    $(function () {
        //标题
        var regs=/^[a-zA-Z\/ ]{2,20}$/;
        var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
        //状态
        var sta = /^[0-1]$/;
        //定义标题验证规则
        $.extend($.fn.validatebox.defaults.rules, {
            name: {
                validator: function (value) {
                    var s = true;
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
        $("#confirmuser").linkbutton({
            iconCls: "icon-save",
            //单击保存按钮触发表单提交事件
            onClick: function () {
                //提交表单事件
                $("#addUserForm").form("submit", {
                    url: "${pageContext.request.contextPath}/user/insertUser",
                    onSubmit: function () {
                        //进行验证
                        return $(this).form("validate");
                    },
                    success: function (data) {
                        //关闭对话框
                        $("#dialogUser").dialog("close");
                        //调出系统提示框
                        $.messager.show({
                            title: "添加成功",
                            msg: "恭喜！成功加入列表！",

                        });
                        //刷新datagrid表格
                        $("#dbUser").datagrid("reload")

                    }
                })
            },


        })

    })
</script>

<div>
    <h1>
        add User info:
    </h1>
    <form id="addUserForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    姓名:
                </td>
                <td>
                    <input id="name" type="text" class="easyui-validatebox" name="name"
                           data-options="validType:'name',required:true"/>
                </td>
            </tr>
            <tr>
                <td>
                    昵称:
                </td>
                <td>
                    <input id="nickname" type="text" class="easyui-validatebox" name="nickname"
                           data-options="validType:'name',required:true"/>
                </td>
            </tr>
            <tr>
                <td>
                    密码:
                </td>
                <td>
                    <input id="password" type="text" class="easyui-validatebox" name="password"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    签名:
                </td>
                <td>
                    <input id="sign" type="text" class="easyui-validatebox" name="sign"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    性别:
                </td>
                <td>
                    <input id="sex" type="text" class="easyui-validatebox" name="sex"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    头像:
                </td>
                <td align="left"><input type="file" name="file1" value="上传"/></td>
            </tr>
            <tr>
                <td>
                    省份:
                </td>
                <td>
                    <input id="province" type="text" class="easyui-validatebox" name="province"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    城市:
                </td>
                <td>
                    <input id="city" type="text" class="easyui-validatebox" name="city"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    地址:
                </td>
                <td>
                    <input id="address" type="text" class="easyui-validatebox" name="address"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    手机号:
                </td>
                <td>
                    <input id="phone" type="text" class="easyui-validatebox" name="phone"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>
                    status:
                </td>
                <td>
                    <input id="status" type="text" class="easyui-validatebox" name="status"
                           data-options="validType:'status'"/>
                </td>
                <td>请输入0或1</td>
            </tr>
        </table>
        <p>
            <a id="confirmuser" class="easyui-linkbutton">保存</a>
        </p>
    </form>
</div>
</body>
