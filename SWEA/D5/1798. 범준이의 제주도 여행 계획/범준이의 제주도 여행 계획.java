import java.io.*;
import java.util.*;

public class Solution {
    /*
    * 26-7-28 20:10 ~
    *
    * 만족도가 최대가 될 때, 만족도와 경로를 구하자
    * */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int airport;
    static Set<Integer> hotels;
    static Set<Integer> sites;
    static List<List<Node>> graph;
    static int[] airportDists;
    static int[] siteStayTimes;
    static int[] siteScores;

    static boolean[] visited;
    static int maxScore;
    static List<Integer> course;
    static List<Integer> maxCourse;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int test = 1; test <= T; test++){
            init();
            solve();
            System.out.println("#" + test + " " + getScore() + " " + getCourse());
        }
    }

    static void init() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= N - 1; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j = i + 1; j <= N; j++){
                int time = Integer.parseInt(st.nextToken());
                graph.get(i).add(new Node(j, time));
                graph.get(j).add(new Node(i, time));
            }
        }

        hotels = new HashSet<>();
        sites = new HashSet<>();
        siteStayTimes = new int[N + 1];
        siteScores = new int[N + 1];

        for(int i = 1; i <= N; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            String command = st.nextToken();
            if("A".equals(command)){
                airport = i;
                continue;
            }
            if("H".equals(command)){
                hotels.add(i);
                continue;
            }
            if("P".equals(command)){
                sites.add(i);
                siteStayTimes[i] = Integer.parseInt(st.nextToken());
                siteScores[i] = Integer.parseInt(st.nextToken());
            }
        }

        airportDists = new int[N + 1];
        for(Node n : graph.get(airport)){
            airportDists[n.to] = n.moveMinute;
        }

        visited = new boolean[N + 1];

        course = new ArrayList<>();

        maxScore = 0;
        maxCourse = new ArrayList<>();
    }

    static void solve(){
        travel(airport, 1, 9 * 60, 0);
    }

    static void travel(int cur, int day, int remainMinute, int score) {
        if (day > M) {
            if(course.get(course.size() - 1) != airport){
                return;
            }
            if (score > maxScore) {
                maxScore = score;
                maxCourse = new ArrayList<>(course);
            }
            return;
        }

        if(remainMinute <= 0){
            return;
        }

        for(Node next : graph.get(cur)){
            if(sites.contains(next.to)){
                if(visited[next.to]) {
                    continue;
                }
                if(remainMinute < next.moveMinute + siteStayTimes[next.to]){
                    continue;
                }
                visited[next.to] = true;
                course.add(next.to);
                travel(next.to, day, remainMinute - next.moveMinute - siteStayTimes[next.to], score + siteScores[next.to]);
                course.remove(course.size() - 1);
                visited[next.to] = false;
            }
        }

        if(day == M){
            if(remainMinute < airportDists[cur]){
                return;
            }
            course.add(airport);
            travel(airport, day + 1, 9 * 60, score);
            course.remove(course.size() - 1);
            return;
        }

        for(Node next : graph.get(cur)) {
            if(hotels.contains(next.to)) {
                if (remainMinute < next.moveMinute) {
                    continue;
                }
                course.add(next.to);
                travel(next.to, day + 1, 9 * 60, score);
                course.remove(course.size() - 1);
            }
        }
    }

    static int getScore(){
        return maxScore;
    }

    static String getCourse(){
        StringBuilder sb = new StringBuilder();
        for(int c : maxCourse){
            sb.append(c + " ");
        }
        return sb.toString();
    }

    static class Node{
        int to;
        int moveMinute;

        public Node(int to, int moveMinute){
            this.to = to;
            this.moveMinute = moveMinute;
        }
    }
}