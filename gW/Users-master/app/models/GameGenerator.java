package models;

import java.util.List;

import game.Game;
import board.Board;

public class GameGenerator  {
    
    private static Game[] games = new Game[100];
    
    public static  Game getGame(int id){
        if(games[id]==null)
            games[id] = new Game(new Board(9, 4));
            
        return games[id];
    }
}