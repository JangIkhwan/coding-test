import java.util.*;

class Solution {
    /*
    19:44 ~ 
    
    하노이의탑은 대표적인 재귀함수 문제
    */
    public int[][] solution(int n) {
        
        List<Node> ret = tower(1, 3, 2, n);
        
        int[][] answer = new int[ret.size()][2];
        for(int i = 0; i < ret.size(); i++){
            answer[i][0] = ret.get(i).s;
            answer[i][1] = ret.get(i).e;
        }
        return answer;
    }
    
    private List<Node> tower(int from, int to, int mid, int disks){
        if(disks <= 1){
            List<Node> ret = new ArrayList<Node>();
            ret.add(new Node(from, to));
            return ret;
        }
        List<Node> ret = tower(from, mid, to, disks - 1);
        ret.add(new Node(from, to));
        ret.addAll(tower(mid, to, from, disks - 1));
        return ret;
    }
    
    static class Node{
        int s;
        int e;
        
        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
}