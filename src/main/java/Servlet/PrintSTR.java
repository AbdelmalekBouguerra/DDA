package Servlet;

import DAO.STRDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/PrintSTR")

public class PrintSTR extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        STRDAO strdao = new STRDAO();
        // displaying str table
        LinkedHashMap<String, String> infoList = strdao.getAllSTR();
        StringBuilder STR = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : infoList.entrySet()) {
            if (i % 2 != 0 ) STR.append("<tr>\n");
            STR.append("<td>").append(entry.getValue()).append("</td>\n");
            System.out.println("SetSTR ///// "+entry.getValue());
            if (i % 2 == 0 ) STR.append("</tr>\n");
            i++;

        }

        request.setAttribute("STR_Table", STR.toString());
        request.getRequestDispatcher("STR_Table.jsp").forward(request, response);
    }
}
