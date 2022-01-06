package Servlet;


import Classes.DATE;
import Classes.EmailSender;
import DAO.DEMDAO;
import DAO.PERSDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/DirectCNAS")
public class DirectCNAS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        String MAT = (String)session.getAttribute("username");

        String Fonc = (String)session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin",adminDash);
        request.setAttribute("user", admin);

        System.out.println("session attribute is "+MAT);
        session.setAttribute("id", request.getParameter("id"));
        request.getRequestDispatcher("dateCNASEmp.jsp").forward(request, response);
    }
}
