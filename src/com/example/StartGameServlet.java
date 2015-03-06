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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");

        if (filename == null) { return; }
        if (FileCache.cache.get(filename) == null)  { return; }

        MowGameClass mowGame = new MowGameClass();
        mowGame.initBoard(FileCache.cache.get(filename));
        mowGame.play();
        mowGame.getResults(filename);
    }
}
