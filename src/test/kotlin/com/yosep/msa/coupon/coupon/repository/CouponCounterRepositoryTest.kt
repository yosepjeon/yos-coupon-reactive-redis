package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.config.TestRedisConfiguration
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import kotlinx.coroutines.reactive.awaitSingle
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import redis.embedded.Redis

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)

@Import(RedisConfig::class,CouponCounterRepository::class,TestRedisConfiguration::class)
@SpringBootTest(classes = [CouponCounterRepositoryTest::class])
class CouponCounterRepositoryTest(
//    @Autowired
//    val contentRedisOps: ReactiveRedisOperations<String, String>,

//    @Autowired
//    var couponCounterRepository: CouponCounterRepository<String,ResourceProperties.Content>
) {

    @Autowired
    @Qualifier(value = "reactiveLongRedisTemplate")
    lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String,Long>

    @Test
    @Rollback(value = false)
    fun couponCounterSaveImplTest() {
//        StepVerifier.create()


        var valueOps = reactiveRedisTemplate.opsForValue()
//        var valueOps = contentRedisOps.opsForValue()
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)

        var mono = valueOps.set("test1",10)

        StepVerifier.create(mono)
            .expectNext(true)
            .verifyComplete()


//        valueOps.decrement("test1")
//        valueOps.decrement("test1")
        var getMono = valueOps.get("test1")
        var result = getMono
            .doOnNext { valueOps.decrement("test1") }
            .doOnNext { println("$it!!!") }

        StepVerifier.create(valueOps.decrement("test1"))
            .expectNext(9)
            .verifyComplete()
    }


    @Test
    fun couponCounterSaveTest() {
        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)

        // When
//        var createdCouponCounter = couponCounterRepository.save(couponCounter)

        // Then
//        var findedCouponCounter = couponCounterRepository.findById(couponCounter.id)

//        Assert.assertEquals(findedCouponCounter.do, true)
//        Assert.assertEquals(createdCouponCounter, findedCouponCounter)
    }
}