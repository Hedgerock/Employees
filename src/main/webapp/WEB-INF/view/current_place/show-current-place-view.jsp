<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ showAttributes.title }</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        
        <div class="skeleton">
            <header>
                <jsp:include page = "../header-navbar-view.jsp">
                    <jsp:param name = "page" value = "${ page }"/>
                </jsp:include>

                <%@ include file="../current_place/search-param-selection.jsp" %>
                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
                <div class="main-container">
                    <jsp:include page="../util_views/current-place-content.jsp">
                        <jsp:param name="pageValue" value="${ showAttributes.page }"/>
                        <jsp:param name="pagePath" value="../current_place/current-places-content-table-view.jsp"/>
                        <jsp:param name="placeTitle" value="${ showAttributes.placeTitle }"/>
                        <jsp:param name="placeValue" value="${ showAttributes.idValue }"/>
                        <jsp:param name="clearHref" value="/${ showAttributes.page }"/>
                    </jsp:include>
                </div>
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>