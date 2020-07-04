package com.github.m_burst.example

import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import java.sql.Connection
import javax.sql.DataSource

class LoggingDataSourceWrapper(
    private val delegate: HikariDataSource
) : DataSource by delegate {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun getConnection(): Connection {
        log.info("Getting connection from ${delegate.poolName}")
        return delegate.getConnection()
    }
}
