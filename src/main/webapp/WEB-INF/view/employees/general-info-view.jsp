<!DOCTYPE html>
<html lang="en">
<body>
    <jsp:include page="../util_views/table_component/table-main-view.jsp">
        <jsp:param name="content" value="general-info" />
        <jsp:param name="withoutCollection" value="${ true }" />
        <jsp:param name="withoutPagination" value="${ true }" />
    </jsp:include>
</body>
</html>