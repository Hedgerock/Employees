<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="pagination-button-vars-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="pagination">
        <jsp:include page="arrow-view.jsp">
            <jsp:param name="link" value="${ curPrevLink }"/>
            <jsp:param name="className" value="${ prevClassName }"/>
            <jsp:param name="navigation" value="left"/>
        </jsp:include>

        <div class="content">
        
            <jsp:include page="dots-and-link-view.jsp">
                <jsp:param name="mainCondition" value="${ isMoreThanZero }" />
                <jsp:param name="link" value="${ firstPage }" />
                <jsp:param name="content" value="1" />
            </jsp:include>

            <c:forEach 
                var="i" 
                begin="${ beginValue }"
                end="${ endValue }"
            >

                <%@ include file="pagination-for-each-button-vars-view.jsp" %>

                <jsp:include page="pagination-current-page-button-view.jsp">
                    <jsp:param name="condition" value="${ isNumTheSame }" />
                    <jsp:param name="pageValue" value="${ pageNum }" />
                    <jsp:param name="link" value="${ curPage }" />
                </jsp:include>

            </c:forEach>
            
            <jsp:include page="dots-and-link-view.jsp">
                <jsp:param name="mainCondition" value="${ isLessThanMax }" />
                <jsp:param name="link" value="${ lastPage }" />
                <jsp:param name="content" value="${ total }" />
            </jsp:include>

        </div>

        <jsp:include page="arrow-view.jsp">
            <jsp:param name="link" value="${ curNextLink }"/>
            <jsp:param name="className" value="${ nextClassName }"/>
            <jsp:param name="navigation" value="right"/>
        </jsp:include>
    </div>
</body>
</html>