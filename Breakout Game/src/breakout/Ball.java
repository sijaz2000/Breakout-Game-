package breakout;

import edu.macalester.graphics.Ellipse;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class Ball {
    public static final double BALL_RADIUS = 2.5;

    private double centerX;
    private double centerY;

    private double maxBoundX;
    private double maxBoundY;

    private double dx;
    private double dy;

    private Ellipse ball;
    private CanvasWindow canvas;

    public Ball(
        double centerX,
        double centerY,
        double initialSpeed,
        double initialAngle,
        double maxX,
        double maxY,
        CanvasWindow canvas) {

            this.ball = new Ellipse(centerX-BALL_RADIUS, centerY-BALL_RADIUS, 2*BALL_RADIUS, 2*BALL_RADIUS);
            ball.setFillColor(Color.BLACK);
            ball.setFilled(true);
            this.centerX = centerX;
            this.centerY = centerY;
            this.canvas = canvas;

            maxBoundX = maxX;
            maxBoundY = maxY;

            double initialAngleRadians = Math.toRadians(initialAngle);
                
            dx = initialSpeed * Math.cos(initialAngleRadians);
            dy = initialSpeed * -Math.sin(initialAngleRadians);

            canvas.add(ball);
    }

    /**
     * Updates the position of the ball if it is in bounds.
     * @param dt is the number by which the velocities are multipled.
     */
    public void updatePosition(double dt) {

        double newX = centerX + (dx*dt);
        double newY = centerY + (dy*dt);

        if (newX > 0 && newX < maxBoundX && newY > 0 && newY < maxBoundY){

            centerX += dx * dt;
            centerY += dy * dt;
            ball.setPosition(centerX-BALL_RADIUS, centerY-BALL_RADIUS);
        }
        hitSides(newX, newY);
    }    

    /**
     * Reverses the velocities's directions depending on which side of the canvas is hit.
     * @param newX updated centerX of the ball
     * @param newY updated centerY of the ball
     */
    public void hitSides(double newX, double newY) {
        if ((newX) <= 0 || (newX) >= maxBoundX){
            dx = -dx;
        }
        else if ((newY) <= 0) {
            dy = -dy;
        }
    }

    /**
     * @return horizontal velocity
     */
    public double getXSpeed() {
        return dx;
    }
    
    /**
     * Sets new horizontal velocity
     * @param DX new value of horizontal velocity
     */
    public void setXSpeed(double DX) {
        this.dx = DX;
    }

    /**
     * @return veritcal velocity
     */
    public double getYSpeed() {
        return dy;
    }

    /**
     * Sets new vertical velocity
     * @param DY new value of vertical velocity
     */
    public void setYSpeed(double DY) {
        this.dy = DY;
    }
    
    public double getCenterX(){
        return centerX;
    }

    public double getCenterY(){
        return centerY;
    }

    /**
     * @return the shape in order for it to be used in BreakoutGame class.
     */
    public Ellipse getShape() {
        return this.ball;
    }




}
