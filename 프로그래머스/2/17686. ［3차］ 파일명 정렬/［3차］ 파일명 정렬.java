import java.util.*;

class Solution {

    static class File implements Comparable<File> {
        int idx;
        String original;
        String head;
        String numberStr;

        public File(int idx, String original, String head, String numberStr) {
            this.idx = idx;
            this.original = original;
            this.head = head;
            this.numberStr = numberStr;
        }

        @Override
        public int compareTo(File o) {
            int headCompare = this.head.toLowerCase().compareTo(o.head.toLowerCase());
            if (headCompare != 0) return headCompare;

            int thisNum = Integer.parseInt(this.numberStr);
            int otherNum = Integer.parseInt(o.numberStr);
            if (thisNum != otherNum) return thisNum - otherNum;

            return this.idx - o.idx;
        }
    }

    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int idx = 0;

            StringBuilder head = new StringBuilder();
            while (idx < file.length() && !Character.isDigit(file.charAt(idx))) {
                head.append(file.charAt(idx++));
            }

            StringBuilder number = new StringBuilder();
            while (idx < file.length() && Character.isDigit(file.charAt(idx)) && number.length() < 5) {
                number.append(file.charAt(idx++));
            }

            list.add(new File(i, file, head.toString(), number.toString()));
        }

        Collections.sort(list);

        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = list.get(i).original;
        }
        return answer;
    }
}
