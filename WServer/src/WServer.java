import java.net.*;

public class WServer extends HttpRequest
{
	public WServer(Socket socket) throws Exception {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static void main (String[] args) throws Exception {
		
		int port = 9204;
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server is running on port " + port + "\r\n");
		System.out.println("Waiting for requests...\r\n");
	
		while (true)
		{
			Socket sock = serverSocket.accept();
			HttpRequest request = new HttpRequest(sock);
			Thread thread = new Thread(request);
			thread.start();
		}
	}
}