//********************************************************************
//Moving Circle      Author: Gabriel B.
//
//Plays a 2d game of ping pong.
//********************************************************************

package movingCircle;

import javax.swing.JFrame;

public class MovingCircleGame {
	/**
	 * Creates and displays the application frame
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Direction");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MovingCirclePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
