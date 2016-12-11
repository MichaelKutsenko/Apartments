<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="<c:url value="/resources/css/result_page.css" />" rel="stylesheet"/>
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

        <div id="main">
            <div class="toolbar">
                <form action="/search" method="POST">

                    <select class="big_block" name="location">
                        <c:set var="select" value="${param.location}"></c:set>
                        <c:forEach items="${mapLocations}" var="item">
                            <option value="${item.key}" ${item.key == select ? 'selected' : ''}>${item.value}</option>
                        </c:forEach>
                    </select>

                    <select class="big_block" name="status">
                        <c:set var="select" value="${param.status}"></c:set>
                        <c:forEach items="${mapStatues}" var="item">
                            <option value="${item.key}" ${item.key == select ? 'selected' : ''}>${item.value}</option>
                        </c:forEach>
                    </select>

                    <select class="little_block" name="min_price">
                        <c:set var="select" value="${param.min_price}"></c:set>
                        <c:forEach items="${mapMinPrices}" var="item">
                            <option value="${item.key}" ${item.key == select ? 'selected' : ''}>${item.value}</option>
                        </c:forEach>
                    </select>

                    <select class="little_block" name="max_price">
                        <c:set var="select" value="${param.max_price}"></c:set>
                        <c:forEach items="${mapMaxPrices}" var="item">
                            <option value="${item.key}" ${item.key == select ? 'selected' : ''}>${item.value}</option>
                        </c:forEach>
                    </select>

                    <select class="little_block" name="type">
                        <c:set var="select" value="${param.type}"></c:set>
                        <c:forEach items="${mapTypes}" var="item">
                            <option value="${item.key}" ${item.key == select ? 'selected' : ''}>${item.value}</option>
                        </c:forEach>
                    </select>

                    <input type="submit" value="Поиск">
                </form>
            </div>

            <div class="results_container">

                <c:forEach items="${apartments}" var="apartment">
                    <div class="flat_container">
                        <div class="image_container">
                            <c:choose>
                                <c:when test="${fn:length(apartment.photos) > 0}">
                                    <div>
                                        <img height="150" width="150" src="/image/${apartment.id}?serial_number=0">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <img height="150" width="150" src="/resources/img/without_photo_preview.png">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="flat_descriptor">
                            <div class="price">
                                    ${apartment.price}грн
                            </div>

                            <div class="address">
                                    ${mapLocations[apartment.location]};
                                <br>
                                    ${apartment.street}
                            </div>

                            <!-- <div class="reiting">
                                reiting here
                            </div> -->

                            <div class="features">
                                <c:set var="floor" value="${apartment.floor}"></c:set>
                                    ${floor != 0 ? floor : '>9'} этаж, ${mapTypes[apartment.type]}
                            </div>

                            <div class="info">
                                <div class="contacts">
                                        ${apartment.phone}
                                </div>

                                <a href="/search/apartment?id=${apartment.id}" class="info_button">
                                    Детально...
                                </a>
                            </div>
                        </div><!-- .flat_descriptor -->

                        <div class="favorite">
                            O
                        </div>
                    </div>
                    <!-- .flat_container -->
                </c:forEach>

            </div><!-- .results_container -->
        </div><!-- #main -->
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