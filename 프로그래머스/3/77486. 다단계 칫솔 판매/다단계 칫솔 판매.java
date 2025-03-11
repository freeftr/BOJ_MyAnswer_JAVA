import java.util.*;

class Solution {
    
    static ArrayList<Node> emps = new ArrayList<>();
    static HashMap<String, Integer> empCache = new HashMap<>();
    
    static class Node {
        int balance;
        String name;
        Node parent;
        
        public Node(int balance, String name, Node parent) {
            this.balance = balance;
            this.name = name;
            this.parent = parent;
        }
        
        public void payFee(int money) {
            if (this.parent == null || money < 1) return;
            int fee = money / 10;
            this.balance += (money - fee);
            this.parent.payFee(fee);
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int idx = 0;
        
        emps.add(new Node(0, "minho", null));
        empCache.put("minho", idx++);
        
        for (String name : enroll) {
            emps.add(new Node(0, name, null));
            empCache.put(name, idx++);
        }

        for (int i = 0; i < enroll.length; i++) {
            if (!referral[i].equals("-")) {
                int nowId = empCache.get(enroll[i]);
                int parentId = empCache.get(referral[i]);
                emps.get(nowId).parent = emps.get(parentId);
            } else {
                int nowId = empCache.get(enroll[i]);
                emps.get(nowId).parent = emps.get(0);
            }
        }

        for (int i = 0; i < seller.length; i++) {
            int id = empCache.get(seller[i]);
            int money = amount[i] * 100;
            emps.get(id).payFee(money);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = emps.get(empCache.get(enroll[i])).balance;
        }

        return answer;
    }
}
