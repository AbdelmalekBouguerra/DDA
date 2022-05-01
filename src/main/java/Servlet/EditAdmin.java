package Servlet;

import DAO.USERDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditAdmin")
public class EditAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String son = request.getParameter("SonInput");
        String role = request.getParameter("role");

        System.out.println("SonInput " + son);
        System.out.println("role "  + role);

        USERDAO userdao = new USERDAO();
        if (USERDAO.isUsernameExist(son)){
            try {
                userdao.setAdmin(son,role);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("PrintUSER").forward(request, response);
        }else {
            request.setAttribute("invalide", "<script>alert('son inccorect')</script>");
            request.getRequestDispatcher("PrintUSER").forward(request, response);
        }
    }
}
