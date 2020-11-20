package com.yosep.msa.coupon.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jdk.javadoc.internal.doclets.toolkit.Content
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.xerial.snappy.buffer.DefaultBufferAllocator.factory


@Configuration
@EnableRedisRepositories
class RedisConfig {
    @Value("\${spring.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.redis.port}")
    private val redisPort = 0
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost!!, redisPort)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<ByteArray, ByteArray>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }

    @Bean
    fun reactiveRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory?): Any? {
//        val serializer: RedisSerializer<String> = StringRedisSerializer()
//        val jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(
//            String::class.java
//        )
//        val serializationContext: RedisSerializationContext<*, *> = RedisSerializationContext
//            .newSerializationContext<String, String>()
//            .key(serializer)
//            .value(jackson2JsonRedisSerializer)
//            .hashKey(serializer)
//            .hashValue(jackson2JsonRedisSerializer)
//            .build()
//        return ReactiveRedisTemplate<Any?, Any?>(connectionFactory!!, serializationContext)

        val keySerializer = StringRedisSerializer()
        val redisSerializer = Jackson2JsonRedisSerializer(Content::class.java)
            .apply {
                setObjectMapper(
                    jacksonObjectMapper()
                        .registerModule(JavaTimeModule())
                )
            }
        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, Content>()
            .key(keySerializer)
            .hashKey(keySerializer)
            .value(redisSerializer)
            .hashValue(redisSerializer)
            .build()
        return ReactiveRedisTemplate(connectionFactory!!, serializationContext)
    }
}
