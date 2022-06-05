package tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrancTest {

    @Test
    @DisplayName("5CHF * 2 = 10CHF이 되어야 한다.")
    void test1() {
        Franc five = new Franc(5);
        Assertions.assertThat(new Franc(10)).isEqualTo(five.times(2));
        Assertions.assertThat(new Franc(15)).isEqualTo(five.times(3));
    }

    @Test
    @DisplayName("동일 Franc값을 가진 경우 Franc객체는 동일하다")
    void test2() {
        assertThat(new Franc(5)).isEqualTo(new Franc(5));
        assertThat(new Franc(5)).isNotEqualTo(new Franc(6));
    }

    @Test
    @DisplayName("Dollar와 Franc는 같지 않다")
    void test3() {
        assertThat(new Dollar(5)).isNotEqualTo(new Franc(5));
    }
}
