
import Entity.KableEntity;
import procedures.FindInDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            out.println("Вы спрашивали: " + cabel);
            out.println("<br>");
            out.println("В базе данных мы нашли: ");


            FindInDB findInDB = new FindInDB();
            ArrayList<String> list = findInDB.findByRequest(cabel);


            Collections.sort(list);
            if (list.isEmpty()) {
                out.println(", НИЧЕГО ))) ... ((( повторите запрос с другими параметрами");
                out.println("<br>");
            } else {
                out.println(list.size() + " результатов");
                out.println("<br>");
                for (String str : list) {
                    System.out.println(str);
                    out.println(str);
                    out.println("<br>");
                }
            }

            out.println("===============================================================================================");

            List<KableEntity> kableEntities = findInDB.getRezultListOfEntities();

            out.println("<br>");


//------------------------------------------------------------------------------------------------------------------------------------------------


            out.println("<div><table>");
             out.println("<tr>");
                out.println(" <th>Тип кабеля</th>");
                out.println(" <th>Количество</th>");
                out.println("<th>Цена</th>");
                out.println(" <th>Склад</th>");
                out.println("<th>Актуальность</th>");
             out.println(" </tr>");

             for(KableEntity kableEntity:kableEntities) {
                 out.println("<tr>");
                 out.println(" <th>");
                 out.println(kableEntity.getKableType());
                 out.println("</th>");

                 out.println(" <th>");
                 out.println(kableEntity.getKableVolume());
                 out.println("</th>");

                 out.println(" <th>");
                 out.println(kableEntity.getKablePrice());
                 out.println("</th>");

                 out.println(" <th>");
                 out.println(kableEntity.getKableStockCompany());
                 out.println("</th>");

                 out.println(" <th>");
                 out.println(kableEntity.getKableStockActuality());
                 out.println("</th>");

                 out.println(" </tr>");
             }

            out.println("</table></div>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<div>");
            out.println("<a href=\"table.jsp\">таблица результатов</a>");
            out.println("</div>");
            out.println("<br>");
            out.println("<br>");


//------------------------------------------------------------------------------------------------------------------------------------------------
            out.println("<br>");
            out.println("<footer>");
            out.println("<div>");
            out.println("<a href=\"index.jsp\">вернуться к поиску</a>");
            out.println("</div>");
            out.println("</footer>");
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }


    }
}
