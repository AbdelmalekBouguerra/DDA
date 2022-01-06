package Servlet;

import Classes.DATE;
import DAO.STATDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/PrintSTAT")
public class PrintSTAT extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // calling stat class and assign today date.
        STATDAO statdao;
        statdao = new STATDAO();

        // Counting ATS creation and consultation.
        int LoginToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "login au server");
        int ATSDoToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "Telechargement de ATS");
        int PBDoToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "Telechargement de PB");
        int ATCDoToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "Telechargement de ATC");
        int REDoToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "Telechargement de RE");
        int REDDoToday = statdao.getCountAction(DATE.GetDayNum(),DATE.GetMonthNum(),DATE.GetYear(),
                "Telechargement de RED");
        int totalDownload = ATSDoToday + PBDoToday + ATCDoToday + REDoToday + REDDoToday;
        System.out.println("LoginToday : "+LoginToday);
        System.out.println("ATSDoToday : "+ATSDoToday);
        System.out.println("PBDoToday : "+PBDoToday);
        System.out.println("ATCDoToday : "+ATCDoToday);
        System.out.println("REDoToday : "+REDoToday);
        System.out.println("REDDoToday : "+REDDoToday);
        System.out.println("totalDownload : "+totalDownload);

//        StringBuilder ATSDoByMonth = statdao.getDownlandByMonth(DATE.GetYear(),"Telechargement de ATS");
//        StringBuilder PBDoByMonth = statdao.getDownlandByMonth(DATE.GetYear(),"Telechargement de PB");
        request.setAttribute("LoginToday",LoginToday);
        request.setAttribute("ATSDoToday",ATSDoToday);
        request.setAttribute("PBDoToday",PBDoToday);
        request.setAttribute("ATCDoToday",ATCDoToday);
        request.setAttribute("REDoToday",REDoToday);
        request.setAttribute("REDDoToday",REDDoToday);
        request.setAttribute("totalDownload",totalDownload);


        request.getRequestDispatcher("Statistics.jsp").forward(request,response);

    }

}
