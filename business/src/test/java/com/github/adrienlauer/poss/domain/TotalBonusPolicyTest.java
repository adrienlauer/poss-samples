package com.github.adrienlauer.poss.domain;

import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class TotalBonusPolicyTest {

    public static final long CUSTOMER_ID = 200L;
    public static final long ORDER_ID = 100L;

    private TotalBonusPolicy underTest = new TotalBonusPolicy();

    @Test
    public void bonusShouldBeEqualToTheNumberOfItems() {
        assertBonus(0);
        assertBonus(10);
        assertBonus(33);
    }

    public void assertBonus(double total) {
        double bonus = underTest.computeBonus(prepareOrder(total));
        Assertions.assertThat(bonus).isEqualTo(total * .02);
    }

    public Order prepareOrder(double total) {
        Order order = new Order(1L, 2L);
        order.addItem(new OrderItem(1, UUID.randomUUID().hashCode(), total));
        return order;
    }
}
