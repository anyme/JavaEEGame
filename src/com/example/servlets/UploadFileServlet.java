package com.example.servlets;

import com.example.utility.FileCache;
import com.example.utility.LoggerClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by anastasia on 26/02/15.
 */
@WebServlet(name = "UploadFileServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10 MB
        maxFileSize=1024*1024*50,                // 50 MB
        maxRequestSize=1024*1024*100)            // 100 MB

public class UploadFileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename;

        InputStream in = new BufferedInputStream(request.getPart("file").getInputStream());
        if ((filename = getFileName(request.getPart("file"))) != null) {
            FileCache.cache.put(filename, in);
            LoggerClass.LOGGER.info("File successfully uploaded");
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "" + UUID.randomUUID();
    }
}
