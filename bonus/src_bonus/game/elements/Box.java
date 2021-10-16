package game.elements;

public class Box {
	private int x;

	private int y;

	private int type ;



	public Box(int x, int y, int type){

		this.x = x ;
		this.y = y ;
		this.type = type;
	}


	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getType(){
		return type;
	}

	public void setX(int x){
		 this.x=x;
	}

	public void setY(int y){
		 this.y=y;
	}

	public void setType(int type){
		 this.type=type;
	}


}
