<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.05.2023
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>It's time to work!</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pomodoro.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/temp.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/img/svg/Ozi_icon.svg">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/svg/Ozi__iconPNG.png">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Aleo:ital,wght@0,400;0,700;1,400&family=Inter&family=Lato:ital,wght@0,300;0,400;1,300&family=Red+Hat+Text:wght@500&display=swap" rel="stylesheet">
    </head>
    <div class="pomodoro__header">
        <!-- подключение файла header.jsp -->
        <jsp:include page="./components/header.jsp" />
    </div>

    <body class="element">
        <nav>
            <!-- подключение файла navbar.jsp -->
            <jsp:include page="./components/navbar.jsp" />
        </nav>
       <div class="background_rect1">
            <div class="timer_background">
                <div class="radio-button">
                    <input type="radio" id="pomodoro" name="regime" value="pomodoro" class="pomodoro_radio">
                    <label for="pomodoro" class="pomodoro_label">Pomodoro</label><br>
                    <input type="radio" id="short_break" name="regime" value="short_break" class="short_break_radio">
                    <label for="short_break" class = "short_break_label">Short break</label><br>
                    <input type="radio" id="long_break" name="regime" value="long_break" class="long_break_radio">
                    <label for="long_break" class="long_break_label">Long break</label>
                </div>
                <p id="timer", class="timer"></p>
               <div class="start_button">
                    <input type="checkbox", id="start_checkbox", name="start_checkbox", class="start_checkbox", value="start" >
                    <label for="start_checkbox" id="button_text">Start</label>
               </div>
            </div>
           <!-- TASK LIST PART -->
           <div class="task_bar_background">
               <p class="task_text">Tasks</p>
               <div id="newtask">
                   <input type="text" placeholder="Add Tasks">

                   <input type="image" src="${pageContext.request.contextPath}/img/plus icon.png", id="push", class="push" />
               </div>
               <div id="tasks" class="scroll" ></div>
           </div>
           <div class="element'"></div>
           <!-- TASK LIST PART -->
            </div>
        </div>
    <footer class="footer">
        <jsp:include page="components/footer.jsp"/>
    </footer>
    </body>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/PomodoroTimer.js"></script>
    <script src="${pageContext.request.contextPath}/js/driver_code.js"></script>
</html>