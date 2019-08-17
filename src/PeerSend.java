import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PeerSend implements Runnable{

    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public PeerSend(Socket socket) throws IOException {

        this.socket = socket;
        this.reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
    }

    public PrintWriter getOutputStream() throws IOException {
         this.writer = new PrintWriter(this.socket.getOutputStream(),true);
        return writer;
    }
    @Override
    public void run() {
        try {
            while(true){
                String echoString = reader.readLine();
                System.out.println("Echostring " + echoString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
