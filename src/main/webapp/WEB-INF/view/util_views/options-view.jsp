<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="options">
        <c:choose>
            <c:when test="${ not empty param.backHref }">
                <a
                    class="options__link options__link_back"
                    href="${ param.backHref }"
                >
                    Go back
                </a>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${ param.hasParams }">
                <a
                    class="options__link options__link_clear"
                    href="${ param.clearHref }"
                >
                    Clear search params
                </a>
            </c:when>
        </c:choose>
        
        <c:url var="showDetailsHistory" value="/showEmployeeDetailsHistory">
            <c:param name="empDetId" value="${ param.employeeDetailsHistoryId }"/>
        </c:url>

        <c:set 
            var="textContent" 
            value="${ not empty param.textContent ? param.textContent : 'Add new employee' }" 
        />

        <c:choose>
            <c:when test="${ not empty param.addHref }">
                <a
                    class="options__link"
                    href="${ param.addHref }" 
                >
                    ${ textContent }
                </a>
            </c:when>
        </c:choose>
    </div>
</body>
</html>