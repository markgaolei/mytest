package newjavafeature;

import org.junit.Test;

/**
 * @author gaolei
 * @description java8在接口中使用default方法，方法可以直接写实现，实现的子类可以直接使用
 * @date 2021/2/4 9:49
 */
public class JavaDefaultMethodTest {
    interface UserInterface {
        /**
         * 获取花费金额
         */
        void getCost();

        /**
         * 计算公式
         *
         * @param a
         * @return
         */
        default Integer costMethod(int a) {
            return a * 2;
        }
    }

    @Test
    public void test1() {
        UserInterface userInterface = new UserInterface() {
            @Override
            public void getCost() {
                System.out.println(costMethod(8));
            }
        };

        // 调用UserInterface抽象方法
        userInterface.getCost();
        // 调用UserInterface的default方法
        System.out.println(userInterface.costMethod(2).toString());
    }
}
