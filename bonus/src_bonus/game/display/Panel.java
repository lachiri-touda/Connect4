package game.display;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.Game;

import game.elements.Grid;


public class Panel extends JPanel implements ActionListener  {

	private Grid grid;
	private JButton [] columns ;

	JTextArea score;

	public Panel (Grid g) {
		setLayout(null);
		this.grid=g;
		this.columns = new JButton [g.getWidth()];
		for (int i = 0 ; i <grid.getWidth() ; i++) {

		    columns[i] = new JButton(String.valueOf(i+1));
		   columns[i].setBounds((i+1)*55,55*g.getLength(),50,30);
		   columns[i].addActionListener(this);
		   add(columns[i]);

		   }

		Font f = new Font("Helvetica", Font.PLAIN, 25);
		score=new JTextArea();
		score.setFont(f);
		score.setBackground(Color.YELLOW);
        score.setBounds(0,65*grid.getLength(),600,70);
        score.setEditable(false);
        add(score);


	}

  public void paintComponent(Graphics g){
	  Graphics2D g2d = (Graphics2D) g;
	  (g2d).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
	   for(int i = 0; i < grid.getWidth(); i++){
	      for(int j = 0; j < grid.getLength(); j++){

	    	  if (grid.getTypeBox(j,i) == 0  ){
	    		  g.setColor(Color.WHITE);
	 	         g2d.fillOval((i+1)*55, j*55, 50, 50);

	    	     }
	    	     else if (grid.getTypeBox(j,i) == 1 ){

	    	    	 g.setColor(Color.RED);
	    	         g2d.fillOval((i+1)*55, j*55, 50, 50);

	    	     }
	    	     else if (grid.getTypeBox(j,i) == 2 ){

	    	    	 g.setColor(Color.ORANGE);
	    	         g2d.fillOval((i+1)*55, j*55, 50, 50);

	    	     }
	      }
	   }

  }
  public void actionPerformed(ActionEvent event ) {

	  for (int i = 0 ; i < grid.getWidth() ; i++) {
	    if(event.getSource() == columns[i] ) {

	       Game.buttonClicked = i+1;
	    }
	 }
  }
  public void newRound(Grid g) {

	  this.grid=g;
  }

  public void setScore(Game p) {

	  score.setText(p.getScore());

  }


	}
