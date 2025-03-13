<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <c:choose>
            <c:when test="${ param.selectionType == 'city' }">
                <c:set var="actualSelect" value="${ citiesCollection }" />
            </c:when>
            <c:when test="${ param.selectionType == 'department' }">
                <c:set var="actualSelect" value="${ optionCollection }" />
            </c:when>
            <c:when test="${ param.selectionType == 'nationality' }">
                <c:set var="actualSelect" value="${ nationalitiesCollection }" />
            </c:when>
        </c:choose>

        <label class="${ param.formTitle }-label">
            <h3
                class="{ param.formTitle }-label__title"
            >
                ${ param.labelTitle }:
            </h3>

            <form:select
                path = "${ param.path }"
                class = "${ param.formTitle }-label__input ${ param.formTitle }-label__input_${ param.option }"
                type = "${ param.type }"
            >

                <form:options items = "${ actualSelect }" />

            </form:select>

        </label>

    </body>
</html>