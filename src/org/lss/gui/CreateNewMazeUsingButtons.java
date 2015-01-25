package org.lss.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.lss.gui.adapters.MazeGuiAdapter;
import org.lss.maze.Maze;
import org.lss.maze.MazeComponent;

public class CreateNewMazeUsingButtons {
	private JFrame frame;
	private HandlerClass handler = new HandlerClass();
	private Maze maze;
	private JSplitPane mainSplitPane;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JButton sendMaze;
	private MazeGuiAdapter upperPanel;

	public CreateNewMazeUsingButtons(MazeGuiAdapter panel) {

		initMainFrame();
		initMainSplitPane();
		createMazeMatrix();
		createMazeCreatorScreen(maze);
		
		upperPanel = panel;
	}

	private void createMazeMatrix() {
		maze = new Maze(new MazeComponent[50][50]);
		
	}

	private void createMazeCreatorScreen(Maze maze) {
		int x = 10;
		int y = 10;

		for (int i = 0; i < maze.getMaze().length; i++) {
			x = 10;

			for (int j = 0; j < maze.getMaze()[i].length; j++) {
				maze.setMazeComponent(i, j, new MazeComponent());
				maze.getMazeComponent(i, j).setLocation(x, y);
				maze.getMazeComponent(i, j).setBounds(x, y, 20, 20);
				maze.getMazeComponent(i, j).setFocusable(true);
				maze.getMazeComponent(i, j).addActionListener(handler);
				maze.getMazeComponent(i, j).addKeyListener(handler);

				rightPanel.add(maze.getMazeComponent(i, j));
				x += 20;
			}
			y += 20;
		}
	}

	private void initMainFrame() {
		frame = new JFrame("Labyrinth Creator");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(500, 300));
		
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.addKeyListener(handler);
		frame.setLayout(new BorderLayout());
	}
	
	private void initMainSplitPane() {
		mainSplitPane = new JSplitPane();
		frame.getContentPane().add(mainSplitPane, BorderLayout.CENTER);
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		{
			sendMaze = new JButton("Send Maze");
			sendMaze.addActionListener(handler);
			sendMaze.setBounds(10, 10, 100, 50);
			leftPanel.add(sendMaze);
		}
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		mainSplitPane.setLeftComponent(leftPanel);
		mainSplitPane.setRightComponent(rightPanel);
		
		mainSplitPane.setResizeWeight(0.2);
	}

	private class HandlerClass implements ActionListener, KeyListener {
		MazeComponent tempCell;
		MazeComponent tempCell2;
		
		boolean isShiftDown = false;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == sendMaze) {
				upperPanel.setMaze(maze);
				upperPanel.repaint();
				frame.dispose();
			}
			else {
				int RIGHT_SHIFT_MASK =  InputEvent.SHIFT_MASK;
				
				if((e.getModifiers() & RIGHT_SHIFT_MASK) == RIGHT_SHIFT_MASK) {
					if (isShiftDown) {
						tempCell2 = getCell(e.getSource());
						selectArea(tempCell, tempCell2);
						isShiftDown = false;
					}
					else {
						tempCell = getCell(e.getSource());
						isShiftDown = true;
					}
				}
				else {
					tempCell = getCell(e.getSource());
					
					isShiftDown = false;
					
					if (tempCell.isWall()) {
						tempCell.setAsStartPoint();
						return;
					}
					else if (tempCell.isStartPoint()) {
						tempCell.setAsEndPoint();
						return;
					}
					else if (tempCell.isEndPoint()) {
						tempCell.setAsSpace();
						return;
					}
					else {
						tempCell.setAsWall();
						return;
					}	
				}
			}
		}

		private void selectArea(MazeComponent cell1, MazeComponent cell2) {
			Point t = findCoordinate(cell1);
			Point k = findCoordinate(cell2);
			
			int temp = 0;
			
			if (t.x > k.x) {
				temp = t.x;
				t.x = k.x;
				k.x = temp;
			}
			if (t.y > k.y) {
				temp = t.y;
				t.y = k.y;
				k.y = temp;
			}
			
			for (int i = t.x; i < k.x + 1; i++) {
				for (int j = t.y; j < k.y + 1; j++) {
					if (maze.getMazeComponent(i, j).isWall())
						maze.getMazeComponent(i, j).setAsSpace();
					else
						maze.getMazeComponent(i, j).setAsWall();
				}
			}
		}
		
		

		private Point findCoordinate(MazeComponent cell) {
			
			for (int i = 0; i < maze.getMaze().length; i++) {
				for (int j = 0; j < maze.getMaze()[i].length; j++) {
					if (maze.getMazeComponent(i, j) == cell) {
						return new Point(i, j);
					}
				}
			}
			
			return null;
		}

		private MazeComponent getCell(Object source) {

			for (int i = 0; i < maze.getMaze().length; i++) {
				for (int j = 0; j < maze.getMaze()[i].length; j++) {
					if (maze.getMazeComponent(i, j) == source) {
						return maze.getMazeComponent(i, j);
					}
				}
			}
			
			return null;
		}

		

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}
}
