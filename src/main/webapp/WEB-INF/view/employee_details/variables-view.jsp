<!DOCTYPE html>
<html lang="en">
<body>
    <c:url var="updateSocialMedia" value="/updateSocialMedia">
        <c:param name="empId" value="${ employee.id }" />
        <c:param name="empDetId" value="${ employee.employeeDetailsId }" />
    </c:url>
    
    <c:set var = "isTheSame" value = "${ timeCarrier.difference == 0 }"/>
    <c:set var = "dateDifferenceTitle" value = "${ isTheSame ? 'Today' : timeCarrier.dateDifference }"/>
    
    <c:set var="socialMediaTitle" value="${ isFired ? 'Social media not found' : 'Update social media for ${ employee.firstName }?' }" />
    <c:set var="telegram" value="${ employee.employeeDetails.socialMedia.telegram.link }" />
    <c:set var="viber" value="${ employee.employeeDetails.socialMedia.viber.link }" />
    <c:set var="whatsapp" value="${ employee.employeeDetails.socialMedia.whatsApp.link }" />
    <c:set var="linkedin" value="${ employee.employeeDetails.socialMedia.linkedIn.link }" />
    <c:set var="totalyEmpty" value="${ empty telegram && empty viber && empty whatsapp && empty linkedin }" />

    <c:url var="restoreButton" value="/returnEmployee">
        <c:param name="empId" value="${ employee.id }" />
    </c:url>
    <c:url var="firedEmployeeHistory" value="/showFiredEmployeeHistory">
        <c:param name="empId" value="${ employee.id }" />
    </c:url>
    <c:url var="terminate" value="/terminateEmployeeData">
        <c:param name="empId" value="${ employee.id }" />
    </c:url>
</body>
</html>