<%--
  Created by IntelliJ IDEA.
  User: santa.krauja
  Date: 06-Dec-17
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in with facebook:</title>
</head>
<body>
    <div>
        <form action="/connect/facebook" method="POST">
            <input type="hidden" name="scope" value="user_posts" />
            <div class="formInfo">
                <p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
            </div>
            <p><button type="submit">Connect to Facebook</button></p>
        </form>
    </div>

</body>
</html>
