import java.util.*;

class Solution {
    /*
    25/1/5 9:23 ~ 10:00 실패 
    17:35 ~ 
    
    과제를 마치는 순서를 배열로 구하자
    
    과제 시작 시간은 분으로 바꾸고 정렬해 배열에 저장
    배열에서 차례로 꺼낸다
    중간에 멈추는 작업은 스택에 저장
    새로 할일이 없으면 멈춘 작업 수행
    */
    private List<Task> tasks = new ArrayList<>();
    private List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] plans) {
        // 작업 시작 시간을 기준으로 정렬
        tasks = convertToTask(plans);
        tasks.sort((a, b) -> a.startTime - b.startTime);
        for(Task task : tasks){
            System.out.println(task.startTime);
        }
        
        // run();
        schedule();
           
        return answer.toArray(new String[0]);
    }
    
    private List<Task> convertToTask(String[][] plans){
        for(String[] plan : plans){
            tasks.add(new Task(plan[0], convertToMinute(plan[1]), Integer.parseInt(plan[2])));
        }
        return tasks;
    }
    
    private int convertToMinute(String time){
        String[] tokens = time.split(":");
        int hh = Integer.parseInt(tokens[0]);
        int mm = Integer.parseInt(tokens[1]);
        return hh * 60 + mm;
    }
    
    private void schedule(){
        // 큐에 모든 작업을 넣는다
        Queue<Task> qu = new LinkedList<>();
        Stack<Task> st = new Stack<>();
        for(Task t : tasks){
            qu.add(t);
        }
        
        Task cur = qu.poll();
        while(!qu.isEmpty()){
            Task next = qu.peek();
            int runningTime = next.startTime - cur.startTime;
            if(runningTime < cur.amount){
                cur.amount -= runningTime;
                st.push(cur);
                cur = qu.poll();
                continue;
            }
            if(runningTime == cur.amount){
                answer.add(cur.name);
                cur = qu.poll();
                continue;
            }
            if(runningTime > cur.amount){
                answer.add(cur.name);
                if(!st.isEmpty()){
                    int baseTime = cur.startTime;
                    int amount = cur.amount;
                    cur = st.pop();
                    cur.startTime = amount + baseTime;
                }
                else{
                    cur = qu.poll();
                }
                continue;
            }
        }
        answer.add(cur.name);
        while(!st.isEmpty()){
            answer.add(st.pop().name);
        }
        // 큐에서 작업을 차례로 뽑는다
            // 다음 작업 전까지 현재 작업을 진행한다
            // 다음 작업 시작 전까지 다 못끝냈으면 
                // 스택에 저장
                // 다음 작업 실행
            // 다 끝냈으면 
                // 정답에 기록하고 다음 작업을 꺼내서 다시 반복
            // 시간이 남았으면 
                //스택에서 작업을 가져오고 다시 반복
        // 현재 작업이 안끝났으면 저장
        // 스택에 남아 있는 작업을 저장
    }
    
    private void schedule0(){
        Queue<Task> qu = new LinkedList<>();
        Stack<Task> st = new Stack();
        for(Task task : tasks){
            qu.add(task);
        }
        
        Task cur = qu.poll();
        int i = cur.startTime;
        while(!st.isEmpty() || !qu.isEmpty()){
            if(!qu.isEmpty() && i == qu.peek().startTime){
                if(cur != null){
                    if(cur.amount > 0){
                        st.push(cur);
                    }
                    else if (cur.amount == 0){
                        answer.add(cur.name);
                    }
                }
                cur = qu.poll();
            }
            
            i++;
            
            if(cur != null){
                if(cur.amount == 0){
                    answer.add(cur.name);
                    if(!st.isEmpty()){
                        cur = st.pop();
                    }
                    else{
                        cur = null;
                        continue;
                    }
                }
                cur.amount -= 1; 
            }
        }
        
        while(!st.isEmpty()){
            Task t = st.pop();
            answer.add(t.name);
        }
    }
    
    private void run(){
        // 배열에서 첫 작업을 꺼낸다
        Stack<Task> st = new Stack();
        int i = 0;
        Task cur = tasks.get(i);
        // 스택에 작업 남음 그리고 배열에 작업이 남음 동안 
        while(i < tasks.size()){
            // 새로 시작할 작업을 꺼내서 남은 시간 계산
            int curTime = cur.startTime;
            if(i + 1 < tasks.size()){
                Task next = tasks.get(i + 1);
                int term = next.startTime - curTime;
                
                // 작업이 남았으면 스택에 저장
                if(term < cur.amount){
                    cur.amount -= term;
                    st.push(cur);
                    cur = tasks.get(i + 1);
                    i++;
                    continue;
                }
                // 작업이 끝났으면 정답 배열에 저장
                if(term == cur.amount){
                    answer.add(cur.name);
                    cur = tasks.get(i + 1);
                    i++;
                    continue;
                }
                // 만약 현재 작업이 끝내도 시간이 남으면 스택의 탑에 있는 남은 시간을 차감
                answer.add(cur.name);
                if(!st.isEmpty()){
                    cur = st.peek();
                    cur.startTime = curTime + term;
                    continue;
                }
            }
            else{
                answer.add(cur.name);
                while(!st.isEmpty()){
                    answer.add(st.peek().name);
                }
            }
        }
    }
    
    static class Task{
        String name;
        int startTime;
        int amount;
        
        public Task(String name, int startTime, int amount){
            this.name = name;
            this.startTime = startTime;
            this.amount = amount;
        }
    }
}