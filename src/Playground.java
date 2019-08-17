import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Playground {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String[] portString = reader.readLine().split(" ");
//        String[] splitted = portString.split(" ");
        System.out.println(Arrays.toString(portString));

    }
}
