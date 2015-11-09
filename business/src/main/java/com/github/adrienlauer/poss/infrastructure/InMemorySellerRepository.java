package com.github.adrienlauer.poss.infrastructure;

import com.github.adrienlauer.poss.domain.seller.Seller;
import org.seedstack.business.domain.BaseRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemorySellerRepository extends BaseRepository<Seller, Long> {
    private ConcurrentMap<Long, Seller> sellers = new ConcurrentHashMap<>();

    @Override
    protected Seller doLoad(Long sellerId) {
        return sellers.get(sellerId);
    }

    @Override
    protected void doDelete(Long sellerId) {
        sellers.remove(sellerId);
    }

    @Override
    protected void doDelete(Seller seller) {
        doDelete(seller.getEntityId());
    }

    @Override
    protected void doPersist(Seller seller) {
        if (sellers.putIfAbsent(seller.getEntityId(), seller) != null) {
            throw new IllegalStateException(String.format("Seller %d is already persisted", seller.getEntityId()));
        }
    }

    @Override
    protected Seller doSave(Seller seller) {
        sellers.put(seller.getEntityId(), seller);
        return seller;
    }
}
