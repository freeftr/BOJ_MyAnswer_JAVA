import java.io.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> cache = new ArrayList<>();
        
        if (cacheSize==0) {
            return cities.length*5;
        }
        
        for (String city : cities) {
            if (cache.contains(city.toLowerCase())) {
                cache.remove(city.toLowerCase());
                cache.add(city.toLowerCase());
                answer++;
            } else {
                if (cache.size() >= cacheSize) {
                    cache.remove(0);
                }
                cache.add(city.toLowerCase());
                answer+=5;
            }
        }
        
        return answer;
    }
}