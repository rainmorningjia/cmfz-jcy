<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<script>
    $(function () {
        var editing; //判断用户是否处于编辑状态
        var flag;      //判断新增和修改方法
        $("#addImg").linkbutton({
            onClick: function () {
                $("#dialogImg").dialog({
                    title: "添加轮播图",
                    width: 500,
                    height: 300,
                    closed: false,
                    cache: false,
                    iconCls: "icon-add",
                    href: "${pageContext.request.contextPath}/image/addImge.jsp",
                    modal: true
                })
            }
        })
        //点击更新事件按钮
        $("#updateImg").linkbutton({
            onClick: function () {

                var arr = $('#dbImg').datagrid('getSelections');
                if (arr.length != 1) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '只能选择一条记录进行修改!'
                    });
                } else {
                    if (editing == undefined) {
                        flag = 'edit';
                        //根据行记录对象获取该行的索引位置
                        editing = $('#dbImg').datagrid('getRowIndex', arr[0]);
                        //开启编辑状态
                        $('#dbImg').datagrid('beginEdit', editing);
                    }
                }
            }
        });
        //点击删除事件
        $("#deleteImg").linkbutton({
            onClick: function () {
                var arr = $('#dbImg').datagrid('getSelections');
                if (arr.length <= 0) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选择进行删除操作!'
                    });
                } else {
                    $.messager.confirm('提示信息', '确认删除?', function (r) {
                        if (r) {
                            var ids = '';
                            for (var i = 0; i < arr.length; i++) {
                                ids += arr[i].id + ',';
                            }
                            ids = ids.substring(0, ids.length - 1);
                            $.post('${pageContext.request.contextPath}/image/deleteImage', {ids: ids}, function () {
                                $('#dbImg').datagrid('reload');
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '操作成功!'
                                });
                            });
                        } else {
                            return;
                        }
                    });
                }
            }
        })
        //点击保存按钮事件
        $("#saveImg").linkbutton({
            onClick: function () {
                //保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段
                if ($('#dbImg').datagrid('validateRow', editing)) {
                    $('#dbImg').datagrid('endEdit', editing);
                    editing = undefined;
                }
            }
        })
        //初始化数据表格
        $("#dbImg").datagrid({
            toolbar: "#tbImg",
            fitColumns: true,
            url: "${pageContext.request.contextPath}/image/queryImageByRow",
            pagePosition: "bottom",
            pagination: true,
            pageSize: 4,
            cash:false,
            pageList: [4, 8, 10],
            frozenColumns: [[
                {field: 'ck', checkbox: true}
            ]],
            columns: [[{
                field: "id",
                title: "ID",
                width: 100,
                sortable: true,

            }, {
                field: "title",
                title: "标题",
                width: 100,
                sortable: true,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                        missingMessage: '标题必填!'
                    }
                }
            }, {
                field: "status",
                title: "状态",
                width: 100,
                sortable: true,
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "展示中";
                    } else {
                        return "未展示";
                    }
                },
                editor: {
                    type: 'numberbox',
                    options: {
                        required: true,
                        missingMessage: '状态必须填写',
                        min: 0,
                        max: 1,
                        precision: 0
                    }
                }
            }, {
                field: "publish_time",
                title: "时间",
                width: 100,
                sortable: true,
                editor: {
                    type: 'datebox',
                    options: {
                        required: true,
                        missingMessage: '上传时间必填!',
                        editable: false
                    }
                }
            }]],
            view: detailview,
            //细节展示表
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="http://192.168.73.136/'+rowData.groupname+'/' + rowData.urlname + '" style="height:200px;"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:20px">description: ' + rowData.description + '</p>' +
                    '<p style="font-size:20px">path: ' + rowData.imagepath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            onAfterEdit: function (index, data, changes) {

                //data对应编辑完成后的行的记录
                //changes:更改后的字段值。
                console.info(changes);
                console.info(data);
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/image/updateImage",
                    data: {
                        "id": data.id,
                        "title": data.title,
                        "status": data.status,
                        "publishTime": data.publishTime,
                        "imagepath": data.imagepath,
                        "description": data.description
                    },
                    dataType: "json",
                    success: function () {
                        $.messager.show({
                            title: '提示信息',
                            msg: '操作成功!'
                        });
                    }
                })
            }


        })
    })
</script>
<div>
    <h1 align="center">轮播图管理</h1>
    <table id="dbImg"></table>
    <div id="tbImg">
        <a id="addImg" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-add',plain:true">添加</a>
        <a id="updateImg" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-edit',plain:true">修改</a>
        <a id="deleteImg" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-remove',plain:true">删除</a>
        <a id="saveImg" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-save',plain:true">保存</a>
    </div>
    <div id="dialogImg"></div>
</div>