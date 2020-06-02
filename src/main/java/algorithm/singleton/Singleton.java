package algorithm.singleton;
//双重校验锁懒汉模式
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
