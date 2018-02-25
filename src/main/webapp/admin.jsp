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
        <input type="submit" name="DTS" value="�������� ������� ���" align="center">
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

            out.println("������� ��� ��������� ! ");}
        out.println("<br>");

    %>
    <br>
</div>



<div align="left">
    <form method="post">
        <input type="submit" name="MK" value="�������� ������� ������ ������" align="center">
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

            out.println("������� ������������ ��������� ! ");}
        out.println("<br>");
    %>
    <br>
</div>

<div align="left">
    <form method="post">
        <input type="submit" name="EA" value="�������� ������� ������������" align="center">
    </form>

    <%
        if(request.getParameter("EA")!=null){
            try {
                loadEAfile();
                loadPriceEA();
            }catch (NullPointerException e){
            } catch (SQLException e) {
            }

            out.println("������� ������������ ��������� ! ");}
        out.println("<br>");
    %>
    <br>
</div>

<div align="left">
    <form method="post">
        <input type="submit" name="KPKZ" value="�������� ����� �������-���������� ��������� �����" align="center">
    </form>

    <%
        if(request.getParameter("KPKZ")!=null){
            try {
                loadKPKZpriceToDB();
            }catch (NullPointerException e){
            } catch (SQLException e) {
            }

            out.println("����� ������� �������� ! ");}
        out.println("<br>");
    %>
    <br>
</div>
<br>


<footer>

    <div align="left">
        <a href="index.jsp">��������� �� �������� ������</a>
    </div>
</footer>
</body>
</html>
