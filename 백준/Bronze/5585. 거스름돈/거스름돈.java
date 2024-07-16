import java.util.*;
public class Main {
    static int cnt = 0;
    public static int calc(int money){
        cnt++;
        if(money >= 500){
            return calc(money-500);
        }
        else if(money >= 100){
            return calc(money - 100);
        }
        else if(money >= 50){
            return calc(money - 50);
        }
        else if(money >= 10){
            return calc(money - 10);
        }
        else if(money >= 5){
            return calc(money - 5);
        }
        else{
            return cnt + money - 1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        money = 1000 - money;
        System.out.println(calc(money));
    }
}
