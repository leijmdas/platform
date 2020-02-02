package com.kunlong.platform.config.redis;


import cn.kunlong.center.api.model.SysUserDTO;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.kunlong.platform.model.LoginSso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
//
//	@Bean
//	@ConfigurationProperties(prefix="spring.redis")
//	public JedisPoolConfig jedisPoolConfig() {
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		return jedisPoolConfig;
//	}
//
//    @SuppressWarnings("deprecation")
//	@Bean
//    @ConfigurationProperties(prefix="spring.redis")
//    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
//        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
//        factory.setUsePool(true);
//        factory.setPoolConfig(jedisPoolConfig());
//
//        return factory;
//    }
//	/**
//	 * 设置数据存入 redis 的序列化方式,并开启事务
//	 *
//	 * @param redisTemplate
//	 * @param factory
//	 */
//	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
//		// 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to
//		// String！
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
////		 开启事务
////		redisTemplate.setEnableTransactionSupport(true);
//		redisTemplate.setConnectionFactory(factory);
//	}
//
//	/**
//	 * 实例化 RedisTemplate 对象
//	 *
//	 * @return
//	 */
////	@Bean(name = "redisTemplate")
////	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
////		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
////		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
////		return redisTemplate;
////	}
//
//
	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate sringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.setDefaultSerializer(stringSerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

    @Bean("loginSsoRedisTemplate")
    public RedisTemplate<String, LoginSso> loginSsoRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        RedisTemplate<String, LoginSso> redisTemplate = new RedisTemplate<String, LoginSso>();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<LoginSso>(LoginSso.class));
        redisTemplate.setHashKeySerializer(stringSerializer);
         redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<SysUserDTO>(SysUserDTO.class));

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }



    @Bean("sysUserRedisTemplate")
    public RedisTemplate<String, SysUserDTO> sysUserRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        RedisTemplate<String, SysUserDTO> redisTemplate = new RedisTemplate<String, SysUserDTO>();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<SysUserDTO>(SysUserDTO.class));
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<SysUserDTO>(SysUserDTO.class));

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
