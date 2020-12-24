package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gaolei
 * @description 寻找两个正序数组的中位数
 * @date 2020/12/23 21:35
 */
public class FindMedianSortedArraysTest {

    // =============================方法一：推荐 begin============================
    private double method2(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        // 如果偶数个元素,则只取第length/2和length/2 - 1个元素的平均值
        if (totalLength % 2 == 0) {
            int midIndex1 = totalLength / 2, midIndex2 = totalLength / 2 - 1;
            double median = (getElement(nums1, nums2, midIndex1 + 1) + getElement(nums1, nums2, midIndex2 + 1))/2;
            return median;
        } else {
            int index = totalLength / 2;
            double median = getElement(nums1, nums2, index + 1);
            return median;
        }
    }

    private double getElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    @Test
    public void runTest2() {
        int[] nums1 = {1, 3, 4, 9};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(method2(nums1, nums2));
    }
    // =============================方法一：推荐 end============================

    // =============================方法二：不推荐 begin============================
    @Test
    public void testRun() {
        int[] array1 = {3, 4};
        int[] array2 = {1, 2};
        System.out.println(method1(array1, array2));

    }

    private double method1(int[] nums1, int[] nums2) {
        double result = 0f;
        int temp = 0;
//        List<Integer> list = new ArrayList(Arrays.asList(nums1, nums2));
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        list.addAll(list2);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size() - 1; j++) {
                if (list.get(i) > list.get(j + 1)) {
                    temp = list.get(j + 1);
                    list.set(j + 1, list.get(i));
                    list.set(i, temp);
                }
            }
        }
        if (list.size() % 2 == 0) {
            int index = list.size() / 2;
            result = (list.get(index) + list.get(index - 1)) / 2;
        } else
            result = list.get(list.size() / 2);
        return result;
    }

    @Test
    public void testFload() {
        int x = 2;
        int y = 9;
        // float声明为没有小数点
        float f1 = 0f;
        f1 = (x + y) / 2;
        System.out.println(f1);

        // float声明为没有小数点
        float f2 = 0.00000f;
        f2 = (x + y) / 2.00000f;
        System.out.println(f2);
    }
    // =============================方法二：不推荐 end============================
}
