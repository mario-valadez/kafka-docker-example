package com.example.service

import com.example.repository.MessageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MessageService(
  private val messageRepository: MessageRepository,
) {
  @Transactional
  suspend fun createMessage(message: String) {
    messageRepository.create(message)
  }

  @Transactional
  suspend fun getMessages() = messageRepository.getList()
}