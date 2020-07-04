package com.github.m_burst.example

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    @Value("\${spring.datasource.url}")
    private val applicationDataSourceConnectionString: String,

    @Value("\${camunda.bpm.datasource.url}")
    private val camundaDataSourceConnectionString: String
) {

    @Bean
    @Primary
    fun applicationDataSource(properties: DataSourceProperties): DataSource {
        val hikariDataSource = HikariDataSource().apply {
            jdbcUrl = applicationDataSourceConnectionString
            poolName = "HikariPool-Application"
        }
        return LoggingDataSourceWrapper(hikariDataSource)
    }

    @Bean
    @Qualifier("camundaBpmDataSource")
    fun camundaBpmDataSource(): DataSource {
        val hikariDataSource = HikariDataSource().apply {
            jdbcUrl = camundaDataSourceConnectionString
            poolName = "HikariPool-Camunda"
        }
        return LoggingDataSourceWrapper(hikariDataSource)
    }
}
