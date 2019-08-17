import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread implements Runnable{
    ServerSocket serverSocket;
    Set<PeerSend> hashSet = new HashSet();

    public ServerThread(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void communicate() throws IOException {
        while(true){
            System.out.println("Please input");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String msg = reader.readLine();
            for(PeerSend peer : hashSet){
                peer.getOutputStream().println(msg);
            }
        }
    }
    @Override
    public void run() {
        while(true){
            try {
                Socket socket = this.serverSocket.accept();
                PeerSend peer = new PeerSend(socket);
                hashSet.add(peer);
                Thread t = new Thread(peer);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
