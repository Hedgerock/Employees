<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../variables-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <ul class="social-media-list">
        <c:choose>
            <c:when test="${ not empty telegram }">
                <li title="${ employee.firstName } telegram">
                    <a
                        target="_blank"  
                        href="https://${ telegram }"
                    >
                        <i class="fa-brands fa-telegram"></i>
                    </a>
                </li>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${ not empty viber }">
                <li title="${ employee.firstName } viber">
                    <a
                        target="_blank"  
                        href="${ viber }"
                    >
                        <i class="fa-brands fa-viber"></i>
                    </a>
                </li>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${ not empty whatsapp }">
                <li title="${ employee.firstName } whatsapp">
                    <a
                        target="_blank"  
                        href="${ whatsapp }"
                    >
                        <i class="fa-brands fa-whatsapp"></i>
                    </a>
                </li>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${ not empty linkedin }">
                <li title="${ employee.firstName } linkedin">
                    <a
                        target="_blank"  
                        href="${ linkedin }"
                    >
                        <i class="fa-brands fa-linkedin"></i>
                    </a>
                </li>
            </c:when>
        </c:choose>
    </ul>
</body>
</html>