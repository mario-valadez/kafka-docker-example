import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    jacoco
    application
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20" apply true
    id("org.unbroken-dome.test-sets") version "4.0.0"  apply true
    id("org.springframework.boot") version "3.0.6" apply true
    id("io.spring.dependency-management") version "1.1.0" apply true
    id("com.google.cloud.tools.jib").version("3.2.1") apply true
}

allprojects {
    group = "com.example"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://packages.confluent.io/maven/")
    maven(url = "https://jitpack.io")
}

testSets {
    create("integrationTest")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.projectreactor.kafka:reactor-kafka")
    implementation("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:3.2.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.openapitools:jackson-databind-nullable:0.2.4")

    implementation("io.confluent:kafka-schema-registry-client:7.3.3")
    implementation("io.confluent:kafka-avro-serializer:7.3.3")
    implementation("org.postgresql:r2dbc-postgresql")

    runtimeOnly("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.springframework.boot:spring-boot-starter-jdbc")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    compileOnly("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
}

jacoco {
    toolVersion = "0.8.9"
}

sourceSets {
    main {
        resources.setSrcDirs(listOf("src/main/resources"))
    }
}

tasks.test {
    useJUnitPlatform()
}

jib {
    container {
        mainClass = "com.example.KafkaExampleApplication"
        ports = listOf("8091")
        jvmFlags = listOf("-javaagent:/dd-java-agent.jar")
    }
}

application {
    mainClass.set("com.example.KafkaExampleApplication")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}