package game.elements;

import java.util.Arrays;

public class Grid {

	private int width ;
	private int length ;
	private Box [][] matrix;

	public Grid(){

	}

	public int getWidth(){
		return width;
	}

	public int getLength(){
		return length;
	}


   public int getTypeBox(int i , int j) {

	   return matrix[i][j].getType();
   }


	public void setWidth(int width){
		 this.width=width;
	}

	public void setLength(int length){
		 this.length=length;
	}

	public void setType(int i ,int j ,int type) {
		matrix[i][j].setType(type);
	}


	public void newGrid(){


		 this.matrix = new Box [getLength()][getWidth()];
		 for(int i=0 ; i< getLength() ;++i){

				for (int j=0 ; j< getWidth() ;++j){
				   matrix[i][j]= new Box(i,j,0);

				}
			}

	}


	public void copyGrid(Grid g) {

		this.length=g.getLength();
		this.width=g.getWidth();
		this.matrix = new Box [g.getLength()][g.getWidth()];
		for(int i=0 ; i< g.getLength() ;++i){

			for (int j=0 ; j< g.getWidth() ;++j){
			   this.matrix[i][j]= new Box(i,j,g.getTypeBox(i,j));

			}
		}


	}

	public int CheckWinVec(  Box [] vec ) {
		boolean win = true ;
		int winner=0;
		int i =0;
		while (  i < 3 ) {

			if (  vec[i].getType()  != 0  ) {


			if ( (vec[i].getType() != vec[i+1].getType())  ) {



				win = false ;
				return winner;
			}
			}
			else { win = false ;
			return winner;}
			++i;
		}

		if (win) {
			winner =vec[0].getType();
		}
		return winner;
	}

	public int CheckWin4x4 ( int i , int j) {
		int winner =0;
		Box [] newDiag = new Box [4];
		Box [] newDiagInv = new Box [4];
		for ( int ii =0; ii< 4 ; ++ii ) {

			newDiag[ii]= matrix[i+ii][j+ii];

			newDiagInv[ii]= matrix[i+ii ][3+j-ii];

			Box [] line = matrix[i+ii];
			Box [] newLine = Arrays.copyOfRange(line, j ,j+4);

		winner = CheckWinVec( newLine);
		if(	winner  != 0 ) {
			return winner ;
		}

		}
		winner = CheckWinVec(  newDiag );
		if(	winner  != 0 ) {
			return winner ;
		}

		winner = CheckWinVec(  newDiagInv );
		if(	winner  !=0 ) {
			return winner ;
		}

		for ( int jj =0; jj< 4 ; ++jj ) {

					Box[] newColumn = new Box [4];
					for ( int ii =0 ; ii <4 ; ++ii) {
						newColumn[ii]= matrix[i+ii][j+jj];
					}
					winner = CheckWinVec(  newColumn );
				if(	winner  != 0 ) {
					return winner ;
				}

				}
		return winner;
	}


	public int CheckWin() {
		int winner = 0;

		for ( int i = 0 ; i< length ; ++i) {
			for (int j=0 ; j < width  ; ++j ) {

				if ( (i <= length - 4) &&  ( j <= width-4)    ) {

					winner = CheckWin4x4(i,j);
					if(	winner != 0 ) {
						return winner ;
					}
				}
			}
		}

		return winner;
	}

	public  boolean nullManche(){

		boolean draw = true;

		 for(int i=0 ; i< length ;++i){

				for (int j=0 ; j< width ;++j){
				if(   matrix[i][j].getType() == 0 ) {
					draw= false ;
					return draw;
							}

				}
			}
		return draw;
	}


}
