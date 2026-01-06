import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long r = N % 5;

        if (r == 0 || r == 2) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
