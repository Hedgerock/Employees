<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Page not found</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="skeleton">
            <header>
                <jsp:include page = "/WEB-INF/view/header-navbar-view.jsp">
                    <jsp:param name = "page" value = "notFoundPage"/>
                </jsp:include>
                
                <jsp:include page = "../util_views/search-setup-view.jsp" />
                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
                <div class="not-found-container">
                    <div class="error-img">
                        <img
                            class="error-img__src"
                            src="${ pageContext.request.contextPath }/assets/error.png" 
                            alt=""
                        >
                    </div>

                    <a 
                        class="not-found-container__link"
                        href="/" 
                    >
                        Return to the main page
                    </a>
                </div>
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>