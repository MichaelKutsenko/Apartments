<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <span>${message}</span>
        </div><!-- #content -->
    </div><!-- .main_content -->

    <footer>
        <div class='footer_body'>
            &#169 Michael Kutsenko, 2016.<br>
            Связаться с автором идеи и разработчиком: <b>mikhaillo@i.ua</b>.<br>
            <b>PS: ищу работу as java-developer)</b>
        </div>
    </footer>
</div>
</body>
</html>
