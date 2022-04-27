package Servlet;

import Classes.DATE;
import DAO.HISDAO;
import DAO.PERSDAO;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/* The Java file upload Servlet example */

@WebServlet(name = "PERSUpload", urlPatterns = { "/persupload" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class PERSUpload extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String year = (String) session.getAttribute("yearPERS");
        String month = (String) session.getAttribute("monthPERS");

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        /* creation new dir */
        String filePath = "C:\\upload\\PERS"+DATE.now();
        System.out.println(filePath);
        File file = new File(filePath);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){

            System.out.println("Directory created successfully");
        }else{
            try {
                FileUtils.cleanDirectory(file); //clean out directory (this is optional -- but good know)
                System.out.println("cleaning"+filePath);
                FileUtils.forceDelete(file); //delete directory
                System.out.println("deleating"+filePath);
                FileUtils.forceMkdir(file); //create directory
                System.out.println("creating"+filePath);
                System.out.println("Directory created successfully");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Sorry couldn’t create specified directory");
            }
        }

        for (Part part : request.getParts()) {
            part.write(filePath+"\\"+fileName);
        }

        session.setAttribute("PERSfilePath",filePath);

        PERSDAO PERSdao = new PERSDAO();
        // updating str table
        PERSdao.setPERSXLS(filePath+"\\"+fileName,year,month);
        // track action
        String MAT = (String)session.getAttribute("username");

        HISDAO hisdao = new HISDAO();

        hisdao.saveAction(MAT,"Upload fiche pers dbf de "+month+" "+year);


        System.out.println("The PERS file uploaded successfully");

        request.getRequestDispatcher("PERS_Table.jsp").forward(request, response);
    }

}