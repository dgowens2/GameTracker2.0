package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DTG2 on 09/19/16.
 */
@RestController
//  Rest API is a JSON API over HTTP (uses HTTP to talk to network and the content it sends is JSON content)
//  Says this controller is going to have endpoints that take in data and returns JSON.

public class GameTrackerAppJsonController {
    @Autowired //repo is initialized by the framework, so we don't have to pass a connection (among other things). Uses the application properties to connect to the database.
    GameRepository games;

    @RequestMapping(path = "/games.json", method = RequestMethod.GET) //REST JSOn endpoint is NOT required to end in .json. We can name it whatever we want, but we like to be expressive.
    public List<Game> getGames() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        Iterable<Game> allGames = games.findAll();  //this is where Hibernate come into play.  Object Relational Mapping
        for (Game game : allGames) {
            gameList.add(game);
        }
        try {
            System.out.println("Catching a nap..");
            Thread.sleep(3000);
            System.out.println("Power naps are the best!!!");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return gameList;
//  this is an endpoint for a microservice
    }


    @RequestMapping(path = "/toggleGame.json", method = RequestMethod.GET)
    public List<Game> toggleGame(int gameID) {
        System.out.println("toggling game with ID " + gameID);
        Game game = games.findOne(gameID);
        game.name = "**" + game.name;
        games.save(game);

        return getGames();
    }

    @RequestMapping(path = "/addGame.json", method = RequestMethod.POST)
    public List<Game> addGame(HttpSession session, @RequestBody Game game) throws Exception {
        User user = (User)session.getAttribute("user");

        if (user == null) {
            throw new Exception("Unable to add game without an active user in the session");
        }
        game.user = user;

        games.save(game);

        return getGames();
    }

}
