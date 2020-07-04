package com.github.m_burst.example

import org.camunda.bpm.spring.boot.starter.configuration.impl.DefaultDatasourceConfiguration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

// Uncomment the below line to fix the problem
// @Component
class FixedCamundaDataSourceConfiguration : DefaultDatasourceConfiguration() {

    @PostConstruct
    private fun fixTransactionManager() {
        val camundaDataSource = this.camundaDataSource
        if (camundaDataSource != null) {
            transactionManager = DataSourceTransactionManager(camundaDataSource)
        }
    }
}
