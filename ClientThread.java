import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

	public class ClientThread extends Thread {
	boolean isConnected = true;
	Socket server;
	BufferedReader userIn;
	PrintWriter serverOut;
	
	ClientThread(Socket socket){
		this.server = socket;
	}
	
	public void run() {
		
		//listen to client
		//listen to server
		//send information back and forth
		
			userIn = new BufferedReader(new InputStreamReader(System.in));		//take in input
			
			try {
				serverOut = new PrintWriter(this.server.getOutputStream(),true);
				while(isConnected) {
					
					
					String userInput = userIn.readLine();
					serverOut.println(userInput);
					System.out.print("> ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}

