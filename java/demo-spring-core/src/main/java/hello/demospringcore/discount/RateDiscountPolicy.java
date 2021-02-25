package hello.demospringcore.discount;

import hello.demospringcore.member.Grade;
import hello.demospringcore.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPolicy = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPolicy / 100;
        }else{
            return 0;
        }
    }
}
