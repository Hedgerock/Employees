<%@ include file="../../util_views/button-commands-view.jsp" %>
<%@ include file="../variables-view.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <div class="details">
            <div class="details-header">
                <div class="details-left-side">
                    <jsp:include page = "../employee-image-view.jsp" />
                    <c:set var="sinceTitle" value="${ isFired ? timeCarrier.fireDate : timeCarrier.hireDate }" />
                    <span
                        title="${ isTheSame ? 'Today' : timeCarrier.previousChanges }" 
                        class="details-header-image__hire-date"
                    >
                        Since "${ sinceTitle }" (${ dateDifferenceTitle })
                    </span>
                    <jsp:include page="../social_media/social-media-view.jsp" />
                </div>
                <jsp:include page = "../employee-details-list-view.jsp" />
            </div>

            <jsp:include page = "../../util_views/siblings-view.jsp">
                <jsp:param name="withParams" value="${ empty isFired }" />
                <jsp:param name="alterGetter" value="${ true }" />
                <jsp:param name="alterLink" value="showFiredEmployeeDetails" />
                <jsp:param name="paramTitle" value="empId" />
            </jsp:include>

            <c:if test="${ slider.size() > 0 }">
                <jsp:include page="../../util_views/slider/slider-view.jsp" />
            </c:if>
        </div>
    </body>
</html>