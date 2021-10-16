package game;

import java.util.Random;

import game.elements.Grid;


public class IaPlayer extends Player {

	private int numPlayer;
	private String name ;
	private int score;




	public IaPlayer (String name, int score , int numPlayer){

		this.name = name ;
		this.score = score ;
		this.numPlayer =numPlayer ;

	}


	public int getNumPlayer(){
		return numPlayer;
	}
	public  String getName(){
		return name;
	}
	public  int getScore(){
		return score;
	}
	public  void increaseScore(){
		++this.score;
	}

	public boolean isFull(Grid g , int column) {
		boolean full = true ;
		for (int i = 0 ; i < g.getLength() ; ++i) {
			if (g.getTypeBox(i,column) == 0) {
				full =false;
				return full;
			}
		}
		return full;

	}
	public boolean insertInColumn(Grid g , int column , int type) {
		boolean valide = true;
		int row= g.getLength() - 1;

		while ( valide == true && (g.getTypeBox(row,column)!= 0)) {
			if (row == 0)  {
				valide = false  ;

			}
			else {--row; }
		}
		if (valide == true ) {
			g.setType(row,column,type);
		}

		return valide;

	}

	public boolean play( Grid g ,Game game) {
		boolean valide =true ;
		int move = 0;
		if ( this.name.equals("minimax")) {

			 move = bestMove(g);
		}

		else {


			 Random r = new Random();
		     move= r.nextInt((g.getWidth() - 1) + 1) + 1;


			--move;
		}



		if ( (0 > move ) || (move >= g.getWidth() )) {
			valide = false;
		}
		else {

	        valide=insertInColumn(g,move,numPlayer);
					if (valide){
			game.writer.println("Joueur "+ String.valueOf(numPlayer) + " joue " + String.valueOf(move+1) );
		        }
					else {
						game.writer.println("Erreur colonne pleine "+ String.valueOf(move+1));
						System.out.println("Erreur colonne pleine "+ String.valueOf(move+1));
					}

		}

		return valide ;
		}

	public int bestMove(Grid g) {


		  int bestScore = -20000;
		 int move=0;

		    for ( int j = 0; j < g.getWidth(); ++j) {

		      if (!isFull(g,j)) {
		    	  Grid copy=g.copyGrid();
		    	  insertInColumn(copy,j,numPlayer);
		        int score = minimax(copy, 6, false);


		        if (score > bestScore) {
		          bestScore = score;
		          move = j;

		        }
		      }

		  }
		  return move;

		}

	public int getGameScore(Grid g) {
			int score;
			int win =g.CheckWin();
			if ( g.nullManche() == true ) {
				score =0;
			}
			else if ( win == numPlayer) {


				score = 10;
			}
			else if ( win  == 0 ) {
				score = -1;
			}
			else {
				score = -10;
			}


			return score;

		}

	public int  minimax(Grid g, int depth, boolean isMaximizing) {
		   int winner = getGameScore(g);

		  if (winner != -1 || depth == 0 ) {

		    return winner;
		  }
		  else {

		  if (isMaximizing) {

		    int bestScore = -20000;

		      for (int j = 0; j < g.getWidth() ; ++j) {

		    	  if (!isFull(g,j)) {
		    		  Grid copy=g.copyGrid();
			    	  insertInColumn(copy,j,numPlayer);
		          int score = minimax(copy, depth - 1, false);

		          bestScore = Math.max(score, bestScore);
		        }
		      }

		    return  bestScore;
		  } else {

		    int bestScore = 20000;
		     int human;
		     if (numPlayer == 1) {
		    	 human =2;
		     }
		     else {human = 1;}
		     for (int j = 0; j < g.getWidth() ; ++j) {

		    	 if (!isFull(g,j)) {
		    		 Grid copy=g.copyGrid();
			    	  insertInColumn(copy,j,human);
		          int score = minimax(copy, depth - 1, true);

		          bestScore = Math.min(score, bestScore);
		        }
		      }

		    return  bestScore;
		  }
		}
	}


}
