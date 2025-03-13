<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:if test="${ param.mainCondition }">
        <c:choose>
            <c:when test="${ param.content == 1 }">
                <a 
                    href="${ param.link }"
                    class="pagination__link">
                    ${ param.content }
                </a>
                <span class="dots">...</span>
            </c:when>
            <c:otherwise>
                <span class="dots">...</span>
                <a 
                    href="${ param.link }"
                    class="pagination__link">
                    ${ param.content }
                </a>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>