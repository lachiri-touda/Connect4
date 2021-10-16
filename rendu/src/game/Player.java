package game;


import myexceptions.EndGameException;
import game.elements.Grid;


public abstract class Player {


	public abstract boolean play(Grid g,Game game) throws EndGameException ;
	public  abstract int getNumPlayer();
	public  abstract int getScore();
	public  abstract void increaseScore();
	public abstract  String getName();


}
