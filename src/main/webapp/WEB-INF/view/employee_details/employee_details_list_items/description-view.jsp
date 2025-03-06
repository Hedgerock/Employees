<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="value" value="${ employeeDescriptionAttribute }" />
    <c:set var="currentDescription" value="${ not empty value ? value : 'Empty description' }" />

    <li 
        class="employee-details-list__item employee-details-list__item_description" 
        data-title="Description"
    >
        <span>${ currentDescription }</span>
    </li>
    

    <script src="${ pageContext.request.contextPath }/js/employeeDescription.js"></script>
</body>
</html>