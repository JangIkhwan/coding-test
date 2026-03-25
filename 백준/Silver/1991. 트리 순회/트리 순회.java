import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Node[] nodes = new Node[27];
    private static Node root;
    public static void main(String[] args) throws IOException {
        init();
        root.preorder();
        System.out.println();
        root.inorder();
        System.out.println();
        root.postorder();
        System.out.println();
    }

    private static void init() throws IOException {
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            char parentValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);
            if(nodes[parentValue - 'A'] == null){
                Node parent = new Node(parentValue);
                nodes[parentValue - 'A'] = parent;
                if(parentValue == 'A'){
                    root = parent;
                }
            }
            if(leftValue != '.' && nodes[parentValue - 'A'].left == null) {
                Node left = new Node(leftValue);
                nodes[leftValue - 'A'] = nodes[parentValue - 'A'].left = left;
            }
            if(rightValue != '.' && nodes[parentValue - 'A'].right == null) {
                Node right = new Node(rightValue);
                nodes[rightValue - 'A'] = nodes[parentValue - 'A'].right = right;
            }
        }
    }

    static class Node{
        Character value;
        Node left;
        Node right;

        public Node(Character value){
            this.value = value;
            this.left = null;
            this.right = null;
        }

        private void preorder(){
            System.out.print(this.value);
            if(left != null) left.preorder();
            if(right != null) right.preorder();
        }

        private void inorder(){
            if(left != null) left.inorder();
            System.out.print(this.value);
            if(right != null) right.inorder();
        }

        private void postorder() {
            if (left != null) left.postorder();
            if (right != null) right.postorder();
            System.out.print(this.value);
        }
    }
}