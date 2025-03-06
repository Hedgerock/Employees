const employeeDescription = document.querySelector(".employee-details-list__item_description");
const content = employeeDescription.querySelector("span");


employeeDescription.onclick = initTextArea;


function initTextArea() {
    const parent = employeeDescription.parentElement;
    const path = window.location.href.split("?")[1];
    const id = path.split("=")[1].replace(/\D/g, "");

    const textAreaBox = document.createElement("form");
    textAreaBox.action = "/saveDescription";

    textAreaBox.className = "text-area-box";

    const textArea = document.createElement("textarea");
    textArea.className = "employee-details-list__item employee-details-list__item_textarea";
    textArea.name = "employeeDescriptionAttribute";
    textArea.id = "employeeDescriptionAttribute";

    const currentContent = content.textContent;

    if (currentContent !== "Empty description") {
        textArea.value = currentContent;
    } else {
        textArea.placeholder = currentContent;
    }

    const buttonsBox = document.createElement("div");
    buttonsBox.className = "buttons-box";

    const confirmButton = document.createElement("button");
    confirmButton.className = "text-area-box__button"
    confirmButton.textContent = "Confirm";
    confirmButton.setAttribute("type", "submit");

    const declineButton = document.createElement("button");
    declineButton.textContent = "Decline";
    declineButton.className = "text-area-box__button";

    declineButton.onclick = () => {
        textAreaBox.remove();
        parent.append(employeeDescription);
    }

    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.name = "empId";
    input.value = id;

    buttonsBox.append(confirmButton);
    buttonsBox.append(declineButton);
    buttonsBox.append(input);

    textAreaBox.append(textArea);
    textAreaBox.append(buttonsBox);

    employeeDescription.remove();

    parent.append(textAreaBox);
}