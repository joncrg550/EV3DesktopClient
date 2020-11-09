package Client;

import java.net.*;

import GUI.DesktopClientGUI;
import RemoteControl.RemoteControlEV3;

import java.io.*;

public class DesktopClient {

	private Socket socket = null;
	private DataOutputStream output = null;
	
	public DesktopClient(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Connected to robot");
			
			output = new DataOutputStream(socket.getOutputStream());
			
			
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					DesktopClientGUI.buildGUI(output);
				}
			});
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	
}
