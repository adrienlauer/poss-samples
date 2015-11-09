/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.github.adrienlauer.poss.domain.order;


import org.seedstack.business.domain.BaseValueObject;

public class OrderItem extends BaseValueObject {
    private final int quantity;
    private final long productId;
    private final double price;

    public OrderItem(int quantity, long productId, double price) {
        this.quantity = quantity;
        this.productId = productId;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}
