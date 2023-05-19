package com.example.consumer

import com.example.service.MessageService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Service
import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.consumer.ConsumerRecord

@Service
class ApplyPolicyChangesListener(
  private val messageEntityService: MessageService,
) {
  @KafkaListener(topics = ["create-message-v1.0"])
  fun consumer(message: ConsumerRecord<String, String>) {
    runBlocking {
      messageEntityService.createMessage(message.value())
    }
  }
}