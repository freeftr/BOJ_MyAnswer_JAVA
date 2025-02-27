import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        answer = bs(stones, k);
        return answer;
    }
    
    static int bs(int[] stones, int k){
        int low = 0;
        int high = 200000000;
        
        while(low<=high){
            int mid = (low+high)/2;
            
            if (across(stones, mid, k)){
                low = mid+1;
            } else{
                high = mid-1;
            }
        }
        
        return high;
    }
    
    static boolean across(int[] stones, int mid, int k){
        int cnt = 0;
        
        for (int stone : stones){
            if (stone - mid < 0){
                cnt++;
            } else {
                cnt = 0;
            }
            
            if(cnt==k){
                return false;
            }
        }
        return true;
    }
}