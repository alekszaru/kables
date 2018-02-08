<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.File" %>
<%@ page import="main.FindMatches" %>
<%@ page import="static sun.misc.MessageUtils.out" %>
<%@ page language="java" contentType="text/html; charset=windows-1251" pageEncoding="windows-1251"%>

<html>
<head>
    <title>
        Поиск
    </title>
</head>
<header>
    <div align="center">
    <img src="https://elektro-baza.com.ua/image/data/watermark/elektro-baza.png" title="Интернет-магазин электротоваров Elektro-Baza.com.ua" alt="Интернет-магазин электротоваров Elektro-Baza.com.ua">
    </div>
</header>

<body>
<br>
<br>
<br>
<div align="center">
<form method="post" action="find">
    Введите запрос <input type="text" name="cabel" align="">
    <input type="submit" value="Найти" align="center">
</form>
</div>

<br>
<br>
<div align="center">
    Ведите поиск по формату запроса:
    <br>
    АВВГ 3х2,5
    <br>
    КГ 1х16
    <br>
    ВВГ 3х16+
</div>
<br>


</body>
</html>
