package com.github.adrienlauer.poss.domain;

import com.github.adrienlauer.poss.domain.order.Order;
import org.seedstack.business.domain.DomainPolicy;

@DomainPolicy
public interface BonusPolicy {
    String PER_ITEM = "item";
    String TOTAL_PERCENTAGE = "percentage";

    double computeBonus(Order order);
}
