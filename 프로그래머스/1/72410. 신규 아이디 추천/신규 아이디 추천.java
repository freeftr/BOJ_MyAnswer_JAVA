class Solution {
    public String solution(String new_id) {
        String answer = "";
        System.out.println("0 " + new_id);
        //1.
        new_id = new_id.toLowerCase();
        System.out.println("1 " + new_id);
        
        String temp = "";
        //2.
        for (int i = 0; i < new_id.length(); i++) {
            if (new_id.charAt(i) == '.' || new_id.charAt(i) == '-' || new_id.charAt(i) == '_') {
                temp += new_id.charAt(i) + "";
            }
            
            if (new_id.charAt(i) >= 'a' && new_id.charAt(i) <= 'z') {
                temp += new_id.charAt(i) + "";
            }
            
            if (new_id.charAt(i) >= '0' && new_id.charAt(i) <= '9') {
                temp += new_id.charAt(i) + "";
            }
        }
        System.out.println("2 " + temp);
        
        //3. 
        temp = temp.replaceAll("\\.+", ".");
        System.out.println("3 " + temp);
        
        if (temp.length() > 0 && temp.charAt(0) == '.') {
            temp = temp.substring(1);
        }
        if (temp.length() > 0 && temp.charAt(temp.length() - 1) == '.') {
            temp = temp.substring(0, temp.length() - 1);
        }

        System.out.println("4 " + temp);
        
        if (temp.equals("")) temp = "a";
        System.out.println("5 " + temp);
        
        if (temp.length() > 15) {
            temp = temp.substring(0, 15);
            while (temp.charAt(temp.length() - 1) == '.') {
                temp = temp.substring(0, temp.length() - 1);
            }
        }
        System.out.println("6 " + temp);
        
        if (temp.length() <= 2) {
            while (temp.length() < 3) {
                temp += temp.charAt(temp.length() - 1);
            }
        }
        System.out.println("7 " + temp);
        return temp;
    }
}

/*
3자 이상 15자 이하
소문자, 숫자, -, _, .만 가능
.은 처음과 끝 불가능, 연속 블가능

1. 소문자화
2. 안되는 문자 제거.
3. 연속된 . 한개로 치환
4. 처음과 끝 . 제거
5. 빈문자열이면 a넣기
6. 16자 이상이면 15자까지 줄이고 마지막 . 이면 제거
7. 2자이하면 마지막 문자 반복해서 3자로
*/