package game;


import java.util.InputMismatchException;

import myexceptions.EndGameException;
import game.elements.Grid;




public class HumanPlayer extends Player {
	private int numPlayer;
	private String name ;
	private int score;


	public HumanPlayer(String name,  int score , int numPlayer){

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

	public boolean play( Grid g , Game game) throws EndGameException {
		boolean error;
		boolean valide = true ;
		String msg;
		int column =0;
	    do {
	    	error =false;

        try {

        	if (Game.scanner.hasNextInt()) {
		 column = Game.scanner.nextInt();
		 Game.scanner.nextLine();
		 }
        	else {
        		if ((msg=Game.scanner.nextLine()).equals("sortir")) {

        			throw new EndGameException();
        		}
        		else {

        			game.writer.println("Erreur saisie colonne "+ msg);
                	System.out.println("Erreur saisie colonne "+ msg);
        			throw new InputMismatchException(); }
        	}
        }
        catch (InputMismatchException e){
        	error=true;
        }


        } while (error);


		--column;
		if ( (0 > column ) || ( column >= g.getWidth() )) {
			valide = false;
			game.writer.println("Erreur colonne non valide "+ String.valueOf(column+1));
			System.out.println("Erreur colonne non valide "+ String.valueOf(column+1));
			return valide;
		}
		else {

			  valide=insertInColumn(g,column,numPlayer);
				game.writer.println("Joueur "+ String.valueOf(numPlayer) + " joue " + String.valueOf(column+1) );
				if (valide == false ){
					game.writer.println("Erreur colonne pleine "+ String.valueOf(column+1));
				 System.out.println("Erreur colonne pleine "+ String.valueOf(column+1));
			   }


		}

		return valide ;
		}



}
