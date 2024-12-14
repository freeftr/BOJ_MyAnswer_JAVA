import java.io.*;
import java.util.*;

public class Main {
    static String[] word1;
    static String[] word2;
    static int[] g1;
    static int[] g2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        word1 = br.readLine().split("");
        word2 = br.readLine().split("");
        g1 = new int[word1.length];
        g2 = new int[word2.length];

        int sum1 = toInt(g1, word1);
        int sum2 = toInt(g2, word2);

        int ans1 = sum1 + sum2;

        String ans2 = toWord(ans1);

        System.out.println(ans1);
        System.out.println(ans2);
    }

    static public String toWord(int sum) {
        StringBuilder sb = new StringBuilder();

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (sum >= values[i]) {
                sb.append(symbols[i]);
                sum -= values[i];
            }
        }

        return sb.toString();
    }

    static public int toInt(int[] g, String[] word) {
        for (int i = 0; i < word.length; i++) {
            switch (word[i]) {
                case "I":
                    g[i] = 1;
                    break;
                case "V":
                    g[i] = 5;
                    break;
                case "X":
                    g[i] = 10;
                    break;
                case "L":
                    g[i] = 50;
                    break;
                case "C":
                    g[i] = 100;
                    break;
                case "D":
                    g[i] = 500;
                    break;
                case "M":
                    g[i] = 1000;
                    break;
            }
        }

        int sum = 0;
        for (int i = 0; i < word.length - 1; i++) {
            if (g[i] >= g[i + 1]) {
                sum += g[i];
            } else {
                sum -= g[i];
            }
        }
        sum += g[word.length - 1];

        return sum;
    }
}