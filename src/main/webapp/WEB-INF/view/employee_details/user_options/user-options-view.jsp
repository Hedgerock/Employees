<%@ include file="../../util_views/button-commands-view.jsp" %>
<%@ include file="../variables-view.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ not isFired }">
            <jsp:include page = "../user-options-view.jsp">
                <jsp:param name = "page" value = "showCurrentDepartment"/>
            </jsp:include>
        </c:when>
        <c:otherwise>
            <div class="current-user-options">
                <a href="${ restoreButton }" class="current-user-options__link">
                    <i class="fa-solid fa-rotate-left"></i>
                </a>
                <a href="${ firedEmployeeHistory }" class="current-user-options__link">
                    <i class="fa-solid fa-book-bookmark"></i>
                </a>
                <a href="${ terminate }" class="current-user-options__link">
                    <i class="fa-solid fa-eraser"></i>
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>