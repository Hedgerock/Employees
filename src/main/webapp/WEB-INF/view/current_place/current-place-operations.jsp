<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="dto" value="${ not empty showPlacesAttributes ? showPlacesAttributes : showAttributes }" />
    <c:set var="id" value="${ not empty idValue ? idValue : currentPlace.id }" />
    <c:set var="isZero" value="${ empty currentPlace.totalEmployees }" />
    <c:set var="value" value="employee${ currentPlace.totalEmployees == 1 ? '' : 's' }" />

    <c:choose>
        <c:when test="${ dto.placeTitle == 'cityId' }">
            <c:url var="updateButton" value="/updateCity">
                <c:param name="cityId" value="${ id }" />
            </c:url>
            <c:url var="showPlace" value="/showCurrentCity">
                <c:param name="cityId" value="${ id }" />
            </c:url>
            <c:url var="deleteButton" value="/deleteCity">
                <c:param name="cityId" value="${ id }" />
            </c:url>
            <c:url var="showHistoryButton" value="/showCityHistory">
                <c:param name="cityId" value="${ id }" />
            </c:url>
        </c:when>

        <c:when test="${ dto.placeTitle == 'natId' }">
            <c:url var="updateButton" value="/updateNationality">
                <c:param name="natId" value="${ id }" />
            </c:url>
            <c:url var="showPlace" value="/showCurrentNationality">
                <c:param name="natId" value="${ id }" />
            </c:url>
            <c:url var="deleteButton" value="/deleteNationality">
                <c:param name="natId" value="${ id }" />
            </c:url>
            <c:url var="showHistoryButton" value="/showNationalityHistory">
                <c:param name="natId" value="${ id }" />
            </c:url>
        </c:when>

        <c:when test="${ dto.placeTitle == 'depId' }">
            <c:url var="updateButton" value="/updateCurrentDepartment">
                <c:param name="depId" value="${ id }" />
            </c:url>
            <c:url var="showPlace" value="/showCurrentDepartment">
                <c:param name="depId" value="${ id }" />
            </c:url>
            <c:url var="deleteButton" value="/deleteCurrentDepartment">
                <c:param name="depId" value="${ id }" />
            </c:url>
            <c:url var="showHistoryButton" value="/showDepartmentHistory">
                <c:param name="depId" value="${ id }" />
            </c:url>
        </c:when>

    </c:choose>

</body>
</html>