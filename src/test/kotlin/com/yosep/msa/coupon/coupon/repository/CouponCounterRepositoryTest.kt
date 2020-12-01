package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.config.RedisConfig
//import com.yosep.msa.coupon.config.TestRedisConfiguration
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.test.annotation.Rollback
import reactor.test.StepVerifier

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(RedisConfig::class, CouponRedisCounterRepository::class)
@SpringBootTest(classes = [CouponCounterRepositoryTest::class])
class CouponCounterRepositoryTest(
//    @Autowired
//    val contentRedisOps: ReactiveRedisOperations<String, String>,

//    @Autowired
//    var couponCounterRepository: CouponCounterRepository<String,ResourceProperties.Content>

    @Autowired
    @Qualifier(value = "reactiveLongRedisTemplate")
    var reactiveRedisTemplate: ReactiveRedisTemplate<String, Long>,

    @Autowired
    var couponRedisCounterRepository: CouponRedisCounterRepository
) {
    lateinit var valueOps:ReactiveValueOperations<String,Long>

    @BeforeEach
    fun setUp() {
        valueOps = reactiveRedisTemplate.opsForValue()

        var couponCounter = CouponCounter.of("coupon-decrease-test", 1000)
        couponRedisCounterRepository.save(couponCounter)
    }

    @Test
    @DisplayName("쿠폰 수량 저장 레포지터리 내부 구현 테스트")
    @Rollback(value = false)
    fun couponCounterSaveImplTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)

        // When
        var createdMono = valueOps.set("test1", 10)
        println("$createdMono!")

        // Then
        StepVerifier.create(createdMono)
            .expectNext(true)
            .verifyComplete()


//        var getMono = valueOps.get("test1")
//        var result = getMono
//            .doOnNext { valueOps.decrement("test1") }
//            .doOnNext { println("$it!!!") }
//
//        StepVerifier.create(valueOps.decrement("test1"))
//            .expectNext(9)
//            .verifyComplete()
    }


    @Test
    @DisplayName("쿠폰 수량 저장 테스트")
    fun couponCounterSaveTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)

        // When
        var savedCouponCounter = couponRedisCounterRepository.save(couponCounter)
        var findedCouponCounter = valueOps.get(couponCounter.id)

        // Then
        StepVerifier.create(findedCouponCounter)
            .expectNext(10)
            .verifyComplete()
    }

    @Test
    @DisplayName("특정 쿠폰 수량 가져오기 테스트")
    fun couponCounterGetTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)
        var savedCouponCounter = couponRedisCounterRepository.save(couponCounter)

        // When
        val findedCouponCounter = couponRedisCounterRepository.findById(couponCounter.id)

        // Then
        var anotherCouponCounter = CouponCounter.of("coupon-id2", 10)
        var findedCouponCounter2 = couponRedisCounterRepository.findById(anotherCouponCounter.id)
        StepVerifier.create(findedCouponCounter)
            .expectNext(couponCounter)
            .verifyComplete()

        StepVerifier.create(findedCouponCounter2)
            .expectNext()
            .verifyComplete()
    }

    @Test
    @DisplayName("쿠폰 수량 1개 증가 및 감소 테스트")
    fun couponCounterIncreaseAndDecreaseTest() {
        // Given
        var id = "coupon-decrease-test"

        // When
        var decreaseResult = couponRedisCounterRepository.decrease(id)

        // Then
        StepVerifier.create(decreaseResult)
            .expectNext(999)
            .verifyComplete()

        // When
        var increaseResult = couponRedisCounterRepository.increase(id)

        // Then
        StepVerifier.create(increaseResult)
            .expectNext(1000)
            .verifyComplete()
    }

    @Test
    @DisplayName("쿠폰 수량 2개 이상 증가 및 감소 테스트")
    fun couponCounterIncreaseAndDecreseTest() {
        // Given
        var id = "coupon-decrease-test"

        // When
        var decreaseResult = couponRedisCounterRepository.decrease(id, 100)

        // Then
        StepVerifier.create(decreaseResult)
            .expectNext(900)
            .verifyComplete()

        // When
        var increaseResult = couponRedisCounterRepository.increase(id,50)

        // Then
        StepVerifier.create(increaseResult)
            .expectNext(950)
            .verifyComplete()
    }

    @Test
    @DisplayName("쿠폰 삭제 테스트")
    fun couponCounterDeleteTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)
        var savedCouponCounter = couponRedisCounterRepository.save(couponCounter)

        // When
        var deleteResult = couponRedisCounterRepository.deleteById(couponCounter.id)
        var findedCoupon = couponRedisCounterRepository.findById(couponCounter.id)

        // Then
        StepVerifier.create(deleteResult)
            .expectNext()
            .verifyComplete()

        StepVerifier.create(findedCoupon)
            .expectNext()
            .verifyComplete()
    }
}