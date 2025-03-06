<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="siblings">
        <c:forEach var = "entityNeighbor" items = "${ siblings }">
            <%@ include file="../util_views/sibling-link-view.jsp" %>
            <c:set 
                var="title" 
                value="${ param.alterGetter ? entityNeighbor.firstName : entityNeighbor.name }"
            />

            <c:set
                var="titleExtended"
                value="${ param.alterGetter ? entityNeighbor.lastName : '' }"
            />

            <c:choose>
                <c:when test = "${ not empty entityNeighbor.id }">
                    <c:url var="alterLink" value="${ param.alterLink }">
                        <c:param name="${ param.paramTitle }" value="${ entityNeighbor.id }"/>
                    </c:url>

                    <a
                        class="siblings__link"
                        title="${ title } ${ titleExtended }"
                        href="${ param.withParams ? siblingLink : alterLink }" 
                    >
                        ${ title } ${ titleExtended }
                    </a>
                </c:when>
                <c:otherwise>
                    <a
                        class="siblings__link siblings__link_disabled"
                        href="#" 
                    >
                        Not found
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div> 
</body>
</html>