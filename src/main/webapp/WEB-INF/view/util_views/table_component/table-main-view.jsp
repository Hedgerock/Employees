<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="responsive-table">
        <c:set var="headPath" value="table_content/table_head_content/${ param.content }-table-head-content-view.jsp" />
        <c:set var="bodyPath" value="table_content/table_body_content/${ param.content }_content/${ param.content }-table-body-content-view.jsp" />

        <table class="content-table ${ param.tableModificator }">
            <thead class="content-table-head content-table-head_default">
                <tr class="content-table-row">
                    <c:if test="${ not empty param.captionLink && not empty param.captionTitle }">
                        <caption class="content-table-caption">
                            <a class="content-table-caption__link" href="${ param.captionLink }">
                                ${ param.captionTitle }
                            </a>
                        </caption>
                    </c:if>

                    <jsp:include page="${ headPath }" />
                </tr>
            </thead>
            
            <c:set var="collectionTitle" value="${ empty param.collectionTitle ? 'employee' : param.collectionTitle}" />

            <c:choose>
                <c:when test="${ collectionTitle == 'employee' }">
                    <c:set var="currentCollection" value="${ allEmployees }" />
                </c:when>
                <c:when test="${ collectionTitle == 'revision' }">
                    <c:set var="currentCollection" value="${ revisions }" />
                </c:when>
                <c:when test="${ collectionTitle == 'currentPlace' }">
                    <c:set var="currentCollection" value="${ currentPlaces }" />
                </c:when>
                <c:when test="${ collectionTitle == 'specificContent' }">
                    <c:set var="currentCollection" value="${ actualValue.currentPlaces }" />
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${ not empty param.withoutCollection }">
                    <jsp:include page="${ bodyPath }" />
                </c:when>

                <c:when test="${ not empty currentCollection }">
                    <c:forEach var = "content" items = "${ currentCollection }" varStatus = "status">
                        <c:set var = "content" value = "${ content }" scope = "request"/>
                        <c:set var="indexVal" value="${ status.index + 1 + pageSize * paginationCurrentPage }" scope="request"/>
                        <jsp:include page="${ bodyPath }" />
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <c:set var="imgContent" value="${ not empty param.imgValue ? param.imgValue : 'not-found' }" />

                    <tr class="content-table-row">
                        <td class="content-table-row__value" colspan="9">
                            <jsp:include page="../not-found-image-view.jsp">
                                <jsp:param name="image" value="${ imgContent }.png" />
                            </jsp:include>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>

        <c:if test="${ not empty param.withSiblings }">
            <jsp:include page="../siblings-view.jsp">
                <jsp:param name="withParams" value="${ false }" />
                <jsp:param name="alterLink" value="${ showCurrentCity }" />
                <jsp:param name="paramTitle" value="${ actualValue.placeTitle }" />
            </jsp:include>
        </c:if>
    </div>

    <c:if test="${ empty param.withoutPagination }">
        <jsp:include page="../pagination/pagination-setup-view.jsp" />
    </c:if>
</body>
</html>