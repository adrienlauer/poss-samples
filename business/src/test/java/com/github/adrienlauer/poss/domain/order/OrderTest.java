package com.github.adrienlauer.poss.domain.order;

import org.assertj.core.api.Assertions;
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
    public void testAddItemIncreaseTheTotal() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;

        underTest.addItem(new OrderItem(quantity, productId, price));
        assertThat(underTest.getTotal()).isEqualTo(quantity * price);
        assertThat(underTest.getItems().size()).isEqualTo(1);
    }

    @Test
    public void testRemoveItemUpdateTheTotal() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;
        final OrderItem orderItem = new OrderItem(quantity, productId, price);

        underTest.addItem(orderItem);
        underTest.removeItem(orderItem);
        assertThat(underTest.getTotal()).isZero();
        assertThat(underTest.getItems()).isEmpty();
    }

    @Test
    public void itemQuantityShouldNotBeLowerThan1() throws Exception {
        try {
            new OrderItem(0, 1L, 50d);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("The quantity cannot be lower than 1");
        }
    }

    @Test
    public void itemPriceShouldNotBeLowerThan0() throws Exception {
        try {
            new OrderItem(1, 1L, -1);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("The price cannot be lower than 0");
        }
    }
}
