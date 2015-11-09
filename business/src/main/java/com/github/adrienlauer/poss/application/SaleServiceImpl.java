package com.github.adrienlauer.poss.application;

import com.github.adrienlauer.poss.domain.BonusPolicy;
import com.github.adrienlauer.poss.domain.order.Order;
import com.github.adrienlauer.poss.domain.seller.Seller;
import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.domain.Repository;

import javax.inject.Inject;

class SaleServiceImpl implements SaleService {
    private final Repository<Seller, Long> sellerRepository;
    private final DomainRegistry domainRegistry;

    @Inject
    public SaleServiceImpl(Repository<Seller, Long> sellerRepository, DomainRegistry domainRegistry) {
        this.sellerRepository = sellerRepository;
        this.domainRegistry = domainRegistry;
    }

    @Override
    public void updateBonus(Order order, Long sellerId) {
        Seller seller = sellerRepository.load(sellerId);
        if (seller == null) {
            throw new IllegalArgumentException("Seller " + sellerId +  " cannot be found");
        }

        BonusPolicy bonusPolicy = domainRegistry
                .getPolicy(BonusPolicy.class, seller.getBonusPolicy());

        double orderBonus = bonusPolicy.computeBonus(order);
        seller.addToMonthlyBonus(orderBonus);

        sellerRepository.save(seller);
    }
}
