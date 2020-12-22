package reflect;

import algorithm.stream.User;
import com.google.common.base.CaseFormat;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaolei
 * @description 通过反射获取类的属性/方法
 *              通过guava的Format方法格式化
 * @date 2020/11/30 9:53
 */
public class ReflectTest {

    @Test
    public void getInstaceByReflect() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<Map<String, Object>> userList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name", "Mark");
            userMap.put("age", i + 10);
            userList.add(userMap);
        }

        Class clazz = User.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Map map : userList) {
            Object user = clazz.newInstance();
            for (Field field : fields) {
                // 构造set属性的方法
//                System.out.println("====================before format:" + field.getName());
                String formatFieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field.getName());
//                System.out.println("====================after format:" + formatFieldName);
                String methodName = "set" + formatFieldName;
                // 设置当前字段值
                if (map.get(field.getName()) != null) {
                    // 反射调用set方法
                    clazz.getDeclaredMethod(methodName, field.getType()).invoke(user, map.get(field.getName()));
                }
            }
            System.out.println(clazz.getDeclaredMethod("toString").invoke(user));
        }
    }

    @Test
    public void simpleReflectTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> clazz = Class.forName("algorithm.stream.User");
        User user = (User)clazz.newInstance();
        /**
         * 获取指定方法
         */
        Method method = clazz.getDeclaredMethod("setAge", int.class);
        method.invoke(user,2);
        System.out.println(user.toString());
        /**
         * 获取指定属性并设置值
         */
        Field field = clazz.getDeclaredField("name");
        // 为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(user, "gaolei");
        System.out.println(user.toString());
    }
}
