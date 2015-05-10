package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

@Entity
public class User extends Model {
	@Id
	public Long id;

	@Required
	public String email;
	public String password;
<<<<<<< HEAD
=======
	
	public String getEmail(){
	    return email;
	}
	
	public void setEmail(String email){
	    this.email = email;
	}
>>>>>>> origin/CrisIntegration

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public static User findById(Long id) {
		return finder.byId(id);
	}

	public static List<User> all() {
		return finder.all();
	}

	public static void create(User user) {
		user.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	public static Finder<Long, User> finder = new Finder(Long.class, User.class);
	
}
