/**
 * Created on 9/6/15.
 */
public class MyUnionFindTest {
    public static void main(String[] args) {
        testNumbers();
        testQuiz();
    }

    static void testQuiz() {
        MyUnionFind unionFind = new MyUnionFind(10);
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(5, 6);
        unionFind.union(7, 8);
        unionFind.union(7, 9);
        unionFind.union(2, 8);
        unionFind.union(0, 5);
        unionFind.union(1, 9);
        System.out.println(unionFind);
        System.out.println("Number of Connected Groups: " + unionFind.count());
    }

    static void testNumbers() {
        MyUnionFind unionFind = new MyUnionFind(10);
        unionFind.union(4, 3);
        unionFind.union(3, 8);
        unionFind.union(6, 5);
        unionFind.union(9, 4);
        unionFind.union(2, 1);
        unionFind.union(8, 9);
        unionFind.union(5, 0);
        unionFind.union(7, 2);
        unionFind.union(6, 1);
        unionFind.union(1, 0);
        unionFind.union(6, 7);
        System.out.println(unionFind.isConnected(1, 3));
        System.out.println(unionFind);
        System.out.println("Number of Connected Groups: " + unionFind.count());
    }
}
