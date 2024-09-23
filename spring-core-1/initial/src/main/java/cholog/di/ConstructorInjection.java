package cholog.di;

import org.springframework.stereotype.Service;

@Service
public class ConstructorInjection {
    private InjectionBean injectionBean;

    /*
    ConstructorInjection으로 InjectionBean 주입받기
     */

    //생성자 주입 인데 어노테이션 없나? > Spring 4.3 이상 버전부터 불필요
    public ConstructorInjection(InjectionBean injectionBean) {
        this.injectionBean = injectionBean;
    }


    public String sayHello() {
        return injectionBean.hello();
    }
}
