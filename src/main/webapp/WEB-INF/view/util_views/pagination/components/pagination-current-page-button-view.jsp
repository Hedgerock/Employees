<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ param.condition }">
            <span class="pagination__current-page">
                ${ param.pageValue }
            </span>
        </c:when>
        <c:otherwise>
            <a href="${ param.link }" class="pagination__link">
                ${ param.pageValue }
            </a>
        </c:otherwise>
    </c:choose>
</body>
</html>