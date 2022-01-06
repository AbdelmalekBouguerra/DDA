package Servlet;

import DAO.LOCDAO;
import DAO.STRDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/PrintLOC")

public class PrintLOC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOCDAO locdao = new LOCDAO();
        // displaying str table
        LinkedHashMap<String, String> infoList = locdao.getAllLOC();
        StringBuilder LOC = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : infoList.entrySet()) {
            if (i % 2 != 0 ) LOC.append("<tr>\n");
            LOC.append("<td>").append(entry.getValue()).append("</td>\n");
            System.out.println("SetLOC ///// "+entry.getValue());
            if (i % 2 == 0 ) LOC.append("</tr>\n");
            i++;

        }

        request.setAttribute("LOC_Table", LOC.toString());
        request.getRequestDispatcher("LOC_Table.jsp").forward(request, response);
    }
}
