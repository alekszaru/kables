<%@ page language="java" contentType="text/html; charset=windows-1251" pageEncoding="windows-1251"%>

<html>
<head>
    <title>
        Поиск кабеля
    </title>
</head>
<header>
    <div align="center">
        <a href="admin.jsp">
    <img src="https://elektro-baza.com.ua/image/data/watermark/elektro-baza.png" title="Интернет-магазин электротоваров Elektro-Baza.com.ua" alt="Интернет-магазин электротоваров Elektro-Baza.com.ua" >
        </a>
    </div>
</header>


<body>
<br>
<br>
<br>
<div align="center">
    <form method="post" action="table.jsp">
    Введите запрос <input type="text" name="cabel" align="">

        <input type="submit" value="Найти" align="center">
        <br>
        <br>
        <input id="checkBoxVolume" type="checkbox" name="volume" > Искать только с остатком <br>
        <input id="checkBoxPrice" type="checkbox" name="price" > Искать только сценой <br>

</form>
</div>

<br>
<div align="center">
    Ведите поиск по формату запроса:
    <br>
    АВВГ 3х25
    <br>
    КГ 1х16
    <br>
    ПВ 3 2,5
</div>
<br>
<br>

</body>
</html>
