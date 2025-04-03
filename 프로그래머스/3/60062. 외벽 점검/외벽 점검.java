import java.util.*;

class Solution {
    
    static int N;
    static int weakLength;
    static int distLength;
    static int answer;
    static int[] newWeak;

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        
        N = n;
        weakLength = weak.length;
        distLength = dist.length;
        newWeak = new int[weakLength * 2];

        // 원형 취약점 배열 만들기
        for (int i = 0; i < weakLength; i++) {
            newWeak[i] = weak[i];
            newWeak[i + weakLength] = weak[i] + n;
        }

        // 이동 거리 배열을 큰 값부터 정렬 (최대 거리 먼저 배치)
        Arrays.sort(dist);
        reverseArray(dist);

        // 가능한 모든 친구 조합을 검사
        for (int i = 1; i <= distLength; i++) { // 1명부터 distLength 명까지 사용 가능
            generatePermutations(new ArrayList<>(), new boolean[distLength], dist, i);
        }

        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    // 순열 생성 (DFS 백트래킹) - 주어진 인원만큼만 선택
    static void generatePermutations(ArrayList<Integer> candidate, boolean[] used, int[] dist, int limit) {
        if (candidate.size() == limit) {
            checkCoverage(candidate);
            return;
        }
        
        for (int i = 0; i < distLength; i++) {
            if (used[i]) continue;
            used[i] = true;
            candidate.add(dist[i]);
            generatePermutations(candidate, used, dist, limit);
            candidate.remove(candidate.size() - 1);
            used[i] = false;
        }
    }

    // 특정 친구 배치로 취약 지점을 커버할 수 있는지 검사
    static void checkCoverage(ArrayList<Integer> candidate) {
        int candidateSize = candidate.size();

        for (int start = 0; start < weakLength; start++) {
            int friendCount = 1; // 사용한 친구 수
            int position = newWeak[start] + candidate.get(friendCount - 1);
            
            for (int i = start; i < start + weakLength; i++) {
                if (newWeak[i] > position) {
                    friendCount++;
                    if (friendCount > candidateSize) break;
                    position = newWeak[i] + candidate.get(friendCount - 1);
                }
            }

            if (friendCount <= candidateSize) {
                answer = Math.min(answer, friendCount);
            }
        }
    }

    // 배열을 내림차순으로 정렬
    static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
