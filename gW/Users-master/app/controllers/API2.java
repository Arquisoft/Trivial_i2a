package controllers;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.User;
import models.GameModel;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.*;
import views.html.*;

public class API2 extends Controller {

    static Form<User>  	  userForm     = Form.form(User.class);

	public static Result showUsersHATEOAS() {
		JsonNodeFactory factory = JsonNodeFactory.instance;
	    ArrayNode result = new ArrayNode(factory);
	    for (User user: User.all()) {
	      ObjectNode countryJson = Json.newObject();
	      countryJson.put("email", user.email);
	      countryJson.put("password", user.password);
	      ArrayNode links = new ArrayNode(factory);
	      ObjectNode self = Json.newObject();
	      self.put("rel", "self");
	      self.put("href", routes.API2.showUserHATEOAS(user.id).absoluteURL(request()));
	      
	      links.add(self);
	      countryJson.put("links", links);
	      result.add(countryJson);
	    }
	    return ok(result);
	}

	public static Result showUserHATEOAS(Long id) {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		
		User user = User.findById(id);
	    ObjectNode userJson = Json.newObject();
	    userJson.put("email", user.email);
	    userJson.put("password", user.password);
	    ArrayNode links = new ArrayNode(factory);
	    ObjectNode self = Json.newObject();
	    self.put("rel", "self");
	    self.put("href", routes.API2.showUserHATEOAS(user.id).absoluteURL(request()));
	    links.add(self);
	     
	    ObjectNode parent = Json.newObject();
	    parent.put("rel", "parent");
	    parent.put("href", routes.API2.showUsersHATEOAS().absoluteURL(request()));
	    links.add(parent);
	
	    userJson.put("links", links);
		return ok(userJson);
	}
	
	static Form<GameModel>  	  gameForm     = Form.form(GameModel.class);
	
	public static Result showGamesHATEOAS() {
		JsonNodeFactory factory = JsonNodeFactory.instance;
	    ArrayNode result = new ArrayNode(factory);
	    for (GameModel user: GameModel.all()) {
	      ObjectNode countryJson = Json.newObject();
	      countryJson.put("gameName", user.gameName);
	      countryJson.put("password", user.password);
	      ArrayNode links = new ArrayNode(factory);
	      ObjectNode self = Json.newObject();
	      self.put("rel", "self");
	      self.put("href", routes.API2.showUserHATEOAS(user.id).absoluteURL(request()));
	      
	      links.add(self);
	      countryJson.put("links", links);
	      result.add(countryJson);
	    }
	    return ok(result);
	}

	public static Result showGameHATEOAS(Long id) {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		
		GameModel user = GameModel.findById(id);
	    ObjectNode userJson = Json.newObject();
	    userJson.put("gameName", user.gameName);
	    userJson.put("password", user.password);
	    ArrayNode links = new ArrayNode(factory);
	    ObjectNode self = Json.newObject();
	    self.put("rel", "self");
	    self.put("href", routes.API2.showUserHATEOAS(user.id).absoluteURL(request()));
	    links.add(self);
	     
	    ObjectNode parent = Json.newObject();
	    parent.put("rel", "parent");
	    parent.put("href", routes.API2.showUsersHATEOAS().absoluteURL(request()));
	    links.add(parent);
	
	    userJson.put("links", links);
		return ok(userJson);
	}
}
