<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ showPlacesAttributes.title }</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="../util_views/options-view.jsp">
            <jsp:param name="addHref" value="${ showPlacesAttributes.addHref }" />
            <jsp:param name="textContent" value="${ showPlacesAttributes.buttonContent }" />
        </jsp:include>

        <jsp:include page="current-places-content-table-view.jsp" />
            
        <c:set var="curParam" value="2" scope="request" />
        <c:set var="placeTitle" value="${ placeTitle }" scope="request" />
        <c:set var="isMoreThanZero" value="${ currentPage - curParam > 0 }" scope="request"/>
        <c:set var="actualTotal" value="${ total - 1 }" scope="request"/>
        <c:set var="isLessThanMax" value="${ currentPage + curParam < actualTotal }" scope="request"/>

        <c:if test="${ actualTotal > 0 }">
            <jsp:include page="../util_views/pagination/pagination-view.jsp"/>
        </c:if>
    </body>
</html>