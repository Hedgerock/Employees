<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <ul class = "employee-details-list">
        <jsp:include page = "../employee_details/employee_details_list_items/first-name-view.jsp" />
        <jsp:include page = "../employee_details/employee_details_list_items/last-name-view.jsp" />
        <jsp:include page = "../employee_details/employee_details_list_items/middle-name-view.jsp" />
        <jsp:include page = "../employee_details/employee_details_list_items/date-of-birth-view.jsp" />

        <jsp:include page = "../employee_details/employee_details_list_items/department-view.jsp">
            <jsp:param name = "currentDepartment" value = "${ currentDepartment }" />
            <jsp:param name = "updateButton" value = "${ updateButton }" />
        </jsp:include>

        <jsp:include page = "../employee_details/employee_details_list_items/salary-view.jsp" />
        <jsp:include page = "../employee_details/employee_details_list_items/email-view.jsp" />
        <jsp:include page = "../employee_details/employee_details_list_items/phone-number-view.jsp" />
        
        <jsp:include page = "../employee_details/employee_details_list_items/city-view.jsp">
            <jsp:param name = "updateButton" value = "${ updateButton }" />
        </jsp:include>
        
        <jsp:include page = "../employee_details/employee_details_list_items/nationality-view.jsp">
            <jsp:param name = "updateButton" value = "${ updateButton }" />
        </jsp:include>

        <jsp:include page = "../employee_details/employee_details_list_items/description-view.jsp" />
    </ul>
</body>
</html>