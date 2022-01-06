package Servlet;

import Classes.DATE;
import DAO.HISDAO;
import DAO.LOCDAO;
import DAO.STRDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
/* The Java file upload Servlet example */

@WebServlet(name = "LOCUpload", urlPatterns = { "/locupload" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class LOCUpload extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        /* creation new dir */
        String filePath = "C:\\upload\\LOC"+DATE.now();
        System.out.println(filePath);
        File file = new File(filePath);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){

            System.out.println("Directory created successfully");
        }else{

            System.out.println("Sorry couldn’t create specified directory");
        }

        for (Part part : request.getParts()) {
            part.write(filePath+"\\"+fileName);
        }
        HttpSession session = request.getSession();
        session.setAttribute("LOCfilePath",filePath);

        LOCDAO locdao = new LOCDAO();
        // updating str table
        locdao.setLOC(filePath+"\\"+fileName);
        // track action
        String MAT = (String)session.getAttribute("username");
        HISDAO hisdao = new HISDAO();
        hisdao.saveAction(MAT,"Upload fiche loc dbf");
        System.out.println("The LOC file uploaded successfully");
        request.getRequestDispatcher("LOC_Table.jsp").forward(request, response);
    }

}