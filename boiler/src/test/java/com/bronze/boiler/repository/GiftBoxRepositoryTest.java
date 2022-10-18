package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.gift.GiftBoxWithSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class GiftBoxRepositoryTest {


    @Autowired
    private GiftBoxRepository giftBoxRepository;


    @Autowired
    private GiftBoxWithSetRepository giftBoxWithSetRepositoryCst;

    @Test
    @DisplayName("2개 이상의 List타입의 1:N관계를 가지고있는 주인엔티티는 MultipleBagFetchException이 발생")
    void 쿠폰지갑조회_fetchJoin에서_where사용(){


        assertThatThrownBy(() -> giftBoxRepository.findAllWithFetchJoin())
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .hasMessageContaining("MultipleBagFetchException");
    }

    @Test
    @DisplayName("2개 이상의 Set타입의 1:N관계를 가지고있는 주인엔티티는 정상조회")
    void 쿠폰지갑조회_fetchJoin에서_where사용1(){


        List<GiftBoxWithSet> giftBoxWithSetList = giftBoxWithSetRepositoryCst.findAllWithFetchJoin();
        assertThat(giftBoxWithSetList.size()).isEqualTo(0l);
    }






}
