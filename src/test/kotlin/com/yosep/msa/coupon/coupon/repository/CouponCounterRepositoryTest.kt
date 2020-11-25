package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.config.TestRedisConfiguration
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.test.annotation.Rollback
import reactor.test.StepVerifier

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(RedisConfig::class, CouponRedisCounterRepository::class, TestRedisConfiguration::class)
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
    }

    @Test
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
    fun couponCounterGetTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)
        var savedCouponCounter = couponRedisCounterRepository.save(couponCounter)

        // When
        val findedCouponCounter = couponRedisCounterRepository.findById(couponCounter.id)

        // Then
        var anotherCouponCounter = CouponCounter.of("coupon-id2", 10)
        StepVerifier.create(findedCouponCounter)
            .expectNext(couponCounter)
            .verifyComplete()
    }
}