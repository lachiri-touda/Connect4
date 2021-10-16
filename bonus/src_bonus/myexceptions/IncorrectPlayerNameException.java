package myexceptions;

public class IncorrectPlayerNameException  extends Exception {
	
	
	public IncorrectPlayerNameException() {
		
		System.out.println("This name already exists ! Please enter a new name :");
		
	}


}
