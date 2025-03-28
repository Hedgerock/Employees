<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="user-content">
        <div class="options">
            <a href="/addUser" class="options__link">
                Add user
            </a>
        </div>
        <c:forEach var="curUser" items="${ users }" varStatus="status">
            <%@ include file="utils/admin-variables-view.jsp" %>

            <div class="${ curClassName }" title="${ title }">
                <span>${ index }</span>
                <span>${ username }</span>
                <c:if test="${ not isYou }">
                    <div class="user-options">
                        <c:choose>
                            <c:when test="${ isEnabled }">
                                <c:url var="deactivateUrl" value="/deactivateUser">
                                    <c:param name="username" value="${ username }" />
                                </c:url>

                                <a href="${ deactivateUrl }" title="Deactivate ${ username }">
                                    <i class="fa-solid fa-xmark"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <c:url var="activateUrl" value="/activateUser">
                                    <c:param name="username" value="${ username }" />
                                </c:url>

                                <a href="${ activateUrl }" title="Activate ${ username }">
                                    <i class="fa-solid fa-check"></i>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </div>
        </c:forEach>

        <c:set var="curParam" value="2" scope="request" />
        <c:set var="placeTitle" value="${ placeTitle }" scope="request" />
        <c:set var="isMoreThanZero" value="${ paginationCurrentPage - curParam > 0 }" scope="request"/>
        <c:set var="actualTotal" value="${ total - 1 }" scope="request"/>
        <c:set var="isLessThanMax" value="${ paginationCurrentPage + curParam < actualTotal }" scope="request"/>

        <c:if test="${ actualTotal > 0 }">
            <jsp:include page="../util_views/pagination/pagination-view.jsp"/>
        </c:if>
    </div>
</body>
</html>