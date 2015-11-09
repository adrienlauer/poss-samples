package com.github.adrienlauer.poss.domain.order;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    public static final long ORDER_ID = 1L;
    public static final long CUSTOMER_ID = 2L;

    private Order underTest;

    @Before
    public void setup() {
        underTest = new Order(ORDER_ID, CUSTOMER_ID);
    }

    @Test
    public void testInitialOrderState() throws Exception {
        assertThat(underTest.getItems()).isEmpty();
        assertThat(underTest.getTotal()).isZero();
    }

    @Test
    public void testAddItem() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;

        underTest.addItem(new OrderItem(quantity, productId, price));
        assertThat(underTest.getTotal()).isEqualTo(quantity * price);
        assertThat(underTest.getItems().size()).isEqualTo(1);
    }

    @Test
    public void testRemoveItem() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;
        final OrderItem orderItem = new OrderItem(quantity, productId, price);

        underTest.addItem(orderItem);
        underTest.removeItem(orderItem);
        assertThat(underTest.getTotal()).isZero();
        assertThat(underTest.getItems()).isEmpty();
    }
}
