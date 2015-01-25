package org.lss.maze.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.lss.maze.MazeComponent;

public class TXTMazeFileParser {
	
	private Scanner txtFileScanner;
	private int row = 0;
	private MazeComponent[][] maze;
	private ArrayList<MazeComponent[]> mazeMatrix;
	
	public TXTMazeFileParser(String path) throws FileNotFoundException {
		txtFileScanner = new Scanner(new File(path));
		
		mazeMatrix = new ArrayList<MazeComponent[]>();
		
		while (txtFileScanner.hasNextLine()) {
			String temp = txtFileScanner.nextLine();
			
			parseLine(temp);
		}
		
		arrayListToMatrix();
	}
	
	private void arrayListToMatrix() {
		maze = new MazeComponent[mazeMatrix.size()][mazeMatrix.get(0).length];
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				try {
					maze[i][j] = mazeMatrix.get(i)[j];
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private void parseLine(String text) {
		mazeMatrix.add((new MazeComponent[text.length()]));
		
		for (int i = 0; i < text.length(); i++) {
			
			mazeMatrix.get(row)[i] = new MazeComponent();
			
			switch (text.charAt(i)) {
			case '0':
				mazeMatrix.get(row)[i].setAsSpace();
				break;
			case '1':
				mazeMatrix.get(row)[i].setAsWall();
				break;
			case '2':
				mazeMatrix.get(row)[i].setAsStartPoint();
				break;
			case '3':
				mazeMatrix.get(row)[i].setAsEndPoint();
				break;
			}
		}
		
		row++;
	}
	
	public MazeComponent[][] getParsedMaze() {
		return maze;
	}
}
