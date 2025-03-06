const preview = document.querySelector(".preview__src");
const input = document.querySelector(".current-employee-form-label__input_file");

input.onchange = (e) => {
    const file = e.target.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = (ev) => {
            preview.src = ev.target.result;
        }
        reader.readAsDataURL(file);
    }
}