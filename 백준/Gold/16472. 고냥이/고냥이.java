import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int[] alphabet = new int[26];

        int left = 0;
        int right = 1;
        int ans = 0;

        alphabet[s.charAt(0)-'a']++;
        alphabet[s.charAt(1)-'a']++;

        while(left<=right) {
            int cnt = 0;

            for (int i = 0; i < 26; i++) {
                if (alphabet[i]!=0) {
                    cnt++;
                }
            }
//            System.out.println("left: "+left+" right: "+right+" ans: "+ans);

            if (cnt<=n){
                ans = Math.max(ans, right - left + 1);
                right++;
                if (right == s.length()) {
                    break;
                }
                alphabet[s.charAt(right)-'a']++;
            } else {
                alphabet[s.charAt(left)-'a']--;
                left++;
            }


        }

        System.out.println(ans);
    }
}
