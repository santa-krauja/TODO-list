<%@include file="includes/header.jsp" %>

<body>
<script src='http://connect.facebook.net/en_US/all.js'></script>
<h3>Hello, <span><c:out value="${facebookProfile.name}">Some User</c:out></span>!</h3>

<h4>Here is your feed:</h4>

<div c:each="post:${feed}">
    <img c:if="${post.picture}" c:src="${post.picture}"/>
    <hr/>
</div>

<div>
    <table>
        <c:if test="${friends.size() == 0}">No friends found :(</c:if>
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td><c:out value="${friend.name}">from</c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
