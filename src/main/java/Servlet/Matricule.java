package Servlet;


import Classes.DATE;
import Classes.Printer;
import DAO.LoginDAO;
import DAO.PERSDAO;
import DAO.REFDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/Matricule")
public class Matricule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String matricule = request.getParameter("mat");
        String type = request.getParameter("type");

        String year = DATE.GetYear();
        if (DATE.GetMonthNum().equals("01")) year = String.valueOf(Integer.parseInt(DATE.GetYear()) - 1);
        System.out.println("matricule :"+matricule);
        System.out.println("year :"+year);
        if (PERSDAO.isMatriculeExist(matricule, year)) {
            session.setAttribute("matricule", matricule);
            session.setAttribute("type", type);
            System.out.println("entre if statement");
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
                case "ATmat":
                    request.getRequestDispatcher("MatriculeAT.jsp").forward(request, response);
                    break;
                case "ATC":
                    request.getRequestDispatcher("MatriculeATC.jsp").forward(request, response);
                    break;
                case "BPmat":
                    request.getRequestDispatcher("MatriculeBP.jsp").forward(request, response);
                    break;
                case "REmat":
                    request.getRequestDispatcher("MatriculeRE.jsp").forward(request, response);
                    break;
                case "REDmat":
                    request.getRequestDispatcher("MatriculeRED.jsp").forward(request, response);
                    break;
            }
        }
    }
}
