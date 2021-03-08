package demo.算法.排序;

import java.util.Arrays;

/**
 *
 * 外层循环每次都把最小的数排到第一位
 *
 */

public class 选择排序 {
    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        selectionSort(array);

        System.out.println(Arrays.toString(array));
    }


    public static void selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            // 保存最小数的索引
            int minIndex = i;

            for (int j = i + 1; j < length; j++) {
                // 找到最小的数
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换元素位置
            if (i != minIndex) {
                swap(array, minIndex, i);
            }
        }

    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
