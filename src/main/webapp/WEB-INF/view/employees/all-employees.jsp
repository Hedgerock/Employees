<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
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
    </body>
</html>