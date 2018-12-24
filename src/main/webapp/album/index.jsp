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
            pagePosition: "bottom",
            pagination: true,
            pageNumber: 1,
            pageSize: 3,
            pageList: [3, 6, 9],
            columns: [[
                {title: "title", field: "title", width: 400},
                {title: "大小", field: "size", width: 100},
                {title: "时长", field: "duration", width: 100},
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
                        height: 650,
                        closed: false,
                        cache: false,
                        iconCls: "icon-man",
                        href: "${pageContext.request.contextPath}/album/detailAlbum.jsp?id=" + row.id,
                        modal: true,
                        buttons: [{
                            text: "关闭",
                            handler: function () {
                                $("#detailAlbumDia").dialog("close");
                            }
                        }]
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
                    modal: true,
                    buttons: [{
                        text:"保存",
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
                    },
                        {
                            text: "取消",
                            handler: function () {
                                $("#addAlbumDia").dialog("close")
                            }
                        }]
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
                    modal: true,
                    buttons: [{
                        text:"保存",
                        handler:function () {
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
                            })
                        }
                    },{
                        text: "取消",
                        handler: function x() {
                            $("#addChapterDia").dialog("close");
                        }
                    }]
                })
            }
        });
        $("#deleteAlbum").linkbutton({

            onClick: function () {
                $.messager.confirm('提示信息', '确认删除?', function (r) {
                    if (r) {
                        var album = $("#albumT").treegrid("getCheckedNodes");
                        var ids = '';
                        for (var j = 0; j < album.length; j++) {
                            if (album[j].children == null) {
                                ids += album[j].id + ',';
                                ids = ids.substring(0, ids.length - 1);
                            }
                            if (album[j].children != null) {
                                $.post('${pageContext.request.contextPath}/album/deleteAlbum', {id: album[j].id}, function () {

                                });
                            }
                            ;
                            if (j == (album.length - 1)) {
                                $('#dbImg').datagrid('reload');
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '操作成功!'
                                });
                            }
                        }
                        ;
                        $.post('${pageContext.request.contextPath}/chapter/deleteChapter', {ids: ids}, function () {


                        });
                    }
                })


            }
        });
        $("#load").click(function (e) {
            e.preventDefault();
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/chapter/downT",
                dataType: "json",
                data: {},
                success: function () {
                    var album = $("#albumT").treegrid("getCheckedNodes");
                    var bs="";
                    if (album.length==0){
                        alert("请选择要下载的文件！");
                    }
                    else if (album.length>1){
                        alert("最多下载一个文件")
                    }else{
                    for(var j=0;j<album.length;j++){
                        if (album[j].children==null){
                            bs+=album[j].url+",";
                            bs=bs.substring(0,bs.length-1);
                        }

                    }
                        $.download("${pageContext.request.contextPath}/chapter/downAudio", "post", bs)//下载文件
                    }

                }
            })


        })
        //导出Excel表格
        $("#import").click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/chapter/downT",
                    dataType: "json",
                    data: {},
                    success:function () {
                        $.download("${pageContext.request.contextPath}/chapter/importAlbumExcel","post","")

                    }
                })
            })




    });
    //定义下载函数
    $.download = function (url, method, fileDire) {
        var form = jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' +  // action请求路径及推送方法
            '<input type="text" name="filePath" value="' + fileDire + '"/>' + // 文件路径
            '</form>');
        $(document.body).append(form);
        form.submit().remove();
    }

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
    <a id="load" class="easyui-linkbutton" href="#" data-options="iconCls:'icon-add',plain:true">下载</a>
    <a id="import"  class="easyui-linkbutton" href="#" data-options="iconCls:'icon-save',plain:true"> 导出</a>
</div>
<div id="addAlbumDia"></div>
<div id="addChapterDia"></div>
<div id="detailAlbumDia"></div>
<div id="updateAlbumDia"></div>
</body>