package com.myprojects.twitchplus.servelt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.twitchplus.external.TwitchClient;
import com.myprojects.twitchplus.external.TwitchException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get gamename from request url
        String gameName = request.getParameter("game_name");
        TwitchClient client = new TwitchClient();

        //let the client know the returned data is in Json format
        response.setContentType("application/json;charset=UTF-8");

        try {
            if (gameName != null) {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchGame(gameName)));
            } else {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}

