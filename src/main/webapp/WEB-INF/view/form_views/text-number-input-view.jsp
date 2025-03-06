<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <label class="${ param.formTitle }-label">
            <h3
                class="${ param.formTitle }-label__title"
            >
                ${ param.labelTitle }: ${ param.number }
            </h3>

            <c:choose>
                <c:when test = "${ param.option != null && param.option != '' }">
                    <form:input
                        path = "${ param.path }"
                        class = "${ param.formTitle }-label__input ${ param.formTitle }-label__input_${ param.option }"
                        type = "${ param.type }"
                        value = "${ param.value }"
                        placeholder = "${ param.placeholder }"
                    />
                </c:when>
                <c:otherwise>
                    <form:input
                        path = "${ param.path }"
                        class = "${ param.formTitle }-label__input"
                        type = "${ param.type }"
                        value = "${ param.value }"
                        placeholder = "${ param.placeholder }"
                    />
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test = "${ param.index > 0 }">
                    <button 
                        title = "Remove current field"
                        class = "${ param.formTitle }-label__delete-button"
                    >
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </c:when>
            </c:choose>
        </label>
    </body>
</html>