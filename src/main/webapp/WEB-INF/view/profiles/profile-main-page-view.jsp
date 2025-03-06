<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="current-user">
        <div class="user-img">
            <i class="fa-solid fa-user"></i>
        </div>
        <div class="description-content">
            <span class="current-user__value">${ user }</span>
            <div class="roles">
                <h3>${ userDetails.authorities.size() > 1 ? 'Roles:' : 'Role:' }</h3>
                <c:forEach var="authority" items="${ userDetails.authorities }">
                    <span class="roles__value">${ authority }</span>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>