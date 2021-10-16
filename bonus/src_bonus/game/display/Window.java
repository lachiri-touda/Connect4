package game.display;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import myexceptions.IncorrectPlayerType;
import game.Game;
import game.elements.Grid;



public class Window  implements ActionListener {

	private JFrame f;
	private JFrame form;
	 private JPanel gridPanel ;
	private JTextField tf;JTextField tf2; JLabel player1; JButton b; JLabel player2;
	private JTextField numRounds;
	JLabel rounds;
	private JTextField numRows;
	private JTextField numColumns;
	JLabel gridSize;
	private String name1;
	private String name2;

	private Game game;
	private static int WIDTH;
	private static int LENGTH;



	 public Window( Game game)  {

		   f = new JFrame();
		   form = new JFrame();
		   tf=new JTextField();
	        tf.setBounds(120,50, 150,20);
	        tf2=new JTextField();
	        tf2.setBounds(120,100, 150,20);
	        player1=new JLabel("Player 1");
	        player1.setBounds(50,50, 100,30);
	        player2=new JLabel("Player 2");
	        player2.setBounds(50,100, 100,30);
	        numRounds=new JTextField();
	        numRounds.setBounds(230,150, 80,20);
	        rounds=new JLabel("How many rounds to win ?");
	        rounds.setBounds(50,150, 260,30);
	        numRows=new JTextField();
	        numRows.setBounds(130,200, 30,20);
	        numColumns=new JTextField();
	        numColumns.setBounds(190,200, 30,20);
	        JTextArea x=new JTextArea();
	        x.setText("X");
	        x.setEditable(false);
	        x.setBounds(170,200,30,20);
	        x.setOpaque(false);
	        form.add(x);
	        gridSize=new JLabel("Grid Size :");
	        gridSize.setBounds(50,200, 80,30);


	        b=new JButton("Start");
	        b.setBounds(50,250,95,30);
	        b.addActionListener(this);
	        form.setTitle("Welcome to the game");
	        form.add(numColumns);form.add(numRows);form.add(numRounds);form.add(gridSize);form.add(rounds);
	        form.add(b);form.add(tf);form.add(player1);form.add(player2);form.add(tf2);
	        form.setSize(390,370);

	        form.setLayout(null);
	        form.setVisible(true);
	        form.setLocationRelativeTo(null);
	        form.setResizable(false);


	        this.game=game;



		    f.setBackground(Color.BLUE);

		    f.setTitle("Connect 4");

		    f.setLayout(null);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    f.setLocationRelativeTo(null);




		    f.setVisible(false);
		    f.setResizable(false);
		  }

	 public void actionPerformed(ActionEvent event ) {


		    if(event.getSource() == b ) {
		    	boolean error1;
		    	boolean error2;

		    	this.name1 = tf.getText();
		    	this.name2 = tf2.getText();

		    	error1=checkPlayer(name1);
		    	error2=checkPlayer(name2);

		    	boolean checkSize=checkGridSize(Integer.parseInt(numColumns.getText()),Integer.parseInt(numRows.getText()));
		    	if (  error1 == false && error2 == false && checkSize == true ) {

		    	game.getGrid().setWidth(Integer.parseInt(numColumns.getText()));
		    	game.getGrid().setLength(Integer.parseInt(numRows.getText()));
		    	game.setRounds(Integer.parseInt(numRounds.getText()));
		    	game.getGrid().newGrid();
		    	WIDTH=Integer.parseInt(numColumns.getText());
		    	LENGTH=Integer.parseInt(numRows.getText());
		    	f.setSize(WIDTH*50+140,60*LENGTH+120);
		    	gridPanel= new Panel(game.getGrid());
		    	f.setContentPane(gridPanel);
		    	form.dispose();
		    	f.setVisible(true);
			    f.setLocationRelativeTo(null);
		    	Game.gameStarted=1;

		    	}

		    }

	  }

	 public String []  getNames () {

		 String[] names =new String[2];

		 names[0]=this.name1;
		 names[1]=this.name2;
		 return names;

	 }
	 public boolean checkGridSize(int rows , int columns) {
		 boolean valide=false;
		 System.out.println((rows*columns) % 2);
		 if ( rows*columns >= 8 && ((rows*columns) % 2 == 0)) {
			 valide =true;
		 }
		 else {
			   JOptionPane.showMessageDialog(null, "Grid size incorrect !!", "Error: " , JOptionPane.INFORMATION_MESSAGE);

		 }

		 return valide;
	 }




	 public void refresh () {

		 f.revalidate();
		f.repaint();
	 }


	 public void newRound(Grid g ) {


		  ((Panel) this.gridPanel).newRound(g);


	  }

	 public void setScore(Game p) {



		  ((Panel) this.gridPanel).setScore(p);

	  }

	 public boolean checkPlayer(String name) {

		 boolean error;
		 String[] result1 = new String [2];


        	   error=false;
        	   try {

      			 result1 = name.split("\\s",2);
		   if( result1.length != 2 ) {

			   System.out.println("Please enter a type and a name !!");
			   error=true;
			   JOptionPane.showMessageDialog(null, "Please enter a type and a name !!", "Error: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   else {
		   Game.checkCorrectType( result1[0]);
		   }
        	   }
        	  catch( IncorrectPlayerType e) {
        		   error=true;
        		   JOptionPane.showMessageDialog(null, "Type invalide ! please choose between ia/humain :", "Error: " , JOptionPane.INFORMATION_MESSAGE);
        	   }

           return error;

	 }



}
