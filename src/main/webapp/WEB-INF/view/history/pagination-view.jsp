<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="curParam" value="2" scope="request" />
    <c:set var="placeTitle" value="${ placeTitle }" scope="request" />
    <c:set var="isMoreThanZero" value="${ paginationCurrentPage - curParam > 0 }" scope="request"/>
    <c:set var="actualTotal" value="${ total - 1 }" scope="request"/>
    <c:set var="isLessThanMax" value="${ paginationCurrentPage + curParam < actualTotal }" scope="request"/>

    <c:if test="${ actualTotal > 0 }">
        <jsp:include page="../util_views/pagination/pagination-view.jsp"/>
    </c:if>
</body>
</html>