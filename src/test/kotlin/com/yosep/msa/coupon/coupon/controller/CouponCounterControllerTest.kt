package com.yosep.msa.coupon.coupon.controller

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.config.TestRedisConfiguration
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.junit.jupiter.api.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(RedisConfig::class, CouponRedisCounterRepository::class, TestRedisConfiguration::class)
@WebFluxTest
class CouponCounterControllerTest (
    @Autowired
    val webTestClient: WebTestClient,

    @Autowired
    val couponCounterRepository: CouponRedisCounterRepository
){
    @BeforeEach
    fun setUp() {

    }

    @Test
    @DisplayName("특정 쿠폰 조회 컨트롤러 구현 테스트")
    fun getCouponControllerTest() {
        // Given

        // When
        webTestClient
            .get()
            .uri("/reactive/api/coupons/{id}","")

        // Then
    }
}