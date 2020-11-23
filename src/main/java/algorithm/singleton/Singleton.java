package algorithm.singleton;
/**
 * @description 双重校验锁懒汉模式
 * @author gaolei
 * @date 2020/11/23 9:58
 */
public class Singleton {
    private volatile static Singleton singletonInstance;
    private Singleton(){}

    public static Singleton getSingletonInstance() {
        if (singletonInstance == null){
            synchronized (Singleton.class){
                if (singletonInstance == null){
                    singletonInstance = new Singleton();
                }
            }
        }
        return singletonInstance;
    }
}
