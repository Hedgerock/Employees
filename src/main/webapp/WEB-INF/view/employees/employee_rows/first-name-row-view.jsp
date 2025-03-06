<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <a
            class="content-table-row__link"
            target="${ param.target }"
            href="${ param.link }" 
            title = "Show details of ${ employee.firstName } ${ employee.lastName }"
        >
            ${ employee.firstName }
        </a>
    </td>
</body>
</html>