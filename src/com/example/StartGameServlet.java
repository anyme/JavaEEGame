package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anastasia on 04/03/15.
 */
@WebServlet(name = "StartGameServlet")
public class StartGameServlet extends HttpServlet {
    private final String errorNull = "Bad request";
    private final String errorEmpty = "Requested file not found on server";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");

        if (filename == null) { response.sendError(500, errorNull); }
        if (FileCache.cache.get(filename) == null)  { response.sendError(500, errorEmpty); }

        MowGameClass mowGame = new MowGameClass();
        try {
            mowGame.initBoard(FileCache.cache.get(filename));
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
        }
        mowGame.play();
        mowGame.getResults(filename);
    }
}
