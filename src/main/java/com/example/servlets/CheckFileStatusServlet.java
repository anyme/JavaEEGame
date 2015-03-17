package com.example.servlets;

import com.example.utility.FileCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anastasia on 04/03/15.
 */
@WebServlet(name = "CheckFileStatusServlet")
public class CheckFileStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");
        if (FileCache.cache.get(filename) != null) {
            response.getWriter().write("<message>success</message>");
        } else {
            response.getWriter().write("<message>fail</message>");
        }
    }
}
