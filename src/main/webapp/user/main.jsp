<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<script>
    $(function () {

        //初始化数据表格
        $("#dbUser").datagrid({
            toolbar: "#tbUser",
            fitColumns: true,
            url: "${pageContext.request.contextPath}/user/queryUsersByRows",
            pagePosition: "bottom",
            pagination: true,
            pageSize: 4,
            cash: false,
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
                field: "name",
                title: "姓名",
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
                field: "reg_date",
                title: "时间",
                width: 100,
                sortable: true,
            },{
                field:"sign",
                title:"标签",
                width:100
            },{
                field:"dharma",
                title:"大师",
                width:100
            },{
                field:"sex",
                title:"性别",
                width:100
            },{
                field:"status",
                title:"激活状态",
                width:100,
                formatter:function (value,row,index) {
                    if(value==1){
                        return "已激活";
                    }else {
                        return "未激活";
                    }
                }
            }]],
            view: detailview,
            //细节展示表
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                /*    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/imageslun/' + rowData.imagepath + '" style="height:200px;"></td>' +*/
                    '<td style="border:0">' +
                    '<p style="font-size:20px">省份: ' + rowData.province + '</p>' +
                    '<p style="font-size:20px">城市: ' + rowData.city + '</p>' +
                    '<p style="font-size:20px">地址: ' + rowData.address + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
        var editing; //判断用户是否处于编辑状态
        var flag;      //判断新增和修改方法
        $("#addUser").linkbutton({
            onClick: function () {
                $("#dialogUser").dialog({
                    title: "添加用户",
                    width: 500,
                    height: 300,
                    closed: false,
                    cache: false,
                    iconCls: "icon-add",
                    href: "${pageContext.request.contextPath}/user/addUser.jsp",
                    modal: true
                })
            }
        })
        //点击删除事件
        $("#deleteUser").linkbutton({
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

    })
</script>
<div>
    <h1 align="center">用户管理</h1>
    <table id="dbUser"></table>
    <div id="tbUser">
        <a id="addUser" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-add',plain:true">添加</a>
        <a id="updateUser" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-edit',plain:true">修改</a>
        <a id="deleteUser" class="easyui-linkbutton" href="#"
           data-options="iconCls:'icon-remove',plain:true">删除</a>
    </div>
    <div id="dialogUser"></div>
</div>