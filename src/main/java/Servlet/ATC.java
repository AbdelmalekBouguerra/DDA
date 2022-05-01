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

@WebServlet("/ATC")
public class ATC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MAT = (String) session.getAttribute("username");
        String EmpMAT = (String) session.getAttribute("matricule");
        String type = (String) session.getAttribute("type");


        String Fonc = (String) session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin", adminDash);
        request.setAttribute("user", admin);
        String code = (String) session.getAttribute("code");

        String datepicker_start = request.getParameter("experience-start");
        String datepicker_end = request.getParameter("experience-end");
        String baseSS_start = request.getParameter("baseSS-start");
        String baseSS_end = request.getParameter("baseSS-end");
        String[] StartSplit = baseSS_end.split("-");

        String year = StartSplit[0];
        String month = StartSplit[1];

        String yearPers;
        if (DATE.GetMonthNum().equals("01")) {
            yearPers = String.format("%02d", (Integer.parseInt(DATE.GetYear()) - 1));
        } else {
            yearPers = DATE.GetYear();
        }

        if ("ATCmat".equals(type)) {
            MAT = EmpMAT;
            session.removeAttribute("type");
            if (!(PERSDAO.isMatriculeExist(MAT, yearPers))) {
                request.setAttribute("invalidUname", "Matricule invalide");
                request.getRequestDispatcher("MatriculeATC.jsp").forward(request, response);
                return;
            }
            if (!(REFDAO.getCode(MAT).equals(code) || code.equals("DGP") || code.equals("SuperAdmin"))) {
                request.setAttribute("invalidUname", "Matricule n'appartient pas à votre region");
                request.getRequestDispatcher("MatriculeATC.jsp").forward(request, response);
                return;
            }
        }
        Printer printer = new Printer(MAT);
        int sl = Printer.numberOfMonths(baseSS_start, baseSS_end);

        System.out.println("session attribute is " + MAT);

        String fileName = "ATC" + MAT + "_" + year + "_" + DATE.GetMonthNum() + ".pdf";

        printer.PrintATC(datepicker_start, datepicker_end, year, Integer.parseInt(month), sl);

        response.addHeader("Content-Disposition", "inline; filename=Attestation de travail CNAS.pdf");

        response.setContentType("application/pdf");

        ServletContext ctx = getServletContext();

        InputStream is = ctx.getResourceAsStream("RESULT/" + fileName);

        int read;

        byte bytes[] = new byte[1024];

        OutputStream os = response.getOutputStream();
        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }
}
