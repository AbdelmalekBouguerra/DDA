package Servlet;


import Classes.DATE;
import Classes.Printer;
import DAO.HISDAO;
import DAO.PERSDAO;
import DAO.STATDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@WebServlet("/accueil")
public class accueil extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        String admin = (String) session.getAttribute("admin");

        PERSDAO persdao = new PERSDAO();

        String month,year;
        if (DATE.GetMonthNum().equals("01")){
            month = "12";
            year = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));
        } else {
            month = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));
            year =  DATE.GetYear();
        }

        Map<String, String> infoList = persdao.getPERS(uname,year,month);

        session.setAttribute("Fonction",infoList.get("Fonction"));
        session.setAttribute("adminDash","            <li>\n" +
                "                <span class=\"material-icons icons-size\">history</span>\n" +
                "                <a href=\"PrintHIS\">consulter historiques</a>\n" +
                "            </li>\n" +
                "\n" +
                "            <li>\n" +
                "                <span class=\"material-icons icons-size\">poll</span>\n" +
                "                <a href=\"PrintSTAT\">consulter statistiques </a>\n" +
                "            </li>" +
                "            <li>\n" +
                "                <span class=\"material-icons icons-size\">admin_panel_settings</span>\n" +
                "                <a href=\"ap.jsp\">Admin Panel</a>\n" +
                "            </li>");

        String FirstName;
        System.out.println("infolist");
        System.out.println(infoList.get("nom"));
        if (infoList.get("nom") != null) {
            String[] name = Printer.splitName(infoList.get("nom"));
            FirstName = name[0].charAt(0) + name[0].substring(1).toLowerCase();
        } else FirstName = " ";
        request.setAttribute("name", FirstName);
        System.out.println("Admin :" + admin);

        // display option to access adminPanel
        if (admin.equals("SuperAdmin") || admin.equals("Admin_DGP")) {
            request.setAttribute("user", admin);
            request.setAttribute("admin", "<li>\n" +
                    "                <span class=\"material-icons icons-size\">history</span>\n" +
                    "                <a href=\"PrintHIS\">consulter historiques</a>\n" +
                    "            </li>\n" +
                    "\n" +
                    "            <li>\n" +
                    "                <span class=\"material-icons icons-size\">poll</span>\n" +
                    "                <a href=\"PrintSTAT\">consulter statistiques </a>\n" +
                    "            </li>" +
                    "            <li>\n" +
                    "                <span class=\"material-icons icons-size\">admin_panel_settings</span>\n" +
                    "                <a href=\"ap.jsp\">Admin Panel</a>\n" +
                    "            </li>");
        }

        // display normal buttons to users else buttons to chose matricule and customize the files.
        if (admin.equals("user")) {
            request.setAttribute("user", "user");
            request.setAttribute("CNAS", "<button type=\"but2\" class=\"but2\" onClick=\"javascript:window.open(href='CNAS.jsp', '_blank');\">\n" +
                    "                        <div >Demander</div>\n" +
                    "                        <i class=\"icon-arrow-right\"></i>\n" +
                    "                     </button>");
            request.setAttribute("but1", " <button type=\"but2\" class=\"but2\" onClick=\"javascript:window.open(href='AT', '_blank');\">\n" +
                    "                        <div >Télécharger</div>\n" +
                    "                        <i class=\"icon-arrow-right\"></i>\n" +
                    "                     </button>");
            request.setAttribute("but2", "<button type=\"but2\" class=\"but2\" onClick=\"javascript:window.open(href='datePicker.jsp', '_blank');\">\n" +
                    "                        <div >Télécharger</div>\n" +
                    "                        <i class=\"icon-arrow-right\"></i>\n" +
                    "                     </button>");
            request.setAttribute("but3", "<div class=\"dropdown bt2\">\n" +
                    "<button class=\"btn dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"false\" aria-expanded=\"false\">\n" +
                    "                  choisissez\n" +
                    "                </button>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"RE\" target=\"_blank\">Normale</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"RED\" target=\"_blank\">Détaillé</a>\n" +
                    "                </div>");

        } else {
            request.setAttribute("CNAS", "<div class=\"dropdown bt4\">\n" +
                    "                <button class=\"btn dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"false\" aria-expanded=\"false\">\n" +
                    "                    choisissez\n" +
                    "                </button>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"dateCNAS.jsp\" target=\"_blank\">Mon ATS CNAS</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"CNASADMIN\" target=\"_blank\">Les demandes</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"MatriculeATC.jsp\" target=\"_blank\">personnaliser</a>\n" +
                    "\n" +
                    "                </div>\n" +
                    "            </div>");
            request.setAttribute("but1", "<div class=\"dropdown bt1\">\n" +
                    "                <button class=\"btn dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"false\" aria-expanded=\"false\">\n" +
                    "                  choisissez\n" +
                    "                </button>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"AT\" target=\"_blank\">Mon attestation de travail</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"MatriculeAT.jsp\" target=\"_blank\">personnaliser</a>\n" +
                    "                </div>\n" +
                    "            </div>");

            request.setAttribute("but2", "<div class=\"dropdown bt2\">\n" +
                    "                <button class=\"btn dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"false\" aria-expanded=\"false\">\n" +
                    "                  choisissez\n" +
                    "                </button>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"datePicker.jsp\" target=\"_blank\">Mon bulletin de paie</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"MatriculeBP.jsp\" target=\"_blank\">personnaliser</a>\n" +
                    "                </div>");
            request.setAttribute("but3", "<div class=\"dropdown bt2\">\n" +
                    " <button class=\"btn dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"false\" aria-expanded=\"false\">\n" +
                    "                  choisissez\n" +
                    "                </button>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"RE\" target=\"_blank\">Mon relevé des émoluments</a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"MatriculeRE.jsp\" target=\"_blank\">relevé des émoluments personnaliser </a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"RED\" target=\"_blank\">relevé des émoluments détaille </a>\n" +
                    "                    <a class=\"dropdown-item\" href=\"MatriculeRED.jsp\" target=\"_blank\">relevé des émoluments détaille personnaliser </a>\n" +
                    "                </div>\n" +
                    "            </div>");

        }

        request.setAttribute("Fonction", infoList.get("fonction"));

//        Stats to display in the first page. (it gets deleted form the last interface)
//        STATDAO statdao = new STATDAO();
//        int bp = statdao.getDownland("Consultation de bulletin de paie");
//        int at = statdao.getDownland("Consultation de Attestation de travail");
//        int re = statdao.getDownland("Consultation de releve des emolument");
//        int red = statdao.getDownland("Consultation de releve des emolument Detaille");
//        int atc = statdao.getDownland("Consultation de Attestation de travail CNAS");
//        request.setAttribute("downloads",String.valueOf(bp+at+re+red+atc));
//        request.setAttribute("usersCount",String.valueOf(statdao.getCountUsers()));


        request.getRequestDispatcher("accueil.jsp").forward(request, response);
        HISDAO hisdao = new HISDAO();
        hisdao.saveAction(uname, "login au server");
    }
}
