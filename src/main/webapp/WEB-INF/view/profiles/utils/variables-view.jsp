<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="actualContent" value="profile-${ pageName }-page-view.jsp" />
    <!--Mod - modificator-->
    <c:set var="isMainPage" value="${ pageName == 'main'}" />
    <c:set var="isAdminPage" value="${ pageName == 'admins'}" />
    <c:set var="isSettingsPage" value="${ pageName == 'settings'}" />

    <c:set var="mainPageMod" value="${ isMainPage ? 'profile-menu__item_active' : '' }" />
    <c:set var="adminPageMod" value="${ isAdminPage ? 'profile-menu__item_active' : '' }" />
    <c:set var="settingsPageMod" value="${ isSettingsPage ? 'profile-menu__item_active' : '' }" />

    <c:url var="mainLink" value="/profile" />
    <c:url var="adminLink" value="/admins" />
    <c:url var="settingsLink" value="/settings" />

</body>
</html>