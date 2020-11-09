package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.DataOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import RemoteControl.RemoteControlEV3;

public class DesktopClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea windowArea;
	public static JButton upButton,downButton,rightButton, leftButton, doneButton;
	RemoteControlEV3 myController = new RemoteControlEV3();
	DataOutputStream output;
	
	public DesktopClientGUI(String title, DataOutputStream out) {
		super(title);
		this.output = out;
		myController.setOutputStream(out);
	}
	
	
	
	public static void buildGUI(DataOutputStream out) {
		DesktopClientGUI frame = new DesktopClientGUI("EV3 Remote", out);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addComponentsToPane();
		
		frame.pack();
		
		frame.setVisible(true);
	}
	
	private void addComponentsToPane() {
		
		upButton = new JButton("Forward");
		downButton = new JButton("Backward");
		leftButton = new JButton("Left");
		rightButton = new JButton("Right");
		doneButton = new JButton("Shut Down");
		upButton.setVisible(true);
		downButton.setVisible(true);
		rightButton.setVisible(true);
		leftButton.setVisible(true);
		doneButton.setVisible(true);
		upButton.addMouseListener(myController);
		downButton.addMouseListener(myController);
		leftButton.addMouseListener(myController);
		rightButton.addMouseListener(myController);
		doneButton.addMouseListener(myController);
	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(doneButton);
		windowArea = new JTextArea();
		windowArea.setEditable(false);
		windowArea.add(buttonPanel);
	
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
	}
}
