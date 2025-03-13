<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <form:form 
        action="${ not empty param.altAction ? param.altAction : param.formAction }"
        modelAttribute = "${ param.formModelAttribute }"
        method = "${ param.formMethod }"
        class = "${ param.formClassName }"
    >
        
        <jsp:include page="form_fields/${ param.formContent }_fields/${ param.formContent }-view.jsp" />

        <button 
            title="${ param.submitButtonTitle || 'Confirm' }" 
            type="submit"
            class = "${ param.formClassName }__button"
        >
            ${ param.submitButtonContent }
        </button>
        
    </form:form>
</body>
</html>