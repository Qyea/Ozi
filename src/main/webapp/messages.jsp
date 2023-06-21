<%@ page import="by.fpmibsu.ozi.entity.User" %>
<%@ page import="by.fpmibsu.ozi.services.DialogPageService" %>
<%@ page import="by.fpmibsu.ozi.dao.UserDao" %>
<%@ page import="by.fpmibsu.ozi.dao.MessageDao" %>
<%@ page import="by.fpmibsu.ozi.dao.FriendDao" %>
<%@ page import="java.util.*" %>
<%@ page import="by.fpmibsu.ozi.dao.DaoException" %>
<%@ page import="by.fpmibsu.ozi.entity.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Messages</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/img/svg/Ozi_icon.svg">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon/Ozi__iconPNG.png">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/temp.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Aleo:ital,wght@0,400;0,700;1,400&family=Inter&family=Lato:ital,wght@0,300;0,400;1,300&family=Red+Hat+Text:wght@500&display=swap" rel="stylesheet">

</head>
<body class="element">
<div>
    <jsp:include page="./components/header.jsp" />
</div>
<!-- header end -->
<main class="main">
    <section class="intro">
        <!-- info start -->
        <jsp:include page="./components/navbar.jsp" />
        <!-- info end -->

        <!-- message__list start -->

        <div class="profile__wrap">
            <div class="chat__windows">

                    <div class="message__list">
                        <div class="search__chat">
                            <div class="search__items">
                                <div class="search__item">
                                    <p class="dialogs__title">Dialogs</p>
                                </div>
                            </div>
                        </div>

                        <form method="get" class="people element">
                            <div class="dialogs element">
                                <%
                                    DialogPageService service = new DialogPageService(new UserDao(), new MessageDao(), new FriendDao());
                                    Integer userId = (Integer) request.getSession().getAttribute("userId");
                                    List<User> friends = new ArrayList<>();
                                    try {
                                        friends = service.getFriends(userId);
                                    } catch (DaoException | InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    for (User friend : friends) {
                                        session.setAttribute("friendId", friend.getId().toString());
                                        session.setAttribute("radioId", "radio-" + friend.getId());
                                %>
                                <button class="user__but" name="active" value=${friendId}>
                                    <div class="chats">
                                        <div class="start_chat">
                                            <div class="chat">
                                                <div class="chat__img">
                                                    <img class="chat__pic" src="${pageContext.request.contextPath}/img/Ozi.png" width="60" height="60" alt="user photo">
                                                </div>
                                            </div>
                                            <div class="chat">
                                                <div class="chat__center">
                                                    <div class="chat__name">
                                                        <% session.setAttribute("user",friend.getName() +
                                                                " " + friend.getSurname() );
                                                        %>
                                                        ${user}
                                                    </div>
                                                    <div class="chat__last">
                                                        <span class="your__message">you:</span>
                                                        Am I the only one keeping track...
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="chat">
                                            <div class="chat__time">
                                                <div class="chat__last">23:07</div>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                                <% } %>
                            </div>

                        </form>
                    </div>
                    <div class="message__bg">
                        <div class="info__part">
                            <div class="name__part">
                                <%
                                    session.setAttribute("activeButton", request.getParameter("active"));
                                    User user;
                                    if (session.getAttribute("activeButton") == null || session.getAttribute("activeButton").equals(""))
                                    {
                                        user = null;
                                    }
                                    else
                                        user = new UserDao().findById(Integer.parseInt(request.getParameter("active")));
                                    if (user != null)
                                        session.setAttribute("DialogName", user.getName() + " " + user.getSurname());
                                    else
                                        session.setAttribute("DialogName", "");
                                %>
                            </div>
                            <div class="time__part">
                                ${DialogName}
                            </div>
                        </div>
                        <div class="main__part">
                            <div class="messages element">
                                <%
                                    service = new DialogPageService(new UserDao(), new MessageDao(), new FriendDao());
                                    String str = request.getParameter("active");
                                    session.setAttribute("activeButton", str);
                                    List<Message> list;
                                    if (str == null || str.equals(""))
                                    {
                                        list = new ArrayList<>();
                                    }
                                    else
                                    {
                                        list = service.getMessages(userId, Integer.parseInt(request.getParameter("active")));
                                    }
                                    for (Message message : list) {
                                        session.setAttribute("messageName", message.getSentUser().getName());
                                        session.setAttribute("messageText", message.getText());
                                        session.setAttribute("messageDate", message.getMessageDate());
                                %>
                                <div class="message">
                                    <div class="message_item-start">
                                        <div class="message_item">
                                            <div class="message__img">
                                                <img class="chat__pic" src="${pageContext.request.contextPath}/img/Ozi.png" width="60" height="60" alt="user photo">
                                            </div>
                                        </div>
                                        <div class="message_item">
                                            <div class="chat__center">
                                                <div class="chat__name companion">
                                                    ${messageName}
                                                </div>
                                                <div class="chat__last comp_message">
                                                    ${messageText}
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="message_item">
                                        <div class="chat__time">
                                            <div class="chat__last">${messageDate}</div>
                                        </div>
                                    </div>
                                </div>

                                <% } %>
                            </div>
                        </div>
                        <div class="type__part">
                            <div class="components">
                                <form method="post" action="/ozi/messages">

                                        <textarea class="message_in" id="message_in"
                                                  name="message_in" placeholder="Write your message..."></textarea>
                                    <button class="benefits__card-more">send</button>
                                </form>
                            </div>
                        </div>
                    </div>

            </div>

        </div>
        <!-- message__list end -->
    </section>

</main>


</body>
<footer class="footer">
    <jsp:include page="components/footer.jsp"/>
</footer>
</html>