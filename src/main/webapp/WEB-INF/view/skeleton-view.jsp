<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="util_views/page-paths-view.jsp" %>
<%@ include file="util_views/titles-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ pageTitle }</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-96x96.png" sizes="96x96" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-96x96.png" sizes="96x96" />
    <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/assets/favicon/favicon.svg" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/favicon/favicon.ico" />
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/assets/favicon/apple-touch-icon.png" />
    <link rel="manifest" href="/site.webmanifest" />
</head>
<body>
    <div class="skeleton">

        <header>
            <jsp:include page = "header-navbar-view.jsp" />

            <c:choose>
                <c:when test="${ pagePath == 'showCurrentPlace' }">
                    <%@ include file="current_place/search-param-selection.jsp" %>
                </c:when>
                <c:otherwise>
                    <jsp:include page = "util_views/search-setup-view.jsp">
                        <jsp:param name="altAction" value="${ altAction }" />
                    </jsp:include>
                </c:otherwise>
            </c:choose>
            
            <jsp:include page = "util_views/header-authorized-user-view.jsp" />
        </header>

        <main>
            <jsp:include page="${ currentPath }" />
        </main>
    
        <jsp:include page = "footer-view.jsp" />

    </div>
</body>
</html>