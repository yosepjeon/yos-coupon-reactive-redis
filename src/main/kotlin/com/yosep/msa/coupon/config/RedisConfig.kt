package com.yosep.msa.coupon.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.ResourceProperties
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


@Configuration
@EnableRedisRepositories
class RedisConfig(
    @Value("\${spring.redis.host}")
    private val redisHost: String? = "127.0.0.1",

    @Value("\${spring.redis.port}")
    private val redisPort: String = "6379"
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost!!, 6379)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<ByteArray, ByteArray>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }

//    @Bean
//    fun reactiveRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory?): ReactiveRedisTemplate<String, ResourceProperties.Content> {
//        val keySerializer = StringRedisSerializer()
//        val redisSerializer = Jackson2JsonRedisSerializer(ResourceProperties.Content::class.java)
//            .apply {
//                setObjectMapper(
//                    jacksonObjectMapper()
//                        .registerModule(JavaTimeModule())
//                )
//            }
//
//        val serializationContext = RedisSerializationContext
//            .newSerializationContext<String, ResourceProperties.Content>()
//            .key(keySerializer)
//            .hashKey(keySerializer)
//            .value(redisSerializer)
//            .hashValue(redisSerializer)
//            .build()
//
//        return ReactiveRedisTemplate(connectionFactory!!, serializationContext)
//    }

    @Bean
    fun template(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        var redisTemplate = ReactiveRedisTemplate(factory, RedisSerializationContext.string())
        redisTemplate.serializationContext

        return ReactiveRedisTemplate(factory, RedisSerializationContext.string())
    }


}
