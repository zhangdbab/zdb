package demo.算法.查找;

public class 二分法查找 {
    public static void main(String[] args) {
        int[] arr = {5, 12, 23, 43, 66, 98, 100};
        System.out.println(binarySearch(arr, 98));
    }

    /**
     * 循环实现二分查找
     *
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch(int[] arr, int key) {
        //第一个下标
        int low = 0;
        //最后一个下标
        int high = arr.length - 1;
        int mid = 0;
        //防越界
        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }
        while (low <= high) {
            mid = (low + high) >>> 1;//右移除以2
            if (key < arr[mid]) { //要查找的在low 和 mid中间
                high = mid - 1;
            } else if (key > arr[mid]) { //要查找的在mid 和 high中间
                low = mid + 1;
            } else {
                return mid; //相等情况
            }
        }
        return -1;
    }
}
