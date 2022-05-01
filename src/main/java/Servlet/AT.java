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

@WebServlet("/AT")
public class AT extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String MAT = (String) session.getAttribute("username");
        String type = (String) session.getAttribute("type");
        String EmpMAT = (String) session.getAttribute("matricule");
        String Fonc = (String) session.getAttribute("Fonction");
        String code = (String) session.getAttribute("code");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");

        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin", adminDash);
        request.setAttribute("user", admin);

        String day = DATE.GetDayNum();

        String year;
        if (DATE.GetMonthNum().equals("01")) {
            year = String.format("%02d", (Integer.parseInt(DATE.GetYear()) - 1));
        } else {
            year = DATE.GetYear();
        }
        // check if doc is not for the user and change MAT to the entered value
        if ("ATmat".equals(type)) {
            MAT = EmpMAT;
            session.removeAttribute("type");
            // check if matricule entered exist
            if (!PERSDAO.isMatriculeExist(MAT, year)) {
                request.setAttribute("invalidUname", "Matricule invalide");
                request.getRequestDispatcher("MatriculeAT.jsp").forward(request, response);
                return;
            }
            // check if matricule is associated to the user region or privilege
            if (!(REFDAO.getCode(MAT).equals(code) || code.equals("DGP") || code.equals("SuperAdmin"))) {
                request.setAttribute("invalidUname", "Matricule n'appartient pas à votre region");
                request.getRequestDispatcher("MatriculeAT.jsp").forward(request, response);
                return;
            }
        }
        // start creating AT file.
        Printer printer = new Printer(MAT);
        printer.PrintAT();

        String fileName = "ATS" + MAT + "_" + day + "_" + DATE.GetMonthNum() + "_" + year + ".pdf";
        response.addHeader("Content-Disposition", "inline; filename=Attestation de travail.pdf");

        response.setContentType("application/pdf");
        ServletContext ctx = getServletContext();

        InputStream is = ctx.getResourceAsStream("RESULT/AttestationDeTravail/" + fileName);

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
