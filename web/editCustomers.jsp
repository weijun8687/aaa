<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2017/9/7
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head >
    <meta charset="utf-8">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>修改客户信息</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/servlet/Controller?op=editCustomer" method="post">
    <table border="1" width="438">
        <tr>
            <td>姓名:</td>
            <td>
                <input name="name" value="${c.name}">
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <%--<input type="radio" name="gender" value="male" ${c.gender=='male'?'checked="checked"':'' }> 男--%>
                <%--<input type="radio" name="gender" value="female" ${c.gender=='female'?'checked="checked"':''}> 女--%>
            </td>
        </tr>
        <tr>
            <td>生日(yy-MM-dd:)</td>
            <td>
                <input name="birthday" value="${c.birthday}">
            </td>
        </tr>

        <tr>
            <td>电话:</td>
            <td>
                <input name="cellphone" value="${c.cellphone}">
            </td>
        </tr>

        <tr>
            <td>邮箱:</td>
            <td>
                <input name="email" value="${c.email}">
            </td>
        </tr>

        <tr>
            <td>爱好:</td>
            <td>
                <%--<input type="checkbox" name="hobbies" value="篮球" ${fn:contains(c.hobby,'篮球')?'checked="checked"':''}>篮球--%>
                <%--<input type="checkbox" name="hobbies" value="排球" ${fn:contains(c.hobby,'排球')?'checked="checked"':''}>排球--%>
                <%--<input type="checkbox" name="hobbies" value="足球" ${fn:contains(c.hobby,'足球')?'checked="checked"':''}> 足球--%>
            </td>
        </tr>

        <tr>
            <td>类型:</td>
            <td>
                <%--<input type="radio" name="type" value="VIP" ${c.type="='VIP'?'checked=checked':''"}>VIP--%>
                <%--<input type="radio" name="type" value="VVIP" ${c.type="='VIP'?'checked=checked':''"}>VVIP--%>
            </td>
        </tr>

        <tr>
            <td>描述:</td>
            <td>
                <textarea rows="3" cols="38" name="description">${c.description}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="保存">
            </td>
        </tr>
    </table>

</form>

</body>
</html>
