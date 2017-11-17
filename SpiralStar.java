import turtleClasses.World;
import turtleClasses.Turtle;
import java.awt.Color;

public class SpiralStar {
    public static void main(String[] args) throws InterruptedException{
        World w = new World(999,999);
        Turtle turtle = new Turtle(w);
        turtle.hide();
        turtle.turnRight();
        turtle.setPenWidth(15);

        Color[] colors = new Color[5];
        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.BLUE;

       for (int i = 0; i < 90; i++) {
           if (i % 20 == 0) {
               turtle.setPenColor(colors[i / 20]);
           }
            turtle.forward(i * 10);
            Thread.sleep(150);
            turtle.turn(144);
       } 
    }
}