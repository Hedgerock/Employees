<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="utils/variables-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ pageTitle }</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="skeleton">

        <header>
            <jsp:include page = "../header-navbar-view.jsp" />
            <jsp:include page = "../util_views/search-setup-view.jsp" />
            <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
        </header>
    
        <main>
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
        </main>
    
        <jsp:include page = "../footer-view.jsp" />

    </div>
</body>
</html>