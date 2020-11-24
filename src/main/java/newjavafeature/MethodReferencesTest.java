package newjavafeature;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author gaolei
 * @description 方法引用分为三种，方法引用通过一对双冒号:: 来表示，方法引用是一种函数式接口的另一种书写方式
 *              静态方法引用，通过类名::静态方法名， 如 Integer::parseInt
 *              实例方法引用，通过实例对象::实例方法，如 str::substring
 *              构造方法引用，通过类名::new， 如 User::new
 * @date 2020/11/24 13:33
 */
public class MethodReferencesTest {
    class User {
        private String username;
        private Integer age;

        public User() {
        }

        public User(String username, Integer age) {
            this.username = username;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }

        // Getter&Setter
    }

    @Test
    public void testMethodReference() {
        // 使用双冒号::来构造静态函数引用
        Function<String, Integer> fun = Integer::parseInt;
        Integer value = fun.apply("123");
        System.out.println(value);

        // 使用双冒号::来构造非静态函数引用
        String content = "Hello JDK8";
        Function<Integer, String> func = content::substring;
        String result = func.apply(1);
        System.out.println(result);

        // 构造函数引用
        BiFunction<String, Integer, User> biFunction = User::new;
        User user = biFunction.apply("mengday", 28);
        System.out.println(user.toString());

        // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
        sayHello(String::toUpperCase, "hello");
    }

    /**
     * 方法有两个参数，一个是函数
     */
    private void sayHello(Function<String, String> func, String parameter){
        String result = func.apply(parameter);
        System.out.println(result);
    }
}
