package com.yosep.msa.coupon.coupon.controller

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.coupon.domain.withAmount.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import com.yosep.msa.coupon.coupon.service.CouponCounterHandler
import com.yosep.msa.coupon.coupon.service.CouponCounterService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(
    RedisConfig::class,
    CouponRedisCounterRepository::class,
    CouponCounterService::class,
    CouponCounterRouter::class,
    CouponCounterHandler::class
)
@ExtendWith(SpringExtension::class)
//@WebFluxTest(controllers = [CouponCounterRouter::class, CouponCounterHandler::class])
//@WebFluxTest
@SpringBootTest(classes = [CouponCounterControllerTest::class])
class CouponCounterControllerTest(
//    @Autowired
//    val webTestClient: WebTestClient,

    @Autowired
    val couponCounterRouter: CouponCounterRouter,

    @Autowired
    val couponCouponHandler: CouponCounterHandler,

    @Autowired
    val couponCounterRepository: CouponRedisCounterRepository
) {
    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction(couponCounterRouter.routerFunction())
            .build()

        var couponCounter = CouponCounter("test1", 100)
        couponCounterRepository.save(couponCounter)

        var couponCounterForCountableCouponTest = CouponCounter("decrease-test1",100)
        var zeroValueCouponCounterForCountableCouponTest = CouponCounter("decrease-test1-zero-value",0)
        var oneValueCouponCounterForCountableCouponTest = CouponCounter("decrease-test1-one-value",1)

        couponCounterRepository.save(couponCounterForCountableCouponTest)
        couponCounterRepository.save(zeroValueCouponCounterForCountableCouponTest)
        couponCounterRepository.save(oneValueCouponCounterForCountableCouponTest)
    }

    @Test
    @DisplayName("특정 쿠폰 조회 컨트롤러 구현 테스트")
    fun getCouponControllerTest() {
        // Given
        var couponCounter = CouponCounter("test1", 100)

        // When & Then (Success)
        var result = webTestClient
            .get()
            .uri("/reactive/api/coupons/{id}", "test1")
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk
            .expectBody()
//            .json("{'id':'test1','count':'100'}")
            .jsonPath("$.id").isEqualTo("test1")
            .jsonPath("$.count").isEqualTo(100)

        println("$result@@@")

        // When & Then (Fail)
//        webTestClient
//            .get()
//            .uri("/reactive/api/coupons/{id}", "test2")
//            .exchange()
//            .expectHeader()
//            .contentType(MediaType.APPLICATION_JSON)
//            .expectStatus()
//            .isNotFound

    }

    @Test
    @DisplayName("쿠폰 재고수 감소 테스트")
    fun useCountableCouponTest() {
        // Given
        var id = "test1"

        // When & Then
        var result = webTestClient
            .post()
            .uri("/reactive/api/coupons/{id}", id)
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.result").isEqualTo(true)

        println("${result.toString()}")
    }

    @Test
    @DisplayName("쿠폰 재고가 0개일때 쿠폰을 사용할때 테스트")
    fun useCountableCouponButCountZeroTest() {
        // Given
        var id = "decrease-test1-zero-value"

        // When & Then
        var result = webTestClient
            .post()
            .uri("/reactive/api/coupons/{id}",id)
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.result").isEqualTo(false)
    }

    @Test
    @DisplayName("쿠폰 재고가 1개일때 쿠폰을 1개 사용할때 테스트")
    fun useCoutableCouponRestOneCount() {
        // Given
        var id = "decrease-test1-one-value"

        // When & Then
        var result = webTestClient
            .post()
            .uri("/reactive/api/coupons/{id}",id)
            .exchange()
            .expectHeader()
    }

    @Test
    @DisplayName("쿠폰 재고가 0개일때 쿠폰을 3개 한꺼번에 사용할때 테스트")
    fun useCountableCouponRestZeroCount() {
        // Given
        val id = "decrease-test1-multi-value"
        val num = 3L

        // When & Then
        var result = webTestClient
            .post()
            .uri("/reactive/api/coupons/{id}/use-count/{num}",id,num)
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.result").isEqualTo(false)

    }
}