<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <jsp:include page="../util_views/options-view.jsp">
            <jsp:param name="addHref" value="${ showPlacesAttributes.addHref }" />
            <jsp:param name="textContent" value="${ showPlacesAttributes.buttonContent }" />
        </jsp:include>

        <jsp:include page="current-places-content-table-view.jsp" />
            
        <jsp:include page="../util_views/pagination/pagination-setup-view.jsp" />
    </body>
</html>