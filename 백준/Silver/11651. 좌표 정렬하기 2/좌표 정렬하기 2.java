import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    /*
    25/07/02 12:24 ~

     */

    public static void main(String[] args) throws IOException{
        int N;
        Point[] points;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String line = br.readLine();
        N = Integer.parseInt(line);
        points = new Point[N];
        for(int i = 0; i < N; i++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            points[i] = new Point();
            points[i].x = Integer.parseInt(st.nextToken());
            points[i].y = Integer.parseInt(st.nextToken());
        }

        // 배열을 정렬
        Arrays.sort(points);

        // 정렬된 배열을 출력
        for(int i = 0; i < N; i++){
            System.out.println(points[i].x + " " + points[i].y);
        }
    }

    static class Point implements Comparable<Point> { // 왜 static이어야 하나? Comparable의 사용 방법 다시 정리
        int x;
        int y;

        @Override
        public int compareTo(Point o) {
            int diff = this.y - o.y;
            if(diff == 0){
                return this.x - o.x;
            }
            return diff;
        }
    }
}