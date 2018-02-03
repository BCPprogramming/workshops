import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fibonacci extends JComponent {
	// Generates the Fibonacci sequence
	private int getRadius(int index) {
		if (index <= 0)
			return 0;
		if (index <= 2)
			return 1;
		return getRadius(index - 2) + getRadius(index - 1);
	}
	
	// Draws the Fibonacci spiral
	private void fibonacciSpiral(Graphics2D g2, Point start, int index, int size) {
		if (size > 0) {
			g2.setColor(new Color(104, 20, 188));
			g2.setStroke(new BasicStroke(5f));
			
			int radius = 10 * getRadius(index);
			int prevR = 10 * getRadius(index - 2);
			Point center = null;
			int startAngle = 0;
			int arcAngle = 90;
			
			switch(index % 4) {
				case 1:
					center = new Point(start.x + prevR, start.y);
					startAngle = 180;
					g2.drawArc(center.x - radius, center.y - radius, 2 * radius, 2 * radius, startAngle, arcAngle);
					break;
				case 2:
					center = new Point(start.x, start.y - prevR);
					startAngle = 270;
					g2.drawArc(center.x - radius, center.y - radius, 2 * radius, 2 * radius, startAngle, arcAngle);
					break;
				case 3:
					center = new Point(start.x - prevR, start.y);
					startAngle = 0;
					g2.drawArc(center.x - radius, center.y - radius, 2 * radius, 2 * radius, startAngle, arcAngle);
					break;
				case 0:
					center = new Point(start.x, start.y + prevR);
					startAngle = 90;
					g2.drawArc(center.x - radius, center.y - radius, 2 * radius, 2 * radius, startAngle, arcAngle);
					break;
			}
			fibonacciSpiral(g2, center, index + 1, size - 1);
		}
	}
	
	// Separate method for the Fibonacci rectangles to avoid drawing overlay issues.
	// Ensures spiral is always drawn above the rectangles.
	private void fibonacciRect(Graphics2D g2, Point start, int index, int size) {
		if (size > 0) {
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(2f));
			
			int radius = 10 * getRadius(index);
			int prevR = 10 * getRadius(index - 2);
			Point center = null;
			
			switch(index % 4) {
				case 1:
					center = new Point(start.x + prevR, start.y);
					g2.drawRect(center.x - radius, center.y, radius, radius);
					break;
				case 2:
					center = new Point(start.x, start.y - prevR);
					g2.drawRect(center.x, center.y, radius, radius);
					break;
				case 3:
					center = new Point(start.x - prevR, start.y);
					g2.drawRect(center.x, center.y - radius, radius, radius);
					break;
				case 0:
					center = new Point(start.x, start.y + prevR);
					g2.drawRect(center.x - radius, center.y - radius, radius, radius);
					break;
			}
			fibonacciRect(g2, center, index + 1, size - 1);
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1500, 1500);
		
		fibonacciRect(g2, new Point(500, 500), 1, 10);
		fibonacciSpiral(g2, new Point(500, 500), 1, 10);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fibonacci Spiral");
		Fibonacci f = new Fibonacci();
		frame.setSize(new Dimension(1500, 1500));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(f, BorderLayout.CENTER);
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}