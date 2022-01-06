package Servlet;


import Classes.DATE;
import DAO.DEMDAO;
import DAO.PERSDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/CNASADMIN")
public class CNASADMIN extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String month = String.valueOf(Integer.parseInt(DATE.GetMonthNum())-1);
        String MAT = (String)session.getAttribute("username");
        System.out.println("session attribute is "+MAT);

        PERSDAO persdao = new PERSDAO();
        DEMDAO demdao = new DEMDAO();
        // displaying demandes table
        LinkedHashMap<String, String> infoList = demdao.getAllDEM();
        StringBuilder DEM = new StringBuilder();
        int i = 1;
        String id = "";
        for (Map.Entry<String, String> entry : infoList.entrySet()) {
            if (i == 1) {
                DEM.append("<tr>\n");
                id = entry.getValue();
            }
            DEM.append("<td>").append(entry.getValue()).append("</td>\n");

            String Fonc = (String)session.getAttribute("Fonction");
            String adminDash = (String) session.getAttribute("adminDash");
            String admin = (String) session.getAttribute("admin");
            request.setAttribute("Fonction", Fonc);
            request.setAttribute("admin",adminDash);
            request.setAttribute("user", admin);

            System.out.println("SetRUB ///// "+entry.getValue());
            if (i == 4) {
                DEM.append("<td>").append("<form method=\"get\" action=\"DirectCNAS\"><button name=\"id\" value=\"").append(id).
                        append("\" type=\"submit\">Cree</button></form>").append("</td>\n");
                DEM.append("</tr>\n");
                i = 1;
            }else {
                i++;
            }
        }

        request.setAttribute("DEM_Table", DEM.toString());

        Map<String, String> userInfo = persdao.getPERS(MAT,DATE.GetYear(),DATE.GetMonthNum());

        request.getRequestDispatcher("DEM_Table.jsp").forward(request, response);

    }
}
