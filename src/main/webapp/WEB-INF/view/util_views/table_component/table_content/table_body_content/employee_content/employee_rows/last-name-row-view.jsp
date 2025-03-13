<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <a
            class="content-table-row__link"
            href="${ param.link }"
            target="${ param.target }"
            title = "Show details of ${ content.firstName } ${ content.lastName }"
        >
            ${ content.lastName }
        </a>
    </td>
</body>
</html>