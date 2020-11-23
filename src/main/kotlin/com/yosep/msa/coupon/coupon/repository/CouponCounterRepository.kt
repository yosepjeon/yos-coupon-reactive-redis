package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.repository.CrudRepository
//import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
public class CouponCounterRepository<T, U>(
    @Autowired
    val contentRedisOps: ReactiveRedisOperations<T, U>
) : ReactiveCrudRepository<T, U>
{
    override fun <S : T> save(entity: S): Mono<S> {
        TODO("Not yet implemented")
//        Mono.just()
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun <S : T> saveAll(entityStream: Publisher<S>): Flux<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: U): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Publisher<U>): Mono<T> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: U): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Publisher<U>): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<U>): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun findAllById(idStream: Publisher<U>): Flux<T> {
        TODO("Not yet implemented")
    }

    override fun count(): Mono<Long> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: U): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Publisher<U>): Mono<Void> {
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