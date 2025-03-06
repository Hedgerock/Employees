<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:url var = "siblingLink" value = "/showCurrentEmployeeDetails">
        <c:choose>
            <c:when test = "${ not empty depId }">
                <c:param name = "empId" value = "${ entityNeighbor.id }" />
                <c:param name = "depId" value = "${ depId }" />
            </c:when>
            <c:when test = "${ not empty cityId }">
                <c:param name = "empId" value = "${ entityNeighbor.id }" />
                <c:param name = "cityId" value = "${ cityId }" />
            </c:when>
            <c:when test = "${ not empty natId }">
                <c:param name = "empId" value = "${ entityNeighbor.id }" />
                <c:param name = "natId" value = "${ natId }" />
            </c:when>
            <c:otherwise>
                <c:param name = "empId" value = "${ entityNeighbor.id }" />
            </c:otherwise>
        </c:choose>
    </c:url>
</body>
</html>