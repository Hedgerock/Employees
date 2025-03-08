<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <jsp:include page="../form_views/update-current-place-view.jsp">
            <jsp:param name="attribute" value="${ updateAttributes.attrName }" />
            <jsp:param name="updateAction" value="${ updateAttributes.updateAction }" />
            <jsp:param name="mainAction" value="${ updateAttributes.mainAction }" />
            <jsp:param name="currentPlace" value="${ updateAttributes.attrPath }" />
        </jsp:include>
    </body>
</html>