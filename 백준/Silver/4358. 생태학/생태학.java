import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 0;
        HashMap<String, Integer> cnt = new HashMap<>();

        while (true) {
            String tree = br.readLine();

            if (tree == null) break; // EOF 처리

            N++;
            cnt.merge(tree, 1, Integer::sum);
        }

        HashMap<String, Double> info = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for (String name : cnt.keySet()) {
            double n = (double)cnt.get(name) / N * 100;
            result.add(name);
            info.put(name, n);
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();

        for (String name : result) {
            String s = String.format("%.4f", info.get(name));
            sb.append(name + " " + s).append("\n");
        }

        System.out.println(sb);
    }
}
