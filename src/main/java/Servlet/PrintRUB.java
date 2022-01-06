package Servlet;

import Classes.DATE;
import DAO.RUBDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/PrintRUB")

public class PrintRUB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        HttpSession session = request.getSession();
        session.setAttribute("monthRUB",month);
        session.setAttribute("yearRUB",year);
        RUBDAO RUBdao = new RUBDAO();
        // displaying RUB table
        LinkedHashMap<String, String> infoList = RUBdao.getAllRUB(year,month);
        StringBuilder RUB = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : infoList.entrySet()) {
            if (i == 1 ) RUB.append("<tr>\n");
            RUB.append("<td>").append(entry.getValue()).append("</td>\n");
            if (i == 9) {
                RUB.append("</tr>\n");
                i = 1;
            }else {
                i++;
            }
        }
        request.setAttribute("RUB_Table", RUB.toString());
        request.getRequestDispatcher("RUB_Table.jsp").forward(request, response);
    }
}
