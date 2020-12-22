package basic;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gaolei
 * @description
 * @date 2020/12/19 11:45
 */
public class BasicTest {

    /**
     * Objects.equals(null, null)返回true
     */
    @Test
    public void objectsUtilTest() {
        System.out.println("=================两个null比较： " + Objects.equals(null, null)); // true
        System.out.println("=================空字符串： " + Objects.equals(null, ""));
    }

    /**
     * 错误示范：当整形包装类不在[-128, 127]之间时，用==判断相同的数字会返回false
     */
    @Test
    public void integerNumberTest() {
        Integer a = 333;
        Integer b = 333;
        System.out.println("不在[-128, 127]范围内整形包装类比较a==b： " + (a == b)); // false 这种方式会导致程序数据不正常
        System.out.println("不在[-128, 127]范围内整形包装类比较a.equals(b)： " + a.equals(b)); // true 一定要这样使用这种方式判断
    }

    /**
     * 《阿里巴巴Java开发手册》中提到：浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断
     */
    @Test
    public void floatNumberTest() {
        System.out.println("=========================错误示范 begin============================");
        // 错误示范
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        Float c = 1.0F - 0.9F;
        Float d = 0.9F - 0.8F;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println("浮点型基本类型用a==b比较: " + (a==b)); // false
        System.out.println("浮点型包装类型用c.equalse(d)比较:" + c.equals(d)); // false

        // 错误示范使用new BigDecimal (double)
        BigDecimal a1 = new BigDecimal(1.0);
        BigDecimal b1 = new BigDecimal(0.9);
        BigDecimal c1 = new BigDecimal(0.8);
        BigDecimal d1 = a1.subtract(b1);
        BigDecimal e1 = b1.subtract(c1);
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(d1);
        System.out.println(e1);
        System.out.println("BigDecimal使用equals比较: " + d1.equals(e1)); // true
        System.out.println("=========================错误示范 end============================");

        System.out.println("=========================正确示范 begin============================");
        // 正确示范new BigDecimal(String)
        BigDecimal a2 = new BigDecimal("1.0");
        BigDecimal b2 = new BigDecimal("0.9");
        BigDecimal c2 = new BigDecimal("0.8");
        BigDecimal d2 = a2.subtract(b2);
        BigDecimal e2 = b2.subtract(c2);
        System.out.println(a2);
        System.out.println(b2);
        System.out.println(c2);
        System.out.println(d2);
        System.out.println(e2);
        System.out.println("BigDecimal使用equals比较: " + d2.equals(e2));

        // 比较大小
        System.out.println("BigDecimal比较大小a2.compareTo(b2)" + a2.compareTo(b2));

        // 通过 setScale方法设置保留几位小数以及保留规则
        BigDecimal m = new BigDecimal("1.23556");
        BigDecimal n = m.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("BigDecimal设置精度以及保留规则: " + n);
        System.out.println("=========================正确示范 end============================");
    }

    /**
     * 基本数据类型数组转为List时的坑
     * 1. 基本数据类型Arrays.asList()时，真正得到的参数不是数组中的元素而是述责对象本身
     * 2. 使用Arrays.asList()得到的List不能使用add/remove/clear
     */
    @Test
    public void asListTest() {
        // 当传入基本数据类型数组时Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身
        System.out.println("========================错误示范 begin========================");
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]);//1

        String[] myArray2 = {"Apple", "Orange", "Banana"};
        List myList2 = Arrays.asList(myArray2);
//        myList.add("tomato"); //报错：UnsupportedOperationException
//        myList.remove("tomato"); //报错：UnsupportedOperationException
//        myList.clear(); //报错：UnsupportedOperationException
        System.out.println("========================错误示范 end========================");

        System.out.println("========================解决以上两个问题 begin========================");
        // 问题1：基本类型使用ArrayList获取不到原来数组元素
        // 解决：使用包装类代替基本类型 Integer[] myArray = {1, 2, 3};代替int[] myArray = {1, 2, 3};
        Integer[] myArray3 = {1, 2, 3};
        System.out.println(myArray3.length);

        // 问题2:Array.asList()得到的List是java.util.Arrays类的内部类ArrayList,这个类未实现AbstractList的add/remove/clear方法，所以无法使用
        // 解决1:实现工具方法将数组转换为List
        List<String> list2 = arrayToList(myArray2);
        list2.add("tomato");
        System.out.println(list2.get(3));
        // 解决2:使用JDK8 Stream操作
        List list3 = Arrays.stream(myArray).boxed().collect(Collectors.toList());
        list3.add("tomato");
        System.out.println(list3.get(3));
        List list4 = Arrays.stream(myArray2).collect(Collectors.toList());
        list4.add("tomato");
        System.out.println(list4.get(3));
        // 解决3:直接用数组创建List
        List myList3 = new ArrayList<>(Arrays.asList("Apple", "Orange", "Banana"));
        myList3.add("tomato");
        // 解决4:使用 Apache Commons Collections
        List<String> list = new ArrayList<String>();
//        CollectionUtils.addAll(list, str);
        System.out.println("========================解决以上两个问题 end========================");
    }

    private <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>(array.length);
        for (int i = 0, length = array.length; i < length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Collections.toArray() 如果toArray方法没有传递任何参数，返回的是Object数组
     * 应传入对应类型数组，设置数组大小为0 节省空间
     */
    @Test
    public void toArray() {
        List list = new ArrayList(Arrays.asList("Apple", "Orange", "Banana"));
        Collections.reverse(list);
        // 必须传入一个
        String[] myArray = (String[]) list.toArray(new String[0]);
        for (String s: myArray) {
            System.out.println(s);
        }
    }
}
