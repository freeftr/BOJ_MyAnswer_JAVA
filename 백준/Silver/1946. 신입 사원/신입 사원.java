import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            ArrayList<Employee> employees = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int doc = Integer.parseInt(st.nextToken());
                int inter = Integer.parseInt(st.nextToken());

                employees.add(new Employee(doc, inter));
            }

            int answer = N;
            Collections.sort(employees, (a, b) -> a.doc - b.doc);
            int max = employees.get(0).inter;
            for (int i = 1; i < N; i++) {
                Employee now = employees.get(i);

                if (max < now.inter) {
                    answer--;
                } else {
                    max = now.inter;
                }
            }

            System.out.println(answer);
        }
    }

    static class Employee {
        int doc;
        int inter;

        Employee(int doc, int inter) {
            this.doc = doc;
            this.inter = inter;
        }
    }
}
