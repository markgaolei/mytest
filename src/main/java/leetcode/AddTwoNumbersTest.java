package leetcode;

import org.junit.Test;

import java.util.List;

/**
 * @author gaolei
 * @description 两数相加
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @date 2020/12/21 13:33
 */
public class AddTwoNumbersTest {

    /**
     * 给定两个链表,每个节点存储一位数，求这两个链表各节点相加结果
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        // 计算两个链表长度
        int m = 0;
        int n = 0;
        ListNode p = l1;
        ListNode q = l2;
        while (p.nextNode != null) {
            m++;
            p = p.nextNode;

        }
        while (q.nextNode != null) {
            n++;
            q = q.nextNode;
        }
        // 链表长度短的补0
        if (m > n) {
            for (int i = 0; i < m - n; i++) {
                q.nextNode = new ListNode(0);
                q = q.nextNode;
            }
        } else {
            for (int j = 0; j < n - m; j++) {
                p.nextNode = new ListNode(0);
                p = p.nextNode;
            }
        }

        // 将两个链表节点数据相加, flag表示有进位
        p = l1;
        q = l2;
        boolean flag = false; //表示进位
        ListNode midResult = l3;
        int count = 0;
        do {
            count = p.val + q.val;
            if (flag == true) {
                count++;
                midResult.val = count % 10;
            } else {
                midResult.val = count % 10;
            }
            midResult.nextNode = new ListNode();
            midResult = midResult.nextNode;
            flag =  count >= 10 ? true : false;
            p = p.nextNode;
            q = q.nextNode;
        } while (p != null && q != null); // 此处只需要判断p是否为null就行
        return l3;
    }

    @Test
    public void addTwoNumbersTest() {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        ListNode p = l1;
        ListNode q = l2;
        // 初始化两个链表
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    p.val = 2;
                    break;
                case 1:
                    p.val = 4;
                    break;
                case 2:
                    p.val = 3;
                    break;
                default:
                    break;
            }
            if (i < 2) {
                p.nextNode = new ListNode();
            }
            p = p.nextNode;
        }
        for (int j = 0; j < 3; j++) {
            switch (j){
                case 0:
                    q.val = 5;
                    break;
                case 1:
                    q.val = 6;
                    break;
                case 2:
                    q.val = 4;
                    break;
                default:
                    break;
            }
            if (j < 2) {
                q.nextNode = new ListNode();
            }
            q = q.nextNode;
        }

        ListNode result = addTwoNumbers(l1, l2);
        // 遍历结果
        ListNode x = result;
        do {
            System.out.println(x.val);
            x = x.nextNode;
        } while (x.nextNode != null);
    }

    class ListNode {
        private int val;
        ListNode nextNode;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode nextNode) {
            this.val = val;
            this.nextNode = nextNode;
        }
    }
}
