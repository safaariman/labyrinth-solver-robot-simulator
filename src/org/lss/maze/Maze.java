package org.lss.maze;

import java.awt.Point;

public class Maze {
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private MazeComponent[][] maze;
	
	public Maze() {
		maze = null;
	}
	
	public Maze(MazeComponent[][] m) {
		maze = m;
		//findPoints();
	}
	
	public void findPoints() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j].isStartPoint()) {
					startPoint.x = i;
					startPoint.y = j;
				}
				else if (maze[i][j].isEndPoint()) {
					endPoint.x = i;
					endPoint.y = j;
				}
			}
		}
	}
	
	public void setStartPoint(Point sp) {
		startPoint = sp;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public void setEndPoint(Point ep) {
		endPoint = ep;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setStartPointX(int spx) {
		startPoint.x = spx;
	}
	
	public int getStartPointX() {
		return startPoint.x;
	}
	
	public void setStartPointY(int spy) {
		startPoint.y = spy;
	}
	
	public int getStartPointY() {
		return startPoint.y;
	}
	
	public void setEndPointX(int epx) {
		endPoint.x = epx;
	}
	
	public int getEndPointX() {
		return endPoint.x;
	}
	
	public void setEndPointY(int epy) {
		endPoint.y = epy;
	}
	
	public int getEndPointY() {
		return endPoint.y;
	}
	
	public void setMaze(MazeComponent[][] m) {
		maze = m;
		findPoints();
	}
	
	public MazeComponent[][] getMaze() {
		return maze;
	}
	
	public void setMazeComponent(int x, int y, MazeComponent m) {
		maze[x][y] = m;
	}
	
	public MazeComponent getMazeComponent(int x, int y) {
		return maze[x][y];
	}
}
