package Servlet;
import Classes.DATE;
import DAO.HISDAO;
import DAO.STRDAO;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

@WebServlet(name = "STRUpload", urlPatterns = { "/strupload" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class STRUpload extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        /* creation new dir */
        String filePath = "C:\\upload\\STR"+DATE.now();
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
        session.setAttribute("STRfilePath",filePath);

        STRDAO strdao = new STRDAO();
        // updating str table
        strdao.setSTR(filePath+"\\"+fileName);
        // track action
        String MAT = (String)session.getAttribute("username");
        HISDAO hisdao = new HISDAO();
        hisdao.saveAction(MAT,"Upload fiche str dbf");
        System.out.println("The STR file uploaded successfully");
        request.getRequestDispatcher("STR_Table.jsp").forward(request, response);
    }

}