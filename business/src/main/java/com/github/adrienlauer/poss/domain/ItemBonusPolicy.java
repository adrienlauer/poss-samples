package com.github.adrienlauer.poss.domain;

import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.order.OrderItem;

import javax.inject.Named;

@Named(BonusPolicy.PER_ITEM)
class ItemBonusPolicy implements BonusPolicy {
    private static final int ITEM_BONUS = 10;

    @Override
    public double computeBonus(Order order) {
        return order.getItems().stream().mapToInt(OrderItem::getQuantity).sum() * ITEM_BONUS;
    }

}
