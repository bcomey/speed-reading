import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Dimension;	
import java.awt.Graphics;	
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.Font;



public class MyPanel extends JPanel implements KeyListener,ActionListener
{
  JButton doneButton = new JButton("Done");
  boolean reading;
  Font f1;
  Question q1 = new Question("What is the correct?", new String[]{"Z", "X", "Y", "L"}, 2);
  Text testText = new Text("This is a test to see if the button functionality is completely working #great and good and all that stuff let's see try pressing the button, #because I don't know what else to write here", new Question[]{q1});


	public MyPanel(){
    setLayout(null);  //JPanel method that initializes the layout    

    reading = true;
    f1 = new Font("Segoe UI", Font.PLAIN,  35);

    doneButton.setBounds(265, 600, 100, 50);
    this.add(doneButton);
    doneButton.setVisible(true);
    doneButton.addActionListener(this);
    
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }


	public Dimension getPreferredSize() {
        return new Dimension(1200,750);
    }

	public void paintComponent(Graphics g)
	{
	  super.paintComponent(g);
    g.setFont(f1);
    if (reading) {
      drawStringPlus(g, testText.text, 50, 50);
    }
    else {
      for (int i=0; i<testText.questionsList.length; i++) {
        g.drawString(testText.questionsList[i].content, 50, 50);
        for (int j=0; j<4; j++) {
          g.drawString(testText.questionsList[i].answerOptions[j], 50, 200+(40*j));
        }
      }
    }
      
    }


  public void keyTyped(KeyEvent e) {} //Pointless yet necessary
  public void keyPressed(KeyEvent e) {
    int keycode = e.getKeyCode();
    }
    
    
  @Override
  public void keyReleased(KeyEvent e) {
    int keycode = e.getKeyCode();
  }

  public void actionPerformed(ActionEvent event) {
		// check to see which button was clicked
		if (event.getSource() == doneButton) {
      reading = false;
		}
    
    requestFocusInWindow();
	}

  
  public void animationLoop(){
     while (true) { 
        try {
            Thread.sleep(10);
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        
        repaint(); 
    }
  }

  

  private void drawStringPlus(Graphics g, String text, int x, int y) {
    String[] arr = text.split("#");
    for (int i=0; i<arr.length; i++) {
      g.drawString(arr[i], x, y+(i*45));
    }
  }


}

