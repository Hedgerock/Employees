<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="placeTitle" value="${ param.placeTitle }" />
    <c:set var="placeId" value="${ param.placeValue }" />

    <jsp:include page="${ param.pagePath }">
        <jsp:param name = "page" value = "${ param.pageValue }" />
    </jsp:include>

    <c:url var = "createEmployee" value = "/addNewEmployee">
        <c:param name = "${ placeTitle }" value = "${ placeId }"/>
    </c:url>

    <c:url var = "clearHref" value = "${ param.clearHref }">
        <c:param name = "${ placeTitle }" value = "${ placeId }"/>
    </c:url>

    <jsp:include page="../util_views/options-view.jsp">
        <jsp:param name="hasParams" value="${ hasParams }" />
        <jsp:param name="clearHref" value="${ clearHref }" />
        <jsp:param name="addHref" value="${ createEmployee }" />
    </jsp:include>

    <c:choose>
        <c:when test="${ placeTitle == 'natId' }">
            <jsp:include page = "../employees/employees-view.jsp">
                <jsp:param name = "page" value = "${ param.pageValue }" />
                <jsp:param name = "natId" value = "${ placeId }" />
            </jsp:include>
        </c:when>
        <c:when test="${ placeTitle == 'cityId' }">
            <jsp:include page = "../employees/employees-view.jsp">
                <jsp:param name = "page" value = "${ param.pageValue }" />
                <jsp:param name = "cityId" value = "${ placeId }" />
            </jsp:include>
        </c:when>
        <c:when test="${ placeTitle == 'depId' }">
            <jsp:include page = "../employees/employees-view.jsp">
                <jsp:param name = "page" value = "${ param.pageValue }" />
                <jsp:param name = "depId" value = "${ placeId }" />
            </jsp:include>
        </c:when>
    </c:choose>
</body>
</html>