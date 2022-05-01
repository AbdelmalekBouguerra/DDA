package Servlet;

import DAO.USERDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/PrintUSER")

public class PrintUSER extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        USERDAO userdao = new USERDAO();
        // displaying user table
        LinkedHashMap<String, String> infoList = userdao.getAllUsers();
        StringBuilder USER = new StringBuilder();
        int i = 1;
        String id = "";
        for (Map.Entry<String, String> entry : infoList.entrySet()) {
            if (i == 1) {
                USER.append("<tr>\n");
                id = entry.getValue();
            }
            USER.append("<td>").append(entry.getValue()).append("</td>\n");
            if (i == 3) {
                USER.append("<td>").append("<button name=\"id\" id='modif' onclick='showValue(this);'" +
                                " data-toggle=\"modal\" data-target=\"#exampleModal\" value=\"").append(id).
                        append("\" type=\"submit\">Modifier</button> <form id=\"form\" method=\"get\"" +
                                " action=\"EditAdmin\"><input type=\"hidden\" name = \"SonInput\" value=\"").append(id)
                        .append("\">" + "<input name =\"role\" type=\"hidden\" value=\"user\">"+
                                "<button type = \"submit\">Supprimer</button></form>").append("</td>\n");
                USER.append("</tr>\n");
                i = 1;
            } else {
                i++;
            }
        }
        request.setAttribute("USER_Table", USER.toString());
        request.getRequestDispatcher("USER_Table.jsp").forward(request, response);
    }
}
