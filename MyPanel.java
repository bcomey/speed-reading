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
  final JButton AButton = new JButton("A");
  final JButton BButton = new JButton("B");
  final JButton CButton = new JButton("C");
  final JButton DButton = new JButton("D");

  boolean reading;
  Font f1;
  Question q1 = new Question("What is the correct?", new String[]{"Z", "X", "Y", "L"}, "C");
  Question q2 = new Question("Another question:", new String[]{"A", "B", "C", "D"}, "A");
  Text testText = new Text("This is a test to see if the button functionality is completely working #great and good and all that stuff let's see try pressing the button, #because I don't know what else to write here", new Question[]{q1, q2});
  int currQuestion;
  String letters = "ABCD";
  int score;

	public MyPanel(){
    setLayout(null);  //JPanel method that initializes the layout    

    reading = true;
    currQuestion = 0;
    score = 0;
    f1 = new Font("Segoe UI", Font.PLAIN,  35);

    doneButton.setBounds(265, 600, 100, 50);
    AButton.setBounds(265, 600, 100, 50);
    BButton.setBounds(375, 600, 100, 50);
    CButton.setBounds(485, 600, 100, 50);
    DButton.setBounds(595, 600, 100, 50);
    this.add(doneButton);
    this.add(AButton);
    this.add(BButton);
    this.add(CButton);
    this.add(DButton);
    doneButton.setVisible(true);
    setAnswerButtonVisibility(false);
    doneButton.addActionListener(this);
    AButton.addActionListener(this);
    BButton.addActionListener(this);
    CButton.addActionListener(this);
    DButton.addActionListener(this);
    


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
    // display questions and answers
    else {
        g.drawString(testText.questionsList[currQuestion].content, 50, 50);
        for (int i=0; i<4; i++) {
          g.drawString(letters.charAt(i) + ": " + testText.questionsList[currQuestion].answerOptions[i], 50, 200+(40*i));
        }
    }

    //display score
    g.drawString("Score: " + score,1050,710 );
      
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
      doneButton.setVisible(false);
          setAnswerButtonVisibility(true);

		}
    if (event.getSource() == AButton) {
      handleAnswer("A");
		}
    if (event.getSource() == BButton) {
        handleAnswer("B");
		}
    if (event.getSource() == CButton) {
        handleAnswer("C");
		}
    if (event.getSource() == DButton) {
        handleAnswer("D");
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

  public void setAnswerButtonVisibility(boolean set) {
    AButton.setVisible(set);
    BButton.setVisible(set);
    CButton.setVisible(set);
    DButton.setVisible(set);
  }

  public void handleAnswer(String answer) {
      if ( (testText.questionsList[currQuestion].answer).equals(answer) ) {
        score++;
      }
      currQuestion++;
      if (currQuestion > testText.questionsList.length-1) {
        reading = true;
        doneButton.setVisible(true);
        setAnswerButtonVisibility(false);

        currQuestion = 0;
      }
  }


}

