<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>friends</title>
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
        <!-- header start -->
        <div class="header">
            <jsp:include page="./components/login_header.jsp" />
        </div>
        <!-- header end -->
        <main class="main">
            <section class="intro__home">
                <!-- form start -->
                <div class="wrapper__form">
                    <form class="form" id="form" method="post" action="/ozi/register">
                        <h2 class="form__title-top">let's start your</h2>
                        <h1 class="form__title section__title">Registration</h1>

                        <div class="photo__input">
                            <!-- photo start -->
                            <div class="input__item">

                                <div class="container">
                                    <input class="hid" type="file" accept="${pageContext.request.contextPath}/image/*" hidden>
                                    <div class="img-area" data-img="">
                                        <i class='bx bxs-cloud-upload icon'></i>
                                        <h3>Upload Image</h3>
                                        <p>Image size must be less than <span>2MB</span></p>
                                    </div>
                                    <button class="select-image" id="file">Upload a photo</button>
                                </div>

                            </div>

                            <!-- photo end -->

                            <!-- input start -->
                            <div class="input__items">

                                <div class="input__item">
                                    <div class="form__group">
                                        <input type="text" id="name" name="name" class="form__input" placeholder=" " required>
                                        <label class="form__label">Name</label>
                                        <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                        <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                        <small>Error message</small>
                                    </div>

                                    <div class="form__group">
                                        <input type="text" id="surname" name="surname" class="form__input" placeholder=" " required>
                                        <label class="form__label">Surname</label>
                                        <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                        <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                        <small>Error message</small>
                                    </div>

                                    <div class="form__group">
                                <span class="form__span"><input type="text" id="city" name="city" class="form__input" placeholder=" ">
                                <label class="form__label mark">City</label></span>
                                    </div>
                                </div>

                                <div class="input__item">
                                    <div class="form__gender">
                                        <label class="gender__label">Sex</label>
                                        <div class="radio__group">
                                            <div class="form__radio_group">
                                                <label class="radio__lable">
                                                    <input type="radio" id="male" value="m" name="sex">
                                                    male
                                                    <span></span>
                                                </label>

                                            </div>
                                            <div class="form__radio_group">
                                                <label class="radio__lable">
                                                    <input type="radio" id="female" value="f" name="sex">
                                                    female
                                                    <span></span>
                                                </label>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            <!-- input end -->


                        </div>
                        <!-- date start -->
                        <div class="date__section">

                            <div class="date__place">
                                <div class="date__title">Date of birth</div>
                                <input  class="form__input date__input" type="date" name="birthday" max="2023"
                                        id="dateOfBirth" required>
                            </div>
                            <div class="check__date">
                                <label class="custom-checkbox">
                                    <input type="checkbox" value="value-1">
                                    <span>Hide the date of birth from other users</span>
                                </label>
                            </div>

                        </div>
                        <!-- date end -->
                        <!-- input__phone-password start -->
                        <div class="password__section">
                            <div class="input__item inline__in">
                                <div class="form__group new__margin">
                                    <input type="tel" id="phone" name="phone" class="form__input inline__input" placeholder=" " required>
                                    <label class="form__label">Phone</label>
                                    <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                    <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                    <small>Error message</small>
                                </div>

                                <div class="form__group new__margin">
                                    <input class="form__input inline__input " name="password" type="password" id="password" placeholder=" " required>
                                    <label class="form__label">Password</label>
                                    <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                    <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                    <small>Error message</small>


                                </div>

                                <div class="form__group new__margin">
                                    <input class="form__input inline__input password__input" id="password2" type="password" placeholder=" " required>
                                    <label class="form__label"> Repeat the password</label>
                                    <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                    <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                    <small>Error message</small>
                                    <div class="check__date password__check">
                                        <label class="custom-checkbox">
                                            <input type="checkbox" class="checkbox" onclick="myFunction()"> <span>Show the password</span>
                                        </label>

                                    </div>
                                </div>
                                <div class="form__group new__margin">
                                    <input type="email" id="email" name="email" class="form__input inline__input" placeholder=" " required>
                                    <label class="form__label">Email</label>
                                    <i class='bx bx-check-circle fas fa-check-circle' style='color:#64ed9d' ></i>
                                    <i class='bx bx-error-circle fas fa-exclamation-circle' style='color:#b098ee' ></i>
                                    <small>Error message</small>
                                </div>
                            </div>
                        </div>
                        <!-- input__phone-password end -->
                        <!-- registration__button start -->
                        <div class="registration__button">
                            <button type="submit" class="form__button get__registratoin">
                                <span>Register</span></button>
                        </div>
                        <div class="form__account">Already have an account?
                            <a class="form__button" href="http://localhost:8080/login.jsp">sign in</a>
                        </div>

                    </form>
                        <!-- registration__button end -->
                </div>

                <!-- form end -->
            </section>

        </main>
       <script src="${pageContext.request.contextPath}/js/main.js"></script>

    </body>
</html>
