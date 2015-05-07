package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;
import game.Game;
import board.Board;

@Entity
public class GameModel extends Model {
	@Id
	public Long id;

	@Required
	public String email;
	public String password;
	public Game game;

    public Long getID(){
	    return id;
	}

	public GameModel(String email, String password, Game game) {
		this.email = email;
		this.password = "password";
		this.game = game;
		System.out.println("Print constructor lleno");
	}
	
	public Game getGame(){
	    return game;
	}
	
	public void setGame(Game game){
	    this.game =  game;
	}
	
	public String getEmail(){
	    return email;
	}
	
	public void setEmail(String email){
	    this.email = email;
	}
	
	public GameModel() {
		this.password = "password";
		System.out.println("Print constructor vacio");
		
	}

	public static GameModel findById(Long id) {
		GameModel game = finder.byId(id);
		game.setGame(GameGenerator.getGame(id.intValue()));
		return game;
	}

	public static List<GameModel> all() {
		return finder.all();
	}

	public static void create(GameModel user) {
		user.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	public static Finder<Long, GameModel> finder = new Finder(Long.class, GameModel.class);
	
}
