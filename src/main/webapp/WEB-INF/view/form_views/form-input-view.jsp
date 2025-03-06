<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <form:form
            class="${ param.formTitle }"
            modelAttribute = "${ param.modelAttribute }"
            action = "${ param.action }"
        >
            <form:hidden path = "id"/>

            <button 
                type="submit"
                class="${ param.formTitle }__submit-button"
            >   Confirm
            </button>
        </form:form>
    </body>
</html>