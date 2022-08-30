package no.system.filebox.config

import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisStringCommands
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.RedisSentinelConfiguration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfiguration(@Value("\${spring.redis.sentinel.master}") private val master: String,
                         @Value("\${spring.redis.sentinel.nodes}") private val sentinelList: List<String>,
                         @Value("\${spring.redis.sentinel.password}") private val sentinelPassword: String){

    //connect to sentinel redis Cache
//    @Bean
//    fun lettuceConnectionFactorySentinel(): LettuceConnectionFactory{
//        val sentinelConfig = RedisSentinelConfiguration().master(master)
//        sentinelList.map{ sentinelConfig.sentinel(it.split(":")[0],it.split(":")[1].toInt()) }
////        sentinelConfig.setSentinelPassword(sentinelPassword)
//
//        return LettuceConnectionFactory(sentinelConfig, LettuceClientConfiguration.defaultConfiguration())
//    }

//    connect to local redis Cache
    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory{
        return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost",6379))
    }

    @Bean
    fun redisTemplateUser(): RedisTemplate<String, String>?{
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(lettuceConnectionFactory())
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = StringRedisSerializer()
        return template
    }
}