<%@ page import="procedures.FindInDB" %>
<%@ page import="Entity.KableEntity" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>

<style>
    <%@include file="/tags/table.css"%>
</style>

<script type="text/javascript">
    <%@include file="/tags/tableSort.js"%>
</script>


<html>
<head>
    <title>Результат поиска</title>
</head>
<body>


<div align="center">
<% response.setCharacterEncoding("windows-1251");
    request.setCharacterEncoding("windows-1251");
        String cabel = request.getParameter("cabel");
        System.out.println(cabel);
        String volume = request.getParameter("volume");
        System.out.println(volume);
        String price = request.getParameter("price");
        System.out.println(price);
        String volumeHigerThen0 = request.getParameter("volume");
        out.println("Вы запросили: " + cabel);
        if(volume!=null){out.println(" показывать только с остатком/");}
        if(price!=null){out.println("  показывать только с ценой/");}
%>

</div>
<br>





<div class="tg-wrap">
    <table align="center">
        <tr>
            <td class="thd" onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Тип кабеля</td>
            <td class="thd" onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Количество</td>
            <td class="thd" onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Цена</td>
            <td class="thd" onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Склад</td>
            <td class="thd" onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Актуальность</td>
        </tr>
        <%
            FindInDB findInDB = new FindInDB();
            List<KableEntity> list = findInDB.findByLIST(cabel);
            for (KableEntity kableEntity : list) {
                if(volume!=null && kableEntity.getKableVolume()<=0 ){continue;}
                if(price!=null && kableEntity.getKablePrice()<=0){continue;}
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



    <br><footer>

    <div align="center">
        <a href="index.jsp">вернуться к поиску</a>
       </div>
    </footer>
<br>
</body>
</html>
