package tdd;

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
}
