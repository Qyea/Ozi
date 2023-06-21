# Ozi

## 1. Описание
Проект разрабатывался студентами 2 курса ФПМИ БГУ в рамках изучения курса “Технологии программирования”. Цель данного проекта –создание социальной сети, которая позволит пользователям общаться между собой, делиться новостями, а также находить новых друзей. Социальная сеть будет предоставлять возможность создавать личные профили, настраивать значение статуса, а также использовать различные функции, такие как чаты, таймер, новостные ленты и т.д. Каждый пользователь имеет возможность находить новых знакомых, читать чужие посты. Отличительной особенностью нашей социальной сети стал Pomodoro timer, который позволяет пользователям не отвлекаться во время работы и грамотно расписать свои планы. Только зарегистрированные пользователи могут пользоваться нашей социальной сетью, что означает, что все пользователи могут быть уверены в полной конфиденциальности своих данных.

## 2. Используемые технологии

Для разработки данного проекта мы использовали технологии, указанные в следующем списке:
-	Java 19
-	Apache Tomcat - контейнер сервлетов Java для развертывания веб-приложений
-	Maven - инструмент для управления зависимостями, сборки проекта и управления жизненным циклом проекта
-	MySQL - СУБД проекта
-	Apache Logging Log4j2 - библиотека для логирования
-	JSTL - библиотека тегов для разработки веб-приложений с использованием технологии JSP
-	io.swagger - набор инструментов и библиотек, предназначенных для работы со Swagger
-	Свой пул соединений(Connection Pool) для управления подключениями к базе данных.
-	JUnit - фреймворк для модульного тестирования
-	GlassFish Jersey - реализация Java API для RESTful веб-сервисов (JAX-RS)
-	HTML
-	CSS
-	JavaScript

Список шаблонов для проекта, созданных в Figma:
1.	Главная страница проекта (home page).
2.	Страница для регистрации (sign up).
3.	Страница для авторизации (sign in).
4.	Страница друзей пользователя (friends).
5.	Страница подписчиков пользователя (followers).
6.	Страница сообщений (message page).
7.	Страница поиска (search).
8.	Страница Pomodoro Timer’а (Pomodoro timer).
      
      Ознакомиться с макетами можно, перейдя по ссылке https://www.figma.com/file/vedqO3ETEmaVMvQUtWzpau/Ozi?node-id=0%3A1&t=MhEy3NsRbbTlJoih-0

## 3. Use case diagram

![usecase1](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/usecase1.jpg)

![usercase2](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/usecase2.jpg)

## 4. ER-модель базы данных

![dbmodel](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/db.jpg)

## 5. Диаграммы классов

### Entity

![entity](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/entity.png)

### DAO

![dao](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/dao.png)

### Service

![service](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/service.jpg)

### Servlet

![servlet](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/servlet.jpg)

## 6. Диаграмма пакетов

![usercase2](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/packages.png)

## 7. Диаграмма компонетов

![components](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/components.png)

## 8. Диаграмма развертывания

![deployment](https://github.com/KrollikRoddzer/Ozi_Back-end/blob/main/docImages/deployment.jpg)


