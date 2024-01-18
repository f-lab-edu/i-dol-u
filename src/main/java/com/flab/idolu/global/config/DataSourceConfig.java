package com.flab.idolu.global.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
@Profile({"prod1", "prod2"})
public class DataSourceConfig {

	private static final String SOURCE = "source";
	private static final String REPLICA = "replica";

	@Bean
	@Qualifier(SOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.source")
	public DataSource sourceDataSource() {
		return DataSourceBuilder.create()
			.build();
	}

	@Bean
	@Qualifier(REPLICA)
	@ConfigurationProperties(prefix = "spring.datasource.replica")
	public DataSource replicaDataSource() {
		return DataSourceBuilder.create()
			.build();
	}

	@Bean
	public RoutingDataSource routingDataSource(
		@Qualifier(SOURCE) DataSource sourceDataSource,
		@Qualifier(REPLICA) DataSource replicaDataSource) {

		HashMap<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(SOURCE, sourceDataSource);
		dataSourceMap.put(REPLICA, replicaDataSource);

		RoutingDataSource routingDataSource = new RoutingDataSource();
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(sourceDataSource);
		return routingDataSource;
	}

	@Bean
	@Primary
	public DataSource dataSource(RoutingDataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}
}
