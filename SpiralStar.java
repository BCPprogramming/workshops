import turtleClasses.World;
import turtleClasses.Turtle;
import java.awt.Color;

public class SpiralStar  {
	public static void main(String[] args) throws InterruptedException {
		World world = new World(999, 999);
		Turtle turtle = new Turtle(world);
		turtle.hide();
		turtle.setPenWidth(15);
		turtle.turnRight();
		
		String[] hexColors = {"#240056","#4400a3","#6a00ff","#8630ff","#b47fff","#8a7fff","#7f94ff","#4c6aff","#2348ff","#5188ff","#7ca6ff","#a0beff", "#a0eaff", "#a0fffa", "#a0ffd2", "#a0ffb4", "#b3ffb2", "#89ffb0"};
		Color[] colors = new Color[hexColors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.decode(hexColors[i]);
		}
		
		for (int i = 0; i < 80; i++) {
			if (i % 5 == 0) {
				turtle.setPenColor(colors[i / 5]);
			}	
			turtle.forward(i * 10);
			Thread.sleep(150);
			turtle.turn(144);
		}
	}
}