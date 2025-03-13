<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <a
            class="content-table-row__link"
            target="${ param.target }"
            href="${ param.link }" 
            title = "Show details of ${ content.firstName } ${ content.lastName }"
        >
            ${ content.firstName }
        </a>
    </td>
</body>
</html>