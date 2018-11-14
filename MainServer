import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {

	//run first
	private ServerSocket clientIn;
	Socket s;
	Boolean serverOn = true;
	String clientName = "";
	//ArrayList<String> chatHistory = new ArrayList<String>();
	//ArrayList<Threading> clientList = new ArrayList<Threading>();
		
	BufferedReader ai;
	
	/*this constructor sets up the server which the client will connect to
	 * it will listen and wait for a connection to come in*/
	 
	public MainServer(int port) {
		try {
			clientIn = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//accept a connection
	//once the server is instantiated this will start it up/ make it run
	public void start() {
		
		//String adminIn;
		//ai = new BufferedReader(new InputStreamReader(System.in));
		
		while(serverOn) {
			  try {
				System.out.println("server waiting for a connection");
				
				s = clientIn.accept();		//this will be a two way communication link between server and client
				System.out.println("server accepted request on " + clientIn.getLocalPort() + " : " + s.getPort());
				Threading t = new Threading(s);
				
				ArrayHolder.clAdd(t);   //adds client name to the list
				
				t.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			  
			  /*try {
				adminIn = ai.readLine();
				
				if(adminIn.equals("EXIT")) {
					ClientConnected();
					Broadcast("*** Server has been Closed***");
					CloseServer();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
				
			 
		}
	}
	
	
	/*When the server starts the portNumber is set up depending on if a parameter has been passed via the command line,
	 * if none has been passed the default portNumber will be 14001, whereas as if it has been passed the portNumber 
	 * will be the argument passed in. Finally if more that one argument is passed an error message will be displayed
	 */
	public static void main(String[]args) {
		
		int portNumber = 14001;
		
		if(args.length ==1 || args.length > 2) {
			System.err.println("Java ChatServer <portNumber>");
			System.exit(1);
		}
		else if(args.length ==2){
			
			if(args[0].equals("-csp")) {
				portNumber = Integer.parseInt(args[1]);
				System.out.println("Port number set to: " + portNumber);			
			}
			else {
				System.err.println("Java ChatServer <parameter -csp> <portNumber>");
				System.exit(1);;
			}	
		}
		else {
			System.out.println("Port number set to default port: " + portNumber);
		}
			
		new MainServer(portNumber).start();//creates main server
	}
}
