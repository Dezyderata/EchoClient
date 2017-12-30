import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class JavaClient {
	
	String hostName;
	int portNumber;
	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	BufferedReader stdIn;
	public JavaClient(String name, int numeber) {
		this.hostName = name;
		this.portNumber = numeber;		
	}
	public void connect() {
		try {
			this.echoSocket = new Socket(hostName, portNumber);
			this.stdIn = new BufferedReader(new InputStreamReader(System.in));
			this.out = new PrintWriter(this.echoSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(this.echoSocket.getInputStream()));
			smallTalks();
		}	
		catch (UnknownHostException e) {
			e.printStackTrace();
			System.err.println("Something is wrong with host!");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Something went wrong during connecting!");
			System.exit(1);
		}
	}
	
	public void smallTalks() {
		String userInput;
		try {
			while(!(userInput = stdIn.readLine()).equalsIgnoreCase("exit")){
				out.println(userInput);
				System.out.println("Client echo: " + userInput);
				System.out.println(in.readLine());
			}
			System.out.println("Bye server, it was nice to meet you!");
			closeConnection();
		} catch (IOException e) {
			System.err.println("Something went wrong during readLine in smallTalks");
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void closeConnection() throws IOException{
			echoSocket.close();
	}
	
}
