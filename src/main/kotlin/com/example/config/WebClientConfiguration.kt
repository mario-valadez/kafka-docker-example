package com.clearcover.signature.config

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfiguration {
  @Bean
  fun webClientCustomizer() = WebClientCustomizer { builder ->
    val httpClient = HttpClient.create()
      .responseTimeout(Duration.ofMillis(10_000))

    builder.clientConnector(ReactorClientHttpConnector(httpClient))
  }
}