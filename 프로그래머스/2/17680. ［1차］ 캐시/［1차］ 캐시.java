import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        
        ArrayList<String> cache = new ArrayList<>();
        
        for (String city : cities) {
            if (cache.contains(city)) {
                cache.remove(cache.indexOf(city));
                cache.add(city);
                answer++;
            } else {
                if (cache.size() < cacheSize) {
                    cache.add(city);
                } else {
                    cache.remove(0);
                    cache.add(city);
                }
                answer += 5;
            }
        }
        return answer;
    }
}