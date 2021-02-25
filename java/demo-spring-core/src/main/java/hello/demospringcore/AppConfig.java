package hello.demospringcore;

import hello.demospringcore.discount.DiscountPolicy;
import hello.demospringcore.discount.FixDiscountPolicy;
import hello.demospringcore.discount.RateDiscountPolicy;
import hello.demospringcore.member.MemberService;
import hello.demospringcore.member.MemberServiceImpl;
import hello.demospringcore.member.MemoryMemberRepository;
import hello.demospringcore.order.OrderService;
import hello.demospringcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
