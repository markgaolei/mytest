package basic;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author gaolei 泛型使用
 * @description
 * @date 2020/12/18 9:10
 */
public class GenericTest {

    /**
     * 泛型类型在编译时会被擦除
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void genericTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList();
        list.add(1);
        // 泛型在编译时会被擦除，在class文件中只有原始类型
        Class<?> clazz = list.getClass();
        Method addMethod = clazz.getDeclaredMethod("add", Object.class);
        addMethod.invoke(list, "test");
        Iterator listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }

    /**
     * 泛型类声明
     * @param <T>
     */
    class Generic<T> {
        private T key;

        public Generic(T key) {
            this.key = key;
        }

        public T getKey() {
            return key;
        }
    }

    /**
     * 泛型方法声明
     * @param x
     * @param y
     * @param <T>
     * @return
     */
    public <T>T add (T x, T y) {
        return y;
    }

    /**
     * 测试i++
     * 说明：i++之后i的值已经自增并
     * 不是等到下次再用i的时候i的值才变
     */
    @Test
    public void testAdd() {
        int i = 0;
        System.out.println("==================i++之前i的值=" + i);
        i++;
        System.out.println("==================i++之后i的值=" + i);
        int b = 0;
        System.out.println("==================i+b的值=" + (i+b));
    }

    /**
     * 测试HashMap的通过转为Set进行遍历方式
     */
    @Test
    public void entrySetTest() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("test", 124);
        map.put("test1", 125);
        Set<Map.Entry<String, Integer>> sets = map.entrySet();
        Iterator<Map.Entry<String,Integer>> it = sets.iterator();
        while (it.hasNext()) {
            // 不能在循环中使用超过一次it.next()
            String key = it.next().getKey();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }
}
