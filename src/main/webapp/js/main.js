function myFunction() {
    var x = document.getElementById("password");
    var y = document.getElementById("password2");
         if (x.type === "password") {
            x.type = "text";
            y.type = "text";
        } 
     else {      
         x.type = "password"; 
         y.type = "password";        
        }      
}

