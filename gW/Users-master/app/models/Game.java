package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

@Entity
public class Game extends Model {
	@Id
	public Long id;

	@Required
	public String email;
	public String password;
	public Board board;

	public Game(String email, String password,Board board) {
		this.email = email;
		this.password = "password";
	}
	
	public Game() {
		this.password = "password";
		this.board = new Board();
		this.board.set(8);
		this.board.fill(5);
	}

	public static Game findById(Long id) {
		return finder.byId(id);
	}

	public static List<Game> all() {
		return finder.all();
	}

	public static void create(Game user) {
		user.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	public static Finder<Long, Game> finder = new Finder(Long.class, Game.class);
	
}
