<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.05.2023
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome to Ozi!</title>
        <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/img/svg/Ozi_icon.svg">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon/Ozi__iconPNG.png">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/temp.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Aleo:ital,wght@0,400;0,700;1,400&family=Inter&family=Lato:ital,wght@0,300;0,400;1,300&family=Red+Hat+Text:wght@500&display=swap" rel="stylesheet">
    
    </head>
    <body class="element">
        <!-- header start -->
        <div class="header">
            <jsp:include page="./components/login_header.jsp" />
        </div>
        <!-- header end -->
        <main class="main">
            <section class="intro__home">
                <!-- info start -->
                <div class="wrapper__info-1">
                    
                        <div class="benif__items">
                            
                            <div class="benif__item">
                                <div class="benif">
                                    <h2 class="benif__title-top">Amazing features</h2>
                                    <h1 class="benif__title section__title">What You Get</h1>
    
                                    <div class="top__cards">
                                
                                        <div class="top__card">
                                            <div class="top__card-pic">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">Be closer to your friends</h3>
                                            <p class="top__card-desc">You can add your friends to chat with them. Share good emotions in the moment</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="top__card">
                                            <div class="top__card-pic pic__2">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">100% privacy</h3>
                                            <p class="top__card-desc">We do not distribute information about our users</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="top__card">
                                            <div class="top__card-pic pic__3">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">Great Support</h3>
                                            <p class="top__card-desc">We are always ready to help solve any problems. It is important for us to use OZi comfortably</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="top__card">
                                            <div class="top__card-pic pic__4">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">Listen to music</h3>
                                            <p class="top__card-desc">Our social network provides an opportunity to listen to music without restrictions and advertising</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="top__card">
                                            <div class="top__card-pic pic__5">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">Share the news</h3>
                                            <p class="top__card-desc">Each user can write posts on their own and other people's walls. Let everyone see you!</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="top__card">
                                            <div class="top__card-pic pic__6">
                                                <div class="top__card-items">
                                                    <h3 class="top__card-title">Enjoy it</h3>
                                            <p class="top__card-desc">And now you can finally; just enjoy and be creative! if you have any questions, do not hesitate to write to the technical support</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="benif__item">
                                <form class="form__ex" id="form" action="/ozi/login" method="POST">
                                    <div class="form__ex__container">
                                        <div class="form__ex__logo">
                                            <img src="${pageContext.request.contextPath}/img/svg/Untitled-2.png" width="80" height="80" alt="Welcome to Ozi!">
                                        </div>
                                    </div>
                                    <div class="form__ex__container">
                                        <div class="form__ex__input-items">
                                           
                                            <div class="form__container">
                                                <div class="form__input-items">
                                                    <input type="text" class="form__ex__input"
                                                           placeholder="phone or mail"  name="login" id="login" required>
                                                    <input type="password" class="form__ex__input" name="password" id="password"
                                                          placeholder="enter your password" required>
                                                    <div class="check__date">
                                                        <label class="custom-checkbox">
                                                            <input type="checkbox" class="checkbox" onclick="myFunction()"> <span>Show the password</span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <c:if test="${not empty errorMessage}">
                                                    <p class="error__message">${errorMessage}</p>
                                                </c:if>
                                        </div>
                                        <div class="forgot__title">
                                            <a class="forget__password" href="#!">forgot the password?</a>
                                        </div>
                                    </div>
                                        <div class="form__ex__buttons">
                                            <button class="form__ex__button btn__1"><span>sign in</span></button>
                                            <a href="/ozi/register"
                                               class="form__ex__button btn__2" ><span>sign up</span></a>
                                        </div>
                                        
                                </div>
                            </form>
                        </div>
                </div>
                    
                </div>

                
                <!-- info end -->
            </section>
            
        </main>
        <footer class="footer">
            <jsp:include page="./components/footer.jsp" />
        </footer>
    </body>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
