package org.lss.gui;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.lss.algorithms.WallFollower;
import org.lss.gui.adapters.MazeGuiAdapter;
import org.lss.gui.adapters.WallFollowerGuiAdapter;
import org.lss.maze.Maze;
import org.lss.maze.MazeComponent;
import org.lss.maze.input.TXTMazeFileParser;

import javax.swing.JSlider;

public class GraphicalUserInterface {
	private JFrame frame;
	private HandlerClass handler = new HandlerClass();
	private JDesktopPane desktopPane;
	private JSplitPane mainSplitPane;
	private JInternalFrame mainInternalFrame;
	private JInternalFrame innerInternalFrame;
	private JScrollPane scrollPaneForMazePropertiesTree;
	private JScrollPane scrollPaneForRobotPropertiesTree;
	private JPanel rightPanel;
	private MazeGuiAdapter panelForInnerInternalFrame;
	private JPanel leftPanel;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnNew;
	private JMenuItem mntmMaze;
	private JTree mazePropertiesTree;
	private JTree robotPropertiesTree;
	private JToolBar toolBar;
	private JLabel mazePropertiesTitle;
	private JLabel robotPropertiesTitle;
	private JFileChooser loadFile;
	private JButton openFile;
	private JSlider slider;
	private WallFollowerGuiAdapter algorithm;
	
	public GraphicalUserInterface() {

		initMainFrame();
		initTopMenu();
		initToolBar();
		initDesktopPane();
		initInternalFrame();
	}
	
	private void initToolBar() {
		
		loadFile = new JFileChooser();
		loadFile.setFileFilter(new FileNameExtensionFilter("TXT File", "txt"));
		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
		JButton newFile = new JButton(new ImageIcon(getClass().getResource("/newFile.png")));
		openFile = new JButton(new ImageIcon(getClass().getResource("/openFile.png")));
		openFile.addActionListener(handler);
		JButton saveFile = new JButton(new ImageIcon(getClass().getResource("/saveFile.png")));
		toolBar.add(newFile);
		toolBar.add(openFile);
		toolBar.add(saveFile);
		
	}

	private void initMainFrame() {
		frame = new JFrame("Labyrinth Solver Robot Simulator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		System.out.print(getClass().getResource("/icon.jpg"));
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
	}
	
	private void initTopMenu() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		{
			mnNew = new JMenu("New");
			mnFile.add(mnNew);
			{
				mntmMaze = new JMenuItem("Maze");
				mntmMaze.addActionListener(handler);
				mnNew.add(mntmMaze);
			}
		}
	}
	
	
	
	private void initDesktopPane() {
		desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
	}
	
	private void initInternalFrame() {
		mainInternalFrame = new JInternalFrame();
		
		desktopPane.add(mainInternalFrame);
		
		mainInternalFrame.setIconifiable(true);
		
		mainInternalFrame.setResizable(true);
		mainInternalFrame.setClosable(true);
		mainInternalFrame.setMaximizable(true);
		mainInternalFrame.setBounds(92, 50, 485, 320);
		mainInternalFrame.setVisible(true);
		
		try {
			mainInternalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		initMainSplitPane();
		
		
	}

	private void initMainSplitPane() {
		mainSplitPane = new JSplitPane();
		
		mainInternalFrame.getContentPane().add(mainSplitPane, BorderLayout.CENTER);
		
		leftPanel = new JPanel();
		mainSplitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new MigLayout("", "[grow]", "[50%,grow,top][50%,grow,center]"));
		{
			scrollPaneForMazePropertiesTree = new JScrollPane();
			leftPanel.add(scrollPaneForMazePropertiesTree, "cell 0 0,grow");
			
			mazePropertiesTree = new JTree();
			scrollPaneForMazePropertiesTree.setViewportView(mazePropertiesTree);
			
			mazePropertiesTitle = new JLabel("Maze Properties");
			scrollPaneForMazePropertiesTree.setColumnHeaderView(mazePropertiesTitle);
			
			scrollPaneForRobotPropertiesTree = new JScrollPane();
			leftPanel.add(scrollPaneForRobotPropertiesTree, "cell 0 1,grow");
			
			robotPropertiesTree = new JTree();
			scrollPaneForRobotPropertiesTree.setViewportView(robotPropertiesTree);
			
			robotPropertiesTitle = new JLabel("Robot Properties");
			scrollPaneForRobotPropertiesTree.setColumnHeaderView(robotPropertiesTitle);
		}
		
		rightPanel = new JPanel();
		mainSplitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(null);
		{
			innerInternalFrame = new JInternalFrame("Untitled Maze", true, true, true, true);
			innerInternalFrame.setLayout(new BorderLayout());
			innerInternalFrame.setBounds(31, 43, 266, 220);
			rightPanel.add(innerInternalFrame);
			
			slider = new JSlider(1, 20);
			slider.setValue(5);
			slider.addChangeListener(handler);
			innerInternalFrame.add(slider, BorderLayout.SOUTH);
			
			panelForInnerInternalFrame = new MazeGuiAdapter();
			panelForInnerInternalFrame.setLayout(new BorderLayout());
			innerInternalFrame.getContentPane().add(panelForInnerInternalFrame, BorderLayout.CENTER);
			
			
			
			
			try {
				innerInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			
			innerInternalFrame.setVisible(true);
		}
		
		mainSplitPane.setDividerLocation(0.2);
	}

	private class HandlerClass implements ActionListener, ChangeListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mntmMaze) {
				new CreateNewMazeUsingButtons(panelForInnerInternalFrame);
			}
			else if (e.getSource() == openFile) {
				loadFile.showOpenDialog(null);
				
				String s = loadFile.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Loaded: " + s);
				
				try {
					MazeComponent[][] m = (new TXTMazeFileParser(s)).getParsedMaze();
					Maze maze = new Maze(m);
					panelForInnerInternalFrame.setMaze(maze);
					panelForInnerInternalFrame.repaint();
					
					algorithm = new WallFollowerGuiAdapter(new WallFollower(maze), panelForInnerInternalFrame);
					panelForInnerInternalFrame.add(algorithm, BorderLayout.CENTER);
					new Thread(algorithm).start();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			panelForInnerInternalFrame.setMazeSize(slider.getValue());
			algorithm.setRobotSize(slider.getValue());
			panelForInnerInternalFrame.repaint();
			algorithm.repaint();
			
		}
	}
}
