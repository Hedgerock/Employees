<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<%@ include file="../util_views/titles-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <c:url var="showPlace" value="/${ showRef }">
        <c:param name="${ idTitle }" value="${ currentId }" />
    </c:url>

    <body>
        <jsp:include page="../util_views/table_component/table-main-view.jsp">
            <jsp:param name="content" value="revisions-current-place" />
            <jsp:param name="captionLink" value="${ showPlace }" />
            <jsp:param name="captionTitle" value="${ actTitle }" />
            <jsp:param name="collectionTitle" value="revision" />
        </jsp:include>
    </body>
</html>

