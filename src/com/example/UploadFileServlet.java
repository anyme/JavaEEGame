package com.example;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anastasia on 26/02/15.
 */
@WebServlet(name = "UploadFileServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10 MB
        maxFileSize=1024*1024*50,                // 50 MB
        maxRequestSize=1024*1024*100)            // 100 MB

public class UploadFileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";

        ServletInputStream inputStream = request.getInputStream();

        if (inputStream != null) {
            MowClass mowClass = new MowClass();
            try {
                InputStream in = request.getPart("file").getInputStream();
                mowClass.calculateFinalPositions(in);
                message = mowClass.getPositions();
            } catch (MowExceptions mowExceptions) {
                mowExceptions.printStackTrace();
            }
        }

        request.setAttribute("message",  message);
        request.getRequestDispatcher("/response.jsp").forward(
                request, response);
    }
}
