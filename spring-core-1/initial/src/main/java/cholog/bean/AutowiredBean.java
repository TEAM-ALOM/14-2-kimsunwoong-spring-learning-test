package cholog.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    /*
    어떤 방법으로 Component에 Bean을 주입하는지 학습하기
     */
    //의존성 주입. 3가지 방식이 존재
    // 생성자 주입, 세터 주입, 필드 주입 중 필드 주입
    @Autowired// Spring이 자동으로 Bean을 주입해 주는 어노테이션
    private SpringBean springBean;

    public String sayHello() {
        return springBean.hello();
    }

}
//생성자 주입 방식
//@Component
//public class AutowiredBean {
//    private SpringBean springBean;
//    @Autowired// Spring이 자동으로 Bean을 주입해 주는 어노테이션
//    public MyService(SpringBean springBean) {
//        this.springBean = springBean;
//    }
//
//    public String sayHello() {
//        return springBean.hello();
//    }
//}

//세터 주입 방식
//@Component
//public class AutowiredBean {
//    private SpringBean springBean;
//    @Autowired// Spring이 자동으로 Bean을 주입해 주는 어노테이션
//    public void setMyService(SpringBean springBean) {
//        this.springBean = springBean;
//    }
//
//    public String sayHello() {
//        return springBean.hello();
//    }
//}