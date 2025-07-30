public class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skill_tree : skill_trees) {
            String filtered = skill_tree.replaceAll("[^" + skill + "]", "");

            if (skill.startsWith(filtered)) {
                answer++;
            }
        }

        return answer;
    }
}
