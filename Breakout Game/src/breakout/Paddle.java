package breakout;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;


 
public class Paddle {
    private Rectangle paddle;
    private CanvasWindow canvas;

    
    public Paddle(CanvasWindow canvas) {
        this.canvas = canvas;
        paddle = new Rectangle(250, 750, 100, 10);
        paddle.setFillColor(Color.BLACK);
        paddle.setFilled(true);
        canvas.add(paddle);
    }

  
    /**
     * Updates the x coordinate of the paddle, enabling it to move across the screen. 
     * @param paddleX the amount by which paddle will move left or right.
     */
    public void updateX(double paddleX) {
        if (paddle.getX() >= 0 && paddle.getX() <= canvas.getWidth() - paddle.getWidth()){
        paddle.setX(paddle.getX()+paddleX);
        }else {
            if (paddle.getX() < 0) {
                paddle.setX(0);
            }else {
                paddle.setX(canvas.getWidth() - paddle.getWidth());
            }
            
        }
    }

    /**
     * @return the shape in order for it to be used in BreakoutGame class.
     */
    public Rectangle getShape() {
        return this.paddle;
    }

}


    
    

