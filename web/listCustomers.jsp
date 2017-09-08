<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2017/9/6
  Time: 下午2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>客户信息列表</title>


    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <style type="text/css">
        .odd{
            background-color: antiquewhite;
        }
        .even{
            background-color: beige;
        }
    </style>

</head>
<body style="font-size: 12px">

<form action="${pageContext.request.contextPath}/servlet/Controller?op=delmulti" method="post">
    <table width="88%">
        <tr>
            <td>
                <a href="addCustomer.jsp"> 添加 </a>
                <a href="" > 删除 </a>
            </td>
        </tr>

        <tr>
            <td>
                <c:if test="${empty page.records}">
                    没有客户信息
                </c:if>

                <c:if test="${!empty page.records}">
                    <h1>客户信息列表</h1>
                    <table width="100%" border="1">
                        <tr>
                            <th nowrap="nowrap">选择</th>
                            <th nowrap="nowrap">姓名</th>
                            <th nowrap="nowrap">性别</th>
                            <th nowrap="nowrap">生日</th>
                            <th nowrap="nowrap">电话</th>
                            <th nowrap="nowrap">邮箱</th>
                            <th nowrap="nowrap">爱好</th>
                            <th nowrap="nowrap">类型</th>
                            <th nowrap="nowrap">描述</th>
                            <th nowrap="nowrap">操作</th>
                        </tr>
                        <c:forEach items="${page.records}" var="c" varStatus="vs">
                            <tr class="${vs.index%2==0?'odd':'even'}">
                                <td nowrap="nowrap">
                                    <input type="checkbox" name="ids" value="${c.id}">
                                </td>
                                <td nowrap="nowrap">${c.name}</td>
                                <td nowrap="nowrap">${c.gender=='male'?'男':'女'}</td>
                                <td nowrap="nowrap">${c.birthday}</td>
                                <td nowrap="nowrap">${c.cellphone}</td>
                                <td nowrap="nowrap">${c.email}</td>
                                <td nowrap="nowrap">${c.hobby}</td>
                                <td nowrap="nowrap">${c.type}</td>
                                <td nowrap="nowrap">${c.description}</td>
                                <td nowrap="nowrap">
                                    <a href="servlet/Controller?op=editUI&customerId=${c.id}">修改</a>
                                    <a href="">删除</a>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </td>
        </tr>
    </table>

    <%@include file="commons/page.jsp"%>

</form>

<script type="text/javascript">
    function deleOne(customerID) {
        var sure = window.confirm("确定要删除么?")
        if (sure){
            window.location.href = "${pageContext.request.contextPath}/servlet/Controller?op=deleOne&customerId="+customerID;
        }
    }
</script>

</body>
</html>
