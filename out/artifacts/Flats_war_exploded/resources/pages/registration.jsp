<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>MySite.com</title>
    <meta name="description" content="">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-language" content="ru">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="author" content="Michael Kutsenko">
    <meta name="copyright" content="Michael Kutsenko, 2016">

    <link href="<c:url value="/resources/css/core.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/template.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/additional.css" />" rel="stylesheet"/>
</head>

<body>
<div id="wrapper">
    <header><h1>Kvartirant - здесь снять квартиру легко!</h1></header>

    <div class="main_content">
        <nav id="full_menu">
            <a href="/" class="menu_button left_position">
                <div class=button>Kvartirant</div>
            </a>

            <sec:authorize access="!isAuthenticated()">
                <a href="<c:url value="/security/login" />" class="menu_button conspicuous">
                    <div class=button>Войти</div>
                </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/security/logout" />" class="menu_button conspicuous">
                    Выйти
                </a>
            </sec:authorize>

            <a href="/zdat_kvartiru" class="menu_button conspicuous">
                <div class=button>+Дать объявление</div>
            </a>

            <a href="#" class="menu_button">
                <div class=button>Аналитика</div>
            </a>

            <a href="#" class="menu_button">
                <div class=button>Блог</div>
            </a>
        </nav>

        <div id="content" class="main_page">

            <div class="search_form">
                <form:form method="POST" modelAttribute="userForm">
                    <div class="request_container">
                        <spring:bind path="username">
                            <div class="control_containeer">
                                <form:input type="text" path="username" class="login_control" placeholder="Логин"
                                            required="required" autofocus="true"></form:input>

                                <span class="error_message">
                                    <form:errors path="username"></form:errors>
                                </span>
                            </div>
                        </spring:bind>

                        <spring:bind path="designationName">
                            <div class="control_container">
                                <span class="error_message">
                                    Укажите название организации, либо личное имя (если вы частное лицо)
                                </span>
                                <form:input type="text" path="designationName" class="login_control"
                                            placeholder="Отображаемое имя" required="required"></form:input>
                            </div>
                        </spring:bind>

                        <spring:bind path="password">
                            <div class="control_container">
                                <form:input type="password" path="password" class="login_control" placeholder="Пароль"
                                            required="required"></form:input>

                                <span class="error_message">
                                    <form:errors path="password"></form:errors>
                                </span>
                            </div>
                        </spring:bind>

                        <spring:bind path="confirmPassword">
                            <div class="control_container">
                                <form:input type="password" path="confirmPassword" class="login_control"
                                            placeholder="Подтверждение пароля" required="required"></form:input>

                                <span class="error_message">
                                    <form:errors path="confirmPassword"></form:errors>
                                </span>
                            </div>
                        </spring:bind>

                        <button class="big_button" type="submit">Зарегестрироваться</button>
                    </div>
                </form:form>
            </div><!-- .search_form -->
        </div><!-- .main_content -->
    </div><!-- #content -->
</div><!-- .main_content -->

<footer>
    <div class='footer_body'>
        &#169 Michael Kutsenko, 2016.<br>
        Связаться с автором идеи и разработчиком: <b>mikhaillo@i.ua</b>.<br>
        <b>PS: ищу работу as java-developer)</b>
    </div>
</footer>
</div><!-- #wrapper -->
</body>
</html>