import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Sierpinski extends JComponent {
	private Triangle starter;
	private Color backdrop;
	
	public Sierpinski() {
		Point a = new Point(500, 100);
		Point b = new Point(100, 793);
		Point c = new Point(900, 793);
		starter = new Triangle(a, b, c);
		backdrop = new Color(18, 130, 229);
	}
	
	private void drawTriangle(Graphics2D g2, Triangle t, boolean mid) {
		if (mid) {
			g2.setColor(backdrop);
			int[] xPoints = {t.a.x, t.b.x, t.c.x};
			int[] yPoints = {t.a.y, t.b.y, t.c.y};
			g2.fillPolygon(xPoints, yPoints, 3);
		}
	}
	
	private Point getMidpoint(Point a, Point b) {
		return new Point((int)((a.x + b.x)/2), (int)((a.y + b.y)/2));
	}
	
	private Triangle getMidTriangle(Triangle t) {
		Point ma = getMidpoint(t.a, t.b);
		Point mb = getMidpoint(t.b, t.c);
		Point mc = getMidpoint(t.c, t.a);
		return new Triangle(ma, mb, mc);
	}
	
	private void sierpinski(Graphics2D g2, Triangle t, int level) {
		if (level > 0) {
			drawTriangle(g2, t, false);
			Triangle mid = getMidTriangle(t);
			drawTriangle(g2, mid, true);
			sierpinski(g2, new Triangle(t.a, mid.a, mid.c), level - 1);
			sierpinski(g2, new Triangle(mid.a, t.b, mid.b), level - 1);
			sierpinski(g2, new Triangle(mid.c, mid.b, t.c), level - 1);
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1000, 1000);
		
		g2.setColor(Color.WHITE);
		g2.fillPolygon(new int[] {starter.a.x, starter.b.x, starter.c.x}, new int[] {starter.a.y, starter.b.y, starter.c.y}, 3);
		
		sierpinski(g2, starter, 6);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sierpinski");
		Sierpinski s = new Sierpinski();
		frame.getContentPane().add(s, BorderLayout.CENTER);
		frame.setSize(new Dimension(1000,1000));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

class Triangle {
	Point a;
	Point b;
	Point c;
	
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}