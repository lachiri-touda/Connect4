package game;



import game.elements.Grid;




public class HumanPlayer extends Player {
	private String name ;
	private int score;
	private int numPlayer;



	public HumanPlayer(String name, int score , int numPlayer){

		this.name = name ;
		this.score = score ;
		this.numPlayer =numPlayer ;
	}

	public String getName(){
		return name;
	}

	public int getScore(){
		return score;
	}
	public int getNumPlayer(){
		return numPlayer;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setScore(int score){
		this.score=score;
	}

	public void increaseScore(){
		++this.score;
	}

	public void setNumPlayer(int numPlayer){
		this.numPlayer=numPlayer;
	}




	public boolean play( Grid g , Game game) {



		boolean valide = true ;



		if ( Game.buttonClicked !=0 ) {

		int column = Game.buttonClicked;
		int row= g.getLength() - 1;

		--column;
		if ( (0 > column )&& ( column >= g.getWidth() )) {
			valide = false;

		}
		else {

		while ( valide == true && (g.getTypeBox(row,column) != 0)) {
			if (row == 0)  {
				valide = false ;
			}
			else {--row; }
		}
		if (valide == true ) {
			g.setType(row,column,this.numPlayer);
			game.writer.println("Joueur "+ String.valueOf(numPlayer) + " joue " + String.valueOf(column+1) );

		}
		}
		Game.buttonClicked=0;

		}

		else {
			valide = false;
		}
		return valide;

		}

}
