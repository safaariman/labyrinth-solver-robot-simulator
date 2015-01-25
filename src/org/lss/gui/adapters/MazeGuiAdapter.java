package org.lss.gui.adapters;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import org.lss.maze.Maze;

public class MazeGuiAdapter extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Maze maze;
	private int mazeSize = 5;
	
	public MazeGuiAdapter() {
		maze = null;
		this.setOpaque(false);
	}
	
	public MazeGuiAdapter(Maze m) {
		maze = m;
		this.setOpaque(false);
	}

	public void setMaze(Maze m) {
		maze = m;
	}
	
	public void setMazeSize(int s) {
		mazeSize = s;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = 5, y = 5;
		
		try {
			for (int i = 0; i < maze.getMaze().length; i++) {
				for (int j = 0; j < maze.getMaze()[i].length; j++) {
					if (maze.getMazeComponent(i, j).isWall()) {
						g.setColor(Color.black);
						g.fillRect(x, y, mazeSize, mazeSize);
					}
					else if (maze.getMazeComponent(i, j).isStartPoint()) {
						g.setColor(Color.green);
						g.fillRect(x, y, mazeSize, mazeSize);
					}
					else if (maze.getMazeComponent(i, j).isEndPoint()) {
						g.setColor(Color.red);
						g.fillRect(x, y, mazeSize, mazeSize);
					}
					x += mazeSize;
				}
				y += mazeSize;
				x = 5;
			}
			
		} catch (Exception e) {
			System.err.println("Undefined maze. I can not paint it.");
		}
		
	}
	
}
