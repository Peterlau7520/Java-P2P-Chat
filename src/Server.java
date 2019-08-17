import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    ServerSocket serverSocket;
    int PORT;
    ArrayList<ServerSocket> socketList;
    public Server(int PORT){
        try{
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("Server started listening at " + Integer.toString(PORT));
            System.out.println("Input a peer you want to talk to: " );
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            String[] portList = reader.readLine().split(" ");
            for(int i=0; i<portList.length;i++){
                int port =Integer.parseInt(portList[i]);
                Thread t = new Thread(new PeerReceive(port));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen() throws IOException {

        ServerThread serverThread = new ServerThread(this.serverSocket);
        Thread t = new Thread(serverThread);
        t.start();

        while(true){
            serverThread.communicate();
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        System.out.println("Please enter a port number to host this server");
        String portString = reader.readLine();
        int portInt = Integer.parseInt(portString);
        Server server = new Server(portInt);
        server.listen();

    }
}
