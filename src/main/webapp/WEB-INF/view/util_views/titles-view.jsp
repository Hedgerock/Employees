<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="pageName" value="${ not empty contentPage ? contentPage : 'main' }" scope="request"/>
    
    <c:choose>
        <c:when test="${ empty pageTitle }">
            <c:set var="pageTitle" value="${ user } profile: ${ pageName } page" scope="request"/>
        </c:when>
    </c:choose>

    <c:set var="userMode" value="${ mode ? 'dark' : 'light' }" />

    <c:choose>
        <c:when test="${ not empty showAttributes.title }">
            <c:set var="actTitle" value="${ showAttributes.title }" />
        </c:when>
        <c:when test="${ not empty showPlacesAttributes.title }">
            <c:set var="actTitle" value="${ showPlacesAttributes.title }" />
        </c:when>
        <c:otherwise>
            <c:set var="actTitle" value="${ pageTitle }" />
        </c:otherwise>
    </c:choose>
</body>
</html>