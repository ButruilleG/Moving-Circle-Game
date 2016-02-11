//********************************************************************
//Moving Circle       Author: Gabriel B.
//
//Plays a 2d game of ping pong.
//Created before I understood Object Oriented Programming. I'm so sorry.
//********************************************************************

package movingCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MovingCirclePanel extends JPanel {
	private final int WIDTH = 304, HEIGHT = 300;

	static Random rand = new Random();

	public boolean isFary = false;
	public boolean isFarx = false;
	public boolean isFarz = false;
	public boolean isTrue = true;
	public boolean isTrue2 = true;
	public boolean coloryellow = true;
	public boolean sumoIsTrue = false;
	public boolean isSuperSpeed = false;
	public boolean isHighStakes = false;
	public boolean isSloMo = false;
	public boolean isSpeedball = false;
	public boolean modifiersON = true;
	public boolean isBigBall = false;

	Timer timer;

	public static int renew = 0;

	public static int count;
	public static int countGain = 1;

	public static int count2;
	public static int countGain2 = 1;

	public static int sloMo = 0;
	public static int speedball = 0;
	public static int sumo = 0;
	public static int superSpeed = 0;
	public static int highStakes = 0;
	public static int obstruction = 0;
	public static int bigBall = 0;
	public static int powerups = 0;

	public static int z = 0;
	public static int z_decay = 5;

	public static int x = rand.nextInt(250);
	public static int x_decay = 4;
	public static int y = rand.nextInt(250);
	public static int y_decay = 6;
	public static int ballSize = 10;

	public static int lengthAI = 50;
	public static int lengthYou = 50;

	public static int w = 0;
	public static int w_decay = 25;

	/**
	 * Constructor: sets up this panel and loads images
	 */
	public MovingCirclePanel() {
		addKeyListener(new DirectionListener());
		timer = new Timer(20, new ReboundListener());

		setBackground(Color.black);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);

		timer.start();
	}

	/**
	 * Draws the images in their current locations
	 */
	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		page.setColor(Color.gray);// background
		page.fillRect(0, 0, 304, 300);

		page.setColor(Color.yellow);
		page.fillOval(x, y, ballSize, ballSize);// ball

		page.fillRect(277, z, 2, lengthAI);// AI paddle
		page.fillRect(23, w, 2, lengthYou);// your paddle

		if (coloryellow) {
			page.setColor(Color.yellow);
		} else {
			page.setColor(Color.blue);
		}
		page.fillRect(0, 0, 2, 300);
		page.fillRect(302, 0, 2, 300);

		if (isSloMo) {
			page.drawString("Slow Mo", 135, 145);
		} else {
		}
		if (sumoIsTrue) {
			page.drawString("Sumo", 135, 145);
		} else {
		}

		if (((obstruction > 1449 && obstruction < 1531) || (obstruction > 3009 && obstruction < 30)) && modifiersON) {
			page.fillRect(70, 70, 180, 160);// Obstruction

			page.setColor(Color.black);
			page.drawString("Obstruction", 135, 145);
			obstruction++;

			page.setColor(Color.yellow);
		} else {
			obstruction++;
		}

		if (isBigBall) {
			page.drawString("Big Ball", 135, 165);
		} else {
		}

		if (isHighStakes) {
			page.drawString("High Stakes", 135, 145);
		}
		if (isSuperSpeed) {
			page.drawString("SuperSpeed", 135, 145);
		} else {
		}

		if (isSpeedball) {
			page.drawString("Speedball", 135, 145);
		} else {
		}

		if (count >= 12) {
			if (renew < 80) {
				page.setColor(Color.white);
				page.fillRect(0, 0, 310, 310);

				page.setColor(Color.black);
				page.drawString("You Win", 115, 10);

				count2 = 0;
				renew++;
			} else {
				count = 0;
				count2 = 0;

				sloMo = 0;
				speedball = 0;
				sumo = 0;
				superSpeed = 0;
				highStakes = 0;
				obstruction = 0;

				renew = 0;
			}
		}

		else if (count2 >= 12) {
			if (renew < 80) {
				page.setColor(Color.white);
				page.fillRect(0, 0, 310, 310);

				page.setColor(Color.black);
				page.drawString("Computer wins", 110, 10);

				count = 0;
				renew++;
			} else {
				count = 0;
				count2 = 0;

				sloMo = 0;
				speedball = 0;
				sumo = 0;
				superSpeed = 0;
				highStakes = 0;
				obstruction = 0;

				renew = 0;
			}
		} else {
			page.drawString("player points: " + count, 105, 10);
			page.drawString("AI points: " + count2, 105, 30);
		}
	}

	private class ReboundListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// resets modifiers
			if (powerups > 2500) {
				sloMo = 0;
				speedball = 0;
				sumo = 0;
				superSpeed = 0;
				highStakes = 0;
				obstruction = 0;
				powerups = 0;
				powerups++;
			} else {
				powerups++;
			}
			
			// makes the ball larger for a period of time.
			if (((bigBall > 400) && (bigBall < 600)) || ((bigBall > 1300) && (bigBall < 1500)) && modifiersON) {
				ballSize = 20;
				bigBall++;
				isBigBall = true;
			} else {
				ballSize = 10;
				isBigBall = false;
				bigBall++;
			}
			
			// doubles score gained by each player for a limited amount of time
			if (((highStakes > 999 && highStakes < 1161) || (highStakes > 2349 && highStakes < 2461)) && modifiersON) {
				countGain = 2;
				countGain2 = 2;
				highStakes++;
				isHighStakes = true;
			} else {
				{
					countGain = 1;
					countGain2 = 1;
					highStakes++;
					isHighStakes = false;
				}
			}

			// grows both paddles for a limited number of time
			if (((sumo > 529 && sumo < 691) || (sumo > 1949 && sumo < 2101)) && modifiersON) {
				lengthAI = 100;
				lengthYou = 100;
				sumo++;
				sumoIsTrue = true;
			} else {
				lengthAI = 50;
				lengthYou = 50;
				sumo++;
				sumoIsTrue = false;
			}

			// speeds up ball for limited number of time
			if (((speedball > 329 && speedball < 371) || (speedball > 1749 && speedball < 1801)) && modifiersON) {
				isSpeedball = true;
				x_decay = 7;
				y_decay = 7;
				speedball++;
			} else {
				isSpeedball = false;
				x_decay = 4;
				y_decay = 6;
				speedball++;
			}
			
			// slows down opponents paddle for a limited number of time
			if (((sloMo > 149 && sloMo < 281) || (sloMo > 1499 && sloMo < 1541)) && modifiersON) {
				z_decay = 3;
				sloMo++;
				isSloMo = true;
			} else {
				isSloMo = false;
				z_decay = 5;
				sloMo++;
			}

			// makes the AI impossible to beat for a limited amount of time by
			// making it faster.
			if (((superSpeed > 749 && superSpeed < 861) || (superSpeed > 2719 && superSpeed < 2781)) && modifiersON) {
				z_decay = 20;
				superSpeed++;
				isSuperSpeed = true;
			} else {
				superSpeed++;
				isSuperSpeed = false;
			}

			// checks if the ball is at the same x coordinate as the AI paddle
			if (x + ballSize > 276 && x + ballSize < 284) {
				isTrue = true;
			} else {
				isTrue = false;
			}
			
			// checks if the ball is at the same x coordinate as the player paddle
			if (x < 30 && x > 8) {
				isTrue2 = true;
			} else {
				isTrue2 = false;
			}

			// controls the motion of the AI bar
			if ((z + lengthAI / 2) > y) {
				z -= z_decay;
			} else {
				z += z_decay;
			}

			// controls the motion of the ball
			if (x + ballSize > 300) {
				coloryellow = true;
				isFarx = true;
				x -= x_decay;
				count = count + countGain;
			}

			// controls how the ball bounces if it hits the AI bar
			else if ((z + lengthAI >= y && z - 10 <= y) && isTrue) {
				isFarx = true;
				x -= x_decay;
			}
			
			// controls how the ball bounces if it hits the player bar
			else if ((w + lengthYou >= y && w - 10 <= y) && isTrue2) {
				isFarx = false;
				x += x_decay;
			}

			else {
				if (isFarx) {
					if (x > 0) {
						x -= x_decay;
						coloryellow = false;
					} else {
						isFarx = false;
						coloryellow = true;
						x += x_decay;
						count2 = count2 + countGain2;
					}
				} else {
					x += x_decay;
					coloryellow = false;
				}
			}

			if (y > (300 - ballSize)) {
				isFary = true;
				y -= y_decay;
			} else {
				if (isFary) {
					if (y > 0) {
						y -= y_decay;
					} else {
						isFary = false;
						{
							y += y_decay;
						}
					}
				} else {
					y += y_decay;
				}
			}
			repaint();
		}
	}

	/**
	 * Represents the listener for keyboard activity
	 */
	private class DirectionListener implements KeyListener {
		/**
		 * Responds to the user pressing arrow keys by adjusting the image and
		 * image location accordingly.
		 */

		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (w <= 0) {

				} else {
					w -= w_decay;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (w >= (300 - lengthYou)) {

				} else {
					w += w_decay;
				}
				break;
			}

			repaint();
		}

		/**
		 * provide empty definitions for unused event methods
		 */
		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}
}
