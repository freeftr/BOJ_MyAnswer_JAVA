import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        ArrayList<String> caches = new ArrayList<>();
        
        for (String city : cities) {
            city = city.toLowerCase();
            if (caches.contains(city)) {
                answer += 1;
                if (caches.size() < cacheSize) {
                    caches.add(city);
                } else {
                    caches.remove(caches.indexOf(city));
                    caches.add(city);  
                }
            } else {
                answer += 5;
                if (caches.size() < cacheSize) {
                    caches.add(city);
                } else {
                    caches.remove(0);
                    caches.add(city);  
                }
            }
        }
        return answer;
    }
}