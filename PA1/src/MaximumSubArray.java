import java.util.Arrays;

public class MaximumSubArray {

    public static void main(String[] args) {
        int[] a = {-7, -3, -2, -5, -8};
        prettyPrint(maxSubArray(a));
    }

    private static void prettyPrint(int a) {
        System.out.print(a);
    }

    private static void prettyPrint(int[] a) {

        for (int i : a)
            System.out.print(i + " ");
    }


    private static int[] maxSubArray(int[] a) {
        int sum = Integer.MIN_VALUE, currSum = a[0];
        int j = 0, k = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > currSum)
                j = i;

            currSum = max(a[i], currSum);
            sum = max(sum, currSum);
            k = i;
        }
//        return sum;
        System.out.println(" j=" + j + " k=" + k);
        return Arrays.copyOfRange(a, j, k + 1);
    }

    private static int max(int i, int j) {
        return (i <= j) ? j : i;
    }
}
