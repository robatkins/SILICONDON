import java.io.*;
import java.net.*;
import java.util.*;



public class ServerThread extends Thread
{
	
	
	
	
	Socket socket;
	int bufferID = 0; 
	String signalBuffer = null;
	
	Queue<String> signalQueue = new Queue();
	Stack<String> signalStack = new Stack();
	
	ServerThread(Socket socket)
	{
		
		this.socket = socket;
		
	}
	
	public void run()
	{
		
		try {
			
			
			
			
			BufferedReader connectionInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream connectionOutput = new PrintStream(socket.getOutputStream());
			
			while((signalBuffer = connectionInput.readLine()) != null)
			{
				
				signalQueue.Enqueue(signalBuffer);
				
				if(signalQueue != null)
				{
					
					
					System.out.println("Signal Queue received the buffer:       [\"" + signalQueue.GetLastItem() + "\"]");
					connectionOutput.println("buffer with ID=" + bufferID++ + " Received. " );
					connectionOutput.flush();
				
					//System.out.println("Queue Length: " + signalQueue.LengthQueue());
					System.out.println();
					//System.out.println("Signal Dequeue Output: " + signalQueue.Dequeue());
				
					signalBuffer = signalQueue.GetFirstItem();
				
					//Client Greeting Logic
				
					if(signalBuffer.equals("Initial Client greeting to Server."))
					{
						
						signalQueue.Dequeue();
						connectionOutput.println("Acknowledgement of Client greeting from Server.");
						connectionOutput.flush();
						
					
					}
					
					if(signalBuffer.equals("Client received acknowledgement. Establishing connection."))
					{
						
						//No need for a signal sent to the client about this. 
						signalQueue.Dequeue();
					}
				
					if(signalBuffer.equals("Client can now receive input data from Server."))
					{
					
						signalQueue.Dequeue();
						connectionOutput.println("Server is now accepting data from Client.");
						connectionOutput.flush();
						connectionOutput.println("scan internal network");
						connectionOutput.flush();
					
					
					}
				
				
				//Close the connection to the client if requested
					if(signalBuffer.equals("Close connection request from Client."))
					{
					
						signalQueue.Dequeue();
						connectionOutput.println("Server is closing the connection.");
						connectionOutput.flush();
						socket.close();
					
					
					}
					
					
					
					
				}
				
				
				
				
				
				
				
				//System.out.print("Incoming client message");
				
				
			}
			
			socket.close();
			
			
		
		}catch (IOException e) {
			
			
			//failed to establish connection i/o
			e.printStackTrace();
		
		}
		
	}
	

}
