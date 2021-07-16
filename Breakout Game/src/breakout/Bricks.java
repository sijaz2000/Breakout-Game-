package breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Bricks{

    private static final double BRICK_LENGTH = 57.5;
    private static final double BRICK_WIDTH = 5;
    private List<Rectangle> bricks;
    private CanvasWindow canvas;
    private Rectangle brick;


    public Bricks(CanvasWindow canvas) {
        this.canvas = canvas;
        brick = new Rectangle(10, 50, BRICK_LENGTH, BRICK_WIDTH);
        brick.setStrokeColor(Color.WHITE);
        brick.setFillColor(Color.RED);
        brick.setFilled(true);
        bricks = new ArrayList<>();
        bricks.add(brick);
        buildBricks();

        for (Rectangle i : bricks) {
            canvas.add(i);
        }
    }

    /**
     * Each for loop creates 2 rows of bricks and adds it to a list.
     * @return the list of rectangle objects.
     */
    public List<Rectangle> buildBricks() {
        double count = 67.5;
        double line = 50;
        for (int i = 0 ; i < 19 ; i ++) {
            Rectangle brick = new Rectangle(count, line, BRICK_LENGTH, BRICK_WIDTH);
            brick.setStrokeColor(Color.WHITE);
            brick.setFillColor(Color.RED);
            bricks.add(brick);
            if (i == 8) {
                count = 10;
                line = line + 10;
            }
            else{
                count = count + BRICK_LENGTH;
            }
        }
        count = 10;
        for (int i = 0 ; i < 20 ; i ++) {
            Rectangle brick = new Rectangle(count, line + 10, BRICK_LENGTH, BRICK_WIDTH);
            brick.setStrokeColor(Color.WHITE);
            brick.setFillColor(Color.ORANGE);
            bricks.add(brick);
            if (i == 9) {
                count = 10;
                line = line + 10;
            }
            else{
                count = count + BRICK_LENGTH;
            }
        }
        count = 10;
        for (double i = 0 ; i < 20 ; i ++) {
            Rectangle brick = new Rectangle(count, line + 20, BRICK_LENGTH, BRICK_WIDTH);
            brick.setStrokeColor(Color.WHITE);
            brick.setFillColor(Color.YELLOW);
            bricks.add(brick);
            if (i == 9) {
                count = 10;
                line = line + 10;
            }
            else{
                count = count + BRICK_LENGTH;
            }
        }
        count = 10;
        for (double i = 0 ; i < 20 ; i ++) {
            Rectangle brick = new Rectangle(count, line + 30, BRICK_LENGTH, BRICK_WIDTH);
            brick.setStrokeColor(Color.WHITE);
            brick.setFillColor(Color.GREEN);
            bricks.add(brick);
            if (i == 9) {
                count = 10;
                line = line + 10;
            }
            else{
                count = count + BRICK_LENGTH;
            }
        }
        count = 10;
        for (double i = 0 ; i < 20 ; i ++) {
            Rectangle brick = new Rectangle(count, line + 40, BRICK_LENGTH, BRICK_WIDTH);
            brick.setStrokeColor(Color.WHITE);
            brick.setFillColor(Color.blue);
            bricks.add(brick);
            if (i == 9) {
                count = 10;
                line = line + 10;
            }
            else{
                count = count + BRICK_LENGTH;
            }
        }
        return bricks;
    }

    /**
     * @return the list of rectangle 'bricks' to be used in the BreakoutGame class.
     */
    public List<Rectangle> getBricks() {
        return bricks;
    }
    
}
