const rows = document.querySelectorAll(".content-table-row_values");
const parent = document.querySelector(".content-table_selected-employees");
const table = document.querySelector(".content-table_potential-employees");
const caption = document.querySelector(".content-table-caption");
const allCheckboxes = document.querySelector(".select-all");

const tr = document.createElement("tr");
tr.className = "content-table-row content-table-row_notification";
const td = document.createElement("td");
td.className = "content-table-row__value";
td.setAttribute("colspan", 8);
td.textContent = "All employees are selected";
tr.appendChild(td);

const parentDecision = ({ status, content }) => {
    status ? parent.appendChild(content) : table.appendChild(content);
}

const validateCheckBoxes = () => {
    return Array.from(document.querySelectorAll(".current-checkbox")).every(val => val.checked);
}

const statusHandler = ({ status, parent, content }) => {
    status ? validateParentBeforeRemove({ parent, child: content }) : parent.appendChild(content);
}

rows.forEach(element => {
    const checkBox = element.querySelector(".current-checkbox");
    
    checkBox.onchange = (e) => {
        const isChecked = e.target.checked;
        parentDecision({ status: isChecked, content: element });

        allCheckboxes.checked = validateCheckBoxes();

        statusHandler({ status: !allCheckboxes.checked, parent: table, content: tr });

        const isHavingChild = parent.querySelectorAll(".content-table-row").length > 0;
        statusHandler({ status: isHavingChild, parent, content: caption });
    }
})

if (allCheckboxes) {
    allCheckboxes.onchange = (e) => {
        const isChecked = e.target.checked;
    
        rows.forEach(el => updateData({ 
            parent: isChecked ? parent : table, 
            status: isChecked, 
            child: el 
        }))
    
        if (isChecked) {
            table.appendChild(tr);
            validateParentBeforeRemove({ parent, child: caption });
        } else {
            validateParentBeforeRemove({ child: tr, parent: table })
            parent.appendChild(caption);
        }
    }
}

function validateParentBeforeRemove({ child, parent }) {
    if (parent.contains(child)) {
        parent.removeChild(child);
    }
}

function updateData({ parent, status, child }) {
    const checkBox = child.querySelector(".current-checkbox");
    parent.appendChild(child);
    checkBox.checked = status;
}