<%@ page import="by.fpmibsu.ozi.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="by.fpmibsu.ozi.dao.PostDao" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.05.2023
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Ozi!</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/img/svg/Ozi_icon.svg">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon/Ozi__iconPNG.png">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Aleo:ital,wght@0,400;0,700;1,400&family=Inter&family=Lato:ital,wght@0,300;0,400;1,300&family=Red+Hat+Text:wght@500&display=swap" rel="stylesheet">

    <!-- подключение общего CSS-файла -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/temp.css">
</head>
<body class="element">
<!-- вставка общего хедера из header.jsp -->
<div>
    <!-- подключение файла header.jsp -->
    <jsp:include page="./components/header.jsp" />
</div>

<!-- основное содержимое страницы -->
<main class="main">
    <nav>
        <!-- подключение файла navbar.jsp -->
        <jsp:include page="./components/navbar.jsp" />
    </nav>

    <section class="intro__home">
        <!-- profile start -->
        <div class="profile__wrap">
            <div class="profile">
                <div class="profile__item">
                    <div class="profile__pic">
                        <img class="profile__photo" alt="profile picture"
                             src="${pageContext.request.contextPath}/img/Ozi.png" width="350" height="350">
                    </div>
                </div>
                <div class="profile__item">
                    <div class="panel__items">
                        <div class="panel__item">
                            <div class="panel">
                                <div class="user__panel">
                                    <p class="user__name">${name} ${surname}</p>
                                    <p class="user__status">${about}</p>
                                </div>
                                <div class="settings">
                                    <div class="settings__pic">
                                        <a id="change-status-button" >
                                            <img src="${pageContext.request.contextPath}/img/svg/settings.svg">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="change-status-dialog">
                            <div id="change-status-dialog-content">
                                <div class="profile__about">

                                    <div class="panel__items">

                                        <div class="panel__item">

                                            <div class="panel__about">
                                                <form method="post" action="/ozi/editabout">
                                                    <span class="close" onclick="closeDialog()">&times;</span>
                                                    <div class="edit__info">
                                                        <h1 class="edit__title">Edit your status!</h1>
                                                        <p class="edit__p">
                                                            You can change information about your feelings
                                                        </p>
                                                    </div>
                                                    <div class="edit">
                                                    <textarea name="about" class="about_in element" id="status-input"
                                                              placeholder="${about}"></textarea>
                                                        <div class="buttoms">
                                                            <button type="submit" class="edit__but save">Save</button>
                                                            <button type="button" class="edit__but"
                                                                    onclick="closeDialog()">Cancel </button>
                                                        </div>
                                                    </div>
                                                    <div class="statistics__about">
                                                        <!-- statistics part -->
                                                        <jsp:include page="components/statistics.jsp"/>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                        <script>
                            function openDialog() {
                                var dialog = document.querySelector('#change-status-dialog');
                                dialog.style.display = "block";
                            }

                            function closeDialog() {
                                var dialog = document.querySelector('#change-status-dialog');
                                dialog.style.display = "none";
                            }
                        </script>
                        <script>
                            var changeStatusButton = document.querySelector('#change-status-button');
                            changeStatusButton.addEventListener('click', openDialog);
                        </script>

                        <div class="panel__item">
                            <div class="panel bottom">
                                <div class="user__atributies-row">
                                    <div class="user__atributies-row-container">
                                        <div class="user__atributies-row-lable">
                                            date of birth:
                                        </div>
                                        <div class="user__atributies-row-in">
                                            <div class="user__atributies-row-datebirth">
                                                    <span class="user__atributies-row-value">
                                                        <a href="#" class="user__link js-serch">${birthday}</a>
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="user__atributies-row">
                                    <div class="user__atributies-row-container">
                                        <div class="user__atributies-row-lable">
                                            Languages:
                                        </div>
                                        <div class="user__atributies-row-in">
                                            <div class="user__atributies-row-lang">
                                                    <span class="user__atributies-row-value">
                                                        <a href="#" class="user__link js-serch">Russian, English</a>
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="user__atributies-row">
                                    <div class="user__atributies-row-container">
                                        <div class="user__atributies-row-lable">
                                            city:
                                        </div>
                                        <div class="user__atributies-row-in">
                                            <div class="user__atributies-row-city">
                                                    <span class="user__atributies-row-value">
                                                        <a href="#" class="user__link js-serch">${city}</a>
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="statistics">
                                    <!-- statistics part -->
                                    <jsp:include page="components/statistics.jsp"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- profile end -->


        <!-- posts start -->
        <!-- posts start -->
        <div class="profile__wrap">
            <div class="posts__place">
                <div class="posts__panel">
                    <div class="tools__panel">
                        <div class="lable__part">
                            <div class="post__lable">
                                <p>all notes:</p>
                            </div>
                        </div>
                        <form class="tools" method="post" action="/ozi/post">
                            <div class="text-field">
                                <div class="text-field__icon">
                                    <textarea class="text-field__input element" id="post_in" name="post_in" type="text" placeholder="Share the news"></textarea>
                                    <div class="icons__place">
                                        <button class="post__button">
                                            create
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                    <%
                        Integer userId = (Integer) session.getAttribute("userId");
                        PostDao postDao = new PostDao();
                        List<Post> posts = postDao.findAllByUserId(userId);
                        Collections.sort(posts, Comparator.comparing(Post::getDate).reversed());

                    %>
                    <div class="post__panel">
                        <div class="post__container">
                            <% for (Post post : posts) { %>
                            <div class="post">
                                <div class="time__place">
                                    <div class="post__time date">
                                        <% session.setAttribute("date",post.getDate()); %>
                                        ${date}
                                    </div>
                                </div>

                                <div class="post__card">
                                    <div class="post__info">
                                        <div class="post__pic">
                                            <img class="post__picture" src="img/Ozi.png" width="65" height="65">
                                        </div>
                                        <div class="post__plot">
                                            <div class="name__time">
                                                <div class="post__name">
                                                    <% session.setAttribute("user",post.getUser().getName() +
                                                            " " + post.getUser().getSurname() ); %>
                                                    ${user}
                                                </div>
                                            </div>
                                            <div class="post__text">
                                                <% session.setAttribute("text",post.getText()); %>
                                                ${text}
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- posts end -->
    </section>

</main>

<footer class="footer">
    <!-- подключение файла footer.jsp -->
    <jsp:include page="./components/footer.jsp" />
</footer>
</body>
</html>

