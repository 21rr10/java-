import java.util.*;

public class LinearSearch {
    public static int findIt(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 4, 2};
        System.out.println(findIt(nums, 8));
    }
}