package money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrancTest {

    @Test
    @DisplayName("5CHF * 2 = 10CHF이 되어야 한다.")
    void test1() {
        Money five = Money.franc(5);
        assertThat(Money.franc(10)).isEqualTo(five.times(2));
        assertThat(Money.franc(15)).isEqualTo(five.times(3));
    }

    @Test
    @DisplayName("Dollar와 Franc는 같지 않다")
    void test2() {
        assertThat(Money.dollar(5)).isNotEqualTo(Money.franc(5));
    }
}
