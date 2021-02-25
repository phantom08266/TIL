package hello.demospringcore.discount;

import hello.demospringcore.member.Grade;
import hello.demospringcore.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L,"june", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //than
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    public void vip_x(){
        //given
        Member member = new Member(1L,"june", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,10000);
        //than
        assertThat(discount).isEqualTo(0);
    }
}