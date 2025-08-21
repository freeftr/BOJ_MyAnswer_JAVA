import java.util.*;
class Solution {
    
    static ArrayList<Node> list = new ArrayList<>();
    static HashMap<String, Node> map = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        
        map.put("minho", new Node(0, "minho"));
        
        for (int i = 0; i < enroll.length; i++) {
            Node node = new Node(0, enroll[i]);
            map.put(enroll[i], node);
        }
        
        for (int i = 0; i < referral.length; i++) {
            Node child = map.get(enroll[i]);
            if (referral[i].equals("-")) {
                child.makeParent(map.get("minho"));
            } else {
                child.makeParent(map.get(referral[i]));
            }
        }
        
        for (int i = 0; i < seller.length; i++) {
            int fee = amount[i] * 100;
            String sellerName = seller[i];
            
            Node sell = map.get(sellerName);
            sell.pay(fee);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < enroll.length; i++) {
            int balance = map.get(enroll[i]).balance;
            result.add(balance);
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    static class Node {
        int balance;
        Node parent;
        String name;
        
        Node (int balance, String name) {
            this.balance = balance;
            this.name = name;
        }
        
        public void makeParent(Node parent) {
            this.parent = parent;
        }
        
         public void pay(int fee) {
            int commission = fee / 10;
            int keep = fee - commission;
            this.balance += keep;

            if (commission == 0) return;

            if (parent != null) {
                parent.pay(commission);
            }
        }
    }
}