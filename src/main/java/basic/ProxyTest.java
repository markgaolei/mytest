package basic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaolei
 * @description 代理模式测试：包括静态代理，JDK动态代理和CGLib代理
 * @date 2020/12/22 10:43
 */
public class ProxyTest {

    //=====================================静态代理 begin=======================================================
    /**
     * 静态代理思想：
     * 静态代理是代理类和被代理类实现相同接口，在代理类中调用被代理类的方法，并在方法之前或之后增强
     */
    interface MessageService {
        /**
         * 发送信息
         * @return
         */
        String sendMessage();
    }

    class MessageServiceImpl implements MessageService{
        @Override
        public String sendMessage() {
            System.out.println("发送信息");
            return "ok";
        }
    }

    class MessageServiceProxyImpl implements MessageService{
        private MessageService messageService;

        MessageServiceProxyImpl (MessageService messageService) {
            this.messageService = messageService;
        }

        @Override
        public String sendMessage() {
            System.out.println("静态代理==================before send message");
            messageService.sendMessage();
            System.out.println("静态代理==================after send message");
            return "ok";
        }
    }

    @Test
    public void staticProxy () {
        MessageService messageService = new MessageServiceProxyImpl(new MessageServiceImpl());
        messageService.sendMessage();
    }
    //=====================================静态代理 end=======================================================

    //=====================================JDK动态代理 begin=======================================================
    /**
     * 实现InvocationHandler
     */
    class InvocationHandlerImpl implements InvocationHandler{

        // 代理对象
        private Object target;

        public InvocationHandlerImpl(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("JDK动态代理==================before send message");
            Object result = method.invoke(target, args);
            System.out.println("JDK动态代理==================after send message");
            return result;
        }
    }

    /**
     * 创建工厂类获取代理实例
     */
    class JdkProxyFactory{
        public Object getProxyInstance (Object target) {
            // 获取代理实例
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandlerImpl(target));
        }
    }

    @Test
    public void testJDKProxy () {
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();
        MessageService messageService = (MessageService) jdkProxyFactory.getProxyInstance(new MessageServiceImpl());
        messageService.sendMessage();
    }
    //=====================================JDK动态代理 end=======================================================

    //=====================================CGLib动态代理 begin=======================================================

    /**
     * 需要被代理的类
     */
    class AliSendMessage {
        public AliSendMessage() {
        }

        public String sendMessage (String message) {
            return "send " + message;
        }
    }

    class MyMethodInterceptor implements MethodInterceptor{

        /**
         *
         * @param o 被代理对象
         * @param method 被代理方法
         * @param objects 方法参数
         * @param methodProxy 用于调用原始方法
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before method " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after method " + method.getName());
            return result;
        }
    }

    class MyEnhancer {
        public Object getEnhancer (Class<?> clazz) {
            // 创建动态代理增强类
            Enhancer enhancer = new Enhancer();
            // 设置类加载器
            enhancer.setClassLoader(clazz.getClassLoader());
            // 设置被代理类
            enhancer.setSuperclass(clazz);
            // 设置方法拦截器
            enhancer.setCallback(new MyMethodInterceptor());
            // 创建代理类
            return enhancer.create();
        }
    }

    @Test
    public void cglibTest() {
        MyEnhancer myEnhancer = new MyEnhancer();
        AliSendMessage aliSendMessage = (AliSendMessage)myEnhancer.getEnhancer(AliSendMessage.class);
        // TODO 报错：Superclass has no null constructors but no arguments were given
        aliSendMessage.sendMessage("test");
    }
    //=====================================CGLib动态代理 end=======================================================

}
