package controllers;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.User;
import models.GameModel;
import box.Box;
import cheese.Cheese;
import game.Game;
import question.Question;
import board.Board;
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
    
    public static Result gameAction(Long id){
        Game game = GameModel.findById(id).getGame();
        System.out.println(game);
        System.out.println(game);
        System.out.println("----------------------------------------------");
        System.out.println(id);
        System.out.println(GameModel.findById(id).toString());
        System.out.println("Primera");
        System.out.println(GameModel.findById(id).getGame().toString());
        System.out.println("Segunda");
        System.out.println(GameModel.findById(id).getGame().toString());
        return redirect(routes.Application.listGame(id));
    }
    public static Result gamePressed(Long id,Integer row,Integer column){
       Game game = GameModel.findById(id).getGame();
        boolean b = game.pressed(row,column);
        
        System.out.println("chin choppin chin choppin" + row +"  "+column);
        
            return redirect(routes.Application.listGame(id));
        
        
    }
    
    public static Result answerQuestion(Long id, String correctAnswer, String myAnswer){
       Game game = GameModel.findById(id).getGame();
        boolean b = correctAnswer.compareTo(myAnswer)==0;
        Box box = game.getBoard().getActualPlayer().getActualBox();
        Cheese quesito = box.getCheese();
         System.out.println(b);
         game.setActualQuestion(null);
         
         if(b){
             if(quesito!=null){
               game.getBoard().getActualPlayer().addQuesito(quesito);
               game.hasSomeoneWon();
           }
         }
         else
             game.nextTurn();
        
            return redirect(routes.Application.listGame(id));
        
        
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
    	GameModel game = gameForm.bindFromRequest().get();
    	game.setGame(new Game(new Board(9, 4)));
    	game.save();
    	System.out.println("Printeando");
    	System.out.println(game);
    	System.out.println(game.getID());
    	System.out.println(game.getGame());
    	return redirect(routes.Application.listGames());
    }
    
    public static Result showGamesJson() {
        return ok(Json.toJson(GameModel.all()));
    }

    public static Result showGameJson(Long id) {
        return ok(Json.toJson(GameModel.findById(id)));
    }
    static Form<GameModel>  	  gameForm     = Form.form(GameModel.class);
}
