package com.github.adrienlauer.poss.domain.seller;


import com.github.adrienlauer.poss.domain.BonusPolicy;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SellerTest {
    private static final Long SELLER_ID = 1L;
    private Seller underTest;

    @Test
    public void testInitialState() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        assertThat(underTest.getBonusPolicy()).isEqualTo(BonusPolicy.PER_ITEM);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(0);
    }

    @Test(expected = IllegalStateException.class)
    public void testPercentagePolicyIsForbiddenForJuniors() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testPercentagePolicyIsAllowedForSeniors() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testBonusCompute() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.addToMonthlyBonus(450);
        underTest.addToMonthlyBonus(50);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(500);
        underTest.resetMonthlyBonus();
        assertThat(underTest.getMonthlyBonus()).isZero();
    }
}