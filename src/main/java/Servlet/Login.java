package Servlet;

import Classes.LDAP;
import DAO.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/Login")
public class Login extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        LoginDAO loginDAO = new LoginDAO();
        LDAP ldap = new LDAP();
//        if (ldap.connect(uname, pass)) {
            if (loginDAO.check(uname)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", loginDAO.getMat(uname));
                System.out.println("matricule :"+loginDAO.getMat(uname));
                session.setAttribute("admin", loginDAO.isAdmin(uname));
                request.getRequestDispatcher("accueil").forward(request, response);

            } else {
                uname = "value=\"" + uname + '"';
                pass = "value=\"" + pass + '"';
                request.setAttribute("uname", uname);
                request.setAttribute("pass", pass);
                request.setAttribute("invalidPass", "cord incorrect");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
//       } else {
//            uname = "value=\"" + uname + '"';
//            pass = "value=\"" + pass + '"';
//            request.setAttribute("uname", uname);
//            request.setAttribute("pass", pass);
//            request.setAttribute("invalidUname", "error connection LDAP");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
    }
}

