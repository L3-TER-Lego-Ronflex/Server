
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;


public class MainServer {

	private static Scanner s;

	public static void main(String[] args) {
		NXTConnector conn = new NXTConnector();
		
		/*
		conn.addLogListener(new NXTCommLogListener() {
			public void logEvent(String message) {
				System.out.println("BTSend Log.listener: "+message);
				
			}
			public void logEvent(Throwable throwable) {
				System.out.println("BTSend Log.listener - stack trace: ");
				 throwable.printStackTrace();	
			}
		});
		*/
		
		// Connect to RobotAlex over Bluetooth
		boolean connected = conn.connectTo("btspp://RobotAlex");
		
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		DataInputStream dis = new DataInputStream(conn.getInputStream());
		
		ObjectOutputStream oos;
		ObjectInputStream ois;
		try {
			oos = new ObjectOutputStream(conn.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("IO Exception creating ObjectOutputStream:");
			System.out.println(ioe.getMessage());
			return;
		}
		try {
			ois = new ObjectInputStream(conn.getInputStream());
		} catch (IOException ioe) {
			System.out.println("IO Exception creating ObjectInputStream:");
			System.out.println(ioe.getMessage());
			return;
		}
		
		s = new Scanner(System.in);
				
		try {
			String str = s.nextLine();
			System.out.println("Sending " + str);
			dos.writeUTF(str);
			oos.writeObject(new LinkedLabyrinth());
			dos.flush();
			
		} catch (IOException ioe) {
			System.out.println("IO Exception writing String:");
			System.out.println(ioe.getMessage());
		}
		
		try {
			System.out.println("Received " + dis.readUTF());
		} catch (IOException ioe) {
			System.out.println("IO Exception reading String:");
			System.out.println(ioe.getMessage());
		}
		
		try {
			dis.close();
			dos.close();
			conn.close();
		} catch (IOException ioe) {
			System.out.println("IOException closing connection:");
			System.out.println(ioe.getMessage());
		}
	}
}
