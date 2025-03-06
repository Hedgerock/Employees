<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="currentTitle" value = "${ not empty employee.middleName ? employee.middleName : 'Empty' }" />
    <li 
        data-title = "Middle name" 
        class = "employee-details-list__item"
        title = "${ currentTitle }"
    >
        <span>${ currentTitle }</span>
    </li>
</body>
</html>