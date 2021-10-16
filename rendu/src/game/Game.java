package game;


import java.util.Scanner;

import game.display.Display;
import game.elements.Grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import myexceptions.*;
public class Game {
	private Set<Player> players;
	public static final Scanner scanner=new Scanner(System.in);
	private int winner;
	private Grid grid;
	public PrintWriter writer ;
	Game () {

		this.winner=0;
		this.players = new HashSet<Player>();
		this.grid = new Grid(7,6);


	}
	public  void addPlayer(Set<Player> players , String[] result1 , int Num )  {
		Player player=null;

		 if (result1[0].equals("humain")) {
			    player = new HumanPlayer(result1[1],0,Num);
			    players.add(player);

		   }
		   else {
			   player = new IaPlayer(result1[1],0,Num);
			    players.add(player);
		   }
	}
	public void checkExistedName(Set<Player> players , String name) throws IncorrectPlayerNameException {

		for (Player player: players) {

			if ( player.getName().equals(name)) {

				throw new IncorrectPlayerNameException();
			  }
		}

	}
	public void checkCorrectType (String type ) throws IncorrectPlayerType {

		if (  type.equals("humain") == false && (type.equals("ia")) == false ) {

		 throw new IncorrectPlayerType();
		}

	}

	public boolean playerToplay(Set<Player> players ,Grid g ,Game game , int currentNumPlayer ) throws EndGameException {
		boolean valide =true;
		for (Player player: players) {

			if ( player.getNumPlayer() == currentNumPlayer ) {

				valide =player.play(g,game);
				if (valide == true ) {
					 Display.display(game.grid);
				}
			  }
		}
		return valide;
	}



	public void gameInit(Game game) throws IncorrectPlayerNameException, IncorrectPlayerType {

		 boolean exist ;
		 String[] result1 = new String [2];
		 String[] result2 = new String [2];
		   game.grid = grid.newGrid();
		   boolean error;

           do {
						 System.out.println("Joueur 1?");
        	   error=false;
        	   try {
		   String nameandtype1 = scanner.nextLine();
		   result1 = nameandtype1.split("\\s",2);
			 if( nameandtype1.equals("sortir")){
				 game.writer.close();
				 System.exit(0);
			 }
		   if( result1.length != 2 ) {
			   System.out.println("Erreur saisie Joueur 1");
			   error=true;
		   }
		   else {
		   checkCorrectType( result1[0]);
		   break;}
        	   }
        	  catch( IncorrectPlayerType e) {
        		   error=true;
							 System.out.println("Erreur saisie Joueur 1");
        	   }

           }while(error);


		   addPlayer(game.players , result1 ,1);
		   game.writer.println("Joueur 1 est " + result1[0] +" " + result1[1]);


		   do{
				 System.out.println("Joueur 2?");
			   exist =false;
				  try {
			   String nameandtype2 = scanner.nextLine();
				 if( nameandtype2.equals("sortir")){
					 game.writer.close();
					 System.exit(0);
				 }
			   result2 = nameandtype2.split("\\s",2);
			   if( result2.length != 2 ) {
				   System.out.println("Erreur saisie Joueur 2");
				   error=true;
			   }
			   else {
			   checkCorrectType( result2[0]);
			   checkExistedName(game.players, result2[1]);
			   break;
			   }
				  }

				  catch ( IncorrectPlayerType e){

					  exist = true;
						System.out.println("Erreur saisie Joueur 2");

				  }
				  catch ( IncorrectPlayerNameException e){
					  exist = true;
						System.out.println("Erreur saisie Joueur 2");
				  }
		   } while(exist) ;
		   addPlayer(game.players, result2 ,2);
		   game.writer.println("Joueur 2 est " + result2[0] +" " + result2[1]);

	}

	public void gameRun(Game game) throws EndGameException {

		int numCurrentPlayer=1;

		   Display.display(game.grid);
		   boolean valide = true;
		   int win = 0;
		   boolean draw = false;
		   int score1 =0;
		   int score2=0;
		   String score ="Score 0 - 0";
		  game.writer.println("Manche commence");


		   while ( game.winner == 0 ) {

			 try {
			 valide=game.playerToplay(game.players,game.grid,game,numCurrentPlayer);}
			 catch(  EndGameException e) {
				 game.winner=-1;
				 break;
			 }
			 win = game.grid.CheckWin();
			 draw = game.grid.nullManche();

			 if   ( win != 0 ) {

				 System.out.printf("Joueur %d gagne",numCurrentPlayer);
				 System.out.println();
				 game.writer.println("Joueur "+ String.valueOf(numCurrentPlayer) +" gagne");
				 for (Player player: game.players) {


						if ( player.getNumPlayer() == numCurrentPlayer ) {


							player.increaseScore();
							game.grid = game.grid.newGrid();

							if (player.getScore() == 3) {

								game.winner=numCurrentPlayer;


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
					 //System.out.println("Manche commence");
					 game.writer.println("Manche commence");
				 Display.display(game.grid);



				 	}
			 }
			 else if ( draw == true ) {
				 System.out.println("Egalite");
				 game.grid = game.grid.newGrid();
				 Display.display(game.grid);
				 game.writer.println("Egalite");
				 game.writer.println(score);
				 game.writer.println("Manche commence");

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

	public static void main(String[] args) throws FileNotFoundException, IncorrectPlayerNameException, IncorrectPlayerType, EndGameException  {


		 Game game = new Game();
		 game.writer = new PrintWriter(new File("log.txt"));
		 game.gameInit(game);
		 game.gameRun(game);
		 //System.out.println("Partie finie");
		 //game.writer.println("Partie finie");
		 game.writer.close();
		 scanner.close();

	}

}
