package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.openapitools.jackson.nullable.JsonNullableModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter

@SpringBootApplication
@ConfigurationPropertiesScan
class KafkaExampleApplication {
  companion object {
    @Bean
    fun byteArrayJsonMessageConverter(objectMapper: ObjectMapper) = ByteArrayJsonMessageConverter(objectMapper)

    @Bean
    fun jsonNullableModule() = JsonNullableModule()

    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<KafkaExampleApplication>(*args)
    }
  }
}

