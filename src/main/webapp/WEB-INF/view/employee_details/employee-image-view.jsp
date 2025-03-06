<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>

    <c:choose>
        <c:when test="${ not empty slider }">
            <c:set var="layoutTitle" value="Show ${ employee.firstName } pictures" />
        </c:when>
        <c:otherwise>
            <c:set var="layoutTitle" value="Images not found" />
        </c:otherwise>
    </c:choose>

    <div class="details-header-image">
        <div class="details-header-image__content">
            <c:set var="imgSrc" value = "${ employee.employeeDetails.picture.pictureSrc }" />
            <c:choose>
                <c:when test = "${ imageExists }">
                    <img src="${pageContext.request.contextPath}/tmp/${ imgSrc }" alt="">
                </c:when>
                <c:otherwise>
                    <i class="fa-solid fa-user"></i>
                </c:otherwise>
            </c:choose>
            <div title="${ layoutTitle }" class="image-layout"></div>
        </div>

        <c:url var="update" value="/update">
            <c:param name="empId" value="${ employee.id }" />
        </c:url>
        
        <c:choose>
            <c:when test="${ not isFired }">
                <a 
                    title="Change image"
                    class="details-header-image__update-link" 
                    href="${ update }"
                >
                    <i class="fa-solid fa-gear"></i>
                </a>
            </c:when>
        </c:choose>
    </div>
</body>
</html>