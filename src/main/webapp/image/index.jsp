<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<script>
    $(function () {
        $("#dbImg").datagrid({
            toolbar : "#tbImg",
            fitColumns : true,

            url : "${pageContext.request.contextPath}/image/queryImageByRow",
            pagePosition : "bottom",
            pagination : true,
            pageSize : 4,
            pageList : [ 4, 8, 10 ],
            frozenColumns:[[
                {field:'ck' , checkbox:true}
            ]],
            columns : [[{
                field : "id",
                title : "ID",
                width : 100,
                sortable :true,

            },{
                field : "title",
                title : "标题",
                width : 100,
                sortable :true,
                editor:{
                    type:'validatebox',
                    options:{
                        required:true,
                        missingMessage:'标题必填!'
                    }
                }
            },{
                field : "status",
                title : "状态",
                width : 100,
                sortable :true,
                formatter:function (value,row,index) {
                    if(value==1){
                        return "展示中";
                    }else {
                        return "未展示";
                    }
                },
                editor:{
                    type:'numberbox' ,
                    options:{
                        required:true ,
                        missingMessage:'状态必须填写' ,
                        min:0,
                        max:1 ,
                        precision:0
                    }
                }
            },{
                field:"updatetime",
                title:"时间",
                width:100,
                sortable:true,
                    editor:{
                        type:'datebox' ,
                            options:{
                            required:true ,
                                missingMessage:'上传时间必填!',
                                editable:false
                        }
                    }
            }]],
            view:detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/imageslun/' + rowData.imagepath + '" style="height:200px;"></td>' +
                    '<td style="border:0">' +
                    '<p>description: ' + rowData.description + '</p>' +
                    '<p>path: ' + rowData.imagepath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            onAfterEdit:function(index,data,changes)
            //data对应编辑完成后的行的记录
            //changes:更改后的字段值。
            

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