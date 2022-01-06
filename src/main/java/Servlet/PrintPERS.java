package Servlet;

import DAO.PERSDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/PrintPERS")

public class PrintPERS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String month = request.getParameter("month");
        String year = request.getParameter("year");

        System.out.println("year "+year);
        System.out.println("month"+month);
        HttpSession session = request.getSession();
        session.setAttribute("monthPERS",month);
        session.setAttribute("yearPERS",year);
        PERSDAO persdao = new PERSDAO();

        // displaying pers table

        LinkedHashMap<String, String> infoList = persdao.getAllPERS(year,month);
        StringBuilder PERS = new StringBuilder();

        for (int i = 0; i <=infoList.size(); i++) {
            if (!(infoList.get("matricule"+i) == null)) {

                PERS.append("\n{");
                PERS.append("\"matricule\" : \"").append(infoList.get("matricule" + i)).append("\",\n");
                PERS.append("\"nom\" : \"").append(infoList.get("nom" + i)).append("\",\n");
                PERS.append("\"str\" : \"").append(infoList.get("str" + i)).append("\",\n");
                PERS.append("\"codeLieuNais\" : \"").append(infoList.get("codelieunais" + i)).append("\",\n");
                PERS.append("\"dateNais\" : \"").append(infoList.get("datenais" + i)).append("\",\n");
                PERS.append("\"sexe\" : \"").append(infoList.get("sexe" + i)).append("\",\n");
                PERS.append("\"dateRec\" : \"").append(infoList.get("daterec" + i)).append("\",\n");
                PERS.append("\"fonction\" : \"").append(infoList.get("fonction" + i)).append("\",\n");
                PERS.append("\"adresse\" : \"").append(infoList.get("adresse" + i)).append("\",\n");
                PERS.append("\"gsang\" : \"").append(infoList.get("gsang" + i)).append("\",\n");
                PERS.append("\"sf\" : \"").append(infoList.get("sf" + i)).append("\",\n");
                PERS.append("\"scjt\" : \"").append(infoList.get("scjt" + i)).append("\",\n");
                PERS.append("\"nbrEnfS10\" : \"").append(infoList.get("nbrenfs10" + i)).append("\",\n");
                PERS.append("\"nbrEnfM10\" : \"").append(infoList.get("nbrenfm10" + i)).append("\",\n");
                PERS.append("\"rib\" : \"").append(infoList.get("rib" + i)).append("\",\n");
                PERS.append("\"nssagt\" : \"").append(infoList.get("nssagt" + i)).append("\",\n");
                PERS.append("\"nssemp\" : \"").append(infoList.get("nssemp" + i)).append("\",\n");
                PERS.append("\"cpaiem\" : \"").append(infoList.get("cpaiem" + i)).append("\",\n");
                PERS.append("\"echelle\" : \"").append(infoList.get("echelle" + i)).append("\",\n");
                PERS.append("\"groupe\" : \"").append(infoList.get("groupe" + i)).append("\",\n");
                PERS.append("\"salBase\" : \"").append(infoList.get("salbase" + i)).append("\",\n");
                PERS.append("\"iag\" : \"").append(infoList.get("iag" + i)).append("\",\n");
                PERS.append("},\n");
            }
        }
        request.setAttribute("PERS_Table", PERS.toString());
        request.getRequestDispatcher("PERS_Table.jsp").forward(request, response);
    }
}
