import java.util.*;
import java.lang.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt(), P = sc.nextInt();
        String DNA = sc.next();
        int A = sc.nextInt(), C = sc.nextInt(), G = sc.nextInt(), T = sc.nextInt();
        int a = 0, c = 0, g = 0, t = 0;
        int cnt = 0;
        for(int i = 0; i < P; i++){
            if(DNA.charAt(i) == 'A') a++;
            else if(DNA.charAt(i) == 'C') c++;
            else if(DNA.charAt(i) == 'G') g++;
            else if(DNA.charAt(i) == 'T') t++;
        }
        for(int i = 0; i < S - P; i++){
            if(a >= A && c >= C && g >= G && t >= T) cnt++;
            if(DNA.charAt(i) == 'A') a--;
            else if(DNA.charAt(i) == 'C') c--;
            else if(DNA.charAt(i) == 'G') g--;
            else if(DNA.charAt(i) == 'T') t--;
            if(DNA.charAt(i + P)== 'A') a++;
            else if(DNA.charAt(i + P)== 'C') c++;
            else if(DNA.charAt(i + P)== 'G') g++;
            else if(DNA.charAt(i + P)== 'T') t++;
        }
        a = 0;
        c = 0;
        g = 0;
        t = 0;
        for(int i = S - P; i < S; i++){
            if(DNA.charAt(i) == 'A') a++;
            else if(DNA.charAt(i) == 'C') c++;
            else if(DNA.charAt(i) == 'G') g++;
            else if(DNA.charAt(i) == 'T') t++;
        }
        if(a >= A && c >= C && g >= G && t >= T) cnt++;
        System.out.println(cnt);
    }
}