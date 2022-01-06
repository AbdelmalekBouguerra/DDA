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

@WebServlet("/CNAS")
public class CNAS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MAT = (String)session.getAttribute("username");
        String month = String.valueOf(Integer.parseInt(DATE.GetMonthNum())-1);
        System.out.println("session attribute is "+MAT);
        PERSDAO persdao = new PERSDAO();
        DEMDAO demdao = new DEMDAO();

        String Fonc = (String)session.getAttribute("Fonction");
        String adminDash = (String) session.getAttribute("adminDash");
        String admin = (String) session.getAttribute("admin");
        request.setAttribute("Fonction", Fonc);
        request.setAttribute("admin",adminDash);
        request.setAttribute("user", admin);

        String email = request.getParameter("email");
        String raison = request.getParameter("raison");
        Map<String, String> userInfo = persdao.getPERS(MAT,DATE.GetYear(),DATE.GetMonthNum());

        demdao.setDEM(MAT,email);

        String text = "l'employé(e) : \n\t Nom :"+userInfo.get("Nom")
                +"\n\t Matricule : "+userInfo.get("Matricule")
                +"\n\t Fonction : "+userInfo.get("Fonction")
                +"\n demandez ATS CNAS le "+DATE.GetDayNum()+"-"+DATE.GetMonthNum()+"-"+DATE.GetYear()
                +" pour le raison suivant : "+ raison;
        EmailSender sender  = new EmailSender();
        sender.sendMail("Demande ATS CNAS",text,"iap.doc.dgp@gmail.com","gameralgerai@gmail.com",
                "Alger16035@@@");
        request.setAttribute("msg","Votre demande est bien envoyé");
            request.getRequestDispatcher("CNAS.jsp").forward(request, response);

    }
}