package game;

import java.util.Random;

public class MockDefaultScreen {
	
	private Snake snake;
	private Food food;
	private int iteration = 0;
	private static final int GAME_WIDTH = 630;
	private static final int GAME_HEIGHT = 600;
	
	public MockDefaultScreen(Snake newSnake) {
		this.snake = newSnake;
	}
	
	public int generateFoodRandomCoordinate(int iter) {
		int result = 0;
		
		switch (iter) {
		case 0:
			result = 0; // initial posX
			break;
		case 1:
			result = GAME_WIDTH; // final posX
			break;
		case 2:
			result = GAME_HEIGHT; // final posY
			break;
		case 3:
			result = 200; // mid posX
			break;
			
		default:
			break;
		}
		
	    return result;
	}
	
	public void createFood() {
		int posX = 0;
		int posY = 0;
		
		switch (this.iteration) {
			case 0:
				posX = this.generateFoodRandomCoordinate(0);
				posY = this.generateFoodRandomCoordinate(0);
				break;
			case 1:
				posX = this.generateFoodRandomCoordinate(1);
				posY = this.generateFoodRandomCoordinate(0);
				break;
			case 2:
				posX = this.generateFoodRandomCoordinate(0);
				posY = this.generateFoodRandomCoordinate(2);
				break;
			case 3:
				posX = this.generateFoodRandomCoordinate(1);
				posY = this.generateFoodRandomCoordinate(2);
				break;
				
			default:
				posX = this.generateFoodRandomCoordinate(3);
				posY = this.generateFoodRandomCoordinate(3);
				break;
		}
		
		this.iteration++;
		if (this.iteration == 5) {
			this.iteration = 0;
		}
		
	    food = new Food(posX, posY);
	}
	
	public boolean eatFood() {
		boolean result = false;
		
	    if (snake.getBody().get(0).getArea().intersects(food.getArea())) {
	        snake.grow();
	        this.createFood();
	        result = true;
	    }
	    return result;
	}
	
	public Food getFood() {
		return food;
	}
}
