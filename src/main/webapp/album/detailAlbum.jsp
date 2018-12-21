<%@page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<body>
<script>
    $(function () {
        $("#albumForm").form("load","${pageContext.request.contextPath}/album/queryAlbum?id=${param.id}"
        )
    })

</script>
<div>
<div><p style="font-size: large">专辑详情</p></div>
<div>
<form id="albumForm" method="post">
    <table>
        <tr>
            <td>
                ID:
            </td>
            <td><input name="id" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                Title:
            </td>
            <td><input name="title" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                CoverImg:
            </td>
            <td></td>
        </tr>
        <tr>
            <td>
                Score:
            </td>
            <td><input name="score" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                Author:
            </td>
            <td><input name="author" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                BroadCast:
            </td>
            <td><input name="broadcast" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                简介:
            </td>
            <td><input name="brief" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                集数:
            </td>
            <td><input name="count" type="text" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                PubDate:
            </td>
            <td><input class="easyui-datebox" name="pubDate"  readonly="true"/></td>
        </tr>
    </table>
</form>
</div>
</div>
</body>