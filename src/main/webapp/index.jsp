<%@ page import="procedures.FindInDB" %>
<%@ page import="Entity.KableEntity" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=windows-1251" pageEncoding="windows-1251"%>

<html>
<head>
    <title>
        �����
    </title>
</head>
<header>
    <div align="center">
    <img src="https://elektro-baza.com.ua/image/data/watermark/elektro-baza.png" title="��������-������� �������������� Elektro-Baza.com.ua" alt="��������-������� �������������� Elektro-Baza.com.ua">
    </div>
</header>


<body>
<br>
<br>
<br>
<div align="center">
<%--<form method="post" action="find">--%>
    <form method="post" action="table.jsp">
    ������� ������ <input type="text" name="cabel" align="">

    <input type="submit" value="�����" align="center">
</form>
</div>

<br>
<br>
<div align="center">
    ������ ����� �� ������� �������:
    <br>
    ���� 3�2,5
    <br>
    �� 1�16
    <br>
    ��� 3�16+
</div>
<br>
<br>

</body>
</html>
