<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <button class="burger-button">
            <i class="fa-solid fa-bars"></i>
        </button>

        <c:choose>
            <c:when test="${ not empty message }">
                <div class="update-info update-info_${ status }">
                    <div class="update-info-header">
                        <div class="content-info">
                            <h3>Title:</h3>
                            <span>message notification</span>
                        </div>
                        <button class="update-info__close-button">
                            <i class="fa-solid fa-xmark"></i>
                        </button>
                    </div>
                    <div class="update-info-main-part">
                        <h3 class="update-info__title">${ message }</h3>
                    </div>
                    <div class="update-info-footer">
                        <div class="content-info">
                            <h3>Status:</h3>
                            <span>${ status }</span>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>

        <script src="${pageContext.request.contextPath}/js/updateInfo.js"></script>
    </body>
</html>