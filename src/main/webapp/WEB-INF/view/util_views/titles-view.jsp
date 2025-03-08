<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="pageName" value="${ not empty contentPage ? contentPage : 'main' }" scope="request"/>
    <c:set var="pageTitle" value="${ user } profile: ${ pageName } page" scope="request"/>
</body>
</html>