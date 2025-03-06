<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<%@ include file="variables-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
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
                <jsp:include page="../employee_details/user_options/user-options-view.jsp" />
                <jsp:include page="../employee_details/details/details-view.jsp" />
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>