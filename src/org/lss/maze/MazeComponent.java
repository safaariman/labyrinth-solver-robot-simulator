package org.lss.maze;

import java.awt.Color;
import javax.swing.JButton;

public class MazeComponent extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean startPoint;
	public boolean endPoint;
	public boolean wall;
	
	public MazeComponent() {
		wall = false;
		startPoint = false;
		endPoint = false;
	}
	
	public void setAsSpace() {
		wall = false;
		startPoint = false;
		endPoint = false;
		
		super.setBackground(null);
	}
	
	public void setAsWall() {
		wall = true;
		startPoint = false;
		endPoint = false;
		
		super.setBackground(Color.black);
	}
	
	public void setAsStartPoint() {
		wall = false;
		startPoint = true;
		endPoint = false;
		
		super.setBackground(Color.red);
	}
	
	public void setAsEndPoint() {
		wall = false;
		startPoint = false;
		endPoint = true;
		
		super.setBackground(Color.green);
	}
	
	public boolean isStartPoint() {
		return startPoint;
	}
	
	public boolean isEndPoint() {
		return endPoint;
	}
	
	public boolean isWall() {
		return wall;
	}
	
	public boolean isEmpty() {
		if (!startPoint && !endPoint && !wall)
			return true;
		else
			return false;
	}
}
