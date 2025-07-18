class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int[] arr1 = new int[10001];
        int[] arr2 = new int[10001];
        int cnt1 = 0;
        int cnt2 = 0;

        for (int top : topping) {
            if (arr1[top] == 0) {
                cnt1++;
            }
            arr1[top]++;
        }

        for (int top : topping) {
            if (arr2[top] == 0) {
                cnt2++;
            }
            arr2[top]++;

            arr1[top]--;
            if (arr1[top] == 0) {
                cnt1--;
            }

            if (cnt1 == cnt2) {
                answer++;
            }
        }

        return answer;
    }
}
