package com.github.adrienlauer.poss.domain;

import com.github.adrienlauer.poss.domain.order.Order;

import javax.inject.Named;

@Named(BonusPolicy.TOTAL_PERCENTAGE)
class TotalBonusPolicy implements BonusPolicy {
    private static final double BONUS_RATE = 0.02;

    @Override
    public double computeBonus(Order order) {
        return order.getTotal() * BONUS_RATE;
    }
}
