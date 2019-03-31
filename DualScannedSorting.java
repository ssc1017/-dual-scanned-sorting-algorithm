import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DualScannedSorting {

    public static final int INIT_N = 13;

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // shuffle all elements of an array
    private static void fisherYatesShuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int rand = (new Random()).nextInt(i + 1);
            int temp = array[i];
			array[i] = array[rand];
			array[rand] = temp;
		}
    }

    // merge left and right part of an array
    private void merge(int[] array) {
        int len = array.length;
        int[] temp = new int[len];
        int mid = len / 2;
        int left = 0;
        int right = len / 2;
        int index = 0;
        while (left < mid && right < len) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else temp[index++] = array[right++];
        }
        while (left < mid) {
            temp[index++] = array[left++];
        }
        while (right < len) {
            temp[index++] = array[right++];
        }
        for (int i = 0; i < len; i++) {
            array[i] = temp[i];
        }
    }

    // dual scanned sorting
    public void sort(int[] array) {
        System.out.println("before sorting:");
        show(array);
        if (array == null || array.length <= 0) throw new IllegalArgumentException("input is invalid.");
        int n = array.length;
        for (int i = 0, j = n - 1; i <= n / 2 || j >= n / 2 ; i++, j--) {
            if (array[i] > array[j]) swap(array, i, j);
            if (i != 0) {
                for (int k = i - 1, l = j + 1; k >= 0 || l <= n - 1 ; k--, l++) {
                    if (array[k] < array[k + 1] && array[l] > array[l - 1]) break;
                    if (array[k] > array[k + 1]) swap(array, k, k + 1);
                    if (array[l] < array[l - 1]) swap(array, l, l - 1);
                }
            }
            if (i == j && i != 0) {
                int k = i - 1;
                int l = j + 1;
                if (array[k] > array[k + 1] || array[l] < array[l - 1]) {
                    if (i != 0) {
                        for (k = i - 1, l = j + 1; k >= 0 || l <= n - 1 ; k--, l++) {
                            if (array[k] < array[k + 1] && array[l] > array[l - 1]) break;
                            if (array[k] > array[k + 1]) swap(array, k, k + 1);
                            if (array[l] < array[l - 1]) swap(array, l, l - 1);
                        }
                    }
                }
            }
        }
        System.out.println("after sorting:");
        show(array);
        merge(array);
        System.out.println("after merging:");
        show(array);
    }

    // generate an array
    public static int[] initializeArray(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be > 0");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        fisherYatesShuffle(array);
        return array;
    }

    // show arrays
    public static void show(int[] array) {
        for (int i : array) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DualScannedSorting dualScannedSorting = new DualScannedSorting();
        int[] array = initializeArray(INIT_N);
        dualScannedSorting.sort(array);
    }

}