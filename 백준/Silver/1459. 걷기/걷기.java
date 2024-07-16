import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long X = sc.nextInt();
        long Y = sc.nextInt();
        long W = sc.nextInt();
        long S = sc.nextInt();
        long result = 0;
        if((2 * W) <= S){
            result = ((X + Y) * W);
        }
        else{
            result = Math.min(X,Y) * S;
            if(W > S){
                if(Math.abs(X - Y) % 2  == 0){
                    result += Math.abs(X - Y) * S;
                }
                else{
                    result += S * (Math.abs(X - Y) - 1) + W;
                }
            }
            else{
                result += Math.abs(X - Y) * W;
            }
        }
        System.out.println(result);
    }
}
