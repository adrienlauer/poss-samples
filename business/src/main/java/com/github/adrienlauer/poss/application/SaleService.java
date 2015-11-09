package com.github.adrienlauer.poss.application;

import com.github.adrienlauer.poss.domain.order.Order;
import org.seedstack.business.Service;

@Service
public interface SaleService {

    void updateBonus(Order order, Long sellerId);

}
