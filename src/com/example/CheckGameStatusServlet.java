package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anastasia on 06/03/15.
 */
@WebServlet(name = "CheckGameStatusServlet")
public class CheckGameStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");
        String result = GameCache.cache.get(filename);
        if (result != null) {
            response.getWriter().write("<message>" + result + "</message>");
        } else {
            response.getWriter().write("<message>fail</message>");
        }
    }
}
