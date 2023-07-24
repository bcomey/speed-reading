import javax.swing.JFrame;

public class Runner {
  public static void main(String args[]) 
  {    
    JFrame frame = new JFrame("Speed Reading");
    MyPanel canvas = new MyPanel();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.add(canvas);
    //frame.add(new JLabel(new ImageIcon("logo.png")));
    //emptyLabel.setPreferredSize(new Dimension(700, 400));
		frame.pack();
    frame.setSize(1200, 750);
		
		frame.setVisible(true);

    canvas.animationLoop();
  }
}