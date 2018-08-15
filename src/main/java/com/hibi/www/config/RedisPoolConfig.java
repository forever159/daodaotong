package com.hibi.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisPoolConfig {
    @Autowired
    CacheConfig cacheConfig;
    @Bean
    public JedisPool configJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(cacheConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(cacheConfig.getPoolMaxTotal());
        //需要注意的是时间的单位都是毫秒，我们的配置中都是秒，所以要*1000
        poolConfig.setMaxWaitMillis(cacheConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, cacheConfig.getHost(), cacheConfig.getPort(),
                cacheConfig.getTimeout()*1000, cacheConfig.getPassword(), 0);
        return jp;
    }

}
