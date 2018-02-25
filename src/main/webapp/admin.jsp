<%@ page import="static procedures.LoadDTSfileToDB.loadDTSfile" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="static procedures.LoadMKfileToDB.loadMKfile" %>
<%@ page import="static procedures.LoadEAfileToDB.loadEAfile" %>
<%@ page import="static procedures.LoadEApriceToDB.loadPriceEA" %>
<%@ page import="static procedures.LoadKPKZfileToDB.loadKPKZpriceToDB" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<html>
<head>
    <title>adminka</title>
</head>
<body>
<div align="left">
    <form method="post" name="DTSform">
        <input type="submit" name="DTS" value="Обновить остатки ДТС" align="center">
    </form>


    <%
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
    %>

    <%
        if(request.getParameter("DTS")!=null){
            try {
                loadDTSfile();
            } catch (NullPointerException e) {
            } catch (SQLException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.println("Остатки ДТС обновлены ! ");}
        out.println("<br>");

    %>
    <br>
</div>



<div align="left">
    <form method="post">
        <input type="submit" name="MK" value="Обновить остатки Мастер Кабель" align="center">
    </form>

    <%
        if(request.getParameter("MK")!=null){
            try {
                loadMKfile();
            } catch (NullPointerException e) {
            } catch (SQLException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.println("Остатки МастерКабель обновлены ! ");}
        out.println("<br>");
    %>
    <br>
</div>

<div align="left">
    <form method="post">
        <input type="submit" name="EA" value="Обновить остатки Энергоальянс" align="center">
    </form>

    <%
        if(request.getParameter("EA")!=null){
            try {
                loadEAfile();
                loadPriceEA();
            }catch (NullPointerException e){
            } catch (SQLException e) {
            }

            out.println("Остатки Энергоальянс обновлены ! ");}
        out.println("<br>");
    %>
    <br>
</div>

<div align="left">
    <form method="post">
        <input type="submit" name="KPKZ" value="Обновить прайс Каменец-Подольский кабельный завод" align="center">
    </form>

    <%
        if(request.getParameter("KPKZ")!=null){
            try {
                loadKPKZpriceToDB();
            }catch (NullPointerException e){
            } catch (SQLException e) {
            }

            out.println("Прайс Каменца обновлен ! ");}
        out.println("<br>");
    %>
    <br>
</div>
<br>


<footer>

    <div align="left">
        <a href="index.jsp">вернуться на страницу поиска</a>
    </div>
</footer>
</body>
</html>
