import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ClientServer {

	//the client needs the IP address and port Number to connect to a server, this s known as the socket
	
	Socket server;
	BufferedReader userIn;
	BufferedReader serverIn;
	
	public ClientServer(String a, int p) {
		
		//find server
		
		try {
			server = new Socket(a, p);
		} catch (UnknownHostException e) {
			// the IP address could not be found
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start() {
				
		try {
			 
			serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));		//receive response from server 
			
			//make requests (send user input)
			
			System.out.println("enter a username: ");
			ClientThread ct = new ClientThread(server);
			
			ArrayHolder.ctlAdd(ct);
			
			ct.start();
			while(true) {
				
				String serverRes = serverIn.readLine(); //server response (coming from thread out)
				if(serverRes != null) {
					System.out.println(serverRes);
				}
				else {
					System.exit(1);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //catch (InterruptedException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		finally {
			
			/*even if the try...catch runs successfully or if it fails this will always be ran
			 *this makes sure that the socket is closed after every message */
			 
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*public void ServerClosed() {
		
		if(server.isClosed()) {
			
				System.out.println("*** User disconnected***");
				System.exit(1);
			
		}
		else {}
	}*/
	
	
	
	public static void main(String[]args) {
		
		String address = "localhost";
		int port = 14001;
		
		if(args.length ==1|| args.length >4) {
			System.err.println("Java ChatClient <parameter -cca, -ccp>");
			System.exit(1);
		}
		else if(args.length==2) {
			
			if(args[0].equals("-cca")) {
				address = args[1];
				System.out.println("Address set to: " + address);
			}
			else if(args[0].equals("-ccp")) {
				port = Integer.parseInt(args[1]);
				System.out.println("Port set to : " + port);
			}
			else {
				System.err.println("Java ChatClient <parameter -cca, -ccp>");
				System.exit(1);
			}
		}
		else if(args.length==4) {
			if(args[0].equals("-cca") && args[2].equals("-ccp")) {
				address = args[1];
				port = Integer.parseInt(args[3]);
				System.out.println("Address set to: " + address + " and port set to : " + port);
			}
			else{
				System.err.println("Java ChatClient <parameter -cca> <address > <parameter -ccp> <port>");
				System.exit(1);
			}
		}
		else {
			System.out.println("Address set to default address: " + address + " and port set to default port : " + port);
		}

			
		//tells the client where to find the server
		new ClientServer(address, port).start();
		System.out.println("Client connected to address: " + address + " on port: " + port);
	}
}
