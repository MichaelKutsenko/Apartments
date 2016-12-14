<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>MySite.com</title>
    <meta name="description" content="">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-language" content="ru">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="author" content="Michael Kutsenko">
    <meta name="copyright" content="Michael Kutsenko, 2016">

    <link href="<c:url value="/resources/css/core.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/template.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/result_page.css" />" rel="stylesheet"/>

    <link href="<c:url value="/resources/css/owl.carousel.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/owl.theme.css" />" rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/owl.carousel.js" />" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#slider").owlCarousel({
                navigation: true, // Show next and prev buttons
                slideSpeed: 300,
                paginationSpeed: 400,
                singleItem: true,
//                autoPlay: true
            });
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

        <div id="main">
            <div class="toolbar">
            </div><!-- .toolbar -->

            <div id="apartment_descriptor">
                <header id="apartment_header">
                    <h1>
                        <c:set var="type" value="${apartment.type}"></c:set>
                        <c:choose>
                            <c:when test="${type == 'STUDIO'}">
                                Однокомнатная квартира
                            </c:when>
                            <c:when test="${type == 'TWO_ROOM'}">
                                Двухкомнатная квартира
                            </c:when>
                            <c:when test="${type == 'THREE_ROOM'}">
                                Трехкомнатная квартира
                            </c:when>
                            <c:when test="${type == 'MAX_ROOM'}">
                                Квартира (больше трех комнат)
                            </c:when>
                            <c:when test="${type == 'ROOM_WITHOUT_OWNER'}">
                                Отдельная комната в квартире без хозяев
                            </c:when>
                            <c:when test="${type == 'ROOM_WITH_OWNER'}">
                                Отдельная комната в квартире с хозяевами
                            </c:when>
                            <c:when test="${type == 'BED_PLACE'}">
                                Койкоместо
                            </c:when>
                        </c:choose>
                    </h1>

                    <div class="top_address">
                        <p>
                            <fmt:formatDate value="${apartment.date}" pattern="HH:mm dd.MM.YYYY"/><br>
                            ${apartment.user.designationName}
                        </p>

                        <p>
                            ${apartment.street}
                            <c:set var="floor" value="${apartment.floor}"></c:set>
                            <c:choose>
                                <c:when test="${floor != 0}">
                                    (${floor} этаж);
                                </c:when>
                                <c:otherwise>
                                    (выше 9 этажа);
                                </c:otherwise>
                            </c:choose>
                            ${mapLocations[apartment.location]}; г. Киев
                        </p>
                    </div>

                    <div class="top_price">
                        ${apartment.price}грн
                        <div class="phone_item">&#128241;${apartment.phone}</div>
                    </div>
                </header><!-- #apartment_header -->

                <section>

                    <div id="slider">
                        <c:choose>
                            <c:when test="${fn:length(photos) gt 0}">
                                <c:forEach items="${photos}" var="photo">
                                    <div>
                                        <img class="slide" src="/image/${apartment.id}?serial_number=${photo.serial}">
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div>
                                    <img class="slide" src="/resources/img/without_photo.png">
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div><!-- #slider -->

                    <div class="description">
                        <p>${apartment.description}</p>
                        <p><b>${apartment.phone} ${apartment.name}</b></p>
                    </div><!-- .description -->
                </section>
            </div><!-- #apartment_descriptor -->
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
