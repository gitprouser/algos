
public class DPCoin {
    public static void main(String[] args) {
        int[] v = {1, 2, 3};
        System.out.println(total(5, v, 0));
    }

    public static int total(int amt, int[] v, int ways) {
        if (amt < 0) {
            return 0;
        }
        if (amt == 0) {
            return 1;
        }
        // means coins over and amt>0 so no solution
        if (ways == v.length && amt > 0) {
            return 0;
        }
        return total(amt - v[ways], v, ways) + total(amt, v, ways + 1);
    }
}
