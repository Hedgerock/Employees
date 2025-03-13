<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <c:set 
            var="actualValue" 
            value="${ not empty showPlacesAttributes ? showPlacesAttributes : showAttributes }"
            scope="request"
        />

        <jsp:include page="../util_views/table_component/table-main-view.jsp">
            <jsp:param name="content" value="place" />
            <jsp:param name="collectionTitle" value="specificContent" />
            <jsp:param name="withSiblings" value="${ true }" />
            <jsp:param name="withoutPagination" value="${ actualValue.currentPlaces.size() > 1 }" />
            <jsp:param name="imgValue" value="${ imgValue }" />
        </jsp:include>
    </body>
</html>
