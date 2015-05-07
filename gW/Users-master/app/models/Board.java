package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cheese.BlueCheese;
import cheese.Cheese;
import cheese.GreenCheese;
import cheese.RedCheese;
import cheese.YellowCheese;
import player.Player;
import box.AbstractBox;
import box.BlueBox;
import box.Box;
import box.GreenBox;
import box.MultiCheeseBox;
import box.RedBox;
import box.ThrowAgainBox;
import box.YellowBox;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

@Entity
public class Board extends Model{ 
	
	@Required
	public int numberOfPlayers;
    List<String> a = null;
	
	public void set(int a){
	    this.numberOfPlayers=a;
	}
	
	public void fill(int b){
	   this.a = new ArrayList<String>();
	   a.add("3");
	}

	
}
