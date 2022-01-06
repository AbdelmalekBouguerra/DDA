package Servlet;

import DAO.HISDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/PrintHIS")

public class PrintHIS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HISDAO hisdao = new HISDAO();
        // displaying his table
        LinkedHashMap<String, String> infoList = hisdao.getAllHIS();
        StringBuilder HIS = new StringBuilder();

        for (int j = 0; j < infoList.size(); j++) {
            if (!(infoList.get("Matricule"+j) == null)) {
                HIS.append("\n{");
                HIS.append("\"matricule\" : \"").append(infoList.get("Matricule" + j)).append("\",\n");
//                HIS.append("\"nom\" : \"").append(infoList.get("Nom" + j)).append("\",\n");
                HIS.append("\"date\" : \"").append(infoList.get("Date" + j)).append("\",\n");
                HIS.append("\"action\" : \"").append(infoList.get("TypeAction" + j)).append("\"\n");
                HIS.append("},\n");
            }else break;
        }
        request.setAttribute("HIS_Table", HIS.toString());
        request.getRequestDispatcher("Historique.jsp").forward(request, response);
    }
}
