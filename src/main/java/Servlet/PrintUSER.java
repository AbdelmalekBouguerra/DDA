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
        for (int j = 0; j < infoList.size(); j++) {
            if (!(infoList.get("Matricule"+j) == null)) {
                USER.append("\n{");
                USER.append("\"matricule\" : \"").append(infoList.get("Matricule" + j)).append("\",\n");
                USER.append("\"pass\" : \"").append(infoList.get("Pass" + j)).append("\",\n");
                USER.append("\"admin\" : \"").append(infoList.get("Admin" + j)).append("\",\n");
                USER.append("},\n");
            }else break;
        }
        request.setAttribute("USER_Table", USER.toString());
        request.getRequestDispatcher("USER_Table.jsp").forward(request, response);
    }
}
