<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script>
    $(function () {
        $("#submitAlbum").linkbutton({
            iconCls:"icon-save",
            onClick:function(){
                $("#albumFormAdd").form("submit",{
                    url:"${pageContext.request.contextPath}/album/insertAlbum",
                    onSubmit:function () {
                        return true;
                    },
                    success:function () {
                        $("#addAlbumDia").dialog("close");
                        $.messager.show({
                            title:"添加成功",
                            msg:"恭喜，添加成功"
                        });
                        $("#albumT").treegrid("reload")

                    }
                })
            }

        })

    })

</script>
<div>
    <div><p style="font-size: large">添加专辑</p></div>
    <div>
        <form id="albumFormAdd" method="post" enctype="multipart/form-data">
            <table>

                <tr>
                    <td>
                        Title:
                    </td>
                    <td><input name="title" type="text" /></td>
                </tr>
                <tr>
                    <td>
                        上传封面:
                    </td>
                    <td><input name="file1" type="file"  /></td>
                </tr>
                <tr>
                    <td>
                        评分:
                    </td>
                    <td><input name="score" type="text" ></td>
                </tr>
                <tr>
                    <td>
                        作者:
                    </td>
                    <td><input name="author" type="text" /></td>
                </tr>
                <tr>
                    <td>
                        广播员:
                    </td>
                    <td><input name="broadcast" type="text" /></td>
                </tr>
                <tr>
                    <td>
                        简介:
                    </td>
                    <td><input name="brief" type="text" /></td>
                </tr>

                <tr>
                    <td>
                        <a id="submitAlbum" class="easyui-linkbutton" href="#">保存</a>
                    </td>

                </tr>
            </table>
        </form>
    </div>
</div>
</body>