package com.example.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryOptionsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

@Configuration
class DatabaseConfiguration() : AbstractR2dbcConfiguration() {
  @Bean
  fun connectionFactoryOptionsBuilderCustomizer() = ConnectionFactoryOptionsBuilderCustomizer {
  }

  override fun connectionFactory(): ConnectionFactory =
    throw IllegalStateException("Connection factory bean must be provided by auto configuration")

}