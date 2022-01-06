package Servlet;


import Classes.DATE;
import Classes.EmailSender;
import Classes.Printer;
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

@WebServlet("/ATCEmp")
public class ATCEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MAT = (String)session.getAttribute("username");
        String id = (String)session.getAttribute("id");
        String monthf = String.valueOf(Integer.parseInt(DATE.GetMonthNum())-1);
        String datepicker_start = request.getParameter("experience-start");
        String datepicker_end = request.getParameter("experience-end");
        String baseSS_start = request.getParameter("baseSS-start");
        String baseSS_end = request.getParameter("baseSS-end");
        String[] StartSplit = baseSS_end.split("-");
        String year = StartSplit[0];
        String month = StartSplit[1];
        DEMDAO demdao = new DEMDAO();
        Printer printer = new Printer(demdao.getMAT(id));
        int sl = Printer.numberOfMonths(baseSS_start,baseSS_end);
        PERSDAO persdao = new PERSDAO();
        Map<String, String> userInfo = persdao.getPERS(demdao.getMAT(id),DATE.GetYear(),DATE.GetMonthNum());
        Map<String, String> AdminInfo = persdao.getPERS(MAT,DATE.GetYear(),DATE.GetMonthNum());
        System.out.println("session attribute is "+demdao.getMAT(id));

        String Fonc = (String)session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin",adminDash);
        request.setAttribute("user", admin);

        printer.PrintATC(datepicker_start,datepicker_end,year, Integer.parseInt(month),sl);
        session.setAttribute("filePath","C:\\RESULT\\ATC"+demdao.getMAT(id)+"_"+year+"_"+DATE.GetMonthNum()+".pdf");
        session.setAttribute("type","ATC");
        request.setAttribute("filePathSVG","RESULT/ATC"+demdao.getMAT(id)+"_"+year+"_"+DATE.GetMonthNum()+".svg");
        request.setAttribute("download", "<a href=\"Download\">download</a>");
        String text = "Bonjour monsieur "+userInfo.get("Nom")+
                "\n votre Attestation de travail CNAS est prête"+
                "\n établi  par " +AdminInfo.get("Nom")+" "+AdminInfo.get("Fonction")+
                "\n Merci de utilse notre platform"+
                "\n Cordialement";

        demdao.Done(id);
        System.out.println(demdao.getEmail(id));
        EmailSender sender = new EmailSender();
        sender.sendMailAttachments("ATS CNAS IAP",text,"C:\\RESULT\\ATC"+demdao.getMAT(id)+"_"+year+"_"
                        +DATE.GetMonthNum()+".pdf","iap.doc.dgp@gmail.com",demdao.getEmail(id),"Alger16035@@@");
        request.getRequestDispatcher("CNASADMIN").forward(request, response);

    }
}
