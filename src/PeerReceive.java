import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerReceive implements Runnable{
    Socket socket;
    int PORT;
    BufferedReader reader;

    public PeerReceive(int PORT) throws IOException {
        try {
            this.PORT = PORT;
            System.out.println(PORT);
            this.socket = new Socket("localhost", PORT);
            System.out.println("Connected");
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        while(true){
            try {
                String msg = reader.readLine();
                System.out.println("Message received "+ msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
