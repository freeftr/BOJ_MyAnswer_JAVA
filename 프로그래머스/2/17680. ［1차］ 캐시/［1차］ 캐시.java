import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> cache = new ArrayList<>();
        String[] citie = new String[4];
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        citie[0] = "1";
        citie[1] = "1";
        citie[2] = "1";
        citie[3] = "1";
        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.isEmpty()) {
                cache.add(city);
                answer += 5;
                continue;
            }
            if (!cache.contains(city)) {
                if (cache.size() < cacheSize) {
                    cache.add(city);
                } else {
                    cache.remove(0);
                    cache.add(city);
                }
                answer += 5;
            } else {
                int idx = cache.indexOf(city);
                cache.remove(idx);
                cache.add(city);
                answer++;
            }
        }
        // System.out.println(answer + " " + cache.size());
        return answer;
    }
}

/*
DB 캐시에 따른 실행 시간 측정
hit - 1
miss - 5
*/