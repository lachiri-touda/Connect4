package game.display;

import game.elements.Grid;


public class Display {

	public static void display (Grid grid){

  
    	for (int l=1 ; l <= grid.getWidth(); ++l) {
    		System.out.print(l);
    		System.out.print(' ');
    	}
    	System.out.println();

    	 for(int i=0 ; i< grid.getLength() ;++i){

				for (int j=0 ; j< grid.getWidth() ;++j){


    	     if (grid.getTypeBox(i,j) == 0  ){
    	     	System.out.print('.');
    	     }
    	     else if (grid.getTypeBox(i,j) == 1 ){
    	     	System.out.print('X');
    	     }
    	     else if (grid.getTypeBox(i,j) == 2 ){
    	     	System.out.print('O');
    	     }
    	     System.out.print(' ');


    		}

		 System.out.println();

    	}

	}


}
