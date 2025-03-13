<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var = "page" value = "${ param.page }" scope = "request"/>
    <c:set var = "depId" value = "${ param.depId }" scope = "request" />

    <jsp:include page = "single-employee-view.jsp">
        <jsp:param name="currentPlace" value="${ param.currentPlace }" />
        <jsp:param name="modificator" value="${ param.rowModificatior }" />
    </jsp:include>
</body>
</html>