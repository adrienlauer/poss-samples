package com.github.adrienlauer.poss;

import com.github.adrienlauer.poss.application.SaleService;
import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.order.OrderItem;
import com.github.adrienlauer.poss.domain.seller.Seller;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class SaleServiceTest {

    public static final long SELLER_ID = 1111L;
    public static final long CUSTOMER_ID = 3333L;
    public static final long ORDER_ID = 2222L;

    @Inject
    private SaleService saleService;

    @Inject
    private Repository<Seller, Long> sellerLongRepository;

    @Test
    public void testCalculateBonus() {
        // Add a new seller
        sellerLongRepository.persist(new Seller(SELLER_ID, LocalDate.now()));

        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        order.addItem(new OrderItem(2, 444L, 50d));

        // Update the bonus with the order
        saleService.updateBonus(order, SELLER_ID);

        // Check that the seller has the expected bonus
        Seller seller = sellerLongRepository.load(SELLER_ID);
        Assertions.assertThat(seller.getMonthlyBonus()).isEqualTo(20);
    }
}
