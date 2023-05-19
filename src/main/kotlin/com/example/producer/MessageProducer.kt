package com.example.producer

import com.example.config.KafkaConfiguration
import com.example.config.asyncSend
import org.apache.kafka.clients.producer.*
import org.springframework.stereotype.Service

@Service
class MessageProducer(
  kafkaConfiguration: KafkaConfiguration
) {
  companion object {
    const val TOPIC = "create-message-v1.0"
  }

  val producer = KafkaProducer<String, String>(kafkaConfiguration.getProperties())

  suspend fun sendApplyPolicyChangesEvent(message: String) {
    producer.asyncSend(
      ProducerRecord(
        TOPIC, message
      )
    )
  }
}

