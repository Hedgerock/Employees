<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${not empty depId || not empty param.depId}">
            <c:set var="actualDepartmentValue" value="${not empty depId ? depId : param.depId}" />
            <jsp:include page="../form_views/find-employee-form-view.jsp">
                <jsp:param name="placeHolder" value="Find employees by current department" />
                <jsp:param name="action" value="/showCurrentDepartment" />
                <jsp:param name="depId" value="${actualDepartmentValue}" />
            </jsp:include>
        </c:when>
        <c:when test="${not empty cityId || not empty param.cityId}">
            <c:set var="actualCityValue" value="${not empty cityId ? cityId : param.cityId}" />
            <jsp:include page="../form_views/find-employee-form-view.jsp">
                <jsp:param name="placeHolder" value="Find employees by current city" />
                <jsp:param name="action" value="/showCurrentCity" />
                <jsp:param name="cityId" value="${ actualCityValue }" />
            </jsp:include>
        </c:when>
        <c:when test="${not empty natId || not empty param.natId}">
            <c:set var="actualNationalityValue" value="${not empty natId ? natId : param.natId}" />
            <jsp:include page="../form_views/find-employee-form-view.jsp">
                <jsp:param name="placeHolder" value="Find employees by current nationality" />
                <jsp:param name="action" value="/showCurrentNationality" />
                <jsp:param name="natId" value="${ actualNationalityValue }" />
            </jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="../form_views/find-employee-form-view.jsp">
                <jsp:param name="placeHolder" value="${ not empty param.altAction ? 'Find fired employees' : 'Find employees' }" />
                <jsp:param name="action" value="/" />
                <jsp:param name="altAction" value="${ param.altAction }" />
            </jsp:include>
        </c:otherwise>
    </c:choose>
</body>
</html>