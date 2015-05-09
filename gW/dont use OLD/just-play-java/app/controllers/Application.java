package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import assets.Board;
import play.mvc.*;
import play.libs.Json;

public class Application extends Controller {

  public static Result index()  {
    return ok(views.html.main.render());
  }
  
  public static Result listBoard()  {
	  //Only for testing board should not be created here
	  Board board = new Board();
	  board.test = "testString";
	  JsonNode json = Json.toJson(board);
	    return ok(json);
	  }

}
