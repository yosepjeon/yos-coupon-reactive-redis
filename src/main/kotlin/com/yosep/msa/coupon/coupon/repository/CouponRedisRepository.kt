package com.yosep.msa.coupon.coupon.repository

import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class CouponRedisRepository<T, K>(
    @Autowired
    val contentRedisOps: ReactiveRedisOperations<T, K>,

    @Autowired
    val reactiveRedisTemplate: ReactiveRedisTemplate<K, T>
): ReactiveCrudRepository<T, K> {
    val opsValue = contentRedisOps.opsForValue();

    override fun <S : T> save(entity: S): Mono<S> {

        TODO("Not yet implemented")
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun <S : T> saveAll(entityStream: Publisher<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: K): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Publisher<K>): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: K): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Publisher<K>): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<K>): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun findAllById(idStream: Publisher<K>): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun count(): Mono<Long> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: K): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Publisher<K>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun delete(entity: T): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<T>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entityStream: Publisher<out T>): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Mono<Void> {
        TODO("Not yet implemented")
    }

}