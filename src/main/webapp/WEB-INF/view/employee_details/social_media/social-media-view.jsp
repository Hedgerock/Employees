<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../util_views/button-commands-view.jsp" %>
<%@ include file="../variables-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="social-media">
        <c:choose>
            <c:when test="${ not isFired }">
                <a title="Update ${ employee.firstName } social media" href="${ updateSocialMedia }">
                    <i class="fa-solid fa-plus"></i>
                </a>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test = "${ not totalyEmpty }">
                <jsp:include page="social-media-list-view.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="social-media-empty" title="${ socialMediaTitle }">
                    <c:set var="textContent" value="EMPTY :(" />

                    <c:choose>
                        <c:when test="${ not isFired }">
                            <a href="${ updateSocialMedia }">${ textContent }</a>
                        </c:when>
                        <c:otherwise>
                            <span href="${ updateSocialMedia }">${ textContent }</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>