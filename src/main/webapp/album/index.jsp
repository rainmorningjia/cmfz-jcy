<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script>

    $(function () {

        $("#albumT").treegrid({
            url: "${pageContext.request.contextPath}/album/queryAllAlbum",
            idField: "id",
            treeField: "title",
            toolbar: "#tbAlbum",
            fitColumns: true,
            fit: true,

            columns: [[
                {title: "title", field: "title", width: 400},
                {title: "Size", field: "size", width: 100},
                {title: "Duration", field: "duration", width: 100},
                {title: "上传时间", field: "uploadDate", width: 200},
                {
                    title: "音频", field: "url", width: 200,
                    formatter: function (value, row, index) {
                        if (value != null) {
                            return "<audio style='height:50px' controls src='${pageContext.request.contextPath}/audio/" + value + "'>";
                        }
                    }
                },
            ]],
            onlyLeafCheck: true,
            checkbox: function (row) {

                return true;


            },
            onClickRow: function (row) {
                if (row.albumId == null) {
                    $("#detailAlbumDia").dialog({
                        title: "专辑细节展示",
                        width: 500,
                        height: 300,
                        closed: false,
                        cache: false,
                        iconCls: "icon-man",
                        href: "${pageContext.request.contextPath}/album/detailAlbum.jsp?id=" + row.id,
                        modal: true
                    })
                }
            }
            //细节展示表
            /*    view: detailview,
                detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="cmfz/imageslun/' + rowData.coverImg + '" style="height:200px;"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:20px">Brief: ' + rowData.brief + '</p>' +
                    '<p style="font-size:20px">Author: ' + rowData.author + '</p>' +
                    '<p style="font-size:20px">score:'+rowData.score+'</p>'+
                    '</td>' +
                '</tr></table>';
        }*/
        })
        //添加专辑
        $("#addAlbum").linkbutton({
            onClick: function () {
                $("#addAlbumDia").dialog({
                    title: "专辑添加",
                    width: 500,
                    height: 300,
                    closed: false,
                    cache: false,
                    iconCls: "icon-add",
                    href: "${pageContext.request.contextPath}/album/addAlbum.jsp",
                    modal: true
                })
            }
        })
        //添加章节
        $("#addChapter").linkbutton({
            onClick: function () {
                $("#addChapterDia").dialog({
                    title: "添加章节",
                    width: 500,
                    height: 300,
                    closed: false,
                    cache: false,
                    iconCls: "icon-add",
                    href: "${pageContext.request.contextPath}/album/addChapter.jsp",
                    modal: true
                })
            }
        });
        $("#deleteAlbum").linkbutton({
            onClick: function () {
                var album = $("#albumT").treegrid("getCheckedNodes");
                console.info(album);
                var ids = '';
                for (var j = 0; j < album.length; j++) {
                    if (album[j].children == null) {
                        ids += album[j].id + ',';
                        ids = ids.substring(0, ids.length - 1);
                        alert(ids);
                    }
                    if (album[j].children!= null) {
                        $.post('${pageContext.request.contextPath}/album/deleteAlbum', {id: album[j].id}, function () {

                        });
                    }
                    ;
                    if (j==album.length-1) {
                        $('#dbImg').datagrid('reload');
                        $.messager.show({
                            title: '提示信息',
                            msg: '操作成功!'
                        });
                    }
                };
                $.post('${pageContext.request.contextPath}/chapter/deleteChapter', {ids: ids}, function () {


                });

            }
        })
    })
</script>
<table id="albumT"></table>
<div id="tbAlbum">
    <a id="addAlbum" class="easyui-linkbutton" href="#"
       data-options="iconCls:'icon-add',plain:true">添加专辑</a>
    <a id="addChapter" class="easyui-linkbutton" href="#"
       data-options="iconCls:'icon-add',plain:true">添加章节</a>
    <a id="updateAlbum" class="easyui-linkbutton" href="#"
       data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a id="deleteAlbum" class="easyui-linkbutton" href="#"
       data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a id="saveAlbum" class="easyui-linkbutton" href="#"
       data-options="iconCls:'icon-save',plain:true">保存</a>
    <a id="load" class="easyui-linkbutton" href="#" data-options="iconCls:'icon-add',plain:true">下载</a>
</div>
<div id="addAlbumDia"></div>
<div id="addChapterDia"></div>
<div id="detailAlbumDia"></div>
<div id="updateAlbumDia"></div>
</body>