package hug_fall_legs;
import javax.swing.JFrame;

public class runMainWindow{
	public static void main(String[] args) {
		MainWindow myFrame = new MainWindow();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(1000, 800); // set frame size
		myFrame.setVisible(true); // display frame
	}
}
