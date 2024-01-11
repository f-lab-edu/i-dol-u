package com.flab.idolu.global.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	@Value("${spring.data.redis.redisson.host}")
	private String redisHost;

	@Value("${spring.data.redis.redisson.port}")
	private int redisPort;

	@Value("${spring.data.redis.redisson.password}")
	private String redisPassword;

	private static final String REDISSON_HOST_PREFIX = "redis://";

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer()
			.setAddress(REDISSON_HOST_PREFIX + redisHost + ":" + redisPort)
			.setPassword(redisPassword);

		return Redisson.create(config);
	}
}
