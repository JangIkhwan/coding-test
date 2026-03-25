import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException{
        final int INF = 1123456789;
        int N;
        Course[] courses;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        courses = new Course[N];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            courses[i] = new Course(s, e);
        }

        // 강의를 시작 시간에 대해 정렬
        Arrays.sort(courses, (a, b) -> a.start - b.start);

        // 강의를 가설에 맞게 강의실에 할당
        PriorityQueue<Course> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        for(Course course : courses){
            if(pq.isEmpty()){
                pq.offer(course);
                continue;
            }
            Course poll = pq.poll();
            if(poll.end <= course.start){
                pq.offer(course);
            }
            else{
                pq.offer(poll);
                pq.offer(course);
            }
        }

        // 할당된 강의실을 출력
        System.out.println(pq.size());
    }

    static class Course{
        int start;
        int end;

        public Course(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}