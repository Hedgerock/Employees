<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="isEnabled" value="${ curUser.enabled }" />
    <c:set var="username" value="${ curUser.username }" />
    <c:set var="isYou" value="${ username == user }" />
    <c:set var="index" value="${ status.index + 1 + paginationCurrentPage * pageSize }." />
    <c:set 
        var="curClassName" 
        value="user-content__value ${ isEnabled ? 'user-content__value_active' :'user-content__value_inactive'} ${ isYou ? 'user-content__value_you' : '' }"
        />
    <c:set var="title" value="${ isEnabled ? 'active' : 'inactive' }" />
</body>
</html>