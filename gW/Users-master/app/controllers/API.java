package controllers;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.User;
import models.Game;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.*;
import views.html.*;

public class API extends Controller {

    public static Result showUsersJson() {
        return ok(Json.toJson(User.all()));
    }

    public static Result showUserJson(Long id) {
        return ok(Json.toJson(User.findById(id)));
    }

    public static Result newUser() {
    	User user = userForm.bindFromRequest().get();
    	user.save();
    	return redirect(routes.Application.showUsers());
    }
    
    public static Result updateUser(Long id) {
    	User user = userForm.bindFromRequest().get();
    	User.create(user);
        return redirect(routes.Application.showUsers());
    }

    public static Result deleteUser(Long id) {
    	User.delete(id);
        return redirect(routes.Application.showUsers());
    }
    
    static Form<User>  	  userForm     = Form.form(User.class);
    
    public static Result newGame() {
    	Game game = gameForm.bindFromRequest().get();
    	game.save();
    	return redirect(routes.Application.listGames());
    }
    
    public static Result showGamesJson() {
        return ok(Json.toJson(Game.all()));
    }

    public static Result showGameJson(Long id) {
        return ok(Json.toJson(Game.findById(id)));
    }
    static Form<Game>  	  gameForm     = Form.form(Game.class);
}
