import edu.princeton.cs.algs4.SET;


public class MyUnionFind {

    int[] arr;
    MyUnionFind(int N) {
        arr = new int[N];
        for (int i =0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    void union(int p, int q) {
        int root_q = find(q);
        int root_p = find(p);
        arr[root_p] = arr[root_q];
    }

    int find(int p) {
        while(arr[p] != p) {
            p = arr[p];
        }
        return p;
    }

    int count() {
        SET<Integer> roots = new SET<Integer>();
        for (int i = 0;i < arr.length; i++) {
            int root = find(i);
            if (!roots.contains(root)) {
                roots.add(root);
            }
        }
        return roots.size();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("|");
        for(int i =0; i<arr.length; i++)
            sb.append(" " + i + " | " );
        sb.append("\n" + "|");
        for(int i =0; i<arr.length; i++)
            sb.append("------");
        sb.append("\n" + "|");
        for(int i =0; i<arr.length; i++)
            sb.append(" " + arr[i] + " | " );

        return sb.append("\n").toString();
    }
}
