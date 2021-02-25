package hello.demospringcore;

import hello.demospringcore.discount.DiscountPolicy;
import hello.demospringcore.discount.FixDiscountPolicy;
import hello.demospringcore.discount.RateDiscountPolicy;
import hello.demospringcore.member.MemberService;
import hello.demospringcore.member.MemberServiceImpl;
import hello.demospringcore.member.MemoryMemberRepository;
import hello.demospringcore.order.OrderService;
import hello.demospringcore.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
