package hello.demospringcore.discount;

import hello.demospringcore.member.Grade;
import hello.demospringcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmout = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmout;
        }else{
            return 0;
        }
    }
}
