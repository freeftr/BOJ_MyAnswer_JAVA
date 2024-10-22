import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        int l = queue1.length;
        for(int i = 0; i < l; i++){
            sum1+=queue1[i];
            q1.add(queue1[i]);
            sum2+=queue2[i];
            q2.add(queue2[i]);
        }
        
        int ans = 0;
        
        long target = (sum1+sum2)/2;
        
        while(true){
            if(ans>2*l+5){
                ans=-1;
                break;
            }
            if(sum1<target){
                ans++;
                int a = q2.poll();
                q1.add(a);
                sum1+=a;
                sum2-=a;
            } else if(sum2<target){
                ans++;
                int a = q1.poll();
                q2.add(a);
                sum1-=a;
                sum2+=a;
            }
            else{
                break;
            }
        }
        return ans;
    }
}