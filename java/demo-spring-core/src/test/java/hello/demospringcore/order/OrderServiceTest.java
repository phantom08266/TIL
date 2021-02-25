package hello.demospringcore.order;

import hello.demospringcore.AppConfig;
import hello.demospringcore.discount.FixDiscountPolicy;
import hello.demospringcore.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void boforeEach(){
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    public void createOrder(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "june", Grade.VIP);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "june",10000);
        //than
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
