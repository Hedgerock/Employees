<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <form:input
            path = "searchParams"
            type="search"
            placeHolder="${ param.placeHolder }"
            class = "${ param.formTitle }__input"
            required = "true"
        />
    </body>
</html>