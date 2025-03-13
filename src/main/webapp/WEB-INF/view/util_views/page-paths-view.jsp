<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ pagePath == 'profiles' }">
            <c:set var="currentPath" value="profiles/my-profile-view.jsp" />
        </c:when>

        <c:when test="${ pagePath == 'employees' }">
            <c:set var="currentPath" value="employees/all-employees.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'updateEmployee' }">
            <c:set var="currentPath" value="employees/current-employee-details.jsp" />
        </c:when>

        <c:when test="${ pagePath == 'currentPlace' }">
            <c:set var="currentPath" value="current_place/current-place-info.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'showCurrentPlace' }">
            <c:set var="currentPath" value="current_place/show-current-place-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'currentPlaces' }">
            <c:set var="currentPath" value="current_place/current-places-view.jsp" />
        </c:when>

        <c:when test="${ pagePath == 'showCurrentEmployeeDetails' }">
            <c:set var="currentPath" value="employee_details/show-current-employee-details-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'updateDetails' }">
            <c:set var="currentPath" value="employee_details/update-details-info-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'updateImage' }">
            <c:set var="currentPath" value="employee_details/update-image-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'updateSocialMedia' }">
            <c:set var="currentPath" value="employee_details/update-social-media-view.jsp" />
        </c:when>

        <c:when test="${ pagePath == 'currentPlaceList' }">
            <c:set var="currentPath" value="history/history-current-place-list-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'historyCurrentPlace' }">
            <c:set var="currentPath" value="history/history-current-place-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'historyList' }">
            <c:set var="currentPath" value="history/history-list-view.jsp" />
        </c:when>
        <c:when test="${ pagePath == 'history' }">
            <c:set var="currentPath" value="history/history-view.jsp" />
        </c:when>

        <c:when test="${ pagePath == 'notFoundPage' }">
            <c:set var="currentPath" value="util_views/not-found-view.jsp" />
        </c:when>
    </c:choose>
</body>
</html>