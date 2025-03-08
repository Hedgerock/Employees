<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="utils/variables-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="profile-content">
        <jsp:include page="utils/profile-menu-view.jsp">
            <jsp:param name="mainPageMod" value="${ mainPageMod }" />
            <jsp:param name="adminPageMod" value="${ adminPageMod }" />
            <jsp:param name="settingsPageMod" value="${ settingsPageMod }" />
            <jsp:param name="mainLink" value="${ mainLink }" />
            <jsp:param name="adminLink" value="${ adminLink }" />
            <jsp:param name="settingsLink" value="${ settingsLink }" />
        </jsp:include>

        <div class="profile-current-page">
            <jsp:include page="${ actualContent }" />
        </div>
    </div>
</body>
</html>