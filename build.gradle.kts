import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.3.1.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-web:2.3.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.3.1.RELEASE")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.13.0")
    implementation("org.postgresql:postgresql:42.2.14")
}

application {
    mainClassName = "com.github.m_burst.example.ApplicationKt"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
