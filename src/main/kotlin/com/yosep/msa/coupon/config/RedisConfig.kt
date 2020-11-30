package com.yosep.msa.coupon.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
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
class RedisConfig(
    @Value("\${spring.redis.host}")
    private val redisHost: String? = "127.0.0.1",

    @Value("\${spring.redis.port}")
    private val redisPort: Int = 6379
) {

    @Primary
    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(redisHost!!, redisPort)
    }

//    @Bean
//    fun redisTemplate(): RedisTemplate<*, *> {
//        val redisTemplate = RedisTemplate<ByteArray, ByteArray>()
//        redisTemplate.setConnectionFactory(redisConnectionFactory())
//        return redisTemplate
//    }

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

//    @Bean
//    fun template(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
//        var redisTemplate = ReactiveRedisTemplate(factory, RedisSerializationContext.string())
//
//        return ReactiveRedisTemplate(factory, RedisSerializationContext.string())
//    }

//    @Bean(value = ["reactiveStringRedisTemplate"])
//    fun reactiveStringRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory?): ReactiveRedisTemplate<String?, String?> {
//        val serializer: RedisSerializer<String> = StringRedisSerializer()
//        val jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<String> = Jackson2JsonRedisSerializer(
//            String::class.java
//        )
//        val serializationContext: RedisSerializationContext<String, String> = RedisSerializationContext
//            .newSerializationContext<String, String>()
//            .key(serializer)
//            .value(jackson2JsonRedisSerializer)
//            .hashKey(serializer)
//            .hashValue(jackson2JsonRedisSerializer)
//            .build()
//
//        return ReactiveRedisTemplate<String, String>(connectionFactory!!, serializationContext) as ReactiveRedisTemplate<String?, String?>
//    }

    //    @Primary
//    @Bean(value = ["reactiveLongRedisTemplate"])
    @Bean
    fun reactiveLongRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory?): ReactiveRedisTemplate<String, Long>? {

        val serializer: RedisSerializer<String> = StringRedisSerializer()
        val jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<Long> = Jackson2JsonRedisSerializer(
            Long::class.java
        )
        val serializationContext: RedisSerializationContext<String, Long> = RedisSerializationContext
            .newSerializationContext<String, Long>()
            .key(serializer)
            .value(jackson2JsonRedisSerializer)
            .hashKey(serializer)
            .hashValue(jackson2JsonRedisSerializer)
            .build()

        var reactiveRedisTemplate: ReactiveRedisTemplate<String, Long>? =
            ReactiveRedisTemplate<String, Long>(connectionFactory!!, serializationContext)

        return reactiveRedisTemplate
    }

}
