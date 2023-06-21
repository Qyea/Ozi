<%@ page import="by.fpmibsu.ozi.services.FindPeoplePageService" %>
<%@ page import="by.fpmibsu.ozi.dao.UserDao" %>
<%@ page import="by.fpmibsu.ozi.entity.User" %>
<%@ page import="java.util.*" %>
<%@ page import="by.fpmibsu.ozi.services.Status" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Search</title>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/img/svg/Ozi_icon.svg">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon/Ozi__iconPNG.png">
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

                <!-- search-results start -->
                <div class="search__wrap">
                    <div class="result__window">
                        <div class="search__results">
                            <div class="search__result">
                                <div class="time__place">
                                    <div class="post__time date">People</div>
                                </div>
                                <div class="people element">
                                    <form method="post" class="people element">
                                        <%
                                            FindPeoplePageService service = new FindPeoplePageService(new UserDao());
                                            String name = (String)request.getAttribute("findPersonName");
                                            List<User> users;
                                            if (name == null || name.equals(""))
                                            {
                                                users = new UserDao().findAll();
                                            }
                                            else
                                            {
                                                users = service.findByName(name);
                                            }
                                            Integer userId = (Integer)request.getSession().getAttribute("userId");
                                            for (User friend : users) {
                                                if (friend.getId().equals(userId)) continue;
                                        %>
                                        <div class="user__info">
                                            <% session.setAttribute("toFollowId",friend.getId());%>
                                            <a class="start__info" href="/ozi?pageId=${toFollowId}">
                                                <div class="info-s__item">
                                                    <div class="info__img">
                                                        <img class="info__pic"
                                                             src="${pageContext.request.contextPath}/img/Ozi.png"
                                                             width="80" height="80" alt="user photo">
                                                    </div>
                                                </div>
                                                <div class="info-s__item">
                                                    <div class="info__item__center">
                                                        <div class="info__item__name">
                                                            <% session.setAttribute("user",friend.getName() +
                                                                    " " + friend.getSurname() );
                                                            %>
                                                            ${user}
                                                        </div>
                                                        <div class="info__status">
                                                            <% session.setAttribute("status",friend.getAbout());
                                                            %>
                                                            ${status}
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>

                                            <%--<div class="info-s__item">
                                                <div class="info__button">
                                                    <%
                                                        String statusText = "";
                                                        String st = (String)request.getAttribute("status");
                                                        if (st == Status.REQUEST_SEND.toString()) statusText = "Accept request";
                                                        else if (st == Status.FRIEND.toString()) statusText = "Remove from friends";
                                                        else if (st == Status.FOLLOWER.toString()) statusText = "Unfollow";
                                                        else statusText = "Add to friends";
                                                        request.setAttribute("statusText", statusText);
                                                        request.setAttribute("pageId", request.getParameter("pageId"));
                                                    %>
                                                    <form method = "post" action="/ozi/editFriends?pageId=${pageId}&status=${status}">
                                                        <button class="change-status" name="statusButton" value="${st}" >${statusText}</button>
                                                    </form>
                                                </div>
                                            </div>--%>
                                        </div>
                                        <% } %>
                                    </form>


                                </div>

                                
                                
                            
                            </div>
                        </div>
                    </div>
                </div>
                <!-- search-results end -->

            </section>

        </main>
            

    </body>
    <footer class="footer">
        <jsp:include page="components/footer.jsp"/>
    </footer>
    <script src="./js/main.js"></script>
</html>