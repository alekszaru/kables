package main;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/find")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("windows-1251");
        req.setCharacterEncoding("windows-1251");
        PrintWriter out = resp.getWriter();


        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Результат поиска</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div>");
            out.println("<a href=\"index.jsp\">вернуться к поиску</a>");
            out.println("</div>");
            out.println("</header>");
            out.println("<br>");

            String cabel = req.getParameter("cabel");
            FindMatches findMatches = new FindMatches();
            ArrayList<String> list =  findMatches.findMatchesInAvailableFiles(cabel);
            Collections.sort(list);
                if(list.isEmpty()){
                    out.println("ничего не найдено... ((( повторите запрос с другими параметрами");
                    out.println("<br>");
                }
            else {
                for (String str : list) {
                    System.out.println(str);
                    out.println(str);
                    out.println("<br>");
                }
            }
            out.println("<br>");
            out.println("<footer>");
            out.println("<div>");
            out.println("<a href=\"index.jsp\">вернуться к поиску</a>");
            out.println("</div>");
            out.println("</footer>");
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }




    }
}
