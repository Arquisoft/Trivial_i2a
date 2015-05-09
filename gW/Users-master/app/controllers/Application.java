package controllers;

import models.User;
import models.Game;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Hola"));
    }

    public static Result greeting(String name) {
        return ok(index.render(name));
    }

    public static Result showUsers() {
        return ok(users.render(User.all(),userForm));
    }

    public static Result showUser(Long id) {
        return ok(user.render(User.findById(id)));
    }
    
    public static Result listGames() {
        return ok(games.render(Game.all(),gameForm));
    }
    
    public static Result listGame(Long id) {
        return ok(game.render(Game.findById(id)));
    }

    static Form<User>  	  userForm     = Form.form(User.class);
    static Form<Game>  	  gameForm     = Form.form(Game.class);
}
