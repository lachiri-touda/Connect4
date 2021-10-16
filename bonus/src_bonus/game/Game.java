package game;


import game.display.Window;
import game.elements.Grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import myexceptions.EndGameException;
import myexceptions.IncorrectPlayerNameException;
import myexceptions.IncorrectPlayerType;


public class Game {
	private Set<Player> players;

	public static int buttonClicked;
	public static int gameStarted;
	private int winner;
	private Grid grid;
	public PrintWriter writer ;
	 private Window f;
	 private int numRounds;

	Game () {

		this.winner=0;
		this.players = new HashSet<Player>();
		buttonClicked=0;
		gameStarted=0;
		this.grid = new Grid();

	}

	public String getScore() {
		String score=null;
		score ="       Score : ";
		for (Player player: this.players) {

			score = score + String.valueOf(player.getName()) +" ";
			score = score + String.valueOf(player.getScore()) +"  ";

				}
		return score;
	}

	public Grid getGrid() {

		return this.grid;
	}

	public void setRounds(int num) {
		this.numRounds=num;
	}
	public void setGrid(Grid g) {
		this.grid=g;
	}

	public  void addPlayer(Set<Player> players , String name , int Num )  {
		Player player=null;
		String[] result1 = name.split("\\s",2);
		 if (result1[0].equals("humain")) {
			    player = new HumanPlayer(result1[1],0,Num);
			    players.add(player);

		   }
		   else {
			   player = new IaPlayer(result1[1],0,Num);
			    players.add(player);
		   }
		 writer.println("Joueur "+ Num + " est " + result1[0] +" " + result1[1]);
	}

	public void checkExistedName(Set<Player> players , String name) throws IncorrectPlayerNameException {

		for (Player player: players) {

			if ( player.getName().equals(name)) {

				throw new IncorrectPlayerNameException();
			  }
		}

	}
	public static void checkCorrectType (String type ) throws IncorrectPlayerType {

		if (  type.equals("humain") == false && (type.equals("ia")) == false ) {

		 throw new IncorrectPlayerType();
		}

	}

	public boolean playerToplay(Set<Player> players ,Grid g ,Game p , int currentNumPlayer ) throws EndGameException {
		boolean valide =true;
		for (Player player: players) {

			if ( player.getNumPlayer() == currentNumPlayer ) {

				valide =player.play(g,p);
			  }
		}
		return valide;
	}
	public void gameInit(Game game) throws IncorrectPlayerNameException, IncorrectPlayerType {



		  game.f = new Window(game);


		   while(gameStarted == 0){
			    try {
			       Thread.sleep(200);
			    } catch(InterruptedException e) {
			    }
			}
		   String nameandtype1 = f.getNames()[0];

			   addPlayer(game.players , nameandtype1 ,1);

				  String nameandtype2 = f.getNames()[1];

			   addPlayer(game.players, nameandtype2 ,2);
			   f.setScore(game);



	}
public void gameRun(Game game ) throws EndGameException {


	int numCurrentPlayer=1;


	 boolean valide = true;
	   int win = 0;
	   boolean draw = false;
	   int score1 =0;
	   int score2=0;
	   String score =null;
	   game.writer.println("Manche commence");
	   System.out.println("Manche commence");
	   while ( game.winner == 0 ) {

		   try {
				 valide=game.playerToplay(game.players,game.grid,game,numCurrentPlayer);
				 game.f.refresh();}
				 catch(  EndGameException e) {
					 game.winner=-1;
					 break;
				 }

		   win = game.grid.CheckWin();
			 draw = game.grid.nullManche();

			 if   ( win != 0 ) {

			 System.out.printf("Joueur "+ String.valueOf(numCurrentPlayer) +" gagne");
			 System.out.println();
			 game.writer.println("Joueur "+ String.valueOf(numCurrentPlayer) +" gagne");

			 for (Player player: game.players) {


					if ( player.getNumPlayer() == numCurrentPlayer ) {
						player.increaseScore();


						if (player.getScore() == numRounds) {

							game.winner=numCurrentPlayer;
							game.f.setScore(game);
							game.f.refresh();
						JOptionPane.showMessageDialog(null, player.getName()+"\n Winner Winner Chicken Dinner :)","InfoBox: " , JOptionPane.INFORMATION_MESSAGE);

						}
						else {
							JOptionPane.showMessageDialog(null, "Player "+player.getName()+" won \n New round will begin","InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
							game.grid.newGrid();

						}
					}
					if ( player.getNumPlayer() == 1 ) {
						 score1=player.getScore();
					 }
					 if ( player.getNumPlayer() == 2 ) {
						 score2=player.getScore();
					 }
				}
			 score = "Score "+String.valueOf(score1) + " - " + String.valueOf(score2);
			 System.out.printf(score);
			 System.out.println();
			 game.writer.println(score);
			 if (game.winner == 0) {
				 System.out.println("Manche commence");
		   game.writer.println("Manche commence");
			game.f.newRound(game.grid);
			game.f.setScore(game);
			game.f.refresh();

				 }

		 }
		 else if ( draw == true ) {
			 System.out.println("Egalite");
			JOptionPane.showMessageDialog(null, "Draw match ! \n New round will begin ","InfoBox: " , JOptionPane.INFORMATION_MESSAGE);

			  game.grid.newGrid();

			 game.writer.println("Egalite");
			   game.f.newRound(game.grid);
			   game.f.setScore(game);
			   game.f.refresh();

		 }


		      if (valide) {
		      if ( numCurrentPlayer == 1 ) {

		    	  numCurrentPlayer=2;
		      }
		      else {
		      numCurrentPlayer=1;
		    		  }
		      }
	  }


}



	public static void main(String[] args) throws FileNotFoundException, IncorrectPlayerNameException, IncorrectPlayerType, EndGameException {

		 Game game = new Game();

		 game.writer = new PrintWriter(new File("log.txt"));

		 game.gameInit(game);
		 game.gameRun(game);
		 System.out.println("Partie finie");
		 game.writer.println("Partie finie");
		 game.writer.close();


	}
}
