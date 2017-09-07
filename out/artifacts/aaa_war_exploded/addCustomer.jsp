<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2017/9/6
  Time: 下午3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head >
    <meta charset="utf-8">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>添加新客户</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/servlet/Controller?op=addCustomer" method="post">
    <table border="1" width="438">
        <tr>
            <td>姓名:</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <input type="radio" name="gender" value="male" checked="checked"> 男
                <input type="radio" name="gender" value="female"> 女
            </td>
        </tr>
        <tr>
            <td>生日(yy-MM-dd:)</td>
            <td>
                <input name="birthday">
            </td>
        </tr>

        <tr>
            <td>电话:</td>
            <td>
                <input name="cellphone">
            </td>
        </tr>

        <tr>
            <td>邮箱:</td>
            <td>
                <input name="email">
            </td>
        </tr>

        <tr>
            <td>爱好:</td>
            <td>
                <input type="checkbox" name="hobbies" value="篮球">篮球
                <input type="checkbox" name="hobbies" value="排球">排球
                <input type="checkbox" name="hobbies" value="足球"> 足球
            </td>
        </tr>

        <tr>
            <td>类型:</td>
            <td>
                <input type="radio" name="type" value="VIP" checked>VIP
                <input type="radio" name="type" value="VVIP">VVIP
            </td>
        </tr>

        <tr>
            <td>描述:</td>
            <td>
                <textarea rows="3" cols="38" name="description"></textarea>
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
