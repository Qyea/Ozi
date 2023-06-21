const states = {POMODORO: 1, SHORT_BREAK: 5, LONG_BREAK: 15};
let pomodoro_radio = document.getElementById("pomodoro");
pomodoro_radio.checked = true;
let short_break_radio = document.getElementById("short_break");
let long_break_radio = document.getElementById("long_break");

pt = new PomodoroTimer();
function pomodoro_on_change() {
}


let state = states.POMODORO;


let time_in_seconds = 0;
var countDownDate = new Date("Jan 5, 2024 15:37:25").getTime();

document.getElementById("timer").innerHTML = state + ":" + "00";

x = setInterval(function() {

    var distance = state * 60 - time_in_seconds;
    let minutes = Math.floor(distance/60);
    let seconds = distance %60;
    
    if (seconds < 10){
     seconds = "0" + seconds;
    }
    document.getElementById("timer").innerHTML = minutes + ":" + seconds;
    time_in_seconds++;
      
    if (distance < 0) {
      clearInterval(x);
      state = states.SHORT_BREAK;
      document.getElementById("timer").innerHTML = state + ":00";
      let ding = new Audio("audio/ding.mp3"); //switchable
      ding.play();
    }
  }, 1000);


function start_over() {
  document.getElementById("timer").innerHTML = state + ":" + "00";

setInterval(function() {

    var distance = state * 60 - time_in_seconds;
    let minutes = Math.floor(distance/60);
    let seconds = distance %60;
    
    if (seconds < 10){
     seconds = "0" + seconds;
    }
    document.getElementById("timer").innerHTML = minutes + ":" + seconds;
    time_in_seconds++;
      
    if (distance < 0) {
      clearInterval(x);
      state = states.SHORT_BREAK;
      document.getElementById("timer").innerHTML = state + ":00";
      let ding = new Audio("audio/ding.mp3"); //switchable
      ding.play();
    }
  }, 1000);

}

  //console.log(x);

