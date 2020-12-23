package basic;

import org.junit.Test;

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
            System.out.println("静态代理==================before send message");
            return "ok";
        }
    }

    @Test
    public void staticProxy () {
        MessageService messageService = new MessageServiceProxyImpl(new MessageServiceImpl());
        messageService.sendMessage();
    }
    //=====================================静态代理 end=======================================================

}
