<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ not empty altAction ? 'Fired employees page: ' : '' } All employees</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="skeleton">
            <header>
                <jsp:include page = "../header-navbar-view.jsp">
                    <jsp:param name = "page" value = "homePage"/>
                </jsp:include>
                
                <c:choose>
                    <c:when test="${ not empty altAction }">
                        <jsp:include page = "../util_views/search-setup-view.jsp">
                            <jsp:param name="altAction" value="${ altAction }" />
                        </jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page = "../util_views/search-setup-view.jsp" />
                    </c:otherwise>
                </c:choose>

                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
                <c:choose>
                    <c:when test="${ not empty generalInfo }">
                        <jsp:include page="general-info-view.jsp" />
                    </c:when>
                </c:choose>
                
                <c:set var="altActionPresent" value="${ not empty altAction }" />

                <jsp:include page="../util_views/options-view.jsp">
                    <jsp:param name="hasParams" value="${ hasParams }" />
                    <jsp:param name="clearHref" value="${ altActionPresent ? altAction : '/' }" />
                    <jsp:param name="backHref" value="${ altActionPresent ? '/' : '' }" />
                    <jsp:param name="addHref" value="${ altActionPresent ? '' : 'addNewEmployee' }" />
                </jsp:include>

                <jsp:include page = "employees-view.jsp" />
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>