package tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DollarTest {

    @Test
    @DisplayName("$5 * 2 = $10이 되어야 한다.")
    void test1() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        assertThat(new Dollar(10)).isEqualTo(product);
    }

    @Test
    @DisplayName("Dollar값은 이전 계산 후 변경되지 않아야 한다.")
    void test2() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        assertThat(new Dollar(10)).isEqualTo(product);
        product = five.times(3);
        assertThat(new Dollar(15)).isEqualTo(product);
    }

    @Test
    @DisplayName("동일 Dollar값을 가진 경우 Dollar객체는 동일하다")
    void test3() {
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
    }
}
