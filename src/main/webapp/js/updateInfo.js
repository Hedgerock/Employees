const parent = document.querySelector(".update-info");

if (parent) {
    const button = document.querySelector(".update-info button");
    button.onclick = () => {
        parent.remove();
    }

    setTimeout(() => {
        parent.remove();
    }, 4000);
    
}
