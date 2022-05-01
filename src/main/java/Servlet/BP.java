package Servlet;

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

@WebServlet("/BP")
public class BP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MAT = (String) session.getAttribute("username");
        String type = (String) session.getAttribute("type");
        String EmpMAT = (String) session.getAttribute("matricule");
        String code = (String) session.getAttribute("code");
        String Fonc = (String) session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin", adminDash);
        request.setAttribute("user", admin);
        String month = request.getParameter("month");
        String year = request.getParameter("year");

        // check if doc is not for the user and change MAT to the entered value
        if ("BPmat".equals(type)) {
            MAT = EmpMAT;
            session.removeAttribute("type");
            // check if matricule entered exist
            if (!PERSDAO.isMatriculeExist(MAT, year)) {
                request.setAttribute("invalidUname", "Matricule invalide");
                request.getRequestDispatcher("MatriculeBP.jsp").forward(request, response);
                return;
            }
            // check if matricule is associated to the user region or privilege
                if (!(REFDAO.getCode(MAT).equals(code) || code.equals("DGP") || code.equals("SuperAdmin"))) {
                request.setAttribute("invalidUname", "Matricule n'appartient pas à votre region");
                request.getRequestDispatcher("MatriculeBP.jsp").forward(request, response);
                return;
            }
        }
        //check if user have RUB data in the selected month.
        System.out.println("isUserExist : " + Printer.isUserExist(MAT, year, month));
        if (!(Printer.isUserExist(MAT, year, month))) {
            System.out.println("Rub user do not exist");
            request.setAttribute("error", "<script>\n" +
                    "    $(document).ready(function(){\n" +
                    "        $(\"#myModal\").modal('show');\n" +
                    "    });\n" +
                    "</script>\n" +
                    "<div id=\"myModal\" class=\"modal fade\">\n" +
                    "    <div class=\"modal-dialog\">\n" +
                    "        <div class=\"modal-content\">\n" +
                    "            <div class=\"modal-header\">\n" +
                    "                <h5 class=\"modal-title\">erreur</h5>\n" +
                    "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n" +
                    "            </div>\n" +
                    "            <div class=\"modal-body\">\n" +
                    "                <p>Vous avez pas bulletin de paie ce mois</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>");
            request.getRequestDispatcher("datePicker.jsp").forward(request, response);
            return;
        }

        // start creating BP file.
        Printer printer = new Printer(MAT);
        printer.PrintBP(month, year);

        String fileName = "PB" + MAT + "_" + year + "_" + month + ".pdf";

        response.addHeader("Content-Disposition", "inline; filename=Bulletin de paie.pdf");
        response.setContentType("application/pdf");

        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("RESULT/BulletinPaie/" + fileName);

        int read = 0;
        byte[] bytes = new byte[1024];

        OutputStream os = response.getOutputStream();
        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }
}
