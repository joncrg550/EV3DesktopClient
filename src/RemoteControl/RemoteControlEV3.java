package RemoteControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.util.concurrent.atomic.AtomicReference;

import GUI.DesktopClientGUI;

public class RemoteControlEV3 implements MouseListener {

	private DataOutputStream output = null;
	private Boolean mousePressed = false;
	private AtomicReference<String> message = new AtomicReference<>();
	public void setOutputStream(DataOutputStream out) {
		output = out;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//dont need
	}


	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		new Thread() {
			public void run() {
				while(mousePressed) {
					try {
						Thread.sleep(300);
						output.writeUTF(message.get());
					}
					catch(Exception g) {
						System.out.println("failed to send command -mouse");
						g.printStackTrace();
					}
				}
			}
		}.start();
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			if (e.getSource() == DesktopClientGUI.upButton) {
				System.out.println("up button Selected");
				message.setRelease("forward");
				
			}else if(e.getSource() == DesktopClientGUI.downButton) {
				System.out.println("down button Selected");
				message.setRelease("backward");
				
			}else if(e.getSource() == DesktopClientGUI.leftButton) {
				System.out.println("left button Selected");
				message.setRelease("left"); 
			}else if(e.getSource() == DesktopClientGUI.rightButton) {
				System.out.println("right button Selected");
				message.setRelease("right");
			}
			else if(e.getSource() == DesktopClientGUI.doneButton) {
				System.out.println("left button Selected");
				message.setRelease("done");
			}
			}
			catch(Exception f) {
				System.out.println("couldnt send command- button");
				f.printStackTrace();
			}
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		//dont need
		
	}
}




