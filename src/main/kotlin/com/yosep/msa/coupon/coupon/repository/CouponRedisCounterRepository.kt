package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

//<T,U>
@Repository
public class CouponRedisCounterRepository(

    @Autowired
    val reactiveRedisTemplate: ReactiveRedisTemplate<String, Long>
) : ReactiveCrudRepository<CouponCounter, String>, CounterRepository<Long, String> {
    var valueOps = reactiveRedisTemplate.opsForValue()

    override fun <S : CouponCounter> save(entity: S): Mono<S> {
        valueOps.set(entity.id, entity.count).subscribe()

        return Mono.just(entity)
    }

    override fun <S : CouponCounter> saveAll(entities: MutableIterable<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun <S : CouponCounter> saveAll(entityStream: Publisher<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Mono<CouponCounter> {
        return valueOps.get(id).flatMap { value -> Mono.just(CouponCounter.of(id, value)) }
            .switchIfEmpty(Mono.empty())
    }

    override fun findById(id: Publisher<String>): Mono<CouponCounter> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: String): Mono<Boolean> {
//        return valueOps.get(id)
        TODO("Not yet implemented")
    }

    override fun existsById(id: Publisher<String>): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Flux<CouponCounter> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<String>): Flux<CouponCounter> {
        TODO("Not yet implemented")
    }

    override fun findAllById(idStream: Publisher<String>): Flux<CouponCounter> {
        TODO("Not yet implemented")
    }

    override fun count(): Mono<Long> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String): Mono<Void> {
        valueOps.delete(id).subscribe { println(it) }

        return Mono.empty()
    }

    override fun deleteById(id: Publisher<String>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun delete(entity: CouponCounter): Mono<Void> {
        valueOps.delete(entity.id).subscribe()

        return Mono.empty()
    }

    override fun deleteAll(entities: MutableIterable<CouponCounter>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entityStream: Publisher<out CouponCounter>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun increase(id: String): Mono<Long> {
        println("increase")
        return valueOps.increment(id)
    }

    override fun increase(id: String, delta: Long): Mono<Long> {
        return valueOps.increment(id, delta)
    }

    override fun decrease(id: String): Mono<Long> {
        return valueOps.decrement(id)
    }

    override fun decrease(id: String, delta: Long): Mono<Long> {
        return valueOps.decrement(id, delta)
    }
}