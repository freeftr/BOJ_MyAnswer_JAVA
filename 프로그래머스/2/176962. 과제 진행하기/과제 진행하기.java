import java.io.*;
import java.util.*;

class Solution {

    static PriorityQueue<Work> workList = new PriorityQueue<>();

    static class Work implements Comparable<Work> {
        String title;
        int start;
        int duration;

        public Work(String title, int start, int duration) {
            this.title = title;
            this.start = start;
            this.duration = duration;
        }

        @Override
        public int compareTo(Work o) {
            return this.start - o.start;
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        for (String[] plan : plans) {
            String[] temp = plan[1].split(":");
            String subject = plan[0];
            int start = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            int duration = Integer.parseInt(plan[2]);

            workList.add(new Work(subject, start, duration));
        }

        Stack<Work> stack = new Stack<>();
        int idx = 0;
        Work curWork = workList.poll();
        int time = curWork.start;

        while (!workList.isEmpty()) {
            Work nextWork = workList.peek();

            if (time + curWork.duration <= nextWork.start) {
                time += curWork.duration;
                answer[idx++] = curWork.title;

                if (!stack.isEmpty()) {
                    curWork = stack.pop();
                } else {
                    curWork = workList.poll();
                    time = curWork.start;
                }
            } else {
                curWork.duration -= (nextWork.start - time);
                stack.push(curWork);
                curWork = workList.poll();
                time = curWork.start;
            }
        }

        answer[idx++] = curWork.title;

        while (!stack.isEmpty()) {
            curWork = stack.pop();
            answer[idx++] = curWork.title;
        }

        return answer;
    }
}