package Servlet;

import Classes.DATE;
import DAO.HISDAO;
import DAO.RUBDAO;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
/* The Java file upload Servlet example */

@WebServlet(name = "RUBUpload", urlPatterns = { "/rubupload" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class RUBUpload extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String year = (String) session.getAttribute("yearRUB");
        String month = (String) session.getAttribute("monthRUB");

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        /* creation new dir */
        String filePath = "C:\\upload\\RUB"+ DATE.now();
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
        session.setAttribute("RUBfilePath",filePath+"\\"+fileName);

        RUBDAO rubdao = new RUBDAO();
        // updating RUB table
        rubdao.setRUBXLS(filePath+"\\"+fileName,year,month);
        // track action
        String MAT = (String)session.getAttribute("username");
        HISDAO hisdao = new HISDAO();
        hisdao.saveAction(MAT,"Upload fiche rub dbf de "+month+" "+year);
        request.setAttribute("msg","file uploaded successfully");
        System.out.println("The RUB file uploaded successfully");
        request.getRequestDispatcher("RUB_Table.jsp").forward(request, response);
    }

}