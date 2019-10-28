<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户详情页</title>
</head>
<body>
    <form method="post" action="update_account">
        <input type="hidden" name="id" value="${account.id}"/>
        <table width="100%" border="1">
            <tr>
                <td>用户名：</td><td><input type="text" name="name" value="${account.name}"/></td>
            </tr>
            <tr>
                <td>余额：</td><td><input type="text" name="money" value="${account.money}"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="保存"/></td>
            </tr>
        </table>
    </form>
</body>
</html>