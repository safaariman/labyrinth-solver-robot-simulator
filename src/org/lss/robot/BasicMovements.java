package org.lss.robot;

import java.awt.Point;

public class BasicMovements {
	
	public Direction currentDirection;
	public Point currentPoint;
	
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
	
	public void goForward() {
		if (currentDirection == Direction.EAST) {
			(currentPoint.y)++;
		}

		else if (currentDirection == Direction.NORTH) {
			(currentPoint.x)--;
		}

		else if (currentDirection == Direction.WEST) {
			(currentPoint.y)--;
		}

		else if (currentDirection == Direction.SOUTH) {
			(currentPoint.x)++;
		}
	}
}
