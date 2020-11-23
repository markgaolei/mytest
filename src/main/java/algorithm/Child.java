package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author gaolei
 * @date 2020/11/23 9:53
 */
public class Child extends Person {
    public static List list = new ArrayList();
    public static Object obj = new Object();
    public String grade;

    public static void main(String[] args) {
        //父类变量不能声明为private否则p.name获取不到
        Person p = new Child();
        System.out.println(p.name);

        for (int i = 0; i < 10; i++) {
            list.add(0, obj);
        }
        System.out.println(list.size());
    }
}

class Person {
    protected String name = "Person";
    int age = 0;
    private String NULL = "";
}
