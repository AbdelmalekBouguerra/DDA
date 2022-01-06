package Servlet;


import DAO.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Matricule")
public class Matricule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String matricule = request.getParameter("mat");
        String type = request.getParameter("type");
        LoginDAO loginDAO = new LoginDAO();

        if (loginDAO.check(matricule)) {
            session.setAttribute("matricule", matricule);
            session.setAttribute("type", type);
            switch (type) {
                case "ATmat":
                    request.getRequestDispatcher("AT").forward(request, response);
                    break;
                case "ATCmat":
                    request.getRequestDispatcher("dateCNAS.jsp").forward(request, response);
                    break;
                case "BPmat":
                    request.getRequestDispatcher("datePicker.jsp").forward(request, response);
                    break;
                case "REmat":
                    request.getRequestDispatcher("RE").forward(request, response);
                    break;
                case "REDmat":
                    request.getRequestDispatcher("RED").forward(request, response);
                    break;
            }


        } else {
            request.setAttribute("invalidUname", "matricule incorrect");
            switch (type) {
                case "AT":
                    request.getRequestDispatcher("MatriculeAT.jsp").forward(request, response);
                    break;
                case "ATC":
                    request.getRequestDispatcher("MatriculeATC.jsp").forward(request, response);
                    break;
                case "BP":
                    request.getRequestDispatcher("MatriculeBP.jsp").forward(request, response);
                    break;
                case "RE":
                    request.getRequestDispatcher("MatriculeRE.jsp").forward(request, response);
                    break;
                case "RED":
                    request.getRequestDispatcher("MatriculeRED.jsp").forward(request, response);
                    break;
            }
        }
    }
}
