package com.github.adrienlauer.poss.application;

import com.github.adrienlauer.poss.domain.BonusPolicy;
import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.seller.Seller;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.domain.Repository;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class SaleServiceTest {

    public static final long ORDER_ID = 1111L;
    public static final long CUSTOMER_ID = 222L;
    public static final long SELLER_ID = 3333L;
    public static final double BONUS_VALUE = 100D;

    private SaleService underTest;

    private DomainRegistry domainRegistry;
    private Repository<Seller, Long> repository;

    @Before
    public void before() {
        domainRegistry = mock(DomainRegistry.class);
        //noinspection unchecked
        repository = mock(Repository.class);
        underTest = new SaleServiceImpl(repository, domainRegistry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellerShouldExist() {
        when(repository.load(SELLER_ID)).thenReturn(null);
        underTest.updateBonus(new Order(ORDER_ID, CUSTOMER_ID), SELLER_ID);
    }

    @Test
    public void updateBonusShouldModifyTheSeller() {
        Seller seller = mock(Seller.class);
        when(repository.load(SELLER_ID)).thenReturn(seller);
        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        mockBonusPolicy(seller, order);

        underTest.updateBonus(order, SELLER_ID);

        then(seller).should().addToMonthlyBonus(BONUS_VALUE);
        then(repository).should().save(seller);
    }

    private void mockBonusPolicy(Seller seller, Order order) {
        BonusPolicy bonusPolicy = mock(BonusPolicy.class);
        when(domainRegistry.getPolicy(BonusPolicy.class, seller.getBonusPolicy())).thenReturn(bonusPolicy);
        when(bonusPolicy.computeBonus(order)).thenReturn(BONUS_VALUE);
    }
}
