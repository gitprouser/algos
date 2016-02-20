import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
/**
 *
 *            5
 *        3  ->   8
 *      2  1 <- 9   10
 *          4 ->
 */

    static class Node {
        int data;
        Node l, r;

        Node (int data) {
            this.data = data;
        }
    }

    private static void bfsTraversal(Node root) {
        Queue q = new ArrayDeque();
        __bfs(root, q, false);
    }

    /**
     *
     *            5
     *        3  ->   8
     *      2  1 <- 9   10
     *          4 ->
     */

    private static Node __bfs(Node root, Queue q, boolean isRightToLeft) {
        if (root == null) {
            return null;
        } if (isRightToLeft) {
            if (root.r != null)     q.add(root.r);
            if (root.l != null)     q.add(root.l);
        } else {
            if (root.l != null)     q.add(root.l);
            if (root.r != null)     q.add(root.r);
        }
        System.out.print(root.data + " ");
        Node tmp = null;
        if (!q.isEmpty())
            tmp = (Node)q.remove();
        return __bfs(tmp, q, !isRightToLeft);
    }

    public static void main(String s[]) {
        Node five = new Node(5);
        Node three = new Node(3);
        Node eight = new Node(8);
        Node two = new Node(2);
        Node one = new Node(1);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node four = new Node(4);
/**
 *
 *            5
 *        3  ->   8
 *      2  1 <- 9   10
 *          4 ->
 */
        five.l = three; five.r = eight;
        three.l = two;  three.r = one;
        eight.l = nine; eight.r = ten;
        nine.l = four;

        //        bfsTraversal(five);
        int i  = height(five);
        System.out.println(i);
    }

    private static int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            int lheight = height(root.l);
            int rheight = height(root.r);

            if (lheight >= rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

//    private static int heightStack(Node root) {
//        if (root == null)
//            return 0;
//        Stack s = new Stack();
//        s.push(root);
//        while (!s.isEmpty()) {
//
//        }
//    }
}
