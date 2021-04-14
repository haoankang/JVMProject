package ank.hao.spring.spEL;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = {ank.hao.spring.spEL.SpelDemo.class})
public class SpelDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpelDemo.class);
        CallMethod callMethod = context.getBean(CallMethod.class);
        System.out.println(callMethod.toString());
    }
}

@Component
@ToString
@Getter
class SpelConfig {
    @Value("#{'sdjgds'}")
    private String name;
    @Value("#{33}")
    private Integer value;
}

@Component
@ToString
@Getter
class SpelComponent {
    @Value("#{'copy of '+spelConfig.name}")
    private String copyName;
    @Value("#{spelConfig.value+22}")
    private Integer addValue;
}

@Component
@ToString
@Getter
class CallMethod {
    @Value("#{spelComponent.copyName.substring(3)}")
    private String cName;
    @Value("#{T(System).currentTimeMillis()}")
    private Long time;
}