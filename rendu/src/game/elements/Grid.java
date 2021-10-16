package game.elements;

import java.util.Arrays;

public class Grid {

	private int width ;
	private int length ;
	public Box [][] matrix;

	public Grid(int width, int length){

		this.width = width ;
		this.length = length ;
		this.matrix = new Box [length][width];
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
    

	public  Grid newGrid(){
		 Grid grid1= new Grid( getWidth(),getLength());
		 for(int i=0 ; i< grid1.getLength() ;++i){

				for (int j=0 ; j< grid1.getWidth() ;++j){
				   grid1.matrix[i][j]= new Box(i,j,0);

				}
			}
		return grid1;
	}


	public Grid copyGrid() {
		Grid copy = new Grid(this.width,this.length);

		for(int i=0 ; i< length ;++i){

			for (int j=0 ; j< width ;++j){
			   copy.matrix[i][j]= new Box(i,j,this.matrix[i][j].getType());

			}
		}



		return copy;
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
	
	public Box []  extractColumn(int i,int j) {
		Box[] newColumn = new Box [4];
		for ( int ii =0 ; ii <4 ; ++ii) {
			newColumn[ii]= matrix[i+ii][j];
		}
		return newColumn;
		
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

			winner = CheckWinVec(  extractColumn(i,j+jj));
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
