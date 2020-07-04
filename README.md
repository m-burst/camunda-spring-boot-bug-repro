# Repro for [CAM-12114]

## What happens here

The repository contains a minimal Spring Boot project with Camunda BPM,
where the data source for Camunda is separate from the primary data source.
Both data sources are wrapped to log a message every time when a connection is obtained.

With the default data source auto-configuration in Camunda Spring Boot starter,
Camunda opens unneeded connections to the application data source.
It can be seen in the logs: the message `Getting connection from HikariPool-Application`
is logged even though no one uses the primary data source.

This happens because Camunda's `DefaultDatasourceConfiguration` uses the default
`PlatformTransactionManager` bean (which by default is `DataSourceTransactionManager` 
using the primary data source) even if the data sources are different.

A dirty fix is available in [`FixedCamundaDataSourceConfiguration`]:
the transaction manager is replaced by one with correct data source.
If the `@Component` line in the file is uncommented, there are no more log messages
indicating connection to the application data source.

## How to run

The `start.sh` script will start two databases using docker-compose, and then
compile and run the application with Gradle.

When the application starts, it exposes an endpoint at `http://localhost:8080/test`
which executes a simple query using Camunda's `RuntimeService`; this endpoint can be
used to observe the incorrect behaviour.

[CAM-12114]: https://jira.camunda.com/browse/CAM-12114
[`FixedCamundaDataSourceConfiguration`]: /src/main/kotlin/com/github/m_burst/example/FixedCamundaDataSourceConfiguration.kt
