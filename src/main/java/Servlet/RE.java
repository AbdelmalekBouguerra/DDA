package Servlet;


import Classes.DATE;
import Classes.Printer;
import DAO.PERSDAO;
import DAO.REFDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/RE")
public class RE extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MAT = (String) session.getAttribute("username");
        String type = (String) session.getAttribute("type");
        String EmpMAT = (String) session.getAttribute("matricule");

        String Fonc = (String) session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        String code = (String) session.getAttribute("code");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin", adminDash);
        request.setAttribute("user", admin);

        String year;
        if (DATE.GetMonthNum().equals("01")) {
            year = String.format("%02d", (Integer.parseInt(DATE.GetYear()) - 1));
        } else {
            year = DATE.GetYear();
        }
        if ("REmat".equals(type)) {
            MAT = EmpMAT;
            session.removeAttribute("type");

            if (!(PERSDAO.isMatriculeExist(MAT, year))) {
                request.setAttribute("invalidUname", "Matricule invalide");
                request.getRequestDispatcher("MatriculeRE.jsp").forward(request, response);
                return;
            }
            if (!(REFDAO.getCode(MAT).equals(code) || code.equals("DGP") || code.equals("SuperAdmin"))) {
                request.setAttribute("invalidUname", "Matricule n'appartient pas à votre region");
                request.getRequestDispatcher("MatriculeRE.jsp").forward(request, response);
                return;
            }
        }

        System.out.println("session attribute is " + MAT);
        Printer printer = new Printer(MAT);

        printer.PrintRE(DATE.GetYear());

        String fileName = "RE" + MAT + "_" + year + ".pdf";

        response.addHeader("Content-Disposition", "inline; filename=releve des emoluments.pdf");

        response.setContentType("application/pdf");
        ServletContext ctx = getServletContext();

        InputStream is = ctx.getResourceAsStream("RESULT/ReleveEmoluments/" + fileName);

        int read = 0;
        byte bytes[] = new byte[1024];

        OutputStream os = response.getOutputStream();
        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();

    }
}
