<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.05.2023
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
        <div class="user__info-row">
            <div class="user__item">
                <div class="user__info-row-container">
                    <div class="user__info-row-in">
                        <div class="user__info-row-friends">
                            <span class="user__info-row-value">
                                <form method="post">
                                   <button type="submit"  id="friend" name="friend" value="1"
                                            class="user__link-info statistics__button">${friends}</button>
                                </form>
                            </span>
                        </div>
                    </div>
                    <div class="user__info-row-lable">
                        Friends
                    </div>
                </div>
            </div>
            <div class="user__item">
                <div class="user__info-row-container">
                    <div class="user__info-row-in">
                        <div class="user__info-row-followers">
                            <span class="user__info-row-value">
                                <form method="post">
                                <button type="submit"  id="follower" name="follower" value="1"
                                        class="user__link-info statistics__button">${followers}</button>
                                </form>
                            </span>
                        </div>
                    </div>
                    <div class="user__info-row-lable">
                        followers
                    </div>
                </div>
            </div>
            <div class="user__item">
                <div class="user__info-row-container">
                    <div class="user__info-row-in">
                        <div class="user__info-row-Communities">
                            <span class="user__info-row-value">
                                <a href="#" class="user__link-info js-search">30</a>
                            </span>
                        </div>
                    </div>
                    <div class="user__info-row-lable">
                        Communities
                    </div>
                </div>
            </div>
            <div class="user__item">
                <div class="user__info-row-container">
                    <div class="user__info-row-in">
                        <div class="user__info-row-photos">
                            <span class="user__info-row-value">
                                <a href="#" class="user__link-info js-search">3</a>
                            </span>
                        </div>
                    </div>
                    <div class="user__info-row-lable">
                        photos
                    </div>
                </div>
            </div>
            <div class="user__item">
                <div class="user__info-row-container">
                    <div class="user__info-row-in">
                        <div class="user__info-row-music">
                            <span class="user__info-row-value">
                                <a href="#" class="user__link-info js-search">98</a>
                            </span>
                        </div>
                    </div>
                    <div class="user__info-row-lable">
                        music
                    </div>
                </div>
            </div>

        </div>


</body>
</html>
