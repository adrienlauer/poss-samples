package com.github.adrienlauer.poss.domain;

import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class ItemBonusPolicyTest {

    public static final long CUSTOMER_ID = 200L;
    public static final long ORDER_ID = 100L;

    private ItemBonusPolicy underTest = new ItemBonusPolicy();

    @Test
    public void bonusShouldBeEqualToTheNumberOfItems() {
        assertBonus(1,2);
        assertBonus(7,5);
        assertBonus(0,0);
    }

    public void assertBonus(int quantity, int itemSize) {
        double bonus = underTest.computeBonus(prepareOrder(quantity, itemSize));
        Assertions.assertThat(bonus).isEqualTo(quantity * itemSize * 10);
    }

    public Order prepareOrder(int quantity, int itemSize) {
        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        for (int i = 0; i < itemSize; i++) {
            order.addItem(new OrderItem(quantity, UUID.randomUUID().hashCode(), 50d));
        }
        return order;
    }
}
