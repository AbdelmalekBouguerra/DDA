package Servlet;


import Classes.DATE;
import Classes.Printer;
import DAO.PERSDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.ResultSet;

import static java.lang.System.out;

@WebServlet("/AT")
public class AT extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String MAT = (String)session.getAttribute("username");
        String type = (String)session.getAttribute("type");
        String EmpMAT = (String)session.getAttribute("matricule");
        String Fonc = (String)session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin",adminDash);
        request.setAttribute("user", admin);

        if ("ATmat".equals(type)){
            MAT = EmpMAT;
        }
        session.removeAttribute("type");
        String year = DATE.GetYear();
        String  day = DATE.GetDayNum();
        if (PERSDAO.isMatriculeExist(MAT,year)){
            Printer printer = new Printer(MAT);
            printer.PrintAT();

            String fileName = "ATS"+ MAT +"_"+ day +"_" + DATE.GetMonthNum() + "_" + year+".pdf";
            response.addHeader("Content-Disposition", "inline; filename=Attestation de travail.pdf");

            response.setContentType("application/pdf");
            ServletContext ctx = getServletContext();

            InputStream is = ctx.getResourceAsStream("RESULT/AttestationDeTravail/"+fileName);

            int read = 0;
            byte bytes[] = new byte[1024];

            OutputStream os = response.getOutputStream();
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        }else {
            request.setAttribute("invalidUname", "Matricule invalide");
            request.getRequestDispatcher("MatriculeAT.jsp").forward(request, response);
        }




    }
}
