package breakout;


import java.util.List;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

/**
 * Salman Ijaz
 * COMP 127-02
 * 
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private int count;
    private List<Rectangle> brickList;
    private Paddle paddle;
    private Bricks bricks;
    private Ball ball;

    private CanvasWindow canvas;
    

    public BreakoutGame() {
        initializingObjects();
        gameMechanics();
    }

    /**
     * Initializes the objects to be used in breakOut Game.
     */
    public void initializingObjects() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        paddle = new Paddle(canvas);
        bricks = new Bricks(canvas);
        brickList = bricks.getBricks();
        ball = new Ball(300, 400, 10, (Math.random() * ((340 - 200) + 1)) + 200, 600, 800, canvas);
        this.count = 3;
    }

    /**
     * Handles the way the game will be played out, including win/lose conditions.
     */
    public void gameMechanics() {
        paddleMovement();
        canvas.animate(() -> {
            if (count != 0 && brickList.size() != 0) {
                 ball.updatePosition(0.5);
                 objectCollision();
                 if (testDead()) {
                     resetBall();
                     loseLife();
                 }
            }
            else if (count == 0) {
                resetBall();
                canvas.pause(100);
                System.out.println("You Lose!");
                canvas.closeWindow();
            }
            else if (brickList.size() == 0) {
                resetBall();
                canvas.pause(100);
                System.out.println("You Win!");
                canvas.closeWindow();
            } 
         });
    }

    /**
     * This method handles the movement of paddle obeject.
     */
    public void paddleMovement() {
        canvas.onKeyDown(event -> { 
            if (event.getKey() == Key.LEFT_ARROW) {
                paddle.updateX(-10);
            }
            if (event.getKey() == Key.RIGHT_ARROW) {
                paddle.updateX(10);
            }
        });   
    }

    /**
     * This method resets ball to be in the middle of the screen if testDead method returns true.
     */
    public void resetBall() {
        canvas.remove(ball.getShape());
        canvas.pause(300);
        ball = new Ball(300, 400, 10, (Math.random() * ((340 - 200) + 1)) + 200, 600, 800, canvas);
        canvas.add(ball.getShape());
    }
    /**
     * Updates count if a life is lost.
     */
    public int loseLife() {
        count = count - 1;
        return count;
    }

    /**
     * Checks if the ball has hit the max Y bound of the canvas.
     */
    public boolean testDead() {
        if (ball.getCenterY() + Ball.BALL_RADIUS >=800) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Handles all collision instances using ball obect's midpoints. (Detects collision slighlty outside the ball object)
     * If collision with paddle, no object removed but ball's vertical and horizontal velocities reversed accordingly.
     * If collision with brick, brick object removed from brickList and canvas.
     */
    public void objectCollision() {
        upperAndLowerCollisions();
        leftAndRightCollisions();
    }

    /**
     * Handles collisions that occur at the uppar and lower midpoints of the ball.
     */
    public void upperAndLowerCollisions() {
        GraphicsObject upperMidPoint = canvas.getElementAt((ball.getCenterX()+(ball.getCenterX()+2*Ball.BALL_RADIUS))/2, ball.getCenterY()-0.1);
        GraphicsObject lowerMidPoint = canvas.getElementAt((ball.getCenterX()+(ball.getCenterX()+2*Ball.BALL_RADIUS))/2, (ball.getCenterY()+2*Ball.BALL_RADIUS)+ 0.1);

        if (upperMidPoint != null || lowerMidPoint != null) {
            ball.setYSpeed(-ball.getYSpeed());
            if (upperMidPoint != null && upperMidPoint != paddle.getShape() && brickList.contains((Rectangle)upperMidPoint)) {
                brickList.remove((Rectangle)upperMidPoint);
                canvas.remove(upperMidPoint);
            }
            if (lowerMidPoint != null && lowerMidPoint != paddle.getShape() && brickList.contains((Rectangle)lowerMidPoint)) {
                brickList.remove((Rectangle)lowerMidPoint);
                canvas.remove(lowerMidPoint);
            }     
        }
    }

    /**
     * Handles collisions that occur at the left and right midpoints of the ball.
     */
    public void leftAndRightCollisions() {
        GraphicsObject leftMidPoint = canvas.getElementAt(ball.getCenterX()-0.1,(ball.getCenterY()+(ball.getCenterY()+2*Ball.BALL_RADIUS))/2); 
        GraphicsObject rightMidPoint = canvas.getElementAt((ball.getCenterX()+2*Ball.BALL_RADIUS)+0.1, (ball.getCenterY()+(ball.getCenterY()+2*Ball.BALL_RADIUS))/2);

        if (leftMidPoint != null || rightMidPoint != null) {
            ball.setYSpeed(-ball.getYSpeed());
            ball.setXSpeed(-ball.getXSpeed());
            if (leftMidPoint != null && leftMidPoint != paddle.getShape() && brickList.contains((Rectangle)leftMidPoint)) {
                brickList.remove((Rectangle)leftMidPoint);
                canvas.remove(leftMidPoint);
            }
            if(rightMidPoint != null && rightMidPoint != paddle.getShape() && brickList.contains((Rectangle)rightMidPoint)) {
                brickList.remove((Rectangle)rightMidPoint);
                canvas.remove(rightMidPoint);
            }
        }
    }


    public static void main(String[] args){
        new BreakoutGame();
    }




}
