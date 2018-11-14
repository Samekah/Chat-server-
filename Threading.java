import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Threading extends Thread{
	
	private String userName;
	private int uniqueID;
	Socket socket;
	String clientInput;
	boolean isConnected = true;
	InputStreamReader r;
	BufferedReader clientIn;
	PrintWriter out;
	
	Threading(Socket socket){
		
		this.socket = socket;
				
		try {
			r = new InputStreamReader(this.socket.getInputStream());	//letter by letter
			clientIn = new BufferedReader(r);						//line by line
			
			out = new PrintWriter(this.socket.getOutputStream(),true);
			
			setUserName(clientIn.readLine());
			
			ArrayHolder.chAdd("*** " + getUserName() + " has connected to the server ***");
			System.out.println("\n*** " + getUserName() + " has connected to the server ***");
			
			Broadcast("\n*** " + getUserName() + " has connected to the server ***");
					
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//give a unique address/user name
	private String getUserName() {
		
		return userName;
	}
	
	private void setUserName(String n) {
		
		userName = n;
	}
	
		
	public void run() {
		
		//listen to client
		//listen to server
		//send information back and forth
		try {
		
			while(isConnected) {
			
				clientInput = clientIn.readLine(); //receive data from client 
				ArrayHolder.chAdd(getUserName() + ": "+ clientInput);
				
				if(clientInput.equals("LOGOUT")) {
					Broadcast("*** " + getUserName() + " has disconnected ***");
					r.close();
					clientIn.close();
					out.close();
					this.socket.close();
					RemoveClientThread();
					Thread.currentThread().interrupt();//preserve the message
				    return;
				}
				else {
				System.out.println(getUserName() + ": "+ clientInput);// to server console
				Broadcast(getUserName() + ": "+ clientInput); //back to client console
				}
			}
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				
			//even if the try...catch runs successfully or if it fails this will always be ran
			 //this makes sure that the socket is closed after every message 
				 
			try {
				r.close();
				clientIn.close();
				out.close();
				this.socket.close();
				RemoveClientThread();
				Thread.currentThread().interrupt();
			    return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	  
	}
	private void Broadcast(String msg) {
		
		for(int i = 0; i< ArrayHolder.clSize(); i++) {
			
			Threading bct = ArrayHolder.clFind(i);
			bct.out.println(msg);
		}
	}
	
	private void RemoveClientThread() {
		
		for(int i = 0; i< ArrayHolder.clSize(); i++) {
			
			Threading bct = ArrayHolder.clFind(i);
			
			System.out.println("THREAD IS: " + Thread.currentThread());
			
			if(bct == Thread.currentThread()) {
				ArrayHolder.clremove(Thread.currentThread());
			}
			else {}		
			
			for(int t = 0; t< ArrayHolder.clSize(); t++) {
				
				Threading bct2 = ArrayHolder.clFind(i);
				System.out.println(bct2);
			}
		}
	}
	
}	

