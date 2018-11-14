import java.util.ArrayList;

public class ArrayHolder {

	private static ArrayList<String> chatHistory = new ArrayList<String>();
	private static ArrayList<Threading> clientList = new ArrayList<Threading>();
	private static ArrayList<ClientThread> clientThreadList = new ArrayList<ClientThread>();
	
	public static synchronized void ctlAdd(ClientThread ctl) {
		synchronized(clientThreadList) {
			clientThreadList.add(ctl);
		}
	}
	
	public static synchronized void clAdd(Threading ct) {
		synchronized(clientList) {
			clientList.add(ct);
		}
	}
	
	public static synchronized void clremove(Thread ct) {
		synchronized(clientList) {
			clientList.remove(ct);
		}
	}
	
	public static synchronized int  clSize(){ 
		synchronized(clientList) {
			return clientList.size();
		}
	}
	
	public static synchronized Threading clFind(int ci){
		synchronized(clientList) {
			return clientList.get(ci);
		}
	}
	
	public static synchronized void chAdd(String cm) {
		synchronized(chatHistory) {
			chatHistory.add(cm);
		}
	}
	
	public static synchronized String chFind(int ci){
		synchronized(chatHistory) {
			return chatHistory.get(ci);
		}
	}
	
	public static synchronized int chSize(){ 
		synchronized(chatHistory) {
			return chatHistory.size();
		}
	}
}
	

