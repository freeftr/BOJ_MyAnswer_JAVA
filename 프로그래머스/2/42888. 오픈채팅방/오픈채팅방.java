import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        ArrayList<String> result = new ArrayList<>();
        
        // uid : 닉네임
        HashMap<String, String> userMap = new HashMap<>();
        
        for (String rec : record) {
            String[] s = rec.split(" ");
            String type = s[0];
            String uid = s[1];
            String nick = "";
            if (!type.equals("Leave")) {
                nick = s[2];
            }
            
            // 입장
            if (type.equals("Enter")) {
                userMap.put(uid, nick);
                result.add(uid + ":" + type);
                // 처음 입장 시
                // if (userMap.get(uid) == null) {
                //     userMap.put(uid, nick);
                //     result.add(uid + ":" + type);
                // }
                // 다시 들어올 때
                // else if (userMap.get(uid).equals(nick)) {
                //     result.add(uid + ":" + type);
                // }
                // 닉네임 바꿔서 올 때
                // else if (!userMap.get(uid).equals(nick)) {
                //     userMap.put(uid, nick);
                //     result.add(uid + ":" + type);
                // }
            } else if (type.equals("Change")){
                userMap.put(uid, nick);
            } else {
                result.add(uid + ":" + type);
            }
        }
        
        answer = new String[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            String s = result.get(i);
            String nick = userMap.get(s.split(":")[0]);
            String type = s.split(":")[1];
            
            if (type.equals("Enter")) {
                answer[i] = nick + "님이 들어왔습니다.";
            } else {
                answer[i] = nick + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}