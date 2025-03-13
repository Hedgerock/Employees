<%@ include file="../util_views/button-commands-view.jsp" %>
<%@ include file="../util_views/titles-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <jsp:include page="../util_views/table_component/table-main-view.jsp">
            <jsp:param name="content" value="revisions" />
            <jsp:param name="captionLink" value="${ showButton }" />
            <jsp:param name="captionTitle" value="${ actTitle }" />
            <jsp:param name="collectionTitle" value="revision" />
        </jsp:include>
    </body>
</html>

