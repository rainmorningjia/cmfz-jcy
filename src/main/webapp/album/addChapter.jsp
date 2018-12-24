<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script>
    $(function () {
        $("#submitChapter").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                $("#chapterFormAdd").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/insertChapter",
                    onSubmit: function () {
                        return true;
                    },
                    success: function () {
                        $("#addChapterDia").dialog("close");
                        $.messager.show({
                            title: "添加成功",
                            msg: "恭喜，添加成功"
                        });
                        $("#albumT").treegrid("reload")

                    }
                });
            }

        })

    })

</script>
<div>
    <div><p style="font-size: large">添加专辑</p></div>
    <div>
        <form id="chapterFormAdd" method="post" enctype="multipart/form-data">
            <table>

                <tr>
                    <td>
                        Title:
                    </td>
                    <td><input class="easyui-validatebox" data-options="required:true" name="title" type="text"/></td>
                </tr>
                <tr>
                    <td>
                        大小:
                    </td>
                    <td><input name="size" type="text" class="easyui-validatebox" data-options=""/></td>
                </tr>
                <tr>
                    <td>
                        文件:
                    </td>
                    <td><input name="file1" type="file"/></td>
                </tr>
                <tr>
                    <td>
                        专辑：
                    </td>
                    <td>
                        <input id="albumId" class="easyui-combobox" name="albumId" data-options="valueField:'id',textField:'title',url:'${pageContext.request.contextPath}/album/queryAllAlb'"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a id="submitChapter" class="easyui-linkbutton" href="#">保存</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>
</body>