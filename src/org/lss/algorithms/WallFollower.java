package org.lss.algorithms;

import java.awt.Point;
import org.lss.maze.Maze;
import org.lss.robot.BasicMovements;
import org.lss.robot.Robot;
import org.lss.robot.Direction;

public class WallFollower extends BasicMovements {

	private Maze maze;
	

	public WallFollower(Maze m) {
		this.maze = m;
		currentPoint = m.getStartPoint();
		currentDirection = Direction.EAST;
	}

	

	public Maze getMaze() {
		return maze;
	}

	public void startSolving() {
		ctrl();
		goForward();
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public void ctrl() {
		if (currentDirection == Direction.EAST) {
			if (maze.getMazeComponent(currentPoint.x + 1, currentPoint.y)
					.isEmpty()
					|| maze.getMazeComponent(currentPoint.x + 1, currentPoint.y)
							.isEndPoint())
				turnRight();
			else if ((maze.getMazeComponent(currentPoint.x - 1, currentPoint.y)
					.isEmpty() || maze.getMazeComponent(currentPoint.x - 1,
					currentPoint.y).isEndPoint())
					&& maze.getMazeComponent(currentPoint.x, currentPoint.y + 1)
							.isWall())
				turnLeft();
			else if (maze.getMazeComponent(currentPoint.x, currentPoint.y + 1)
					.isWall()) {
				turnBack();
			}
		}

		else if (currentDirection == Direction.NORTH) {
			if (maze.getMazeComponent(currentPoint.x, currentPoint.y + 1)
					.isEmpty()
					|| maze.getMazeComponent(currentPoint.x, currentPoint.y + 1)
							.isEndPoint())
				turnRight();
			else if ((maze.getMazeComponent(currentPoint.x, currentPoint.y - 1)
					.isEmpty() || maze.getMazeComponent(currentPoint.x,
					currentPoint.y - 1).isEndPoint())
					&& maze.getMazeComponent(currentPoint.x - 1, currentPoint.y)
							.isWall())
				turnLeft();
			else if (maze.getMazeComponent(currentPoint.x - 1, currentPoint.y)
					.isWall()) {
				turnBack();
			}
		}

		else if (currentDirection == Direction.WEST) {
			if (maze.getMazeComponent(currentPoint.x - 1, currentPoint.y)
					.isEmpty()
					|| maze.getMazeComponent(currentPoint.x - 1, currentPoint.y)
							.isEndPoint())
				turnRight();
			else if ((maze.getMazeComponent(currentPoint.x + 1, currentPoint.y)
					.isEmpty() || maze.getMazeComponent(currentPoint.x + 1,
					currentPoint.y).isEndPoint())
					&& maze.getMazeComponent(currentPoint.x, currentPoint.y - 1)
							.isWall())
				turnLeft();
			else if (maze.getMazeComponent(currentPoint.x, currentPoint.y - 1)
					.isWall()) {
				turnBack();
			}
		}

		else if (currentDirection == Direction.SOUTH) {
			if (maze.getMazeComponent(currentPoint.x, currentPoint.y - 1)
					.isEmpty()
					|| maze.getMazeComponent(currentPoint.x, currentPoint.y - 1)
							.isEndPoint())
				turnRight();
			else if ((maze.getMazeComponent(currentPoint.x, currentPoint.y + 1)
					.isEmpty() || maze.getMazeComponent(currentPoint.x,
					currentPoint.y + 1).isEndPoint())
					&& maze.getMazeComponent(currentPoint.x + 1, currentPoint.y)
							.isWall())
				turnLeft();
			else if (maze.getMazeComponent(currentPoint.x + 1, currentPoint.y)
					.isWall()) {
				turnBack();
			}
		}
	}
}
