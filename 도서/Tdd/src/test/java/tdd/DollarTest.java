package tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DollarTest {

    @Test
    @DisplayName("$5 * 2 = $10이 되어야 한다.")
    void test1() {
        Money five = Money.dollar(5);
        assertThat(Money.dollar(10)).isEqualTo(five.times(2));
        assertThat(Money.dollar(15)).isEqualTo(five.times(3));
    }

    @Test
    @DisplayName("동일 Dollar값을 가진 경우 Dollar객체는 동일하다")
    void test3() {
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));
    }

    @Test
    void testCurrency() {
        assertThat("USD").isEqualTo(Money.dollar(1).currency());
        assertThat("CHF").isEqualTo(Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(Money.dollar(10)).isEqualTo(reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(five).isEqualTo(sum.augend);
        assertThat(five).isEqualTo(sum.addend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertThat(Money.dollar(7)).isEqualTo(result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(Money.dollar(1)).isEqualTo(result);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertThat(Money.dollar(1)).isEqualTo(result);
    }

    @Test
    void testIdentityRate() {
        assertThat(1).isEqualTo(new Bank().rate("USD", "USD"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertThat(Money.dollar(10)).isEqualTo(result);
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrance = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrance).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertThat(Money.dollar(15)).isEqualTo(result);
    }
}
