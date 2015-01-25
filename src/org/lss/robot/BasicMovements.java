package org.lss.robot;

public class BasicMovements {
	
	public Direction currentDirection;
	
	public void turnLeft() {
		if (currentDirection == Direction.EAST)
			currentDirection = Direction.NORTH;
		else if (currentDirection == Direction.NORTH)
			currentDirection = Direction.WEST;
		else if (currentDirection == Direction.WEST)
			currentDirection = Direction.SOUTH;
		else if (currentDirection == Direction.SOUTH)
			currentDirection = Direction.EAST;
	}
	
	public void turnRight() {
		if (currentDirection == Direction.EAST)
			currentDirection = Direction.SOUTH;
		else if (currentDirection == Direction.SOUTH)
			currentDirection = Direction.WEST;
		else if (currentDirection == Direction.WEST)
			currentDirection = Direction.NORTH;
		else if (currentDirection == Direction.NORTH)
			currentDirection = Direction.EAST;
	}
}
