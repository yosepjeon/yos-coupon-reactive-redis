package com.yosep.msa.coupon.coupon.controller

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.config.TestRedisConfiguration
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import com.yosep.msa.coupon.coupon.service.CouponCounterHandler
import com.yosep.msa.yoscouponapi.coupon.service.CouponCounterService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(RedisConfig::class, CouponRedisCounterRepository::class, TestRedisConfiguration::class, CouponCounterService::class, CouponCounterRouter::class, CouponCounterHandler::class)
@ExtendWith(SpringExtension::class)
//@WebFluxTest(controllers = [CouponCounterRouter::class, CouponCounterHandler::class])
//@WebFluxTest
@SpringBootTest(classes = [CouponCounterControllerTest::class])
class CouponCounterControllerTest (
//    @Autowired
//    val webTestClient: WebTestClient,

    @Autowired
    val couponCounterRouter: CouponCounterRouter,

    @Autowired
    val couponCouponHandler: CouponCounterHandler,

    @Autowired
    val couponCounterRepository: CouponRedisCounterRepository
){
    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction(couponCounterRouter.routerFunction(couponCouponHandler))
            .build()

        var couponCounter = CouponCounter("test1",100)
        couponCounterRepository.save(couponCounter)
    }

    @Test
    @DisplayName("특정 쿠폰 조회 컨트롤러 구현 테스트")
    fun getCouponControllerTest() {
        // Given
        var couponCounter = CouponCounter("test1",100)

        // When
        var result = webTestClient
            .get()
            .uri("/reactive/api/coupons/{id}","test1")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .isEmpty
//            .json("{'id':'test1','count':100}")
//            .jsonPath("$.id").isEqualTo("test1")
//            .jsonPath("$.count").isEqualTo(100)

        // Then

        println(result)
    }
}