import java.io.*;
import java.util.*;

public class Main {
    static int p, m;
    static ArrayList<Room> rooms = new ArrayList<>();

    static class Player {
        int level;
        String name;

        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }

    static class Room {
        int min, max;
        ArrayList<Player> players = new ArrayList<>();

        Room(int level) {
            this.min = level - 10;
            this.max = level + 10;
        }

        boolean canEnter(Player p) {
            return min <= p.level && p.level <= max && players.size() < m;
        }

        void enter(Player p) {
            players.add(p);
        }

        boolean isFull() {
            return players.size() == m;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            Player player = new Player(level, name);
            boolean entered = false;

            for (Room room : rooms) {
                if (room.canEnter(player)) {
                    room.enter(player);
                    entered = true;
                    break;
                }
            }

            if (!entered) {
                Room newRoom = new Room(level);
                newRoom.enter(player);
                rooms.add(newRoom);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Room room : rooms) {
            if (room.isFull()) sb.append("Started!\n");
            else sb.append("Waiting!\n");

            room.players.sort(Comparator.comparing(p -> p.name));

            for (Player p : room.players) {
                sb.append(p.level).append(" ").append(p.name).append("\n");
            }
        }

        System.out.print(sb);
    }
}
