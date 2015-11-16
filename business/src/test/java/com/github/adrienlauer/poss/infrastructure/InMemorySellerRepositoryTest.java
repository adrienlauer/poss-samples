package com.github.adrienlauer.poss.infrastructure;

import com.github.adrienlauer.poss.domain.seller.Seller;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class InMemorySellerRepositoryTest {

    private InMemorySellerRepository underTest = new InMemorySellerRepository();

    @Test
    public void testRepository() {
        Seller seller = new Seller(1L, LocalDate.now());
        underTest.save(seller);
        Seller loadedSeller = underTest.load(1L);
        Assertions.assertThat(loadedSeller).isEqualTo(seller);

        underTest.delete(seller);
        Seller missingSeller = underTest.load(1L);
        Assertions.assertThat(missingSeller).isNull();
    }
}
