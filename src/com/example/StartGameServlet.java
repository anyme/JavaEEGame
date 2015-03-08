package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.LoggerClass.LOGGER;

/**
 * Created by anastasia on 04/03/15.
 */
@WebServlet(name = "StartGameServlet")
public class StartGameServlet extends HttpServlet {
    private final String errorNull = "Bad request";
    private final String errorEmpty = "Requested file not found on server";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");
        response.sendError(500, errorNull);
        if (filename == null) {
            LOGGER.severe(errorNull);
            response.sendError(500, errorNull);
            return;
        }
        if (FileCache.cache.get(filename) == null)  {
            LOGGER.severe(errorEmpty);
            response.sendError(500, errorEmpty);
            return;
        }

        MowGameClass mowGame = new MowGameClass();
        try {
            mowGame.initBoard(FileCache.cache.get(filename));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            response.sendError(500, e.getMessage());
        }
        mowGame.play();
        mowGame.getResults(filename);
    }
}
