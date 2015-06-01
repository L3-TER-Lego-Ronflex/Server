
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import lejos.pc.comm.NXTConnector;


public class MainServer {

	private static Scanner s;

	public static void main(String[] args) {
		// A connector can be used to connect to the robot over Bluetooth or USB
		NXTConnector conn = new NXTConnector();
		
		// Here we use Bluetooth (btspp) for the robot RobotAlex
		boolean connected = conn.connectTo("btspp://RobotAlex");
		
		// We quit the application if we can't connect
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		DataInputStream dis = new DataInputStream(conn.getInputStream());
				
		try {
			String str = s.nextLine();
			System.out.println("Sending " + str);
			dos.writeUTF(str);
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
