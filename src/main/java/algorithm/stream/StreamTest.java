package algorithm.stream;
import java.util.*;

/**
 * @description
 * @author gaolei
 * @date 2020/11/23 9:57
 */
public class StreamTest {
    public static void main(String[] args) {
        List<User> list = Arrays.asList(
                // name，age
                new User("张三", 11),
                new User("王五", 20),
                new User("王五", 91),
                new User("张三", 8),
                new User("李四", 44),
                new User("李四", 44),
                new User("李四", 44)
        );
        testStreamLoop(list);
        testFilter(list);
        testLimit(list);
        testDistinct(list);
    }

    public static void testDistinct(List<User> list) {
        //list去重
        List<User> users = new ArrayList(list);
        System.out.println("------去重前--------");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("------去重后------");
        System.out.println("------JDK1.8前去重后------");
        for (int i = 0; i < users.size() - 1; i++) {
            for (int j = users.size() - 1; j > i; j--) {
                if (users.get(j).getAge() == users.get(i).getAge() && users.get(j).getName()
                        .equals(list.get(i).getName())) {
                    users.remove(i);
                }
            }
        }
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("------JDK1.8 Stream------");
        list.stream().distinct().forEach(user -> System.out.println(user));
    }

    public static void testLimit(List<User> list) {
        //输出前三个数据
        System.out.println("------截断前--------");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("------截断后------");
        for (int i = 0; i < 3; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("------Stream--------");
        list.stream().limit(3).forEach(user -> System.out.println(user));
    }

    public static void testFilter(List<User> list) {
        //输出年龄大于50的人
        System.out.println("------过滤前--------");
        list.stream().forEach(user -> System.out.println(user));
        System.out.println("------JDK1.8前------");
        for (User user : list) {
            if (user.getAge() > 50) System.out.println(user);
        }
        System.out.println("------Stream--------");
        list.stream().filter((User user) -> user.getAge() > 50).forEach(user -> System.out.println(user));
    }

    public static void testStreamLoop(List<User> list) {
        //Jdk1.8之前
        System.out.println("------JDK1.8前--------");
        for (User user : list) {
            System.out.println(user);
        }
        //Lamda表达式
        System.out.println("------JDK1.8Lamda表达式--------");
        list.forEach(user -> System.out.println(user));
        //Stream流操作
        System.out.println("------JDK1.8Stream流操作--------");
        list.stream().forEach(user -> System.out.println(user));
    }
}