package spring.autowire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.autowire.bean.MyBean;

/**
 * @description
 * @author gaolei
 * @date 2020/11/23 9:53
 */
@Component
public class MyService {

    private final MyBean myBean;

    @Autowired
    public MyService(MyBean myBean){
        this.myBean = myBean;
    }
}
