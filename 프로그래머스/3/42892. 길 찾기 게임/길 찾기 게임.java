import java.util.*;

class Solution {
    /*
    26-7-21 8:37 ~ 
    
    좌표들이 나타내는 이진트리를 전위순회한 결과와 후위순회한 결과를 구하자
    
    ---
    
    좌표만 가지고 이진트리를 만들어야 한다
    
    루트는 Y값이 제일 크다!
    
    
    ---
    
    우선순위 큐에 y에 대해서 오름차순으로 정렬하여 노드를 저장
    노드를 하나씩 꺼내서 이진트리를 생성
    
    이후에 이진트리를 탐색
    
    ---
    
    이진트리는 어떻게 표현?
    노드 자료구조로 표현해야 효율적
    
    */
    private Node root;
    private List<Integer> preorderResult = new ArrayList<>();
    private List<Integer> postorderResult = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        // 이진트리를 구성
        makeBinaryTree(nodeinfo);
        
        // 전위 순회 결과 저장
        preorder(root);
        
        // 후위 순회 결과 저장
        postorder(root);
        
        for(int i = 0; i < preorderResult.size(); i++){
            answer[0][i] = preorderResult.get(i);
        }
        
        for(int i = 0; i < postorderResult.size(); i++){
            answer[1][i] = postorderResult.get(i);
        }
        
        return answer;
    }
    
    private void makeBinaryTree(int[][] nodeinfo){
        // y에 대해서 내림차순으로 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.y - a.y);
        
        for(int i = 0; i < nodeinfo.length; i++){
            pq.offer(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        
        root = pq.poll();
        while(!pq.isEmpty()){
            Node n = pq.poll();
            insert(root, n);
        }
    }
    
    private void insert(Node parent, Node inserted){
        if(parent.x > inserted.x){
            if(parent.left == null){
                parent.left = inserted;
                return;
            }
            insert(parent.left, inserted);
            return;
        }
      
        if(parent.right == null){
            parent.right = inserted;
            return;
        }
        
        insert(parent.right, inserted);
    }
       
    private void preorder(Node cur){
        if(cur == null){
            return;
        }
        preorderResult.add(cur.vertex);
        preorder(cur.left);
        preorder(cur.right);
    }
    
    private void postorder(Node cur){
        if(cur == null){
            return;
        }
        postorder(cur.left);
        postorder(cur.right);
        postorderResult.add(cur.vertex);
    }
    
    static class Node{
        int x;
        int y;
        int vertex;
        Node left;
        Node right;
    
        public Node(int x, int y, int vertex){
            this.x = x;
            this.y = y;
            this.vertex = vertex;
        }
    }
    
}