
public class SecondMinimum {
    public static int tournamentMin(int[] arr) {

        return -1;
    }

    /**
     * Compare adjacent elements and find the minimum and
     * add it to the resultant array.
     */

    public static int[] subArrayOfAdjacentMins(int[] arr) {
        int[] newArr = new int[arr.length / 2 + 1];


        int i = 0, j = 1;
        while (i < arr.length - 1 && j < arr.length) {
            if (arr[i] < arr[j]) {

            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 5, 3, 1, 8, 7, 10};
        int[] aOdd = {2, 4, 5, 3, 1, 8, 7, 10, -19};
    }
}
