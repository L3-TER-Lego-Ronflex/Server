
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.pc.comm.NXTConnector;


public class MainServer {
	public static void main(String[] args) {
		// A connector can be used to connect to the robot over Bluetooth or USB
		NXTConnector conn = new NXTConnector();
		
		// Here we use Bluetooth (btspp) for the robot NXT
		boolean connected = conn.connectTo("btspp://NXT");
		
		// We quit the application if we can't connect
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		
		// We create the streams to send and receive data
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		DataInputStream dis = new DataInputStream(conn.getInputStream());
		
		// We receive the Labyrinth String
		String llstr = "";
		try {
			llstr = dis.readUTF();
			System.out.println("Received " + llstr);
		} catch (IOException ioe) {
			System.out.println("IO Exception reading String:");
			System.out.println(ioe.getMessage());
			return;
		}
		LinkedLabyrinth ll = new LinkedLabyrinth();
		ll.fromString(llstr);
		ll.fortify();
		System.out.println(ll.graphicalRepresentation());
		
		// Compute the shortest path
		String path = ll.findPath();
		
		System.out.println("Waiting key is pressed to send the path (and start)...");
		try {
			System.in.read();
		} catch (IOException e) {}
		
		// We send the displacement sequence String
		try {
			System.out.println("Sending " + path);
			dos.writeUTF(path);
			dos.flush();
		} catch (IOException ioe) {
			System.out.println("IO Exception writing String:");
			System.out.println(ioe.getMessage());
			return;
		}
		
		// We close the streams then the connection
		try {
			dis.close();
			dos.close();
			conn.close();
		} catch (IOException ioe) {
			System.out.println("IOException closing connection:");
			System.out.println(ioe.getMessage());
			return;
		}
	}
}
