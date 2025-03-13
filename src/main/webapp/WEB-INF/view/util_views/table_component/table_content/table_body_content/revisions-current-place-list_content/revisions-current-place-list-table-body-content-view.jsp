<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <tr class="content-table-row">
        <td class="content-table-row__value">${ indexVal }</td>

        <c:url var="showCurrentHistory" value="/${ showHistory }">
            <c:param name="${ idTitle }" value="${ content.id }"/>
        </c:url>

        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showCurrentHistory }"
            >
                ${ content.name }
            </a>
        </td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showCurrentHistory }"
            >
                ${ content.minSalary == 0 ? '-' : content.minSalary }
            </a>
        </td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showCurrentHistory }"
            >
                ${ content.maxSalary == 0 ? '-' : content.maxSalary }
            </a>
        </td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showCurrentHistory }"
            >
                ${ content.totalEmployees == 0 ? '-' : content.totalEmployees }
                ${ content.totalEmployees == 0 ? '' : (content.totalEmployees == 0 ? 'employee' : 'employees') }
            </a>
        </td>
        <td 
            title="${ content.previousChanges }" 
            class="content-table-row__value"
        >
            ${ content.previousShortChanges } ago
        </td>
    </tr>
</body>
</html>