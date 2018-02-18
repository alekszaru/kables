<%@ page import="procedures.FindInDB" %>
<%@ page import="Entity.KableEntity" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>

<html>
<head>
    <title>Результат поиска</title>
</head>
<body>
<div align="center">
<% response.setCharacterEncoding("windows-1251");
    request.setCharacterEncoding("windows-1251");
    String cabel = request.getParameter("cabel");
    out.println("Вы запросили: " + cabel);
%>
</div>
<br>

<link href="tags/table.css" rel="stylesheet" type="text/css">



<div class="tg-wrap">
    <table id="tg-w7VKR" class="tg" align="center">
        <tr>
            <th class="tg-t1l0">Тип Кабеля</th>
            <th class="tg-t1l0">Количество</th>
            <th class="tg-t1l0">Цена</th>
            <th class="tg-t1l0">Склад</th>
            <th class="tg-t1l0">Актуальность</th>
        </tr>
        <%
            FindInDB findInDB = new FindInDB();
            List<KableEntity> list = findInDB.findByLIST(cabel);
            for (KableEntity kableEntity : list) {
                out.println("<tr>");
                out.println("<td class=\"tg-yw4l\">");
                out.println(kableEntity.getKableFullName());
                out.println("</td>");

                out.println("<td class=\"tg-yw4l\">");
                out.println(kableEntity.getKableVolume());
                out.println("</td>");

                out.println("<td class=\"tg-yw4l\">");
                out.println(kableEntity.getKablePrice());
                out.println("</td>");

                out.println("<td class=\"tg-yw4l\">");
                out.println(kableEntity.getKableStockCompany());
                out.println("</td>");

                out.println("<td class=\"tg-yw4l\">");
                out.println(kableEntity.getKableStockActuality());
                out.println("</td>");

                out.println("</tr>");
            }%>
    </table>
</div>

<script SRC="tags/table.js" rel="script" language=JavaScript/>

    <br><footer>

    <div align="center">
        <a href="index.jsp">вернуться к поиску</a>
       </div>
    </footer>
<br>
</body>
</html>
