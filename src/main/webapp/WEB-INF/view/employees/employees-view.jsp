<!DOCTYPE html>
<html lang="en">
    <body>
        <jsp:include page="../util_views/table_component/table-main-view.jsp">
            <jsp:param name="content" value="employee" />
            <jsp:param name="currentPlace" value="${ param.currentPlace }" />
        </jsp:include>
    </body>
</html>