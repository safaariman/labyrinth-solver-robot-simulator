package org.lss.gui.adapters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.lss.algorithms.WallFollower;

public class WallFollowerGuiAdapter extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private WallFollower wf;
	private int x, y;
	private JPanel panel;
	int robotSize = 5;

	public WallFollowerGuiAdapter(WallFollower wf, JPanel p) {
		this.wf = wf;
		panel = p;
		this.setOpaque(false);
		this.setVisible(true);
		this.setBounds(0, 0, 5000, 5000);
	}
	
	@Override
	public void run() {
		try{
			wf.getMaze().findPoints();
			
			while (wf.getMaze().getEndPoint().x != wf.getCurrentPoint().x ||
					wf.getMaze().getEndPoint().y != wf.getCurrentPoint().y) {
				//System.out.println("Current Point: " + wf.getCurrentPoint().x + ", " + wf.getCurrentPoint().y);
				wf.startSolving();
				x = wf.getCurrentPoint().y * robotSize + 5;
				y = wf.getCurrentPoint().x * robotSize + 5;
				repaint();
				//panel.repaint();
				Thread.sleep(10);
			}
			
			System.out.print("Done.");
			panel.removeAll();
			panel.repaint();
			
		} catch (Exception e) {
			
		}
	}
	
	public void setRobotSize(int rSize) {
		robotSize = rSize;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.orange);
		
		g.fillRect(x, y, robotSize, robotSize);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
