<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        
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

                <form:options items = "${ optionCollection }" />

            </form:select>

        </label>

    </body>
</html>