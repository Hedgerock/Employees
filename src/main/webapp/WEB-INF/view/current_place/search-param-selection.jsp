<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ page == 'showCurrentCity' }">
            <jsp:include page = "util_views/search-setup-view.jsp">
                <jsp:param name = "cityId" value = "${ idValue }" />
            </jsp:include>
        </c:when>
        <c:when test="${ page == 'showCurrentDepartment' }">
            <jsp:include page = "util_views/search-setup-view.jsp">
                <jsp:param name = "depId" value = "${ idValue }" />
            </jsp:include>
        </c:when>
        <c:when test="${ page == 'showCurrentNationality' }">
            <jsp:include page = "util_views/search-setup-view.jsp">
                <jsp:param name = "natId" value = "${ idValue }" />
            </jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page = "util_views/search-setup-view.jsp" />
        </c:otherwise>
    </c:choose>
</body>
</html>