<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput-1.2.2.js" />" type="text/javascript"></script>

    <script type="text/javascript">
        jQuery(function ($) {
            $.mask.definitions['~'] = '[+-]';
            $('#date').mask('99/99/9999');
            $('#phone').mask('(999) 999-9999');
            $('#phoneext').mask("(999) 999-9999? x99999");
            $("#tin").mask("99-9999999");
            $("#ssn").mask("999-99-9999");
            $("#product").mask("a*-999-a999");
            $("#eyescript").mask("~9.99 ~9.99 999");
        });
    </script>
</head>
<body>
<div id="wrapper">
    <header><h1>Kvartirant - здесь снять квартиру легко!</h1></header>

    <div class="main_content">
        <nav id="full_menu">
            <a href="<c:url value="/" />" class="menu_button left_position">
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
                <form enctype="multipart/form-data" action="/add" method="POST">
                    <div class="request_container">
                        <select class="big_block" name="status">
                            <c:forEach items="${mapStatues}" var="item">
                                <option value="${item.key}" }>${item.value}</option>
                            </c:forEach>
                        </select>

                        <select class="big_block" name="type">
                            <c:forEach items="${mapTypes}" var="item">
                                <option value="${item.key}" }>${item.value}</option>
                            </c:forEach>
                        </select>

                        <select class="big_block" name="floor">
                            <option value="1">1 этаж</option>
                            <option value="2">2 этаж</option>
                            <option value="3">3 этаж</option>
                            <option value="4">4 этаж</option>
                            <option value="5">5 этаж</option>
                            <option value="6">6 этаж</option>
                            <option value="7">7 этаж</option>
                            <option value="8">8 этаж</option>
                            <option value="9">9 этаж</option>
                            <option value="0">выше 9</option>
                        </select>

                        <select class="big_block" name="location">
                            <c:forEach items="${mapLocations}" var="item">
                                <option value="${item.key}" }>${item.value}</option>
                            </c:forEach>
                        </select>

                        <input class="big_block" type="text" name="street" required placeholder="Адрес"
                               autocomplete="off">
                        <input class="big_block" type="text" name="price" placeholder="Цена"
                               required maxlength="9" pattern="\d+" autocomplete="off">
                        <input id="phone" class="big_block" type="text" name="phone" required placeholder="Телефон">
                        <input class="big_block" type="text" name="name" required placeholder="Имя">

                        <textarea class="big_block description" name="description"
                                  placeholder="Введите описание (до 500 символов)" rows="5" maxlength="500"></textarea>

                        <div>
                            <div class="upload_form">Загрузите фотографии:</div>
                            <br>
                            <div class="upload_form"><input type="file" name="photo"
                                                            accept="image/jpeg,image/png" multiple></div>
                        </div>

                        <div>
                            <button class="big_button" type="submit">Разместить</button>
                        </div>
                    </div><!-- .request_container left_position -->
                </form>
            </div><!-- .search_form -->
        </div><!-- #content -->

        <article>
            <h2>Lorem ipsum</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean lacus massa, pretium quis consequat tempor,
            tempor commodo ante. Nunc sed enim vel metus viverra porta. Nullam pretium viverra feugiat. Duis ullamcorper
            dolor id sapien cursus finibus. In ac quam sollicitudin, aliquam velit sit amet, blandit enim. Praesent eu
            interdum ipsum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis
            egestas. Mauris sollicitudin imperdiet eros a ornare. Duis tristique, justo eget posuere tincidunt, massa
            lorem volutpat orci, in semper tortor nunc at augue. Aliquam tincidunt felis elementum lacus condimentum
            auctor. Nam non nibh mauris. Ut aliquet diam vel dui venenatis, dictum fringilla turpis accumsan.</p>

            <p>Praesent pulvinar felis quis eleifend mollis. Donec efficitur quis ex a lacinia. Nunc sodales viverra lectus
            varius bibendum. Aliquam maximus turpis justo, ut pulvinar ante commodo sit amet. Donec viverra magna quis
            dolor elementum pulvinar. Aenean sed eros felis. Nunc a sagittis libero, dapibus lacinia nisl. Pellentesque
            habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse ac est sed
            felis commodo hendrerit. Sed tempus porta tortor eu commodo. Proin gravida urna vitae posuere laoreet. Morbi
            eros urna, elementum nec nisl eu, dapibus congue nisi. Vestibulum aliquam dui leo, ac aliquet dolor ornare
            vel. Sed a purus lacinia augue fringilla aliquet. Quisque suscipit leo lectus, a venenatis neque rutrum
            congue. Pellentesque leo nunc, vehicula vitae magna non, porttitor fringilla quam.</p>

            <p>Suspendisse potenti. Vestibulum sed tellus auctor, aliquam nibh a, sollicitudin tellus. Nam ullamcorper
            condimentum nisl, nec iaculis felis sollicitudin eu. Donec gravida et libero ut euismod. Praesent non
            posuere tellus. Sed orci velit, sollicitudin vitae metus id, mattis tincidunt quam. Maecenas auctor
            condimentum est, non pellentesque orci pulvinar ut. Integer facilisis dui sit amet lorem ultrices, nec
            accumsan turpis facilisis. Donec facilisis mauris dictum tellus euismod fermentum.</p>
        </article>
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
