const body = document.querySelector("body"),
    siderbar = body.querySelector(".sidebar"),
    toggle = body.querySelector(".toggle"),
    modeSwitch = body.querySelector(".toggle-switch"),
    modeText = body.querySelector(".mode-text");

    toggle.addEventListener("click", ()=>{
        siderbar.classList.toggle("close");
    });


    modeSwitch.addEventListener("click", ()=>{
        body.classList.toggle("dark");
    })