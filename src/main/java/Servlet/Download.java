package Servlet;

import DAO.HISDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/Download")
public class Download extends HttpServlet {
    public Download() {
        super();
    }
    @Override
    // TODO : ADD Loading Servlet.
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
        String filePath = (String) request.getSession().getAttribute("filePath");
        String type = (String) request.getSession().getAttribute("type");
        HttpSession session = request.getSession();
        String MAT = (String)session.getAttribute("username");
        HISDAO hisdao = new HISDAO();
        if (type.equals("BP"))
            hisdao.saveAction(MAT,"Telechargement de PB");
        else if (type.equals("ATS"))
            hisdao.saveAction(MAT,"Telechargement de ATS");
        else if (type.equals("ATC"))
            hisdao.saveAction(MAT,"Telechargement de ATC");
        else if (type.equals("RE"))
            hisdao.saveAction(MAT,"Telechargement de RE");
        else if (type.equals("RED"))
            hisdao.saveAction(MAT,"Telechargement de RED");
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);

        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
