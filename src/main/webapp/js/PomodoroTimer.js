'use strict';

class PomodoroTimer{

static running_timer;
constructor(max_pomodoro_count) {
    this.state = PomodoroTimer.POMODORO;
    this.current_time = 0;
    this.is_running = false;
    this.max_pomodoro_count = max_pomodoro_count;
    this.ding = new  Audio("audio/ding.mp3"); 
   
}

static POMODORO = 25;
static  SHORT_BREAK = 5;
static LONG_BREAK =  15;

  

 change_mode(st) {
    this.state = st;
    this.is_running = false;
    this.current_time = 0;
   // this.pause_timer();
 }

 get_next_state() {
   if (this.state == PomodoroTimer.POMODORO) {
      if (this.pomodoro_count == this.max_pomodoro_count) {
            this.pomodoro_count = 0;
            return PomodoroTimer.LONG_BREAK;
      } else {
         this.pomodoro_count++;
         return PomodoroTimer.SHORT_BREAK;
      }
   } else {
      return PomodoroTimer.POMODORO;
   }
 }

 

}