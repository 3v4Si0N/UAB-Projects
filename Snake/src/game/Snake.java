package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Snake class.
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class Snake implements GameElement {
   
    /**
     * Directions the snake can have
     */
    public enum Directions {
        RIGHT, LEFT, UP, DOWN;
    }
    
    /**
     * Current direction
     */
    private Directions direction;
    
    /**
     * If the snake is alive, if it's not, the game ends
     */
    private boolean isAlive;
    
    /**
     * List of nodes that make the snake's body
     */
    private List<BodyNode> body = new ArrayList<BodyNode>();
    
    /**
     * Minimum X coord to be set
     */
    private static final int MIN_X_COORD = -15;
    
    /**
     * Minimum Y coord to be set
     */
    private static final int MIN_Y_COORD = -15;
    
    /**
     * Constructor
     * @param newPosX initial top left X coord for the first body node
     * @param newPosY initial top left Y coord for the first body node
     */
    public Snake(final int newPosX, final int newPosY) {
        this.isAlive = true;
        this.direction = Directions.UP;
        if (newPosX >= MIN_X_COORD && newPosY >= MIN_Y_COORD) {
            this.body.add(new BodyNode(newPosX, newPosY));
        } else {
            this.body.add(new BodyNode(MIN_X_COORD, MIN_Y_COORD));
        }
    }
    
    /**
     * Sets alive snake
     * @param newIsAlive boolean, true if the snake is alive, false if it dies
     */
    public void setAlive(final boolean newIsAlive) {
        this.isAlive = newIsAlive;
    }

    /**
     * Getter of isAlive
     * @return boolean, true if the snake is alive, false if it dies
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * {@inheritDoc}
     * In this case, the first node coordinate is set
     */
    @Override
    public void setPosX(final int newPosX) {
        if (newPosX >= MIN_X_COORD) {
            this.body.get(0).setPosX(newPosX);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosX() {
        return this.body.get(0).getPosX();
    }

    /**
     * {@inheritDoc}
     * In this case, the first node coordinate is set
     */
    @Override
    public void setPosY(final int newPosY) {
        if (newPosY >= MIN_X_COORD) {
            this.body.get(0).setPosY(newPosY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosY() {
        return this.body.get(0).getPosY();
    }

    /**
     * {@inheritDoc}
     * In this case, each node is drawn
     */
    @Override
    public void drawElement(Graphics g) {
        for (BodyNode node : this.body) {
            node.drawElement(g);
        }
    }
    
    /**
     * Sets a new direction for the snake
     * @param newDirection new direction to be set
     */
    public void setDirection(final Directions newDirection) {
        if (newDirection != null) {
            if (newDirection == Directions.UP && this.direction != Directions.DOWN) {
                this.direction = newDirection;
            } else if (newDirection == Directions.DOWN && this.direction != Directions.UP) {
                this.direction = newDirection;
            } else if (newDirection == Directions.LEFT && this.direction != Directions.RIGHT) {
                this.direction = newDirection;
            } else if (newDirection == Directions.RIGHT && this.direction != Directions.LEFT) {
                this.direction = newDirection;
            }
        }
    }
    
    /**
     * Getter of current direction
     * @return current direction
     */
    public Directions getDirection() {
        return this.direction;
    }
    
    /**
     * Gets the list of nodes from the body
     * @return snake's body
     */
    public List<BodyNode> getBody() {
        return this.body;
    }

    /**
     * Selects the snake movement to do depending on the current direction
     */
    public void move() {
        switch (this.direction) {
            case RIGHT:
                this.moveRight();
                break;
            case LEFT:
                this.moveLeft();
                break;
            case UP:
                this.moveUp();
                break;
            case DOWN:
                this.moveDown();
                break;
            default:
                break;
        }
    }
    
    /**
     * Moves the snake to the right
     */
    private void moveRight() {
        if (this.body.size() > 1) {
            int prevX = this.body.get(0).getPosX();
            int prevY = this.body.get(0).getPosY();
            this.body.get(0).setPosX(prevX + BodyNode.getSize());
            this.moveBody(prevX, prevY);
        } else {
            this.body.get(0).setPosX(this.body.get(0).getPosX() + BodyNode.getSize());
        }
    }

    /**
     * Moves the snake to the left
     */
    private void moveLeft() {
        if (this.body.size() > 1) {
            int prevX = this.body.get(0).getPosX();
            int prevY = this.body.get(0).getPosY();
            this.body.get(0).setPosX(prevX - BodyNode.getSize());
            this.moveBody(prevX, prevY);
        } else {
            this.body.get(0).setPosX(this.body.get(0).getPosX() - BodyNode.getSize());
        }
    }

    /**
     * Moves the snake up
     */
    private void moveUp() {
        if (this.body.size() > 1) {
            int prevX = this.body.get(0).getPosX();
            int prevY = this.body.get(0).getPosY();
            this.body.get(0).setPosY(prevY - BodyNode.getSize());
            this.moveBody(prevX, prevY);
        } else {
            this.body.get(0).setPosY(this.body.get(0).getPosY() - BodyNode.getSize());
        }
    }

    /**
     * Moves the snake down
     */
    private void moveDown() {
        if (this.body.size() > 1) {
            int prevX = this.body.get(0).getPosX();
            int prevY = this.body.get(0).getPosY();
            this.body.get(0).setPosY(prevY + BodyNode.getSize());
            this.moveBody(prevX, prevY);
        } else {
            this.body.get(0).setPosY(this.body.get(0).getPosY() + BodyNode.getSize());
        }
    }
    
    /**
     * Moves the snake's body
     * @param prevX previous X coordinate
     * @param prevY previous Y coordinate
     */
    private void moveBody(int prevX, int prevY) {
        int auxX;
        int auxY;
        
        for (int i = 1; i < this.body.size(); i++) {
            auxX = this.body.get(i).getPosX();
            auxY = this.body.get(i).getPosY();
            
            this.body.get(i).setPosX(prevX);
            this.body.get(i).setPosY(prevY);
            
            prevX = auxX;
            prevY = auxY;
        }
    }
    
    /**
     * Grows snake's body by 1 new bodynode
     */
    public void grow() {
        BodyNode newNode = null;
        int lastbodyPosition = this.body.size() - 1;

        switch (this.direction) {
            case RIGHT:
                newNode = new BodyNode(
                        this.body.get(lastbodyPosition).getPosX() - BodyNode.getSize(),
                        this.body.get(lastbodyPosition).getPosY()
                        );
                break;
            case LEFT:
                newNode = new BodyNode(
                        this.body.get(lastbodyPosition).getPosX() + BodyNode.getSize(),
                        this.body.get(lastbodyPosition).getPosY()
                        );
                break;
            case UP:
                newNode = new BodyNode(
                        this.body.get(lastbodyPosition).getPosX(),
                        this.body.get(lastbodyPosition).getPosY() + BodyNode.getSize()
                        );
                break;
            case DOWN:
                newNode = new BodyNode(
                        this.body.get(lastbodyPosition).getPosX(),
                        this.body.get(lastbodyPosition).getPosY() - BodyNode.getSize()
                        );
                break;
            default:
                break;
        }
        this.body.add(newNode);
    }

    /**
     * NULL
     */
    @Override
    public Rectangle getArea() {
        return null;
    }
}
