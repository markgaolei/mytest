package algorithm.thread;
/**
 * @description 
 * @author gaolei
 * @date 2020/11/23 10:05
 */
public class TestThread4Jvm {
    public static void main(String[] args) {
        System.out.println("aaaaa");
        try{
            Thread.sleep(10000000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
