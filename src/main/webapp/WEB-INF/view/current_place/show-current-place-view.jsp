<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <div class="main-container">
            <jsp:include page="../util_views/current-place-content.jsp">
                <jsp:param name="pageValue" value="${ showAttributes.page }"/>
                <jsp:param name="pagePath" value="../current_place/current-places-content-table-view.jsp"/>
                <jsp:param name="placeTitle" value="${ showAttributes.placeTitle }"/>
                <jsp:param name="placeValue" value="${ showAttributes.idValue }"/>
                <jsp:param name="clearHref" value="/${ showAttributes.page }"/>
            </jsp:include>
        </div>
    </body>
</html>